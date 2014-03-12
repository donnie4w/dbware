package com.dbware.mysql.packet;

import com.dbware.db.BasePacket;
import com.dbware.mysql.buffer.BasicTypesUtils;
import com.dbware.mysql.buffer.MyBuffer;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-14
 * @verion 1.0
 */
public class FeildPacket  extends BasePacket{
	private static final long serialVersionUID = -5339378162055111849L;
	private String catalog = "def";
	private String db = "";
	private String table = "";
	private String orgTable = "";
	private String name = "";
	private String orgName = "";
	private byte filler1 = 0x0c;
	private short charsetnr = CharacterSet.getByte();
	private int length;
	private byte type;
	private short flag = 0;
	private byte decimals = 0;
	private byte[] filler2 = new byte[] { 0, 0 };

	public byte[] toBytes() {
		int capacity = 13 + BasicTypesUtils.getLengthEncodedStringLength(catalog) + BasicTypesUtils.getLengthEncodedStringLength(db) + BasicTypesUtils.getLengthEncodedStringLength(table)
				+ BasicTypesUtils.getLengthEncodedStringLength(orgTable) + BasicTypesUtils.getLengthEncodedStringLength(name) + BasicTypesUtils.getLengthEncodedStringLength(orgName);
		MyBuffer mb = new MyBuffer(capacity);
		mb.putLengthEncodedString(catalog);
		mb.putLengthEncodedString(db);
		mb.putLengthEncodedString(table);
		mb.putLengthEncodedString(orgTable);
		mb.putLengthEncodedString(name);
		mb.putLengthEncodedString(orgName);
		mb.putByte(filler1);
		mb.putShort(charsetnr);
		mb.putInt(length);
		mb.putByte(type);
		mb.putShort(flag);
		mb.putByte(decimals);
		mb.putBytes(filler2);
		return mb.toBytes();
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getOrgTable() {
		return orgTable;
	}

	public void setOrgTable(String orgTable) {
		this.orgTable = orgTable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public byte getFiller1() {
		return filler1;
	}

	public void setFiller1(byte filler1) {
		this.filler1 = filler1;
	}

	public short getCharsetnr() {
		return charsetnr;
	}

	public void setCharsetnr(short charsetnr) {
		this.charsetnr = charsetnr;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public short getFlag() {
		return flag;
	}

	public void setFlag(short flag) {
		this.flag = flag;
	}

	public byte getDecimals() {
		return decimals;
	}

	public void setDecimals(byte decimals) {
		this.decimals = decimals;
	}

	public byte[] getFiller2() {
		return filler2;
	}

	public void setFiller2(byte[] filler2) {
		this.filler2 = filler2;
	}
}
