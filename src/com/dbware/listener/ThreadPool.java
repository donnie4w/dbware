package com.dbware.listener;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.jboss.netty.util.DefaultObjectSizeEstimator;
import com.dbware.db.cfg.DBwareConfigXml;
import org.jboss.netty.channel.UpstreamChannelStateEvent;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-7
 * @verion 1.0
 */
public class ThreadPool {
	private static ExecutorService defualtExecutor = null;
	private static ExecutorService cpuExecutor = null;
	private static ExecutorService memoryAwareExecutor = null;
	static {
		int maximumPoolSize = DBwareConfigXml.getMaxConnections();
		int corePoolSize = maximumPoolSize > 100 ? 100 : maximumPoolSize;
		defualtExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 1800, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));
		cpuExecutor = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors() * 2);
//		memoryAwareExecutor = new MemoryAwareThreadPoolExecutor(corePoolSize, 1048576, 1048576, 30, TimeUnit.SECONDS,
//				new DefaultObjectSizeEstimator(), Executors.defaultThreadFactory());
	}

	public static ExecutorService getDefualExecutor() {
		return defualtExecutor;
	}

	public static ExecutorService getCpuExecutor() {
		return cpuExecutor;
	}

	public static ExecutorService getMemoryAwareExecutor() {
		return memoryAwareExecutor;
	}
}

class MyObjectSizeEstimator extends DefaultObjectSizeEstimator {

	public int estimateSize(Object o) {
		if (o instanceof UpstreamChannelStateEvent) {
			return super.estimateSize(((UpstreamChannelStateEvent) o).getValue());
		} else {
			return super.estimateSize(o);
		}
	}
}
