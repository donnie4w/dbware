package com.dbware.mysql.packet;

import com.dbware.db.BasePacket;
import com.dbware.mysql.buffer.MyBuffer;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-7
 * @verion 1.0 MySql EOF Packet
 */
public class EofPacket extends BasePacket {
	private static final long serialVersionUID = 2521512720393147508L;
	private byte header = (byte) 0xfe;
	private short warningCount = 0;
	private short statusFlags = 0;

	public byte[] toBytes() {
		MyBuffer mb = new MyBuffer(5);
		mb.putByte(header);
		mb.putShort(warningCount);
		mb.putShort(statusFlags);
		return mb.toBytes();
	}

	public byte getHeader() {
		return header;
	}

	public void setHeader(byte header) {
		this.header = header;
	}

	public short getWarningCount() {
		return warningCount;
	}

	public void setWarningCount(short warningCount) {
		this.warningCount = warningCount;
	}

	public short getStatusFlags() {
		return statusFlags;
	}

	public void setStatusFlags(short statusFlags) {
		this.statusFlags = statusFlags;
	}
	
}
