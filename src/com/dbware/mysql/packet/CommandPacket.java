package com.dbware.mysql.packet;

import com.dbware.db.BasePacket;
import com.dbware.mysql.buffer.MyBuffer;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-12
 * @verion 1.0
 */
public class CommandPacket extends BasePacket {
	private static final long serialVersionUID = -5358929931908453073L;
	private byte command;
	private byte[] arg;

	public CommandPacket(byte[] bytes) {
		MyBuffer buffer = new MyBuffer(bytes);
		buffer.flip();
		command = buffer.getByte();
		arg = buffer.getRemainBytes();
	}

	public byte getCommand() {
		return command;
	}

	public byte[] getArg() {
		return arg;
	}

	@Override
	public byte[] toBytes() {
		return null;
	}

}
