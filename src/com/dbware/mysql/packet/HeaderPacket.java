package com.dbware.mysql.packet;

import com.dbware.db.BasePacket;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-8
 * @verion 1.0
 */
public class HeaderPacket extends BasePacket {
	private static final long serialVersionUID = -6555461223206365903L;
	private final static int size = 4;
	private int length;
	// The sequence-id is incremented with each packet and may wrap around. It
	// starts at 0 and is reset to 0 when a new command begins in the Command
	// Phase.
	private byte sequenceId;

	public byte[] toBytes() {
		byte[] temp = new byte[size];
		temp[0] = (byte) (length & 0xff);
		temp[1] = (byte) ((length >> 8) & 0xff);
		temp[2] = (byte) ((length >> 16) & 0xff);
		temp[3] = sequenceId;
		return temp;
	}

	public void putBytes(byte[] bs) {
		length = (bs[0] & 0xff) | ((bs[1] & 0xff) << 8) | ((bs[2] & 0xff) << 16);
		sequenceId = bs[3];
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public byte getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(byte sequenceId) {
		this.sequenceId = sequenceId;
	}
}
