package com.dbware.db;

import java.io.Serializable;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-8
 * @verion 1.0
 */
public abstract class BasePacket implements Serializable {

	private static final long serialVersionUID = 7548505281546097513L;

	public abstract byte[] toBytes();

	public void putBytes(byte[] bytes) {
	};
}
