package com.dbware.db;

import java.io.File;

import org.apache.log4j.PropertyConfigurator;

import com.dbware.cluster.monitor.TraceHB;
import com.dbware.cluster.monitor.TraceRecord;
import com.dbware.db.cfg.DBFilterConfigXml;
import com.dbware.db.cfg.DBServerConfigXml;
import com.dbware.db.cfg.DBwareConfigXml;
import com.dbware.log.LogFactory;
import com.dbware.log.Logger;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-7
 * @verion 1.0 initialize the system variable
 */
public class GlobalVar {
	private static final Logger logger = LogFactory.getLogger(GlobalVar.class);
	public static final String WORKPATH = System.getProperty("user.dir");
	public static final String SEP = File.separator;
	public static final String CRLF = "\r\n";
	private static final String VERSION = "dbware 1.0.4";
	private static final String COMMAND_HEAD = "dbware: (CONNECTED) ";
	private static final String YCBD = "\u006A\u0061\u0076\u0061\u002E\u0072\u006D\u0069\u002E\u0073\u0065\u0072\u0076\u0065\u0072\u002E\u0068\u006F\u0073\u0074\u006E\u0061\u006D\u0065";
	private static final String YCD = "\u0064\u0062\u0077\u0061\u0072\u0065";
	private static final String YCDYXY = "\u0072\u006D\u0069\u003A\u002F\u002F";
	private static final String YCKQXY = "\u0072\u006D\u0069\u003A";
	private static final String TORESP = "\u0073\u0075\u006e\u002e\u0072\u006d\u0069\u002e\u0074\u0072\u0061\u006e\u0073\u0070\u006f\u0072\u0074\u002e\u0074\u0063\u0070\u002e\u0072\u0065\u0073\u0070\u006f\u006e\u0073\u0065\u0054\u0069\u006d\u0065\u006f\u0075\u0074";
	private static final String TOREAD = "\u0073\u0075\u006e\u002e\u0072\u006d\u0069\u002e\u0074\u0072\u0061\u006e\u0073\u0070\u006f\u0072\u0074\u002e\u0074\u0063\u0070\u002e\u0072\u0065\u0061\u0064\u0054\u0069\u006d\u0065\u006f\u0075\u0074";
	private static final String TOYC = "\u0035\u0030\u0030\u0030";
	private static final int AUTH_TIME_OUT = 8000;

	private static boolean ISCLUS = true;

	public static boolean init() {
		if (!initLogConf()) {
			return false;
		}
		if (!DBServerConfigXml.init()) {
			return false;
		}
		if (!DBwareConfigXml.init()) {
			return false;
		}
		if (!DBFilterConfigXml.init()) {
			return false;
		}
		TraceRecord.init();
		TraceHB.init();
		return true;
	}

	private static boolean initLogConf() {
		try {
			File logDir = new File(WORKPATH + SEP + "log");
			if (!logDir.exists()) {
				logDir.mkdirs();
			}
			String log4jfile = WORKPATH + SEP + "conf" + SEP + "dbwareLog.cnf";
			PropertyConfigurator.configure(log4jfile);
			return true;
		} catch (Exception e) {
			logger.error("init log config fail!", e);
			return false;
		}
	}

	public static String getWorkpath() {
		return WORKPATH;
	}

	public static String getSep() {
		return SEP;
	}

	public static String getYcbd() {
		return YCBD;
	}

	public static String getYcd() {
		return YCD;
	}

	public static String getYcdyxy() {
		return YCDYXY;
	}

	public static String getYckqxy() {
		return YCKQXY;
	}

	public static String getToresp() {
		return TORESP;
	}

	public static String getToread() {
		return TOREAD;
	}

	public static String getToyc() {
		return TOYC;
	}

	public static String getVersion() {
		return VERSION;
	}

	public static String getCommandHead() {
		return COMMAND_HEAD;
	}

	public static boolean isISCLUS() {
		return ISCLUS;
	}

	public static void setISCLUS(boolean iSCLUS) {
		ISCLUS = iSCLUS;
	}

	public static int getAuthTimeOut() {
		return AUTH_TIME_OUT;
	}
}
