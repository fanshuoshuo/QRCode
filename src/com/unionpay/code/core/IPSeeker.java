/**
 * @author shuoshuofan
 *
 */
package com.unionpay.code.core;
//java
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;
//hadoop
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class IPSeeker {

 
	private TreeMap<Long,String> ipMap=new TreeMap<Long,String>();
	
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(IPSeeker.class);
	
    /**
     * 读hdfs文件
     * @param filePath
     * @param conf
     */
	public IPSeeker(String dataPath,Configuration conf){
	
		FileSystem fs;
		try {
			fs = FileSystem.get(conf);
			FSDataInputStream inputStream = fs.open(new Path(dataPath));
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
			String str=null;
			String[] tempArray = null;
			int count=0;

			while((str = br.readLine()) != null){
				count++;
				//去标题

				if(count==1)
					continue;
				
				tempArray = str.split(",");
				ipMap.put(Long.parseLong(tempArray[0]),tempArray[1].trim()+","+tempArray[2].trim());
				
				
			}
			br.close();
			inputStream.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 利用DistributedCache读文件
	 * @param filePath
	 */
	public IPSeeker(String dataPath){
		try {
			InputStreamReader inputStream = new InputStreamReader(new FileInputStream(dataPath),"UTF-8");
			BufferedReader br = new BufferedReader(inputStream);
			String str = null;
			String[] tempArray = null;
			int count=0;

			while((str = br.readLine()) != null){
				count++;
				//去标题

				if(count==1)
					continue;
				
				tempArray = str.split(",");
			    ipMap.put(Long.parseLong(tempArray[0]), tempArray[1].trim()+","+tempArray[2].trim());
				
				
			}
			br.close();
			inputStream.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getRegionInfo(String ip){

		String result="0156,0156";
		String[] tokens=ip.replaceAll("[^0-9\\.]", "").split("\\.");
		long keyValue=0;
		if(tokens.length==4){
			keyValue+=Long.valueOf(tokens[3]);
			keyValue+=Long.valueOf(tokens[2])<<8;
			keyValue+=Long.valueOf(tokens[1])<<16;
			keyValue+=Long.valueOf(tokens[0])<<24;
			try{
				result=ipMap.floorEntry(keyValue).getValue();
			
			}catch (Exception e){
				return "0156,0156";
			}
		}
		return result==null?"0156,0156":result;
		

	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IPSeeker seeker=new IPSeeker("D:\\ipDataV2.txt");
		System.out.println(seeker.getRegionInfo("117.136.040.145"));
		System.out.println(seeker.getRegionInfo("223.247.24.0"));

	}

}
