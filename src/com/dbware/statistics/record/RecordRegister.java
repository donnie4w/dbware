package com.dbware.statistics.record;

import java.util.concurrent.Semaphore;
import com.dbware.listener.ThreadPool;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-18
 * @verion 1.0
 */
public class RecordRegister {
	private static final Semaphore semp = new Semaphore(Runtime.getRuntime().availableProcessors());

	public static void registerSlow(String s) {
		ThreadPool.getCpuExecutor().execute(new SlowSqlRecord(semp, s));
	}

	public static void registerGeneral(String s) {
		ThreadPool.getCpuExecutor().execute(new GeneralSqlRecord(semp, s));
	}
	
}
