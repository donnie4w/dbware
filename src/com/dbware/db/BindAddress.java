package com.dbware.db;

import com.dbware.db.cfg.DBwareConfigXml;
import com.dbware.util.StringUtil;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-18
 * @verion 1.0
 */
public class BindAddress {
	private static boolean isBindAddress = false;
	private static final String DEFAUTL_ADDRESS = "0.0.0.0";
	private static String bindPart = "";
	static {
		isBindAddress = isBind(DBwareConfigXml.getBindAddress());
		if (isBindAddress) {
			bindPart = setBindPart();
		}
	}

	public static String wrap(String s) {
		if (!isBindAddress || s.startsWith(bindPart)) {
			return s;
		} else {
			return null;
		}
	}

	private static boolean isBind(String s) {
		if (StringUtil.isEmpty(s) || DEFAUTL_ADDRESS.equals(s.trim())) {
			return false;
		} else {
			return true;
		}
	}

	public static String setBindPart() {
		String[] s = DBwareConfigXml.getBindAddress().split("/");
		if (s.length == 1) {
			return "/"+s[0];
		}
		String[] ss = s[0].split("\\.");
		switch (Integer.parseInt(s[1])) {
		case 8:
			return "/"+ss[0];
		case 16:
			return "/"+ss[0] + "\\." + ss[1];
		case 24:
			return "/"+ss[0] + "\\." + ss[1] + "\\." + ss[2];
		default:
			return "/"+s[0];
		}
	}
}