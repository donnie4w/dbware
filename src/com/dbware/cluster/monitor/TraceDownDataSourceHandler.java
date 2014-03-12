package com.dbware.cluster.monitor;

import com.dbware.db.CommandHandler;
import com.dbware.db.DbSource;
import com.dbware.log.LogFactory;
import com.dbware.log.Logger;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-14
 * @verion 1.0
 */
public class TraceDownDataSourceHandler extends ScheduleTask {
	private static final Logger logger = LogFactory.getLogger(TraceDownDataSourceHandler.class);
	public DbSource dbSource = null;
	private CommandHandler commandHandler = null;
	private int checkCount = 0;
	private Object objLock = new Object();

	public TraceDownDataSourceHandler(DbSource dbSource) {
		this.dbSource = dbSource;
	}

	@Override
	public void run() {
		try {
			synchronized (objLock) {
				if (commandHandler == null) {
					commandHandler = new CommandHandler(dbSource);
				}
			}
			logger.fatal(commandHandler.getDbName() + " is checking...");
			if (commandHandler.ping()) {
				this.cancel();
				commandHandler.getDbSource().setMonitor(false);
				logger.fatal(commandHandler.getDbName() + " is up now...");
				commandHandler.close();
			}
		} catch (Exception e) {
			try {
				logger.error(dbSource.getDbName() + " disconnected, check[" + checkCount++ + "]time");
			} catch (Exception et) {
			}
		}
	};
}
