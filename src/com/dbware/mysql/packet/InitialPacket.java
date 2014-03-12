package com.dbware.mysql.packet;

import java.io.IOException;

import com.dbware.db.BasePacket;
import com.dbware.mysql.buffer.MyBuffer;
import com.dbware.util.StringUtil;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-7
 * @verion 1.0 Initial Handshake Packet - protocol version 10
 */
public class InitialPacket extends BasePacket {
	private static final long serialVersionUID = -7653043263824645571L;
	private final static byte protocol_version = 0x0a;
	private final static String server_version = "5.5.2-MYSQL-DBWARE-1.0.4 \0";
	private final static byte[] auth_plugin_data_part_1 = new byte[] { 1, 1, 1, 1, 1, 1, 1, 1 };
	private final static byte filler = 0x00;
	private final static byte[] capability_flag = new byte[] { (byte) 255, (byte) 247 };
	private final static byte character_set = CharacterSet.getByte();
	private final static short status_flags = 0x0002;
	private final static byte[] capability_flags = new byte[2];
	private final static byte auth_plugin_data_len = 0x00;// +
	private final static byte[] reserved = new byte[10];
	private final static byte[] auth_plugin_name = new byte[13];
	Object obj = null;

	public InitialPacket(Object obj) {
		this.obj = obj;
	}

	public byte[] toBytes(Object obj) throws IOException {
		byte[] serverVersionBytes = StringUtil.getBytes(server_version);
		int length = 45 + serverVersionBytes.length;
		MyBuffer buffer = new MyBuffer(length);
		buffer.putByte(protocol_version);
		buffer.putBytes(serverVersionBytes);
		buffer.putInt((int) Thread.currentThread().getId());
		buffer.putBytes(auth_plugin_data_part_1);
		buffer.putByte(filler);
		buffer.putBytes(capability_flag);
		buffer.putByte(character_set);
		buffer.putShort(status_flags);
		buffer.putBytes(capability_flags);
		buffer.putByte(auth_plugin_data_len);
		buffer.putBytes(reserved);
		buffer.putBytes(auth_plugin_name);
		return buffer.toBytes();
	}

	@Override
	public byte[] toBytes() {
		try {
			return toBytes(obj);
		} catch (IOException e) {
			return new byte[] {};
		}
	}
}