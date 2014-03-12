package com.dbware.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dbware.log.LogFactory;
import com.dbware.log.Logger;
import com.dbware.statistics.record.RecordHandler;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-11
 * @verion 1.0
 */
public class CommandHandler {
	private final static Logger logger = LogFactory.getLogger(CommandHandler.class);

	private DbSource dbSource;
	private Connection connection = null;
	private String dbName = "master";

	public CommandHandler(String db) throws SQLException {
		dbName = db;
		dbSource = DataSourceFactory.getDbSource(db);
		connection = dbSource.getDataSource().getConnection();
		connection.setAutoCommit(true);
	}

	public CommandHandler(DbSource dbSource) throws SQLException {
		dbName = dbSource.getDbName();
		this.dbSource = dbSource;
		connection = dbSource.getDataSource().getConnection();
		connection.setAutoCommit(true);
	}

	public CommandHandler(Connection conn) throws SQLException {
		connection = conn;
	}

	/**
	 * @param sql
	 * @return ResultSet
	 * @throws SQLException
	 */
	public ResultSet executeQuery(String sql) throws SQLException {
		RecordHandler rh = new RecordHandler(sql, dbSource == null ? "" : dbSource.getDbName());
		rh.start();
		ResultSet rs = connection.prepareStatement(sql).executeQuery();
		rh.end();
		return rs;
	}

	/**
	 * @param sql
	 * @return int
	 * @throws SQLException
	 */
	public int executeUpdate(String sql) throws SQLException {
		RecordHandler rh = new RecordHandler(sql, dbSource == null ? "" : dbSource.getDbName());
		rh.start();
		int updateCount = connection.prepareStatement(sql).executeUpdate();
		rh.end();
		return updateCount;
	}

	/**
	 * @param sqls
	 * @throws SQLException
	 */
	public void executeBatch(String[] sqls) throws SQLException {
		final List<Integer> retList = new ArrayList<Integer>();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			connection.setAutoCommit(false);
			for (String sql : sqls) {
				statement.addBatch(sql);
			}
			statement.executeBatch();
		} catch (SQLException e) {
			logger.error("executeBatch exception[" + e.getErrorCode() + " | " + e.getSQLState() + " | " + e.getMessage() + "]", e);
			for (String sql : sqls) {
				retList.add(executeUpdate(sql));
			}
		} finally {
			if (statement != null)
				statement.close();
		}
	}

	public void setAutoCommit(boolean setAutoCommit) throws SQLException {
		connection.setAutoCommit(setAutoCommit);
	}

	public boolean isClose() throws SQLException {
		return connection.isClosed();
	}

	public boolean close() {
		try {
			if (connection != null) {
				connection.close();
			}
			return true;
		} catch (SQLException e) {
			logger.error("Connection close fail[" + e.getErrorCode() + " | " + e.getSQLState() + " | " + e.getMessage() + "]");
			return false;
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public boolean ping() {
		Statement statement = null;
		try {
			statement = this.connection.createStatement();
			statement.executeQuery("SELECT 1").close();
			return true;
		} catch (SQLException se) {
			return false;
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException se) {
				}
			}
		}
	}

	public String getDbName() {
		return dbName;
	}

	public DbSource getDbSource() {
		return dbSource;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
