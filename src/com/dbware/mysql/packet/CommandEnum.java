package com.dbware.mysql.packet;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-11
 * @verion 1.0 mysql Command protocol
 */
@SuppressWarnings("unused")
public enum CommandEnum {
	COM_SLEEP, COM_QUIT, COM_INIT_DB, COM_QUERY, COM_FIELD_LIST;

	private static final byte COM_SLEEP_CODE = 0x00;
	private static final byte COM_QUIT_CODE = 0x01;
	private static final byte COM_INIT_DB_CODE = 0x02;
	private static final byte COM_QUERY_CODE = 0x03;
	private static final byte COM_FIELD_LIST_CODE = 0x04;

	public static CommandEnum getCommandType(byte command) {
		switch (command) {
		case COM_QUERY_CODE:
			return COM_QUERY;
		case COM_QUIT_CODE:
			return COM_QUIT;
		default:
			return COM_QUIT;
		}
	}
}
