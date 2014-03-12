package com.dbware.cluster.monitor;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2013-1-5
 * @verion 1.0.1
 */
public class TraceRecord {
	public static void init() {
		ScheduleFactory.register(new TraceRecordHandler(), 10 * 1000, 10 * 1000);
	}
}
