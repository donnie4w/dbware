package com.dbware.mysql.buffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import com.dbware.db.BaseBuffer;
import com.dbware.util.StringUtil;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-7
 * @verion 1.0 MySql byte buffer
 */
public class MyBuffer implements BaseBuffer, Serializable {
	private static final long serialVersionUID = 5060007404490621505L;
	private ByteBuffer byteBuffer;

	public MyBuffer(ByteBuffer byteBuffer) {
		this.byteBuffer = byteBuffer;
	}

	public MyBuffer(int capacity) {
		byteBuffer = ByteBuffer.allocate(capacity);
		byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
	}

	public MyBuffer(byte[] bytes) {
		byteBuffer = ByteBuffer.allocate(bytes.length);
		byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
		byteBuffer.put(bytes);
	}

	public void putByte(byte b) {
		byteBuffer.put(b);
	}

	public void putBytes(byte[] bytes) {
		byteBuffer.put(bytes);
	}

	public void putShort(short value) {
		byteBuffer.putShort((short) value);
	}

	public void putInt(int value) {
		byteBuffer.putInt(value);
	}

	public void putLong(long value) {
		byteBuffer.putLong(value);
	}

	public void putStirng(String value) {
		byteBuffer.put(StringUtil.getBytes(value));
	}

	public byte getByte() {
		return byteBuffer.get();
	}

	public byte[] getRemainBytes() {
		int length = byteBuffer.limit() - byteBuffer.position();
		if (length > 0) {
			byte[] dst = new byte[length];
			byteBuffer.get(dst, 0, length);
			return dst;
		} else {
			return null;
		}
	}

	public byte[] getBytes(int length) {
		if (length > 0) {
			byte[] dst = new byte[length];
			byteBuffer.get(dst, 0, length);
			return dst;
		} else {
			return null;
		}
	}

	public short getShort() {
		return byteBuffer.getShort();
	}

	public int getInt() {
		return byteBuffer.getInt();
	}

	public long getLong() {
		return byteBuffer.getLong();
	}

	public String getNulTerminatedString() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte b = 0;
		while ((byteBuffer.position() != byteBuffer.limit()) && (b = byteBuffer.get()) != 0) {
			baos.write(b);
		}
		return StringUtil.byte2String(baos.toByteArray());
	}

	public byte[] getLEIBytes() {
		long len = getLengthEncodedInteger();
		byte[] bytes = new byte[(int) len];
		byteBuffer.get(bytes);
		return bytes;
	}

	private long getLengthEncodedInteger() {
		int first = byteBuffer.get() & 0xff;
		switch (first) {
		case 0xfb:
			return 0;
		case 0xfc:
			return getShort();
		case 0xfd:
			return ((byteBuffer.get() & 0xff) >> 16) | ((byteBuffer.get() & 0xff) >> 8) | ((byteBuffer.get() & 0xff));
		case 0xfe:
			return getLong();
		default:
			return first;
		}
	}

	public void putLengthEncodedString(String str) {
		if (str == null) {
			putByte((byte) 251);
		} else {
			byte[] tmp = StringUtil.getBytes(str);
			long len = tmp.length;
			putLengthEncodedInteger(len);
			putBytes(tmp);
		}
	}

	public void putLengthEncodedInteger(long value) {
		if (value < 0xfb) {
			putByte((byte) (value & 0xff));
			return;
		}

		if (value <= 0xffff) {
			putByte((byte) 0xfc);
			putShort((short) value);
			return;
		}

		if (value <= 0xffffff) {
			putByte((byte) 0xfd);
			putByte((byte) ((value >> 16) & 0xff));
			putByte((byte) ((value >> 8) & 0xff));
			putByte((byte) ((value) & 0xff));
			return;
		}

		putByte((byte) 0xfe);
		putLong(value);
	}

	public byte[] toBytes() {
		return byteBuffer.array();
	}

	public int position() {
		return byteBuffer.position();
	}

	public void flip() {
		byteBuffer.flip();
	}
}