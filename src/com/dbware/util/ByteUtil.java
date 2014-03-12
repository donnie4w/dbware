package com.dbware.util;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-07
 * @verion 1.0
 */
public class ByteUtil {
	public static String byte2hex(byte[] b) {
		StringBuilder sb = new StringBuilder();
		for (int n = 0; n < b.length; n++) {
			String s1 = Integer.toHexString(b[n] & 0x0f);
			String s2 = Integer.toHexString(b[n] >> 4 & 0x0f);
			sb.append(s2);
			sb.append(s1);
		}
		return sb.toString().toUpperCase();
	}
}
