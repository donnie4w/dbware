package com.dbware.db;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2013-3-7
 * @verion 1.0.3
 */
public class DistribLookup {
	public final static Object getMsg(HbBeen h, ITask task) throws Exception {
		Coordinated cd = (Coordinated) ContextFactory.getInstance().lookup(ConfBeen.getProtocolInfo(h));
		return cd.invoke(task);
	}
}
