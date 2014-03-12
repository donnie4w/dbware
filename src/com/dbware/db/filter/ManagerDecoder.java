package com.dbware.db.filter;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

import com.dbware.db.cfg.DBwareConfigXml;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @verion 1.0.4
 */
public class ManagerDecoder extends FrameDecoder {
	private StringBuilder sb = new StringBuilder();

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception {
		byte[] decoded = new byte[buffer.readableBytes()];
		buffer.readBytes(decoded);
		String ret = new String(decoded, DBwareConfigXml.getCharacterSet());
		sb.append(ret);
		if (!ret.endsWith("\n")) {
			return null;
		}
		ret = sb.toString();
		sb = new StringBuilder();
		return ret;
	}
}
