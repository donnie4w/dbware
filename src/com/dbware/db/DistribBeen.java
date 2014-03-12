package com.dbware.db;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dbware.db.cfg.DBwareConfigXml;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2013-3-7
 * @verion 1.0.3
 */
public class DistribBeen {
	public static final Map<HbBeen, Byte> HBM = new ConcurrentHashMap<HbBeen, Byte>();

	static {
		for (HbBeen hb : DBwareConfigXml.remoteBeensMap.keySet()) {
			HBM.put(hb, (byte) 0);
		}
	}

	public static void remove(HbBeen hb) {
		HBM.remove(hb);
	}

}
