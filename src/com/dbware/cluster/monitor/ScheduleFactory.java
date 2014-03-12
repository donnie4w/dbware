package com.dbware.cluster.monitor;

import java.util.Timer;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-14
 * @verion 1.0
 */
public class ScheduleFactory {
	private final static Timer timer = new Timer(true);

	public static void register(ScheduleTask st) {
		timer.scheduleAtFixedRate(st, 10 * 1000, 10 * 1000);
	}

	public static void register(ScheduleTask st, long delay, long period) {
		timer.scheduleAtFixedRate(st, delay, period);
	}

}
