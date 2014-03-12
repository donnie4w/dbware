package com.dbware.listener;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.execution.ExecutionHandler;
import org.jboss.netty.handler.timeout.ReadTimeoutHandler;
import org.jboss.netty.util.HashedWheelTimer;

import com.dbware.mysql.filter.Decoder;
import com.dbware.mysql.filter.Encoder;
import com.dbware.mysql.handler.MysqlChannelUpstreamHandler;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-7
 * @verion 1.0
 */
public class ChannelPipelineFactoryImpl implements ChannelPipelineFactory {
	private static ExecutionHandler eh = new ExecutionHandler(ThreadPool.getDefualExecutor());
	static ReadTimeoutHandler timeoutHandler = new ReadTimeoutHandler(new HashedWheelTimer(), 10);

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		try {
			final PubVars vars = new PubVars();
			return Channels.pipeline(new Encoder(vars), new Decoder(vars), eh, new MysqlChannelUpstreamHandler(vars), timeoutHandler);
		} catch (Exception e) {
			throw e;
		}
	}
}
