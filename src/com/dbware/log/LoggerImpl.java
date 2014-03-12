package com.dbware.log;

import org.apache.log4j.Logger;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-7
 * @verion 1.0
 */
public class LoggerImpl implements com.dbware.log.Logger {
	private Logger logger4j;

	public LoggerImpl(Class<?> claz) {
		logger4j = Logger.getLogger(claz);
	}

	public void debug(Object message) {
		if (logger4j.isDebugEnabled()) {
			logger4j.debug(message);
		}
	}

	public void debug(String s, Throwable t) {
		if (logger4j.isDebugEnabled()) {
			logger4j.debug(s, t);
		}
	}

	public void info(Object message) {
		if (logger4j.isInfoEnabled()) {
			logger4j.info(message);
		}
	}

	public void info(Object message, Throwable t) {
		if (logger4j.isInfoEnabled()) {
			logger4j.info(message, t);
		}
	}

	public void warn(Object message) {
		logger4j.warn(message);
	}

	public void warn(Object message, Throwable t) {
		logger4j.warn(message, t);
	}

	public void error(Object message) {
		logger4j.error(message);
	}

	public void error(Object message, Throwable t) {
		logger4j.error(message, t);
	}

	public void fatal(Object message) {
		logger4j.fatal(message);
	}

	public void fatal(Object message, Throwable t) {
		logger4j.fatal(message, t);
	}
}
