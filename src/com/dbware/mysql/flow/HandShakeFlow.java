package com.dbware.mysql.flow;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-7
 * @verion 1.0 HandShakeFlow
 */
public enum HandShakeFlow {
	INIT, // server initial
	AUTH, // client user and password
	OKPACKET, // server to the client to signal successful completion of a command
	COMMAND, // enter command phase
	CLOSE;// connect close
}
