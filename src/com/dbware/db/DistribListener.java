package com.dbware.db;

import java.rmi.registry.LocateRegistry;

import com.dbware.db.cfg.DBwareConfigXml;
import com.dbware.listener.Listener;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2013-3-7
 * @verion 1.0.3
 */
public class DistribListener implements Listener {
	public void listener() throws Exception {
		if (GlobalVar.isISCLUS()) {
			System.setProperty(GlobalVar.getYcbd(), DBwareConfigXml.localBeen.getHost());
			LocateRegistry.createRegistry(DBwareConfigXml.localBeen.getPort());
			ContextFactory.getInstance().rebind(ConfBeen.getProtocolInfo(DBwareConfigXml.localBeen), new CoordinatedStub());
		}
	}
}
