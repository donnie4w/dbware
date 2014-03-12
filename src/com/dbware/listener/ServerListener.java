package com.dbware.listener;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import com.dbware.db.DistribListener;
import com.dbware.db.GlobalVar;
import com.dbware.db.cfg.DBwareConfigXml;
import com.dbware.log.LogFactory;
import com.dbware.log.Logger;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-7
 * @verion 1.0
 */
public class ServerListener implements Listener {
	private static final Logger logger = LogFactory.getLogger(ServerListener.class);

	public void listener() throws Exception {
		if (!GlobalVar.init()) {
			System.exit(0);
		}
		ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool()));
		bootstrap.setPipelineFactory(ChannelFactory.getInstance());
		bootstrap.setOption("child.tcpNoDelay", true);
		bootstrap.setOption("child.keepAlive", true);
		bootstrap.setOption("child.receiveBufferSize", 1048576);
		bootstrap.setOption("allIdleTime", "3");
		bootstrap.bind(new InetSocketAddress(DBwareConfigXml.getPort()));
		new DistribListener().listener();
		new ManagerListener().listener();
		logger.debug(" dbware start...");
		logger.debug(" [listen port:" + DBwareConfigXml.getPort() + "]");
	}
}
