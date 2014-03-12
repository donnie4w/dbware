package com.dbware.db;

import java.sql.SQLException;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-11
 * @verion 1.0
 */
public interface CommandCallBack<T> {
	T executeUpdate(CommandHandler commandHandler) throws SQLException;
	T executeQuery(CommandHandler commandHandler) throws SQLException;
	T execute(CommandHandler commandHandler) throws SQLException;
}
