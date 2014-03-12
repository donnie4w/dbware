package com.dbware.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ContextFactory {
	static Context c = null;
	static {
		System.setProperty(GlobalVar.getToresp(), GlobalVar.getToyc());
		System.setProperty(GlobalVar.getToread(), GlobalVar.getToyc());
		try {
			c = new InitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static Context getInstance() {
		return c;
	}
}
