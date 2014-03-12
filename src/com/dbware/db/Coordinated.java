package com.dbware.db;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2013-3-7
 * @verion 1.0.3
 */
public interface Coordinated extends Remote {
	public Object invoke(ITask task) throws RemoteException;
}
