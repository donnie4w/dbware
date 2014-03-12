package com.dbware.cluster.monitor;

import com.dbware.db.RecordEnum;
import com.dbware.db.cfg.DBwareConfigXml;
import com.dbware.statistics.record.RecordHandler;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2013-1-5
 * @verion 1.0.1
 */
public class TraceRecordHandler extends ScheduleTask {

	public void run() {
		if (DBwareConfigXml.checkInState()) {
			if (DBwareConfigXml.getGeneralSql() == RecordEnum.GENERALSQLON) {
				if (!RecordHandler.isGeneral())
					RecordHandler.setGeneral(true);
			} else {
				if (RecordHandler.isGeneral())
					RecordHandler.setGeneral(false);
			}

			if (DBwareConfigXml.getSlowSql() == RecordEnum.SLOWSQLON) {
				if (!RecordHandler.isSlowSql()) {
					RecordHandler.setSlowSql(true);
					RecordHandler.setSlowTime(DBwareConfigXml.getSlowTime());
				}
				if (RecordHandler.isSlowSql() && RecordHandler.getSlowTime() != DBwareConfigXml.getSlowTime()) {
					RecordHandler.setSlowTime(DBwareConfigXml.getSlowTime());
				}
			}
		}
	}
}
