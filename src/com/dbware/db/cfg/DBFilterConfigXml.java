package com.dbware.db.cfg;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dbware.db.GlobalVar;
import com.dbware.dom.Dom4jFactory;
import com.dbware.dom.Dom4jHandler;
import com.dbware.log.LogFactory;
import com.dbware.log.Logger;
import com.dbware.util.StringUtil;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-18
 * @verion 1.0
 */
public final class DBFilterConfigXml {
	private final static Logger logger = LogFactory.getLogger(DBFilterConfigXml.class);
	private final static String DBFILTERPATH = GlobalVar.WORKPATH + GlobalVar.SEP + "conf" + GlobalVar.SEP + "dbfilter.xml";
	private final static String TABLE_PATH = "dbfilter/table";
	private final static String SQL_PATH = "dbfilter/sql";
	public final static Map<String, String> tableFilterMap = new ConcurrentHashMap<String, String>();
	public final static Map<String, String> sqlFilterMap = new ConcurrentHashMap<String, String>();

	public synchronized static boolean init() {
		try {
			Dom4jHandler dom4jHandler = Dom4jFactory.getDom4jHandler();
			dom4jHandler.loadbyPath(DBFILTERPATH);

			try {
				List<String> tableList = dom4jHandler.getNodesValue(TABLE_PATH);
				for (String str : tableList) {
					if (!StringUtil.isEmpty(str))
						tableFilterMap.put(str.trim().toLowerCase(), "");
				}
			} catch (Exception e) {
			}

			try {
				List<String> sqlList = dom4jHandler.getNodesValue(SQL_PATH);
				for (String str : sqlList) {
					if (!StringUtil.isEmpty(str)) {
						String s = str.replaceAll("(\\s){2,}|(\t)", " ").replaceAll("(\\s*,\\s*)+", ",").toLowerCase();
						sqlFilterMap.put(s, s);
						sqlFilterMap.put(s + " where", "");
						sqlFilterMap.put(s + " order by", "");
						sqlFilterMap.put(s + " group by", "");
						sqlFilterMap.put(s + " union", "");
						sqlFilterMap.put(s + " limit", "");
						sqlFilterMap.put(s + "\\)", "");
						sqlFilterMap.put(s + " \\)", "");
					}
				}
			} catch (Exception e) {
			}
			logger.info("DBFilterConfigXml init finish……");
		} catch (Exception e) {
			logger.error("DBFilterConfigXml init error", e);
		}
		return true;
	}
}
