package com.dbware.db;

import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import com.dbware.cluster.monitor.TraceDownDataSource;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-7
 * @verion 1.0
 */
public class DbSource {
	private String groupName;
	private String dbName;
	private DataSource dataSource;
	private volatile boolean isMonitor = false;
	private volatile boolean isValib = true;
	private int index = 1;
	private int weight = 1;
	private AtomicInteger currentWeight = new AtomicInteger(0);

	// public DbSource previousDbSource;
	// public DbSource nextDbSource;

	public String getDbName() {
		return dbName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public boolean isMonitor() {
		return isMonitor;
	}

	public void setMonitor(boolean isMonitor) {
		this.isMonitor = isMonitor;
	}

	public boolean isValib() {
		return isValib;
	}

	public void setValib(boolean isValib) {
		this.isValib = isValib;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getCurrentWeight() {
		return currentWeight.get();
	}

	public void initCurrentWeight() {
		this.currentWeight.set(this.weight);
	}

	public int currentWeightDec() {
		return this.currentWeight.decrementAndGet();
	}

	public void setCurrentWeight(int weight) {
		this.currentWeight.set(weight);
	}

	public void registerTrace() {
		TraceDownDataSource.register(dbName);
	}

}
