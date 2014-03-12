package com.dbware.mysql.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbware.db.BasePacket;
import com.dbware.db.CommandCallBack;
import com.dbware.db.CommandHandler;
import com.dbware.db.CommandTemplateHandler;
import com.dbware.db.MsgI;
import com.dbware.db.SqlEnum;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-12
 * @verion 1.0
 */
public class MysqlCommandHandler {

	/**
	 * @param sql
	 * @return List<BasePacket>
	 * @throws SQLException
	 */
	public static List<BasePacket> executeQuery(final MsgI mi) throws SQLException {
		return new CommandTemplateHandler(mi.getGroupName()).executeQuery(new CommandCallBack<List<BasePacket>>() {
			@Override
			public List<BasePacket> executeQuery(CommandHandler commandHandler) throws SQLException {
				return MysqlCommandPacketUtil.queryPacket(commandHandler, mi.getMsg());
			}

			@Override
			public List<BasePacket> executeUpdate(CommandHandler commandHandler) throws SQLException {
				return null;
			}

			@Override
			public List<BasePacket> execute(CommandHandler commandHandler) throws SQLException {
				return null;
			}
		});
	}

	public static List<BasePacket> executeQuery_(final MsgI mi) throws SQLException {
		try {
			return executeQuery(mi);
		} catch (Exception e) {
			return new ArrayList<BasePacket>();
		}
	}

	/**
	 * @param sql
	 * @return List<BasePacket>
	 * @throws SQLException
	 */
	public static List<BasePacket> executeUpdate(final MsgI mi) throws SQLException {
		return new CommandTemplateHandler(mi.getGroupName()).executeUpdate(new CommandCallBack<List<BasePacket>>() {
			@Override
			public List<BasePacket> executeQuery(CommandHandler commandHandler) throws SQLException {
				return null;
			}

			@Override
			public List<BasePacket> executeUpdate(CommandHandler commandHandler) throws SQLException {
				return MysqlCommandPacketUtil.updatePacket(commandHandler, mi.getMsg());
			}

			@Override
			public List<BasePacket> execute(CommandHandler commandHandler) throws SQLException {
				return null;
			}
		});
	}

	public static List<BasePacket> executeUpdate_(final MsgI mi) throws SQLException {
		try {
			return executeUpdate(mi);
		} catch (Exception e) {
			return new ArrayList<BasePacket>();
		}
	}

	/**
	 * 
	 * @param sql
	 * @param conn
	 * @return List<BasePacket>
	 * @throws SQLException
	 */
	public static List<BasePacket> executeQueryTrans(final MsgI mi, Connection conn) throws SQLException {
		return new CommandTemplateHandler(mi.getGroupName()).executeQueryTrans(new CommandCallBack<List<BasePacket>>() {
			@Override
			public List<BasePacket> executeQuery(CommandHandler commandHandler) throws SQLException {
				return MysqlCommandPacketUtil.queryPacket(commandHandler, mi.getMsg());
			}

			@Override
			public List<BasePacket> executeUpdate(CommandHandler commandHandler) throws SQLException {
				return null;
			}

			@Override
			public List<BasePacket> execute(CommandHandler commandHandler) throws SQLException {
				return null;
			}
		}, conn);
	}

	/**
	 * 
	 * @param sql
	 * @param conn
	 * @return List<BasePacket>
	 * @throws SQLException
	 */
	public static List<BasePacket> executeUpdateTrans(final MsgI mi, Connection conn) throws SQLException {
		return new CommandTemplateHandler(mi.getGroupName()).executeUpdateTrans(new CommandCallBack<List<BasePacket>>() {
			@Override
			public List<BasePacket> executeQuery(CommandHandler commandHandler) throws SQLException {
				return null;
			}

			@Override
			public List<BasePacket> executeUpdate(CommandHandler commandHandler) throws SQLException {
				return MysqlCommandPacketUtil.updatePacket(commandHandler, mi.getMsg());
			}

			@Override
			public List<BasePacket> execute(CommandHandler commandHandler) throws SQLException {
				return null;
			}
		}, conn);
	}

	/**
	 * @param sql
	 * @return List<BasePacket>
	 * @throws SQLException
	 */
	public static List<BasePacket> execute(final MsgI mi, final SqlEnum sqlType) throws SQLException {
		return new CommandTemplateHandler(mi.getGroupName()).execute(new CommandCallBack<List<BasePacket>>() {
			@Override
			public List<BasePacket> executeQuery(CommandHandler commandHandler) throws SQLException {
				return null;
			}

			@Override
			public List<BasePacket> executeUpdate(CommandHandler commandHandler) throws SQLException {
				return null;
			}

			@Override
			public List<BasePacket> execute(CommandHandler commandHandler) throws SQLException {
				switch (sqlType) {
				case READ:
					return MysqlCommandPacketUtil.queryPacket(commandHandler, mi.getMsg());
				case WRITE:
					return MysqlCommandPacketUtil.updatePacket(commandHandler, mi.getMsg());
				case FILTER:
					return MysqlCommandPacketUtil.queryPacket(commandHandler, mi.getMsg());
				default:
					return MysqlCommandPacketUtil.updatePacket(commandHandler, mi.getMsg());
				}
			}
		}, sqlType);
	}

}
