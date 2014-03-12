package com.dbware.util;

import com.dbware.db.cfg.DBwareConfigXml;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-07
 * @verion 1.0 String util
 */
public class StringUtil {
	public static byte[] getBytes(String str) {
		try {
			return str != null ? str.getBytes(DBwareConfigXml.getCharacterSet()) : null;
		} catch (Exception e) {
			return null;
		}
	}

	public static String byte2String(byte[] bytes) {
		try {
			return new String(bytes, DBwareConfigXml.getCharacterSet());
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean isNotNull(String str) {
		return str == null ? false : true;
	}

	public static boolean isEmpty(String str) {
		return (str == null || "".equals(str.trim())) ? true : false;
	}
}
