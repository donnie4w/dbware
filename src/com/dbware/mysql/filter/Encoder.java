package com.dbware.mysql.filter;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.dbware.listener.PubVars;
import com.dbware.mysql.packet.HeaderPacket;
import com.dbware.mysql.packet.RowAllPacket;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-7
 * @verion 1.0
 */
public class Encoder extends OneToOneEncoder {
	private PubVars vars;

	public Encoder(PubVars vars) {
		this.vars = vars;
	}

	@Override
	protected Object encode(ChannelHandlerContext arg0, Channel arg1, Object arg2) throws Exception {
		if (arg2 instanceof RowAllPacket) {
			return ChannelBuffers.wrappedBuffer(((RowAllPacket) arg2).toBytes());
		} else {
			byte[] temp = (byte[]) arg2;
			HeaderPacket header = new HeaderPacket();
			header.setLength(temp.length);
			header.setSequenceId((byte) vars.getIncSequenceId());
			ChannelBuffer buffer = ChannelBuffers.wrappedBuffer(header.toBytes());
			ChannelBuffer buffer2 = ChannelBuffers.wrappedBuffer(temp);
			ChannelBuffer channelBuffer = ChannelBuffers.wrappedBuffer(buffer, buffer2);
			return channelBuffer;
		}
	}
}
