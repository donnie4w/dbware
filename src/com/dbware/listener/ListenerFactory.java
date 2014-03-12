package com.dbware.listener;
/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-7
 * @verion 1.0
 */
public class ListenerFactory {
	public static Listener getListener() throws Exception {
		return new ServerListener();
	}
}
