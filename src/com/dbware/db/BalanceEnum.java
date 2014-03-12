package com.dbware.db;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-17
 * @verion 1.0
 */
public enum BalanceEnum {

	WeightedRoundRobin, IpHash, LeastConnection;

	public static BalanceEnum getBalanceType(int value) {
		switch (value) {
		case 1:
			return WeightedRoundRobin;
		case 2:
			return IpHash;
		case 3:
			return LeastConnection;
		default:
			return WeightedRoundRobin;
		}
	}
}
