package com.dbware.main;

import com.dbware.db.cfg.DBwareConfigXml;
import com.dbware.listener.ListenerFactory;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-07
 * @verion 1.0
 */
public class StartDbware {
	public static void main(String[] args) {
		System.out.println();
		System.out.println("\t\t================================");
		System.out.println("\t\t========= dbware 1.0.4 =========");
		System.out.println("\t\t================================");
		try {
			ListenerFactory.getListener().listener();
		} catch (Exception e) {
			System.out.println("error | start dbware failed! |" + e.getMessage());
			System.exit(0);
		}
		System.out.println("\t\t dbware start successful! [" + DBwareConfigXml.getPort() + "]");
	}
}
