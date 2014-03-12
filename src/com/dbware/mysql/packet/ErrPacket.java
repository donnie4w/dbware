package com.dbware.mysql.packet;

import com.dbware.db.BasePacket;
import com.dbware.mysql.buffer.MyBuffer;
import com.dbware.util.StringUtil;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-8
 * @verion 1.0
 */
public class ErrPacket extends BasePacket {
	private static final long serialVersionUID = -5971233571875691480L;
	private static final byte header = (byte) 0xff;
	private short errorCode = 0;
	private byte sql_state_marker = 0x23;   //#35
	private byte[] sql_state = new byte[5];
	private String error_message = "error \0";
	
	public ErrPacket(short errorCode,byte[] sql_state,String error_message){
	    this.errorCode=errorCode;
	    this.sql_state=sql_state;
	    this.error_message=error_message;
	}

	public byte[] toBytes() {
		byte[] msgByte = StringUtil.getBytes(error_message);
		int length = 9 + msgByte.length;
		MyBuffer buffer = new MyBuffer(length);
		buffer.putByte(header);
		buffer.putShort(errorCode);
		buffer.putByte(sql_state_marker);
		buffer.putBytes(sql_state);
		buffer.putBytes(msgByte);
		return buffer.toBytes();
	}
	
	
	
}
