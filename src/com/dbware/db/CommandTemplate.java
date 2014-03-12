package com.dbware.db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-11
 * @verion 1.0
 */
public interface CommandTemplate {
	public <T> T executeUpdate(CommandCallBack<T> action) throws SQLException ;
	public <T> T executeQuery(CommandCallBack<T> action) throws SQLException ;

	public <T> T executeQueryTrans(CommandCallBack<T> action, Connection conn) throws SQLException ;
	public <T> T executeUpdateTrans(CommandCallBack<T> action,Connection conn) throws SQLException ;
	
	public <T> T execute(CommandCallBack<T> action,SqlEnum SqlType) throws SQLException ;
}
