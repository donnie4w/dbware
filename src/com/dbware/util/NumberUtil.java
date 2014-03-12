package com.dbware.util;

import java.util.Arrays;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-06
 * @verion 1.0
 */
public class NumberUtil {

	/**
	 * author donnie wu
	 * 
	 * @param nums
	 * @return
	 */
	public static int gcd_(int m, int n) {
		if (n > m) {
			int temp = n;
			n = m;
			m = temp;
		}
		while (m % n != 0) {
			int temp = m % n;
			m = n;
			n = temp;
		}
		return n;
	}

	/**
	 * author donnie wu
	 * 
	 * @param nums
	 * @return
	 */
	public static int gcd(int... nums) {
		int length = nums.length;
		if (length == 1)
			return nums[0];
		if (length == 2) {
			return gcd_(nums[0], nums[1]);
		}
		Arrays.sort(nums);

		int min = nums[0];
		int gcd = 1;
		for (int i = 2; i <= min; i++) {
			boolean isGcd = true;
			for (int num : nums) {
				if (num % i != 0) {
					isGcd = false;
				}
			}
			if (isGcd)
				gcd = i;
		}
		return gcd;
	}
}
