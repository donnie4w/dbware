package com.dbware.mysql.buffer;

import com.dbware.util.StringUtil;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-7
 * @verion 1.0 MySql basic types util
 */
public class BasicTypesUtils {

	public static int getLengthEncodedStringLength(String str) {
		return str == null ? 1 : StringUtil.getBytes(str).length + getLengthEncodedIntegerLength(StringUtil.getBytes(str).length);
	}

	public static int getLengthEncodedIntegerLength(int value) {
		if (value < 251) {
			return 1;
		}
		if (value <= 0xffff) {
			return 3;
		}
		if (value <= 0xffffff) {
			return 4;
		}
		return 9;
	}
}