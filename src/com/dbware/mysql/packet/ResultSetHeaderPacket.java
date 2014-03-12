package com.dbware.mysql.packet;

import com.dbware.db.BasePacket;
import com.dbware.mysql.buffer.BasicTypesUtils;
import com.dbware.mysql.buffer.MyBuffer;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-14
 * @verion 1.0
 */

public class ResultSetHeaderPacket  extends BasePacket{
	private static final long serialVersionUID = 1403730913625412537L;
	public int fieldCount;

	public ResultSetHeaderPacket(int fieldCount) {
		this.fieldCount = fieldCount;
	}

	public byte[] toBytes() {
		int capacity = BasicTypesUtils.getLengthEncodedIntegerLength(fieldCount);
		MyBuffer mb = new MyBuffer(capacity);
		mb.putLengthEncodedInteger(fieldCount);
		return mb.toBytes();
	}
}
