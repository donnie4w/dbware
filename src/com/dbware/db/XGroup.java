package com.dbware.db;
/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2013-3-7
 * @verion 1.0.3
 */
import java.util.ArrayList;
import java.util.List;

public class XGroup {
	String groupName = "";
	DbSource master;
	List<DbSource> slaveList = new ArrayList<DbSource>();

	public XGroup(String groupName) {
		this.groupName = groupName;
	}

	public void addSlave(DbSource dbSource) {
		slaveList.add(dbSource);
	}

	public void SetMaster(DbSource dbSource) {
		this.master = dbSource;
	}

	public List<DbSource> getSlaveList() {
		return slaveList;
	}

	public DbSource getMaster() {
		return master;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
