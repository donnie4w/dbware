package com.dbware.mysql.filter;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

import com.dbware.listener.PubVars;
import com.dbware.mysql.packet.HeaderPacket;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-7
 * @verion 1.0
 */
public class Decoder extends FrameDecoder {
	private volatile int length = 0;
	private PubVars vars;

	public Decoder(PubVars vars) {
		this.vars = vars;
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception {
		if (length == 0 && buffer.readableBytes() < 4) {
			return null;
		}
		if (length == 0) {
			byte[] headBytes = new byte[4];
			buffer.readBytes(headBytes);
			HeaderPacket head = new HeaderPacket();
			head.putBytes(headBytes);
			length = head.getLength();
			vars.setSequenceId(head.getSequenceId());
		}
		if (buffer.readableBytes() < length) {
			return null;
		}
		byte[] decoded = new byte[length];
		buffer.readBytes(decoded);
		length = 0;
		return decoded;
	}
}
