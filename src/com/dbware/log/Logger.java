package com.dbware.log;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-7
 * @verion 1.0
 */

public interface Logger {
	public void debug(Object message);

	public void debug(String s, Throwable t);

	public void info(Object message);

	public void info(Object message, Throwable t);

	public void warn(Object message);

	public void warn(Object message, Throwable t);

	public void error(Object message);

	public void error(Object message, Throwable t);

	public void fatal(Object message);

	public void fatal(Object message, Throwable t);
}
