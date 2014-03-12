package com.dbware.db.filter;

import com.dbware.db.cfg.DBFilterConfigXml;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-18
 * @verion 1.0
 */
public class SlaveFilter implements Filter {
	private static boolean isSlaveFilterOn = false;
	static {
		if (DBFilterConfigXml.sqlFilterMap.size() == 0 && DBFilterConfigXml.tableFilterMap.size() == 0) {
			isSlaveFilterOn = false;
		} else {
			isSlaveFilterOn = true;
		}
	}

	public static boolean isHitFilter(String s) {
		if (s != null && isSlaveFilterOn) {
			s = s.replaceAll("(\\s){2,}|(\t)+", " ").trim().replaceAll("(\\s*,\\s*)+", ",").toLowerCase();
			if (DBFilterConfigXml.tableFilterMap.size() > 0)
				for (String table : DBFilterConfigXml.tableFilterMap.keySet()) {
					if (s.indexOf(table) > -1)
						return true;
				}
			if (DBFilterConfigXml.sqlFilterMap.size() > 0)
				for (String sql : DBFilterConfigXml.sqlFilterMap.keySet()) {
					if (s.indexOf(sql) > -1) {
						if (!sql.equals(DBFilterConfigXml.sqlFilterMap.get(sql))) {
							return true;
						} else {
							if (s.equals(sql)) {
								return true;
							} else if (s.indexOf(sql) > 0) {
								return true;
							}
						}
					}
				}
		}
		return false;
	}
}
