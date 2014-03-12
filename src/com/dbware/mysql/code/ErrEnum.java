package com.dbware.mysql.code;
/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-8
 * @verion 1.0
 */
public enum ErrEnum {
	
	E1040(1040,"08004", "Too many connections"), 
	E1041(1041,"HY000", "Out of memory"), 
	E1042(1042,"08S01", "Can't get hostname for your address"), 
	E1043(1043,"08S01", "Bad handshake"),
	E1045(1045,"28000", "Access denied for user"),
	E1046(1046,"3D000", "No database selected"),
	E1047(1047,"08S01", "Unknown command"),
	E1105(1105,"HY000", "Unknown error ");

	private int errorCode;
	private String sqlState;
	private String errorMessage;

	private ErrEnum(int code,String state ,String msg) {
		this.errorCode = code;
		this.sqlState=state;
		this.errorMessage = msg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getSqlState() {
		return sqlState;
	}

	public void setSqlState(String sqlState) {
		this.sqlState = sqlState;
	}
}
