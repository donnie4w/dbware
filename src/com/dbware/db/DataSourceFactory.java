package com.dbware.db;

import java.util.Map;

import org.jboss.netty.util.internal.ConcurrentHashMap;
import com.dbware.log.LogFactory;
import com.dbware.log.Logger;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-7
 * @verion 1.0
 */
public class DataSourceFactory {
	private final static Logger logger = LogFactory.getLogger(DataSourceFactory.class);

	private final static Map<String, DbSource> dbSourceMap = new ConcurrentHashMap<String, DbSource>();

	private static void checkDBsource(DbSource db) throws Exception {
		CommandHandler ch = null;
		try {
			ch = new CommandHandler(db);
			if (!ch.ping()) {
				throw new Exception();
			}
		} catch (Exception e) {
			String errorMsg = "error!!![" + db.getDbName() + "] init failed ! please check the dbServer.xml configuration <named-config name=\""
					+ db.getDbName() + "\">...";
			System.out.println(errorMsg);
			logger.error(errorMsg);
			throw e;
		} finally {
			if (ch != null)
				ch.close();
		}
	}

	public static DbSource createDbSource(String name, int index, int weight) throws Exception {
		if (dbSourceMap.containsKey(name)) {
			return dbSourceMap.get(name);
		}
		DbSource ds = new DbSource();
		ds.setDbName(name);
		ds.setIndex(index);
		ds.setMonitor(false);
		ds.setValib(true);
		ds.setWeight(weight);
		ds.setDataSource(new ComboPooledDataSource(name));
		dbSourceMap.put(name, ds);
		checkDBsource(ds);
		return ds;
	}

	public static DbSource getDbSource(String name) {
		return dbSourceMap.get(name);
	}
}
