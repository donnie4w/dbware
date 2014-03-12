package com.dbware.db;

import java.sql.Connection;
import java.sql.SQLException;
import com.dbware.mysql.code.ErrEnum;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-11
 * @verion 1.0
 */
public class CommandTemplateHandler implements CommandTemplate {
	private String groupName = "";

	public CommandTemplateHandler(String groupName) {
		this.groupName = groupName;
	}

	@Override
	public <T> T executeQuery(CommandCallBack<T> action) throws SQLException {
		CommandHandler handler = null;
		XGroup xgroup = SourceGroup.getXGroup(groupName);
		if (xgroup == null) {
			throw new SQLException("Group [" + groupName + "] doesn't exist![dbRule.xml]", ErrEnum.E1105.getSqlState(), ErrEnum.E1105.getErrorCode());
		}
		DbSource db = SlaveDataSources.getDbSource(xgroup.getSlaveList());
		try {
			if (db != null && db.isMonitor()) {
				int i = SourceGroup.getXGroup(groupName).getSlaveList().size();
				while (i-- > 0) {
					db = SlaveDataSources.getDbSource(SourceGroup.getXGroup(groupName).getSlaveList());
					if (db == null)
						db = SourceGroup.getXGroup(groupName).getMaster();
					if (!db.isMonitor())
						break;
				}
			}
			if (db == null)
				db = SourceGroup.getXGroup(groupName).getMaster();
			handler = new CommandHandler(db);
			return action.executeQuery(handler);
		} catch (Exception se) {
			if (handler == null || !handler.ping()) {
				db.registerTrace();
				return action.executeQuery(new CommandHandler(SourceGroup.getXGroup(db.getGroupName()).master));
			} else {
				if (se instanceof SQLException) {
					throw new SQLException(se);
				} else {
					throw new SQLException(se.getMessage() != null ? se.getMessage() : ErrEnum.E1105.getErrorMessage(), ErrEnum.E1105.getSqlState(),
							ErrEnum.E1105.getErrorCode());
				}
			}
		} finally {
			if (handler != null)
				handler.close();
		}
	}

	@Override
	public <T> T executeUpdate(CommandCallBack<T> action) throws SQLException {
		CommandHandler handler = new CommandHandler(SourceGroup.getXGroup(groupName).getMaster());
		try {
			return action.executeUpdate(handler);
		} finally {
			handler.close();
		}
	}

	@Override
	public <T> T executeQueryTrans(CommandCallBack<T> action, Connection conn) throws SQLException {
		CommandHandler handler = new CommandHandler(conn);
		return action.executeQuery(handler);
	}

	@Override
	public <T> T executeUpdateTrans(CommandCallBack<T> action, Connection conn) throws SQLException {
		CommandHandler handler = new CommandHandler(conn);
		return action.executeUpdate(handler);
	}

	@Override
	public <T> T execute(CommandCallBack<T> action, SqlEnum sqlTpye) throws SQLException {
		CommandHandler handler = null;
		try {
			switch (sqlTpye) {
			case FILTER:
				handler = new CommandHandler(SourceGroup.getXGroup(groupName).getMaster());
				return action.execute(handler);
			case READ:
				return executeQuery(action);
			case WRITE:
				return executeUpdate(action);
			default:
				return executeUpdate(action);
			}
		} finally {
			if (handler != null)
				handler.close();
		}
	}
}
