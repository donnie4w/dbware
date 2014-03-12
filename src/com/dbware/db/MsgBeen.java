package com.dbware.db;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2013-3-7
 * @verion 1.0.3
 */
public class MsgBeen implements MsgI {

	private static final long serialVersionUID = -2938557969715539589L;
	private String groupName;
	private String msg;
	private Object obj;

	public String getMsg() {
		return msg;
	}

	@Override
	public String getGroupName() {
		return groupName;
	}

	@Override
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Override
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public Object getObject() {
		return obj;
	}

	@Override
	public void setObject(Object obj) {
		this.obj = obj;
	}

}
