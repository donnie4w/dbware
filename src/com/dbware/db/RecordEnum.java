package com.dbware.db;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-18
 * @verion 1.0
 */
public enum RecordEnum {
	SLOWSQLON, SLOWSQLOFF, GENERALSQLON, GENERALSQLOFF;

	public static RecordEnum getSlowSqlType(int value) {
		switch (value) {
		case 0:
			return SLOWSQLOFF;
		default:
			return SLOWSQLON;
		}
	}

	public static RecordEnum getGeneralSqlType(int value) {
		switch (value) {
		case 0:
			return GENERALSQLOFF;
		case 1:
			return GENERALSQLON;
		default:
			return GENERALSQLOFF;
		}
	}

}
