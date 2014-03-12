package com.dbware.db;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2013-3-7
 * @verion 1.0.3
 */
public class HbBeen implements Serializable {
	private static final long serialVersionUID = -3835887318743624619L;
	public static final int MAXFAILNUMBER = 15;
	public static final int WEIGHT = 127;

	private AtomicInteger currentWeight = new AtomicInteger(WEIGHT);
	private boolean valid = true;
	private String host;
	private int port;

	private short failCount = 0;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean equals(Object anObject) {
		if (this == anObject) {
			return true;
		}
		if (anObject instanceof HbBeen) {
			HbBeen anotherString = (HbBeen) anObject;
			if (anotherString.getPort() == this.port && anotherString.getHost().equals(this.host)) {
				return true;
			}
		}
		return false;
	}

	public int hashCode() {
		return (host + ":" + port).hashCode();
	}

	public boolean isValid() {
		return this.valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public int getFailCount() {
		return failCount;
	}

	public void failCountInc() {
		this.failCount++;
	}

	public void initFailCount() {
		this.failCount = 0;
	}

	public void initCurrentWeight() {
		currentWeight.set(WEIGHT);
	}

	public int currentWeightDec() {
		return currentWeight.decrementAndGet();
	}

	public int getCurrentWeight() {
		return currentWeight.get();
	}

}
