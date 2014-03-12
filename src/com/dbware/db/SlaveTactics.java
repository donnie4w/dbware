package com.dbware.db;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-17
 * @verion 1.0
 */
public class SlaveTactics {
	private static final ReentrantLock LOCK4WEIGHTED = new ReentrantLock();

	/**
	 * auther donnie wu
	 * 
	 * @param dbSources
	 * @return DbSource
	 */
	public static DbSource useWeightedRoundRobinTactics(List<DbSource> dbSources) {
		if (dbSources == null || dbSources.size() == 0) {
			return null;
		}
		LOCK4WEIGHTED.lock();
		try {
			int maxWeight = 0;
			DbSource db = null;
			boolean isAllMonitor = true;
			for (DbSource dbsource : dbSources) {
				if (dbsource.isMonitor()) {
					continue;
				} else {
					isAllMonitor = false;
				}
				if (dbsource.getCurrentWeight() > maxWeight) {
					maxWeight = dbsource.getCurrentWeight();
					db = dbsource;
				}
			}

			if (isAllMonitor) {
				return null;
			}
			if (db != null && !db.isMonitor()) {
				db.currentWeightDec();
			} else if (db == null) {
				for (DbSource dbsource : dbSources) {
					dbsource.initCurrentWeight();
				}
				return useWeightedRoundRobinTactics(dbSources);
			} else if (db.isMonitor()) {
				return useWeightedRoundRobinTactics(dbSources);
			}
			return db;
		} finally {
			LOCK4WEIGHTED.unlock();
		}
	}

	public static DbSource useIpHash() {
		return null;
	}

	public static DbSource useLeastConnection() {
		return null;
	}

}