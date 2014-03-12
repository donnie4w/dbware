package com.dbware.db;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2013-3-7
 * @verion 1.0.3
 */
public interface ITask extends Serializable {
	public Object exector() throws RemoteException;
}
