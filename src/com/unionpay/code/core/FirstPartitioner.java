/*
 * @author   shuoshuofan
 * function ：for secondary sort
 */
//
package com.unionpay.code.core;
//java
import java.util.Random;
//hadoop
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 分区函数类。根据first确定Partition。
 */
public class FirstPartitioner extends Partitioner<KeyPair, Text> {
	private static Random rand = new Random();

	@Override
	public int getPartition(KeyPair key, Text value, int numPartitions) {
		if (key.getFirst().equals("")||key.getFirst().equals(Constants.NULL_STRING_FLAG))
			return rand.nextInt(10000) % numPartitions;
		else
			return Math.abs(key.getFirst().hashCode()) % numPartitions;
	}
}