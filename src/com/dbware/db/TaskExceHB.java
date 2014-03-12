package com.dbware.db;

import com.dbware.db.cfg.DBwareConfigXml;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2013-3-7
 * @verion 1.0.3
 */
public class TaskExceHB implements ITask {

	private static final long serialVersionUID = 4177691228114344974L;

	MsgHB m = new MsgHB();

	public TaskExceHB() {
	}

	@Override
	public Object exector() {
		for (HbBeen h : m.hbmap.keySet()) {
			if (!DistribBeen.HBM.containsKey(h) && !h.equals(DBwareConfigXml.localBeen)) {
				DistribBeen.HBM.put(h, (byte) 0);
			}
		}
		return m;
	}
}
