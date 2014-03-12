package com.dbware.mysql.packet;

import java.security.MessageDigest;
import java.util.Arrays;

import com.dbware.db.BasePacket;
import com.dbware.db.cfg.DBwareConfigXml;
import com.dbware.mysql.buffer.MyBuffer;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-10
 * @verion 1.0 check the username and password
 */
public class AuthPacket extends BasePacket {

	private static final long serialVersionUID = -7989823973323211004L;
	private int clientFlag;
	private int maxPacketSize;
	private byte charsetSet;
	private byte[] filler;
	private String clientUser;
	private byte[] clientPwd;
	private String dbName;
	private String groupName;

	public AuthPacket(byte[] bytes) {
		MyBuffer buffer = new MyBuffer(bytes);
		buffer.flip();
		clientFlag = buffer.getInt();
		maxPacketSize = buffer.getInt();
		charsetSet = buffer.getByte();
		filler = buffer.getBytes(23);
		clientUser = buffer.getNulTerminatedString();
		clientPwd = buffer.getLEIBytes();
		dbName = buffer.getNulTerminatedString();
		if (dbName.contains("@")) {
			groupName = dbName.split("@")[1];
			dbName = dbName.split("@")[0];
		} else {
			groupName = dbName;
		}
	}

	public boolean checkAuth() {
		if (!DBwareConfigXml.getServerUser().equals(clientUser)) {
			return false;
		}
		if (clientPwd.length == 0 && DBwareConfigXml.getServerpwd().length() == 0) {
			return true;
		}
		try {
			byte[] pwd = encodePassword(DBwareConfigXml.getServerpwd());
			if (!Arrays.equals(clientPwd, pwd)) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private byte[] encodePassword(String password) throws Exception {
		byte[] seed = new byte[] { 1, 1, 1, 1, 1, 1, 1, 1 };
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		byte[] passwordHashStage1 = md.digest(password.getBytes("ASCII"));
		md.reset();
		byte[] passwordHashStage2 = md.digest(passwordHashStage1);
		md.reset();
		md.update(seed);
		md.update(passwordHashStage2);
		byte[] toBeXord = md.digest();
		int numToXor = toBeXord.length;
		for (int i = 0; i < numToXor; i++) {
			toBeXord[i] = (byte) (toBeXord[i] ^ passwordHashStage1[i]);
		}
		return toBeXord;
	}

	@Override
	public byte[] toBytes() {
		return null;
	}

	public long getClientFlag() {
		return clientFlag;
	}

	public void setClientFlag(int clientFlag) {
		this.clientFlag = clientFlag;
	}

	public long getMaxPacketSize() {
		return maxPacketSize;
	}

	public void setMaxPacketSize(int maxPacketSize) {
		this.maxPacketSize = maxPacketSize;
	}

	public byte getCharsetSet() {
		return charsetSet;
	}

	public void setCharsetNum(byte charsetSet) {
		this.charsetSet = charsetSet;
	}

	public byte[] getFiller() {
		return filler;
	}

	public void setFiller(byte[] filler) {
		this.filler = filler;
	}

	public String getClientUser() {
		return clientUser;
	}

	public void setClientUser(String clientUser) {
		this.clientUser = clientUser;
	}

	public byte[] getClientPwd() {
		return clientPwd;
	}

	public void setClientPwd(byte[] clientPwd) {
		this.clientPwd = clientPwd;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getGroupName() {
		return this.groupName;
	}
}
