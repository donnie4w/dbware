package com.dbware.db;

import java.util.concurrent.locks.ReentrantLock;

import com.dbware.db.cfg.DBwareConfigXml;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2013-3-7
 * @verion 1.0.3
 */
public class GroupTactics {
	private static final ReentrantLock LOCK4WEIGHTED = new ReentrantLock();

	/**
	 * @param dbSources
	 * @return
	 */
	public static HbBeen useWeightedRoundRobinTactics() {
		LOCK4WEIGHTED.lock();
		try {
			HbBeen selectBeen = DBwareConfigXml.localBeen;
			int maxWeight = selectBeen.getCurrentWeight();
			for (HbBeen hb : DistribBeen.HBM.keySet()) {
				if (!hb.isValid()) {
					continue;
				}
				if (hb.getCurrentWeight() > maxWeight) {
					maxWeight = hb.getCurrentWeight();
					selectBeen = hb;
				}
			}
			if (selectBeen != null && selectBeen.isValid()) {
				selectBeen.currentWeightDec();
			}
			if (maxWeight <= 0) {
				DBwareConfigXml.localBeen.initCurrentWeight();
				for (HbBeen hb : DistribBeen.HBM.keySet()) {
					hb.initCurrentWeight();
				}
			}
			return selectBeen;
		} finally {
			LOCK4WEIGHTED.unlock();
		}
	}
}
