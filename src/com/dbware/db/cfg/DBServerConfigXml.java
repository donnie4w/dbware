package com.dbware.db.cfg;

import java.util.List;
import org.dom4j.Element;
import com.dbware.db.DataSourceFactory;
import com.dbware.db.DbSource;
import com.dbware.db.GlobalVar;
import com.dbware.db.SourceGroup;
import com.dbware.db.XGroup;
import com.dbware.dom.Dom4jFactory;
import com.dbware.dom.Dom4jHandler;
import com.dbware.log.LogFactory;
import com.dbware.log.Logger;
import com.dbware.util.NumberUtil;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-18
 * @verion 1.0
 */
public final class DBServerConfigXml {
	private final static Logger logger = LogFactory.getLogger(DBServerConfigXml.class);

	private final static String DBSERVERFILEPATH = GlobalVar.WORKPATH + GlobalVar.SEP + "conf" + GlobalVar.SEP + "dbServer.xml";
	private final static String DBRULEFILEPATH = GlobalVar.WORKPATH + GlobalVar.SEP + "conf" + GlobalVar.SEP + "dbRule.xml";
	private final static String NAMED_CONFIG_PATH = "c3p0-config/named-config";
	private final static String GROUP_PATH = "dbRule/group";
	private final static String NAMED_CONFIG_ATTR_NAME = "name";

	public synchronized static boolean init() {
		try {
			Dom4jHandler dom4jHandler = Dom4jFactory.getDom4jHandler();
			dom4jHandler.loadbyPath(DBSERVERFILEPATH);
			List<String> serverList = dom4jHandler.getNodeAttributeValues(NAMED_CONFIG_PATH, NAMED_CONFIG_ATTR_NAME);

			dom4jHandler.loadbyPath(DBRULEFILEPATH);

			List<Element> list = dom4jHandler.getNodes(GROUP_PATH);

			for (Element element : list) {
				String groupName = element.attributeValue("name");
				XGroup xgroup = new XGroup(groupName);
				// create master
				String master = element.elementText("master");
				if (!serverList.contains(master)) {
					throw new Exception("[master:" + master + "] is not in dbServer.xml");
				}
				xgroup.SetMaster(DataSourceFactory.createDbSource(master, 1, 1));

				// create slaves
				String[] slaves = null;
				try {
					slaves = element.elementText("slave").split(",");
				} catch (Exception e) {
				}
				if (slaves != null) {
					boolean isDogcd = true;
					int[] weights = new int[slaves.length];
					for (int i = 0; i < slaves.length; i++) {
						String[] str = slaves[i].split("\\*");
						String slaveName = str[0];

						if (!serverList.contains(slaveName)) {
							throw new Exception("[slave:" + slaveName + "] is not in dbServer.xml");
						}

						int weight = str.length == 2 ? Integer.parseInt(str[1]) : 1;
						weights[i] = weight;
						if (weight == 1)
							isDogcd = false;
						xgroup.addSlave(DataSourceFactory.createDbSource(slaveName, i, weight));
					}
					if (isDogcd) {
						int gcdNum = NumberUtil.gcd(weights);
						if (gcdNum > 1) {
							for (DbSource db : xgroup.getSlaveList()) {
								db.setCurrentWeight(db.getCurrentWeight() / gcdNum);
							}
						}
					}
				}
				SourceGroup.addXGroup(groupName, xgroup);
			}

			logger.info("DBServerConfigXml init finish……");
		} catch (Exception e) {
			logger.error("DBServerConfigXml init error |" + e.getMessage());
			return false;
		}
		return true;
	}

	// public synchronized static boolean checkinSlave() {
	// try {
	// Dom4jHandler dom4jHandler = Dom4jFactory.getDom4jHandler();
	// dom4jHandler.loadbyPath(DBSERVERFILEPATH);
	// List<String> serverList =
	// dom4jHandler.getNodeAttributeValues(NAMED_CONFIG_PATH,
	// NAMED_CONFIG_ATTR_NAME);
	//
	// dom4jHandler.loadbyPath(DBRULEFILEPATH);
	//
	// String[] slaves = dom4jHandler.getNodeValue(SLAVE_PATH).split(",");
	// boolean isChange = false;
	//
	// for (String s : slaves) {
	// String[] str = s.split("\\*");
	// String slaveName = str[0];
	// if (!serverList.contains(slaveName)) {
	// logger.error("[slave:" + slaveName + "] is not in dbServer.xml");
	// } else if (slaveMap.containsKey(slaveName)) {
	// continue;
	// } else {
	// isChange = true;
	// int weight = str.length == 2 ? Integer.parseInt(str[1]) : 1;
	// slaveMap.put(slaveName, weight);
	// }
	// }
	// return isChange;
	// } catch (Exception e) {
	// return false;
	// }
	// }

	// public static String getMaster() {
	// return master;
	// }
	//
	// public static void setMaster(String master) {
	// DBServerConfigXml.master = master;
	// }
}
