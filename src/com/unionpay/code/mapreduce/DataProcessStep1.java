
/*
 * @author shuoshuofan
 * @date   20170911
 * function  somethin here 
 */

package com.unionpay.code.mapreduce;
//import java
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
//import hadoop 
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import myclass 
import com.unionpay.code.core.KeyPair;
import com.unionpay.code.core.IPSeeker;
import com.unionpay.code.core.MapRuleManager;
import com.unionpay.code.core.ReduceRuleManager;

import com.unionpay.code.core.FirstPartitioner;
import com.unionpay.code.core.GroupingComparator;
import com.unionpay.code.core.Constants;

import com.unionpay.utils.StringUtils;
/*
import com.unionpay.nfc.column.MapColumn;
import com.unionpay.nfc.core.Constants;
import com.unionpay.utils.TimeUtils;
*/
public class DataProcessStep1 {
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	private static Logger logger = LoggerFactory.getLogger(DataProcessStep1.class);
	//Mapper
	public static class DataProcessMapper extends Mapper<LongWritable, Text, KeyPair, Text>{
		
		private KeyPair key = new KeyPair();
		private Text mapOutput = new Text();
		
		private MapRuleManager mapRuleManager = null;
		//
		public void setup(Context context) {
			Configuration conf = context.getConfiguration();		
			logger.info("map setup finished");
		}
		public void map(LongWritable rowKey, Text value, Context context) throws IOException, InterruptedException{
			
			String[] tokens = StringUtils.directSplit(value.toString());
			
			boolean flag = mapRuleManager.setTransStr(tokens);
			if (!flag)
				return;
			//set key and value 
			key.set(mapRuleManager.getArPriAcctNo(), mapRuleManager.getTransDtTm());
			mapOutput.set(mapRuleManager.toString());
			//output key and value
			context.write(key, mapOutput);
			
		}
			
	}
	//Reducer
	public static class DataProcessReducer extends Reducer<KeyPair, Text, Text, Text> {
		
		 private Text reduceOutput = null;
		 private StringBuilder strb = new StringBuilder();
		 
		 protected void setup(Context context) {
				reduceOutput = new Text();
		 }
		 public void reduce(KeyPair key, Iterable<Text> lines, Context context)throws IOException, InterruptedException {
			 
			 ReduceRuleManager ruleManger = new ReduceRuleManager();
			 
			 for (Text line : lines) {
				// String[] tokens = line.toString().split(",");
				 reduceOutput.set(line.toString());
				 context.write(null, reduceOutput);
			 }
			 
		 }
		 protected void cleanup(Context context) throws IOException, InterruptedException {
			
		 
		 }
		 
		
	}
	@SuppressWarnings({ "deprecation" })
	public void execute(Configuration conf,String upmpPath, String destPath) {
		 boolean b = false;
			
		try {
			Job job = new Job(conf, this.getClass().getName());
			
			job.setJarByClass(DataProcessStep1.class);
			 
			job.setPartitionerClass(FirstPartitioner.class);
			job.setGroupingComparatorClass(GroupingComparator.class);
			
			job.setMapperClass(DataProcessMapper.class);
			job.setReducerClass(DataProcessReducer.class); // reducer class
			
			//Question
			job.setNumReduceTasks(Constants.NUM_REDUCE_TASKS); // 无Reduce
			
			job.setInputFormatClass(TextInputFormat.class);
			job.setOutputFormatClass(TextOutputFormat.class);
			job.setMapOutputKeyClass(KeyPair.class);
			
			String inputPath[] = upmpPath.split(",");

			FileSystem fs = FileSystem.get(conf);
			Path itemPath = null;
			for (String path : inputPath) {

				itemPath = new Path(path);
				if (fs.exists(itemPath)) {
					FileInputFormat.addInputPath(job, itemPath);
					logger.info("input path:\t" + path);
				} else {
					logger.info("input path:\t" + path + " does not exist!!!");
				}

			}
			fs.close();
			FileOutputFormat.setOutputPath(job, new Path(destPath + "_step1"));
			b = job.waitForCompletion(true);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Exception", e);
		}
		 
		
	}
	public static void main(String[] args)  throws ParseException, IOException{
		
		Configuration conf = new Configuration();
//		conf.set("mapred.min.split.size", "256000000");
		conf.set("mapred.job.reuse.jvm.num.tasks", "-1");

		conf.setBoolean("fs.hdfs.impl.disable.cache", true);
		conf.set("mapreduce.output.fileoutputformat.compress", "true");
		conf.set("mapreduce.output.fileoutputformat.compress.type", "BLOCK");
		conf.set("mapred.min.split.size", "10240000000");
		conf.set("mapreduce.output.fileoutputformat.compress.codec", "org.apache.hadoop.io.compress.GzipCodec");
		// 1.conf 参数校验
		String[] actualArgs = new GenericOptionsParser(conf, args).getRemainingArgs();

		String upmpPath = conf.get("upmp.path", null);

		if (upmpPath == null) {
			logger.error("Please input upmp.path !");
			return;
		} else {
			logger.info("upmp.path:\t" + upmpPath);

		}
		// 2.输入输出参数校验
		if (actualArgs.length != 1) {
			logger.info("args numbers must equal 4");
			logger.info("usage:\n hadoop jar com.uninonpay.neuralnetwork.mapreduce.DataProcess \n"
					+ "-Dupop.path=/user/hddtmn/in_arsvc_upop_his_trans_log/ \n"
					+ "-Dupmp.path=/user/hddtmn/in_arsvc_upmp_his_trans_log/ \n"
					+ "-Dcup.path=/user/dw_hbkas/hive/hbkdb/bsl/stdtrs_bsl_risk_rec/hp_settle_dt= \n"
					+ "beginDate  endDate   destPath  [upop|upmp|all]");
			return;
		}
		
		// 3.输入输出参数赋值

		String destPath = actualArgs[0];
		logger.info("output path:\t" + destPath);

		DataProcessStep1 job = new DataProcessStep1();
		job.execute(conf, upmpPath.toString(), destPath);

		System.exit(1);

	}

}
