package com.dbware.mysql.packet;

import com.dbware.db.BasePacket;
import com.dbware.mysql.buffer.BasicTypesUtils;
import com.dbware.mysql.buffer.MyBuffer;
import com.dbware.util.StringUtil;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-8
 * @verion 1.0
 */
public class OkPacket extends BasePacket {
	private static final long serialVersionUID = -7431801382157320376L;
	private final byte header = 0;
	private int affectedRows = 0;
	private int last_insert_id = 0;
	private short status_flags = 0;
	private short warnings = 0;
	private String info = "";

	public byte[] toBytes() {
		byte[] msgByte = StringUtil.getBytes(info);
		int len = 1 + BasicTypesUtils.getLengthEncodedIntegerLength(affectedRows) + BasicTypesUtils.getLengthEncodedIntegerLength(last_insert_id) + 4 + msgByte.length;
		MyBuffer buffer = new MyBuffer(len);
		buffer.putByte(header);
		buffer.putLengthEncodedInteger(affectedRows);
		buffer.putLengthEncodedInteger(last_insert_id);
		buffer.putShort(status_flags);
		buffer.putShort(warnings);
		buffer.putBytes(msgByte);
		return buffer.toBytes();
	}

	public long getAffectedRows() {
		return affectedRows;
	}

	public void setAffectedRows(int affectedRows) {
		this.affectedRows = affectedRows;
	}

	public long getInsertId() {
		return last_insert_id;
	}

	public void setInsertId(int insertId) {
		this.last_insert_id = insertId;
	}

	public short getServerStatus() {
		return status_flags;
	}

	public void setServerStatus(short serverStatus) {
		this.status_flags = serverStatus;
	}

	public short getWarningCount() {
		return warnings;
	}

	public void setWarningCount(short warningCount) {
		this.warnings = warningCount;
	}

	public String getMessage() {
		return info;
	}

	public void setMessage(String message) {
		this.info = message;
	}

	public byte getType() {
		return header;
	}
}
