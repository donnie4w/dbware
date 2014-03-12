package com.dbware.mysql.packet;

import com.dbware.db.BasePacket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2013-1-21
 * @verion 1.0
 */
public class RowAllPacket extends BasePacket {
	private static final long serialVersionUID = 2821065022879800404L;
	private List<byte[]> list = new ArrayList<byte[]>();

	@Override
	public byte[] toBytes() {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			for (byte[] bs : list) {
				baos.write(bs);
			}
			return baos.toByteArray();
		} catch (IOException e) {
			return new byte[] {};
		} finally {
			try {
				baos.close();
			} catch (IOException e) {
			}
		}
	}

	public void putBytes(byte[] bytes) {
		// baos.write(bytes);
		list.add(bytes);
	}
}
