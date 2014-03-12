package com.dbware.mysql.packet;

import java.util.ArrayList;
import java.util.List;

import com.dbware.db.BasePacket;
import com.dbware.mysql.buffer.BasicTypesUtils;
import com.dbware.mysql.buffer.MyBuffer;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-8
 * @verion 1.0
 */
public class RowPacket extends BasePacket {
	private static final long serialVersionUID = -8593748232209696269L;
	private List<String> valueList = new ArrayList<String>();
	private int length = 0;

	@Override
	public byte[] toBytes() {
		MyBuffer mb = new MyBuffer(length);
		for (String value : valueList) {
			mb.putLengthEncodedString(value);
		}
		return mb.toBytes();
	}

	public void addValue(String value) {
		valueList.add(value);
		length += BasicTypesUtils.getLengthEncodedStringLength(value);
	}
}
