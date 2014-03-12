package com.dbware.db;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dbware.db.cfg.DBwareConfigXml;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2013-3-7
 * @verion 1.0.3
 */
public class MsgHB implements MsgI {

	private static final long serialVersionUID = 6376689562708333010L;

	public Map<HbBeen, Byte> hbmap = new ConcurrentHashMap<HbBeen, Byte>();

	public MsgHB() {
		hbmap.put(DBwareConfigXml.localBeen, (byte) 0);
		for (HbBeen hb : DistribBeen.HBM.keySet()) {
			hbmap.put(hb, (byte) 0);
		}
	}

	@Override
	public String getGroupName() {
		return null;
	}

	@Override
	public String getMsg() {
		return null;
	}

	@Override
	public Object getObject() {
		return hbmap;
	}

	@Override
	public void setGroupName(String groupName) {
	}

	@Override
	public void setMsg(String msg) {
	}

	@Override
	public void setObject(Object obj) {
	}
}
