/*
 * @author    shuoshuofan
 * 分组函数类。只要first相同就属于同一个组。
 */

package com.unionpay.code.core;


import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupingComparator extends WritableComparator {
	protected GroupingComparator() {
		super(KeyPair.class, true);
	}

	// Compare two WritableComparables.
	@SuppressWarnings("rawtypes")
	public int compare(WritableComparable w1, WritableComparable w2) {
		KeyPair kp1 = (KeyPair) w1;
		KeyPair kp2 = (KeyPair) w2;
		String kp1First = kp1.getFirst();
		String kp2First = kp2.getFirst();
		return kp1First.compareTo(kp2First);
	}
}