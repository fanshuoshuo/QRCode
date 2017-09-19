
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
import com.unionpay.code.entity.MapItem;
import com.unionpay.code.mapreduce.DataProcessStep1;
import com.unionpay.code.mapreduce.DataProcessStep1.DataProcessMapper;
import com.unionpay.code.mapreduce.DataProcessStep1.DataProcessReducer;
import com.unionpay.code.core.CorrectStep1Tokens;
import com.unionpay.code.core.FirstPartitioner;
import com.unionpay.code.core.GroupingComparator;
import com.unionpay.code.core.Constants;
import com.unionpay.code.column.MapColumn;
import com.unionpay.utils.StringUtils;
import com.unionpay.utils.TimeUtils;
/*

import com.unionpay.nfc.core.Constants;
*/
public class DataProcessStep1 {
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	private static Logger logger = LoggerFactory.getLogger(DataProcessStep1.class);
	//
	public static class DataProcessMapper extends Mapper<LongWritable, Text, KeyPair, Text>{
	
		private KeyPair key = new KeyPair();
		private Text mapOutput = new Text();
		private MapRuleManager mapRuleManager = null;
		
		public void setup(Context context) {	
			
			Configuration conf = context.getConfiguration();
	
			mapRuleManager = new MapRuleManager();
			
			logger.info("map setup finished");
		}
		public void map(LongWritable rowKey, Text value, Context context) throws IOException, InterruptedException{
			
			//logger.info("row is :  "+ value.toString());
			//logger.info("row is :  "+ value);
			
			String[] tokens = StringUtils.directSplit(value.toString());
			MapItem tempRecord = new MapItem(tokens);
			
			key.set(tempRecord.getAr_pri_acct_no(), tempRecord.getTfr_dt_dm());
			mapOutput.set(tempRecord.toString());
			
			context.write(key, mapOutput);
		
		}
			
	}
	//Reducer
	public static class DataProcessReducer extends Reducer<KeyPair, Text, Text, Text> {
		
		 private Text reduceOutput = null;
		//private StringBuilder strb = new StringBuilder();
		 
		 protected void setup(Context context) {
				reduceOutput = new Text();
		 }
		 public void reduce(KeyPair key, Iterable<Text> lines, Context context)throws IOException, InterruptedException {
			 
			 //educeRuleManager ruleManger = new ReduceRuleManager();
			 
			 for (Text line : lines) {
				// strb.delete(0, sb.length());
				// String[] tokens = line.toString().split(",");
				//reduceOutput.set(line.toString());
				 context.write(null, line);
			 }
			 
		 }
		 protected void cleanup(Context context) throws IOException, InterruptedException {	
		 
		 }
	}
	@SuppressWarnings({ "deprecation" })
	
	public void execute(Configuration conf, String input_path, String output_path) {
		try {
			Job job = new Job(conf, this.getClass().getName());

			job.setJarByClass(DataProcessStep1.class);
			job.setPartitionerClass(FirstPartitioner.class);
			job.setGroupingComparatorClass(GroupingComparator.class);

			job.setMapperClass(DataProcessMapper.class);
			job.setReducerClass(DataProcessReducer.class); // reducer class
			job.setNumReduceTasks(Constants.NUM_REDUCE_TASKS); // 无Reduce

			job.setInputFormatClass(TextInputFormat.class);
			job.setOutputFormatClass(TextOutputFormat.class);
			
			job.setMapOutputKeyClass(KeyPair.class);
			
			String inputPath[] = input_path.split(",");
			

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
			FileOutputFormat.setOutputPath(job, new Path(output_path + "_step1"));
			
			job.waitForCompletion(true);
			
		} catch (Exception e) {
			logger.error("Exception", e);

		}

	}
	
	public static void main(String args[]) throws ParseException, IOException {
		
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
			logger.error("Please input  upmp.path !");
			return;
		} else {		
			logger.info("upmp.path:\t" + upmpPath);		
		}
		
		// 2.输入输出参数校验
		if (actualArgs.length != 4) {
			logger.info("args numbers must equal 4");
			logger.info("usage:\n hadoop jar com.uninonpay.neuralnetwork.mapreduce.DataProcess \n"
					//hadoop jar xx.jar
					+ "-Dupmp.path=/user/hddtDmn/in_arsvc_upmp_his_trans_log/ \n"
					+ "beginDate  endDate   destPath  upmp");
			return;
		}
		Integer len=actualArgs.length;
		logger.info("actualArgs.length "+ len.toString());
	
		// 3.输入输出参数赋值
		Date beginDate = dateFormat.parse(actualArgs[0]);
		Date endDate =  dateFormat.parse(actualArgs[1]);
		
		String destPath = actualArgs[2];
		String tableName = actualArgs[3];
		
		logger.info("table name:\t" + tableName);
		logger.info("begin date:\t" + actualArgs[0]);
		logger.info("end date:\t" + actualArgs[1]);
		logger.info("output path:\t" + destPath);
		
		
		StringBuffer inputUpmpPath = new StringBuffer();
	
		Date tempBeginDate = beginDate;
		
		if (tableName.equals("upmp")) {
			while (!tempBeginDate.after(endDate)) {
				inputUpmpPath.append(upmpPath + dateFormat.format(tempBeginDate) + "_correct,");
				tempBeginDate = TimeUtils.addDate(tempBeginDate, 1);
			}
		}

	
		//excute
		DataProcessStep1 job = new DataProcessStep1();
		job.execute(conf, inputUpmpPath.toString(), destPath);

		System.exit(1);

	}

}
