package com.dbware.db;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2013-3-7
 * @verion 1.0.3
 */
public class CoordinatedStub extends UnicastRemoteObject implements Coordinated {

	private static final long serialVersionUID = -8536804201186941989L;

	public CoordinatedStub() throws RemoteException {
	}

	@Override
	public Object invoke(ITask task) throws RemoteException {
		return task.exector();
	}

}
