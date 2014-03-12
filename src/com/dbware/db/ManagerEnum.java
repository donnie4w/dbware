package com.dbware.db;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @verion 1.0.4
 */
public enum ManagerEnum {
	HELP, KEYS, EXIST, MU, RM, INFO, CLEAR, QUIT, BYE, ERROR;

	public static ManagerEnum getManagerType(String s) {
		s = s.toUpperCase();
		if (HELP.name().equals(s)) {
			return HELP;
		} else if (KEYS.name().equals(s)) {
			return KEYS;
		} else if (EXIST.name().equals(s)) {
			return EXIST;
		} else if (MU.name().equals(s)) {
			return MU;
		} else if (RM.name().equals(s)) {
			return RM;
		} else if (CLEAR.name().equals(s)) {
			return CLEAR;
		} else if (INFO.name().equals(s)) {
			return INFO;
		} else if (QUIT.name().equals(s)) {
			return QUIT;
		} else if (BYE.name().equals(s)) {
			return BYE;
		} else {
			return ERROR;
		}
	}
}