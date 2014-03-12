package com.dbware.db;

import java.util.ArrayList;
import java.util.List;

import com.dbware.db.filter.SlaveFilter;
import com.dbware.util.StringUtil;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-17
 * @verion 1.0
 */
public class SqlUtil {
	public static Object[] getSqlType(String s) {
		if (StringUtil.isEmpty(s)) {
			return new Object[] { SqlEnum.ERROR, null };
		}
		s = s.trim();
		String commentLine = null;
		if (s.matches("/\\*.*?\\*/.*?")) {
			commentLine = s.trim().substring(2, s.indexOf("*/"));
		}
		boolean isMaster = false;
		List<String> cachekeyList = null;
		if (commentLine != null && commentLine.length() > 0) {
			cachekeyList = new ArrayList<String>();
			commentLine = commentLine.trim().toLowerCase();
			final String[] commentLines = commentLine.split(";");
			for (String str : commentLines) {
				str = str.trim();
				if ("master".equals(str)) {
					isMaster = true;
					continue;
				}
				if (str.startsWith("cache")) {
					final String value = str.split("=")[1].trim();
					if(value==null||"".equals(value.trim())){
						continue;
					}
					for (String v : value.split(",")) {
						cachekeyList.add(v.trim());
					}
				}
			}
		}

		if (cachekeyList != null && cachekeyList.size() == 0)
			cachekeyList = null;

		final String str = s.trim().replaceAll("/\\*.*?\\*/", "").toLowerCase();
		if (str.startsWith("select")) {
			if (SlaveFilter.isHitFilter(str) || isMaster) {
				return new Object[] { SqlEnum.FILTER, cachekeyList };
			}
			return new Object[] { SqlEnum.READ, cachekeyList };
		} else if (str.startsWith("show")) {
			return new Object[] { SqlEnum.SHOW };
		} else if (str.startsWith("set autocommit=0")) {
			return new Object[] { SqlEnum.TRANSACTIONSTART, cachekeyList };
		} else if (str.startsWith("rollback") || str.startsWith("commit")) {
			return new Object[] { SqlEnum.TRANSACTION, cachekeyList };
		} else if (str.startsWith("set autocommit=1")) {
			return new Object[] { SqlEnum.TRANSACTIONEND, cachekeyList };
		} else if (str.startsWith("delete") || str.startsWith("insert") || str.startsWith("replace") || str.startsWith("update")
				|| str.startsWith("set")) {
			return new Object[] { SqlEnum.WRITE, cachekeyList };
		} else {
			return new Object[] { SqlEnum.OTHER };
		}
	}
}
