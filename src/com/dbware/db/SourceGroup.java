package com.dbware.db;

import java.util.HashMap;
import java.util.Map;
/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2013-3-7
 * @verion 1.0.3
 */
public class SourceGroup {
	public final static Map<String, XGroup> groupMap = new HashMap<String, XGroup>();

	public static XGroup getXGroup(String groupName) {
		return groupMap.get(groupName);
	}

	public static void addXGroup(String groupName, XGroup group) {
		groupMap.put(groupName, group);
	}

}