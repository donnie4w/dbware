package com.dbware.db;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2013-3-7
 * @verion 1.0.3
 */
import com.dbware.mysql.handler.MysqlCommandHandler;

public class TaskExceSql implements ITask {

	private static final long serialVersionUID = 7350587636071994001L;
	MsgI mi;
	SqlEnum se;

	public TaskExceSql(MsgI mi, SqlEnum se) {
		this.mi = mi;
		this.se = se;
	}

	@Override
	public Object exector() {
		try {
			switch (se) {
			case READ:
				return MysqlCommandHandler.executeQuery_(mi);
			default:
				return MysqlCommandHandler.executeUpdate_(mi);
			}
		} catch (Exception e) {
			return null;
		}
	}
}
