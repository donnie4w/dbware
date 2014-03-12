package com.dbware.db;

import java.util.List;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-17
 * @verion 1.0
 */
public class SlaveDataSources {

//	private static final List<DbSource> dbSourceList = new CopyOnWriteArrayList<DbSource>();

	public static BalanceEnum balanceEnum = BalanceEnum.WeightedRoundRobin;

//	public static volatile int totalServer = 0;

//	static {
//		totalServer = dbSourceList.size();
//	}

	public static DbSource getDbSource(List<DbSource> dbSourceList) {
		switch (balanceEnum) {
		case WeightedRoundRobin:
//			return getValibDBSource(SlaveTactics.useWeightedRoundRobinTactics(dbSourceList), totalServer);
		return SlaveTactics.useWeightedRoundRobinTactics(dbSourceList);
		case IpHash:
			return SlaveTactics.useIpHash();
		case LeastConnection:
			return SlaveTactics.useLeastConnection();
		default:
			return null;
		}
	}

//	private static DbSource getValibDBSource(DbSource db, int loop) {
//		DbSource retDB = db;
//		if (db.isMonitor() && loop > 0) {
//			retDB = getValibDBSource(db.nextDbSource, --loop);
//		}
//		return retDB;
//	}

	// public static void addDbSource(DbSource dbSource) {
	// dbSourceList.add(dbSource);
	// }
	//
	// public static int getSlaveLength() {
	// return dbSourceList.size();
	// }

	// public static void removeSource(DbSource db) {
	// if (db.isValib()) {
	// LOCK.lock();
	// try {
	// if (db.isValib()) {
	// db.setValib(false);
	// DbSourceList.removeAll(dbSourceMap.get(db.getDbName()));
	// totalServer.decrementAndGet();
	// }
	// } finally {
	// LOCK.unlock();
	// }
	// }
	// }

	// public static void addSource(DbSource db) {
	// if (!db.isValib()) {
	// LOCK.lock();
	// try {
	// if (!db.isValib()) {
	// db.setValib(true);
	// DbSourceList.addAll(dbSourceMap.get(db.getDbName()));
	// totalServer.incrementAndGet();
	// }
	// } finally {
	// LOCK.unlock();
	// }
	// }
	// }
}
