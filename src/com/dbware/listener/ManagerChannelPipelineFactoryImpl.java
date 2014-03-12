package com.dbware.listener;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.execution.ExecutionHandler;
import org.jboss.netty.handler.timeout.ReadTimeoutHandler;
import org.jboss.netty.util.HashedWheelTimer;
import com.dbware.db.filter.ManagerDecoder;
import com.dbware.db.filter.ManagerEncoder;
import com.dbware.db.handler.ManagerChannelUpstreamHandler;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @verion 1.0.4
 */
public class ManagerChannelPipelineFactoryImpl implements ChannelPipelineFactory {
	private static ExecutionHandler eh = new ExecutionHandler(ThreadPool.getCpuExecutor());
	static ReadTimeoutHandler timeoutHandler = new ReadTimeoutHandler(new HashedWheelTimer(), 10);

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		try {
			return Channels.pipeline(new ManagerEncoder(), new ManagerDecoder(), eh, new ManagerChannelUpstreamHandler(), timeoutHandler);
		} catch (Exception e) {
			throw e;
		}
	}
}
