package com.dbware.db;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-7
 * @verion 1.0 base byte buffer interface
 */
public interface BaseBuffer {
	public void flip();

	public byte[] toBytes();

	public int position();

	public void putByte(byte b);

	public void putBytes(byte[] bytes);

	public void putShort(short value);

	public void putInt(int value);

	public void putLong(long value);

	public byte getByte();

	public short getShort();

	public int getInt();

	public long getLong();

}
