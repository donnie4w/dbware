package com.dbware.db;

import java.util.List;

import com.dbware.cluster.cache.ResultSetCache;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2013-7-7
 * @verion 1.0.4
 */
public class TaskExceSyncCache implements ITask {

	private static final long serialVersionUID = 4177691228114344974L;

	List<String> list = null;

	public TaskExceSyncCache(List<String> l) {
		this.list = l;
	}

	public TaskExceSyncCache() {
	}

	@Override
	public Object exector() {
		if (list != null)
			return ResultSetCache.remove(list);
		else
			return clear();
	}

	Object remove(List<String> list) {
		return ResultSetCache.remove(list);
	}

	Object clear() {
		return ResultSetCache.clear();
	}
}
