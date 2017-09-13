/*
 * @author shuoshuofan
 * @date   20170911
 * function：secondary sort
 */

package com.unionpay.code.core;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class KeyPair implements WritableComparable<KeyPair> {
	/**
	 * 第一排序字段
	 */
	String first;

	/**
	 * 第二排序字段
	 */
	String second;

	/**
	 * Set the first,second values.
	 */
	public void set(String first, String second) {
		this.first = first;
		this.second = second;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public void setSecond(String second) {
		this.second = second;
	}
	public String getFirst() {
		return first;
	}

	public String getSecond() {
		return second;
	}



	/**
	 * 反序列化，从流中的二进制转换成KeyPair
	 */
	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		first = in.readUTF();
		second = in.readUTF();
	}

	/**
	 * 序列化，将KeyPair转化成使用流传送的二进制
	 */
	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeUTF(first);
		out.writeUTF(second);
	}

	/**
	 * key的比较
	 */
	@Override
	public int compareTo(KeyPair o) {
		if (!first.equals(o.first)) {
			return first.compareTo(o.first);
		} else  {
			return second.compareTo(o.second);
		}

	}

	// 新定义类应该重写的两个方法
	@Override
	// The hashCode() method is used by the HashPartitioner (the default
	// partitioner in MapReduce)
	public int hashCode() {
		return first.hashCode() + second.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (this == o)
			return true;
		if (o instanceof KeyPair) {
			KeyPair kp = (KeyPair) o;
			return kp.first.equals(first) && kp.second.equals(second);
		} else {
			return false;
		}
	}


}
