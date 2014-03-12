package com.dbware.log;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-7
 * @verion 1.0
 */
public abstract class LogFactory {
	public static Logger getLogger(Class<?> claz) {
		return new LoggerImpl(claz);
	}
}
