package com.dbware.listener;

import org.jboss.netty.channel.ChannelPipelineFactory;

public class ChannelFactory {
	private static ChannelPipelineFactory cpf = new ChannelPipelineFactoryImpl();
	private static ChannelPipelineFactory manager = new ManagerChannelPipelineFactoryImpl();

	public static ChannelPipelineFactory getInstance() {
		return cpf;
	}

	public static ChannelPipelineFactory getManagerInstance() {
		return manager;
	}
}
