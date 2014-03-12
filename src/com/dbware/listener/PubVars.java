package com.dbware.listener;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-7
 * @verion 1.0 the public var
 */
public class PubVars {
	private int sequenceId = 0;
	private String groupName = null;
	private Connection connection = null;
	private volatile boolean isTransaction = false;
	private Object dbJLock = new Object();

	public Connection setConnection(Connection conn) throws SQLException {
		if (connection == null) {
			synchronized (dbJLock) {
				if (connection == null) {
					connection = conn;
				} else {
					conn.close();
				}
			}
		} else {
			conn.close();
		}
		return connection;
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void releaseConnection() throws SQLException {
		if (connection != null) {
			synchronized (dbJLock) {
				if (connection != null) {
					connection.close();
				}
				connection = null;
			}
		}
	}

	public boolean isTransaction() {
		return isTransaction;
	}

	public void setTransaction(boolean isTransaction) {
		this.isTransaction = isTransaction;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getSequenceId() {
		return sequenceId;
	}

	public int getIncSequenceId() {
		return sequenceId++;
	}

	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}

	public Object getDbJLock() {
		return dbJLock;
	}

	public void setDbJLock(Object dbJLock) {
		this.dbJLock = dbJLock;
	}
}
