package com.dbware.statistics.record;

import java.util.concurrent.Semaphore;

import com.dbware.log.LogFactory;
import com.dbware.log.Logger;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-18
 * @verion 1.0
 */
public class GeneralSqlRecord extends Thread {
	private static final Logger logger = LogFactory.getLogger(GeneralSqlRecord.class);
	Semaphore semp = null;
	String s = null;

	public GeneralSqlRecord(Semaphore semp, String str) {
		this.semp = semp;
		this.s = str;
	}

	public void run() {
		try {
			semp.acquire();
		} catch (InterruptedException e) {
		}
		logger.info(s);
		semp.release();
	}

}
