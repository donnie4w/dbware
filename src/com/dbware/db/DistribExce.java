package com.dbware.db;

import java.sql.SQLException;
import java.util.List;

import com.dbware.db.cfg.DBwareConfigXml;
import com.dbware.listener.ThreadPool;
import com.dbware.mysql.handler.MysqlCommandHandler;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2013-3-7
 * @verion 1.0.3
 */
public class DistribExce {
	@SuppressWarnings("unchecked")
	public static List<BasePacket> execute(MsgI mi, SqlEnum se) throws SQLException {
		List<BasePacket> list = null;
		if (GlobalVar.isISCLUS()) {
			HbBeen hb = GroupTactics.useWeightedRoundRobinTactics();
			if (hb != null && hb.isValid() && !hb.equals(DBwareConfigXml.localBeen)) {
				try {
					list = (List<BasePacket>) DistribLookup.getMsg(hb, new TaskExceSql(mi, se));
				} catch (Exception e) {
				}
				return (list == null || list.size() == 0) ? execute_(mi, se) : list;
			} else {
				return execute_(mi, se);
			}

		} else {
			return execute_(mi, se);
		}
	}

	public static void exeuteAsync(List<String> list) {
		ThreadPool.getDefualExecutor().execute(new ExcuteAsync(list));
	}

	public static List<BasePacket> execute_(MsgI mi, SqlEnum se) throws SQLException {
		switch (se) {
		case READ:
			return MysqlCommandHandler.executeQuery_(mi);
		default:
			return MysqlCommandHandler.executeUpdate_(mi);
		}
	}
}

class ExcuteAsync implements Runnable {

	List<String> list = null;

	public ExcuteAsync(List<String> list) {
		this.list = list;
	}

	@Override
	public void run() {
		for (HbBeen hb : DistribBeen.HBM.keySet()) {
			if (!hb.isValid()) {
				continue;
			}
			try {
				DistribLookup.getMsg(hb, new TaskExceSyncCache(list));
			} catch (Exception e) {
			}
		}
	}

}