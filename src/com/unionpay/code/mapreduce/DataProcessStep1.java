
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
import com.unionpay.utils.StringUtils;
/*
import com.unionpay.nfc.column.MapColumn;
import com.unionpay.nfc.core.Constants;
import com.unionpay.nfc.core.FirstPartitioner;
import com.unionpay.nfc.core.GroupingComparator;
import com.unionpay.utils.TimeUtils;
*/
public class DataProcessStep1 {
    
	private static Logger logger = LoggerFactory.getLogger(DataProcessStep1.class);
	//Mapper
	public static class DataProcessMapper extends Mapper<LongWritable, Text, KeyPair, Text>{
		
		//ip var
		private static String ipPath = null;
		private static IPSeeker ipSeeker = null;		
		//map var 
		private KeyPair key = new KeyPair();
		private Text mapOutput = new Text();
		private MapRuleManager mapRuleManager = null;
		//
		public void setup(Context context) {
			Configuration conf = context.getConfiguration();

			ipPath = conf.get("ip.path");
			ipSeeker = new IPSeeker(ipPath, conf);
			mapRuleManager = new MapRuleManager(ipSeeker);
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
				 String[] tokens = line.toString().split(",");
				 ruleManger.setCurrentTrans(tokens);
				 
				 strb.append();
			 }
			 
		 }
		 protected void cleanup(Context context) throws IOException, InterruptedException {

			}
		 
		
	}
	public void execute(Configuration conf, String srcPath, String cupsPath, String destPath) {
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
