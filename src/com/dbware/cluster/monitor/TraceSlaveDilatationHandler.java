package com.dbware.cluster.monitor;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2013-1-5
 * @verion 1.0.1 the slave databases dilatation handler
 */
public class TraceSlaveDilatationHandler extends ScheduleTask {

	public void run() {
		try {
			// DataSourceFactory.checkInSlaves();
		} catch (Exception e) {
		}
	}
}