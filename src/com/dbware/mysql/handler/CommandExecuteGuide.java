package com.dbware.mysql.handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbware.cluster.cache.ResultSetCache;
import com.dbware.cluster.cache.ResultSetShowCache;
import com.dbware.db.BasePacket;
import com.dbware.db.DistribExce;
import com.dbware.db.MsgContent;
import com.dbware.db.MsgI;
import com.dbware.db.SourceGroup;
import com.dbware.db.SqlEnum;
import com.dbware.db.SqlUtil;
import com.dbware.listener.PubVars;
import com.dbware.mysql.code.ErrEnum;
import com.dbware.mysql.packet.CommandEnum;
import com.dbware.mysql.packet.CommandPacket;
import com.dbware.mysql.packet.OkPacket;
import com.dbware.util.StringUtil;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-12
 * @verion 1.0
 */
public class CommandExecuteGuide {
	private CommandPacket commandPacket;
	private PubVars vars;

	private CommandExecuteGuide(CommandPacket commandPacket, PubVars vars) {
		this.commandPacket = commandPacket;
		this.vars = vars;
	}

	public static CommandExecuteGuide newInstance(CommandPacket commandPacket, PubVars vars) {
		return new CommandExecuteGuide(commandPacket, vars);
	}

	@SuppressWarnings("unchecked")
	public List<BasePacket> execute() throws SQLException, IOException {
		List<BasePacket> packetList = new ArrayList<BasePacket>();
		switch (CommandEnum.getCommandType(commandPacket.getCommand())) {
		case COM_QUERY:
			final String s = StringUtil.byte2String(commandPacket.getArg());
			MsgI mi = MsgContent.newInstance();
			mi.setGroupName(vars.getGroupName());
			mi.setMsg(s);
			Object[] o = SqlUtil.getSqlType(s);
			List<String> list = o.length == 2 ? (List<String>) o[1] : null;
			switch ((SqlEnum) o[0]) {
			case READ:
				if (list != null && list.size() > 0) {
					List<BasePacket> retlist = null;
					String k = ResultSetCache.containsListKey(list);
					if (k != null) {
						retlist = ResultSetCache.get(k);
						return retlist != null ? retlist : ResultSetCache.getAndSet(
								list,
								vars.isTransaction() ? MysqlCommandHandler.executeQueryTrans(mi, vars.getConnection()) : DistribExce.execute(mi,
										SqlEnum.READ));
					} else {
						return ResultSetCache.getAndSet(list, vars.isTransaction() ? MysqlCommandHandler.executeQueryTrans(mi, vars.getConnection())
								: DistribExce.execute(mi, SqlEnum.READ));
					}
				} else {
					return vars.isTransaction() ? MysqlCommandHandler.executeQueryTrans(mi, vars.getConnection()) : DistribExce.execute(mi,
							SqlEnum.READ);
				}
			case SHOW:
				List<BasePacket> lb = ResultSetShowCache.get(s);
				return lb == null ? ResultSetShowCache.getAndSet(s, MysqlCommandHandler.executeQuery(mi)) : lb;
			case FILTER:
				if (list != null && list.size() > 0) {
					List<BasePacket> retlist = null;
					String k = ResultSetCache.containsListKey(list);
					if (k != null) {
						retlist = ResultSetCache.get(k);
						return retlist != null ? retlist : ResultSetCache.getAndSet(
								list,
								vars.isTransaction() ? MysqlCommandHandler.executeQueryTrans(mi, vars.getConnection()) : MysqlCommandHandler.execute(
										mi, SqlEnum.FILTER));
					} else {
						return ResultSetCache.getAndSet(list, vars.isTransaction() ? MysqlCommandHandler.executeQueryTrans(mi, vars.getConnection())
								: MysqlCommandHandler.execute(mi, SqlEnum.FILTER));
					}
				} else {
					return vars.isTransaction() ? MysqlCommandHandler.executeQueryTrans(mi, vars.getConnection()) : MysqlCommandHandler.execute(mi,
							SqlEnum.FILTER);
				}

			case WRITE:
				if (list != null && list.size() > 0) {
					ResultSetCache.SyncRemoveList(list);
				}
				// return vars.isTransaction() ? MysqlCommandHandler.executeUpdateTrans(mi, vars.getConnection()) :
				// DistribExce.execute(mi,SqlEnum.WRITE);
				return vars.isTransaction() ? MysqlCommandHandler.executeUpdateTrans(mi, vars.getConnection()) : MysqlCommandHandler
						.executeUpdate(mi);
			case TRANSACTION:
				if (list != null && list.size() > 0) {
					ResultSetCache.SyncRemoveList(list);
				}
				// return vars.isTransaction() ? MysqlCommandHandler.executeUpdateTrans(mi, vars.getConnection()) :
				// DistribExce.execute(mi,SqlEnum.WRITE);
				return vars.isTransaction() ? MysqlCommandHandler.executeUpdateTrans(mi, vars.getConnection()) : MysqlCommandHandler
						.executeUpdate(mi);
			case TRANSACTIONSTART:
				if (list != null && list.size() > 0) {
					ResultSetCache.SyncRemoveList(list);
				}
				if (vars.isTransaction()) {
					return MysqlCommandHandler.executeUpdateTrans(mi, vars.getConnection());
				} else {
					vars.setTransaction(true);
					return MysqlCommandHandler.executeUpdateTrans(mi,
							vars.setConnection(SourceGroup.getXGroup(vars.getGroupName()).getMaster().getDataSource().getConnection()));
				}
			case TRANSACTIONEND:
				if (list != null && list.size() > 0) {
					ResultSetCache.SyncRemoveList(list);
				}
				if (vars.isTransaction()) {
					vars.setTransaction(false);
					List<BasePacket> lbp = MysqlCommandHandler.executeUpdateTrans(mi, vars.getConnection());
					vars.releaseConnection();
					return lbp;
				} else {
					// return DistribExce.execute(mi, SqlEnum.WRITE);
					return MysqlCommandHandler.executeUpdate(mi);
				}
			case OTHER:
				if (list != null && list.size() > 0) {
					ResultSetCache.SyncRemoveList(list);
				}
				// return DistribExce.execute(mi, SqlEnum.WRITE);
				return MysqlCommandHandler.executeUpdate(mi);
			default:
				throw new SQLException(ErrEnum.E1047.getErrorMessage(), ErrEnum.E1047.getSqlState(), ErrEnum.E1047.getErrorCode());
			}
		case COM_QUIT:
			vars.releaseConnection();
			packetList.add(new OkPacket());
			break;
		default:
			vars.releaseConnection();
			packetList.add(new OkPacket());
			break;
		}
		return packetList;
	}
}
