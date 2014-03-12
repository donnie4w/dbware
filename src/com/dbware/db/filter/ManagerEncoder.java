package com.dbware.db.filter;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.dbware.db.GlobalVar;
import com.dbware.db.cfg.DBwareConfigXml;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @verion 1.0.4
 */
public class ManagerEncoder extends OneToOneEncoder {
	int i = 0;

	@Override
	protected Object encode(ChannelHandlerContext arg0, Channel arg1, Object arg2) throws Exception {
		ChannelBuffer buffer = ChannelBuffers.wrappedBuffer(((String) arg2 + GlobalVar.CRLF).getBytes(DBwareConfigXml.getCharacterSet()));
		ChannelBuffer bufferEnd = ChannelBuffers
				.wrappedBuffer((GlobalVar.getCommandHead() + (i++) + "]").getBytes(DBwareConfigXml.getCharacterSet()));
		ChannelBuffer channelBuffer = ChannelBuffers.wrappedBuffer(buffer, bufferEnd);
		return channelBuffer;
	}
}
