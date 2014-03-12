package com.dbware.mysql.packet;

import java.sql.Types;
/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-14
 * @verion 1.0
 */
public class MysqlFieldType {
	private static final byte FIELD_TYPE_DECIMAL = 0;
	private static final byte FIELD_TYPE_TINY = 1;
	private static final byte FIELD_TYPE_SHORT = 2;
	private static final byte FIELD_TYPE_LONG = 3;
	private static final byte FIELD_TYPE_FLOAT = 4;
	private static final byte FIELD_TYPE_DOUBLE = 5;
	private static final byte FIELD_TYPE_NULL = 6;
	private static final byte FIELD_TYPE_TIMESTAMP = 7;
	private static final byte FIELD_TYPE_LONGLONG = 8;
	private static final byte FIELD_TYPE_TIME = 11;
	private static final byte FIELD_TYPE_NEWDATE = 14;
	private static final byte FIELD_TYPE_VARCHAR = 15;
	private static final byte FIELD_TYPE_BIT = 16;
	private static final byte FIELD_TYPE_BLOB = (byte) 252;
	private static final byte FIELD_TYPE_STRING = (byte) 254;
	private static final byte FIELD_TYPE_GEOMETRY = (byte) 255;

	public static int javaToMysql(int javaType) {
		int mysqlType;
		switch (javaType) {
		case Types.DECIMAL:
			mysqlType = FIELD_TYPE_DECIMAL;
			break;
		case Types.SMALLINT:
			mysqlType = FIELD_TYPE_SHORT;
			break;
		case Types.TINYINT:
			mysqlType = FIELD_TYPE_TINY;
			break;
		case Types.INTEGER:
			mysqlType = FIELD_TYPE_LONG;
			break;
		case Types.REAL:
			mysqlType = FIELD_TYPE_FLOAT;
			break;
		case Types.DOUBLE:
			mysqlType = FIELD_TYPE_DOUBLE;
			break;
		case Types.NULL:
			mysqlType = FIELD_TYPE_NULL;
			break;
		case Types.TIMESTAMP:
			mysqlType = FIELD_TYPE_TIMESTAMP;
			break;
		case Types.BIGINT:
			mysqlType = FIELD_TYPE_LONGLONG;
			break;
		case Types.DATE:
			mysqlType = FIELD_TYPE_NEWDATE;
			break;
		case Types.TIME:
			mysqlType = FIELD_TYPE_TIME;
			break;
		case Types.CHAR:
			mysqlType = FIELD_TYPE_STRING;
			break;
		case Types.LONGVARBINARY:
			mysqlType = FIELD_TYPE_BLOB;
			break;
		case Types.VARCHAR:
			mysqlType = FIELD_TYPE_VARCHAR;
			break;
		case Types.BINARY:
			mysqlType = FIELD_TYPE_GEOMETRY;
			break;
		case Types.BIT:
			mysqlType = FIELD_TYPE_BIT;
			break;
		default:
			mysqlType = FIELD_TYPE_VARCHAR;
		}
		return mysqlType;
	}

}
