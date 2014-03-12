package com.dbware.db;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2013-3-7
 * @verion 1.0.3
 */
public class ConfBeen {
	final static String getProtocolInfo(HbBeen h) {
		return GlobalVar.getYcdyxy() + h.getHost() + "\u003A" + h.getPort() + "\u002F" + GlobalVar.getYcd();
	}

	final static String getProtocolInfo4s(String name) {
		return GlobalVar.getYckqxy() + name;
	}
}
