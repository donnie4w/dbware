package com.dbware.cluster.monitor;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2013-1-5
 * @verion 1.0.1 the slave databases dilatation
 */
public class TraceSlaveDilatation {
	public static void init() {
		ScheduleFactory.register(new TraceSlaveDilatationHandler(), 30 * 1000,
				300 * 1000);
	}
}
