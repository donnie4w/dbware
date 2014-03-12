package com.dbware.db;

import java.io.Serializable;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2013-3-7
 * @verion 1.0.3
 */
public interface MsgI extends Serializable {
	public String getGroupName();

	public String getMsg();

	public void setGroupName(String groupName);

	public void setMsg(String msg);

	public Object getObject();

	public void setObject(Object obj);

}
