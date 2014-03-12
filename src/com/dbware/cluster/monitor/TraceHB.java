package com.dbware.cluster.monitor;

public class TraceHB {
	public static void init() {
		ScheduleFactory.register(new TraceHbHandler(), 5 * 1000, 5 * 1000);
	}
}
