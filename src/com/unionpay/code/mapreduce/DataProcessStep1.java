
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
/*

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
		
		public void setup(Context context) {
			Configuration conf = context.getConfiguration();		
			logger.info("map setup finished");
		}
		public void map(LongWritable rowKey, Text value, Context context) throws IOException, InterruptedException{
			
			String[] tokens =  value.toString().split(",");
			MapItem tempRecord = new MapItem(tokens);
				
			CorrectStep1Tokens cToken=new CorrectStep1Tokens(tempRecord);
			//Tfr_dt_dm check
			key.set(tempRecord.getAr_pri_acct_no(), tempRecord.getTfr_dt_dm());
			mapOutput.set(tempRecord.toString());
			
			context.write(key, mapOutput);
			
		}
			
	}
	//Reducer
	public static class DataProcessReducer extends Reducer<KeyPair, Text, Text, Text> {
		
		 private Text reduceOutput = null;
		// private StringBuilder strb = new StringBuilder();
		 
		 
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
	
	public void execute(Configuration conf, String inputPath, String outputPath) {
		

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
	
			FileInputFormat.addInputPath(job,new Path(inputPath));
				
			FileOutputFormat.setOutputPath(job,new Path(outputPath));
 
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
		
		String[] actualArgs = new GenericOptionsParser(conf, args).getRemainingArgs();

		// 2.输入输出参数校验
		if (actualArgs.length != 2) {
			logger.info("args numbers must equal 2");
		
			return;
		}
		DataProcessStep1 job = new DataProcessStep1();
		job.execute(conf, actualArgs[0], actualArgs[1]);

		System.exit(1);

	}

}
