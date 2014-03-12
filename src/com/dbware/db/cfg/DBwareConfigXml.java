package com.dbware.db.cfg;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Element;

import com.dbware.db.GlobalVar;
import com.dbware.db.HbBeen;
import com.dbware.db.RecordEnum;
import com.dbware.dom.Dom4jFactory;
import com.dbware.dom.Dom4jHandler;
import com.dbware.log.LogFactory;
import com.dbware.log.Logger;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-18
 * @verion 1.0
 */
public final class DBwareConfigXml {
	private final static Logger logger = LogFactory.getLogger(DBwareConfigXml.class);

	private final static String DBWAREFILEPATH = GlobalVar.WORKPATH + GlobalVar.SEP + "conf" + GlobalVar.SEP + "dbware.xml";
	private final static String USERNAME_PATH = "dbware/username";
	private final static String PASSWORD_PATH = "dbware/password";
	private final static String CHARACTERSET_PATH = "dbware/characterSet";
	private final static String BINDADDRESS_PATH = "dbware/bindAddress";
	private final static String PORT_PATH = "dbware/port";
	private final static String MAXCONNECTIONS_PATH = "dbware/maxConnections";
	private final static String SLOWSQL_PATH = "dbware/slowSql";
	private final static String GENERALSQL_PATH = "dbware/general_log";
	private final static String SERVERS_PATH = "dbware/servers/";
	private final static String CPROT_PATH = "dbware/cport/";

	private static int maxConnections;
	private static int port;
	private static int cport;
	private static String bindAddress;
	private static String serverUser = "";
	private static String serverpwd = "";
	private static String characterSet = "";
	private static RecordEnum slowSql = null;
	private static int slowTime = 0;
	private static RecordEnum generalSql = null;

	public static HbBeen localBeen = new HbBeen();
	public static Map<HbBeen, Byte> remoteBeensMap = new ConcurrentHashMap<HbBeen, Byte>();

	public synchronized static boolean init() {
		try {
			Dom4jHandler dom4jHandler = Dom4jFactory.getDom4jHandler();
			dom4jHandler.loadbyPath(DBWAREFILEPATH);
			serverUser = dom4jHandler.getNodeValue(USERNAME_PATH);
			serverpwd = dom4jHandler.getNodeValue(PASSWORD_PATH);
			bindAddress = dom4jHandler.getNodeValue(BINDADDRESS_PATH);
			port = Integer.parseInt(dom4jHandler.getNodeValue(PORT_PATH));
			cport = Integer.parseInt(dom4jHandler.getNodeValue(CPROT_PATH));
			maxConnections = Integer.parseInt(dom4jHandler.getNodeValue(MAXCONNECTIONS_PATH));
			characterSet = dom4jHandler.getNodeValue(CHARACTERSET_PATH);
			slowTime = Integer.parseInt(dom4jHandler.getNodeValue(SLOWSQL_PATH)) * 1000;
			slowSql = RecordEnum.getSlowSqlType(slowTime);
			generalSql = RecordEnum.getGeneralSqlType(Integer.parseInt(dom4jHandler.getNodeValue(GENERALSQL_PATH)));
			if (dom4jHandler.hasNode(SERVERS_PATH + "local")) {
				localBeen.setHost(dom4jHandler.getNodeValue(SERVERS_PATH + "local").split(":")[0]);
				localBeen.setPort(Integer.parseInt(dom4jHandler.getNodeValue(SERVERS_PATH + "local").split(":")[1]));
			} else {
				GlobalVar.setISCLUS(false);
			}
			try {
				if (dom4jHandler.hasNode(SERVERS_PATH + "remote")) {
					for (Element le : dom4jHandler.getNodes(SERVERS_PATH + "remote")) {
						HbBeen hb = new HbBeen();
						hb.setHost(le.getTextTrim().split(":")[0]);
						hb.setPort(Integer.parseInt(le.getTextTrim().split(":")[1]));
						remoteBeensMap.put(hb, (byte) 0);
					}
				}
			} catch (Exception ex) {
			}
			logger.info("DBwareConfigXml init finish……");
		} catch (Exception e) {
			logger.error("DBwareConfigXml init error", e);
			return false;
		}
		return true;
	}

	public static int getMaxConnections() {
		return maxConnections;
	}

	public static void setMaxConnections(int maxConnections) {
		DBwareConfigXml.maxConnections = maxConnections;
	}

	public static int getPort() {
		return port;
	}

	public static void setPort(int port) {
		DBwareConfigXml.port = port;
	}

	public static String getBindAddress() {
		return bindAddress;
	}

	public static void setBindAddress(String bindAddress) {
		DBwareConfigXml.bindAddress = bindAddress;
	}

	public static String getServerUser() {
		return serverUser;
	}

	public static void setServerUser(String serverUser) {
		DBwareConfigXml.serverUser = serverUser;
	}

	public static String getServerpwd() {
		return serverpwd;
	}

	public static void setServerpwd(String serverpwd) {
		DBwareConfigXml.serverpwd = serverpwd;
	}

	public static String getCharacterSet() {
		return characterSet;
	}

	public static void setCharacterSet(String characterSet) {
		DBwareConfigXml.characterSet = characterSet;
	}

	public static RecordEnum getGeneralSql() {
		return generalSql;
	}

	public static void setGeneralSql(RecordEnum generalSql) {
		DBwareConfigXml.generalSql = generalSql;
	}

	public static RecordEnum getSlowSql() {
		return slowSql;
	}

	public static void setSlowSql(RecordEnum slowSql) {
		DBwareConfigXml.slowSql = slowSql;
	}

	public static int getCport() {
		return cport;
	}

	public static void setCport(int cport) {
		DBwareConfigXml.cport = cport;
	}

	public static int getSlowTime() {
		return slowTime;
	}

	public static void setSlowTime(int slowTime) {
		DBwareConfigXml.slowTime = slowTime;
	}

	public synchronized static boolean checkInState() {
		try {
			Dom4jHandler dom4jHandler = Dom4jFactory.getDom4jHandler();
			dom4jHandler.loadbyPath(DBWAREFILEPATH);
			slowTime = Integer.parseInt(dom4jHandler.getNodeValue(SLOWSQL_PATH)) * 1000;
			slowSql = RecordEnum.getSlowSqlType(slowTime);
			generalSql = RecordEnum.getGeneralSqlType(Integer.parseInt(dom4jHandler.getNodeValue(GENERALSQL_PATH)));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
