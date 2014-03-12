package com.dbware.statistics.record;

import com.dbware.db.RecordEnum;
import com.dbware.db.cfg.DBwareConfigXml;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-18
 * @verion 1.0
 */
public class RecordHandler {
	private static boolean isSlowSql = false;
	private static boolean isGeneral = false;
	private boolean isMaches = false;
	private static int slowTime = 0;
	private String dbName = "";
	public long startTime;
	public long endTime;
	public String sql;

	public RecordHandler(String s, String dbName) {
		sql = s;
		this.dbName = dbName;
		isMaches = isSqlMatches(sql);
	}

	static {
		if (DBwareConfigXml.getSlowSql() == RecordEnum.SLOWSQLON && DBwareConfigXml.getSlowTime() > 0) {
			isSlowSql = true;
			slowTime = DBwareConfigXml.getSlowTime();
		}
		if (DBwareConfigXml.getGeneralSql() == RecordEnum.GENERALSQLON) {
			isGeneral = true;
		}
	}

	public void start() {
		if (isSlowSql && isMaches)
			startTime = System.currentTimeMillis();
	}

	public void end() {
		if (isGeneral && isMaches) {
			RecordRegister.registerGeneral(sql);
		}
		if (isSlowSql && isMaches) {
			endTime = System.currentTimeMillis();
			long userTime = endTime - startTime;
			if (userTime >= slowTime) {
				RecordRegister.registerSlow("[" + sql + "][" + userTime + " ms][" + dbName + "]");
			}
		}
	}

	public static boolean isSqlMatches(String sql) {
		sql = sql.trim().toLowerCase().replaceAll("/\\*.*?\\*/", "");
		if (sql.startsWith("select")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isSlowSql() {
		return isSlowSql;
	}

	public static void setSlowSql(boolean isSlowSql) {
		RecordHandler.isSlowSql = isSlowSql;
	}

	public static boolean isGeneral() {
		return isGeneral;
	}

	public static void setGeneral(boolean isGeneral) {
		RecordHandler.isGeneral = isGeneral;
	}

	public static int getSlowTime() {
		return slowTime;
	}

	public static void setSlowTime(int slowTime) {
		RecordHandler.slowTime = slowTime;
	}
}
