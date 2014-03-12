package com.dbware.cluster.monitor;

import com.dbware.db.DistribBeen;
import com.dbware.db.DistribLookup;
import com.dbware.db.HbBeen;
import com.dbware.db.TaskExceHB;

public class TraceHbHandler extends ScheduleTask {
	public void run() {
		for (HbBeen hb : DistribBeen.HBM.keySet()) {
			try {
				if (hb.getFailCount() <= HbBeen.MAXFAILNUMBER) {
					DistribLookup.getMsg(hb, new TaskExceHB());
					hb.setValid(true);
					hb.initFailCount();
				} else {
					DistribBeen.remove(hb);
				}
			} catch (Exception e) {
				hb.setValid(false);
				hb.failCountInc();
			}
		}
	}
}
