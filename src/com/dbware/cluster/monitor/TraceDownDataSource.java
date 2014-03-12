package com.dbware.cluster.monitor;

import com.dbware.db.DataSourceFactory;
import com.dbware.db.DbSource;
import com.dbware.log.LogFactory;
import com.dbware.log.Logger;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-14
 * @verion 1.0
 */
public class TraceDownDataSource {
	private static final Logger logger = LogFactory.getLogger(TraceDownDataSource.class);

	public static void register(String dbName) {
		DbSource dbSource = DataSourceFactory.getDbSource(dbName);
		if (!dbSource.isMonitor()) {
			synchronized (TraceDownDataSource.class) {
				if (!dbSource.isMonitor()) {
					dbSource.setMonitor(true);
					logger.info(dbSource.getDbName() + " register monitor successful...");
					ScheduleFactory.register(new TraceDownDataSourceHandler(dbSource));
				}
			}
		}
	}
}
