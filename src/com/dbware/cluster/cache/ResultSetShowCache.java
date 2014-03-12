package com.dbware.cluster.cache;

import java.lang.ref.SoftReference;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dbware.db.BasePacket;
import com.dbware.util.MD5;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-18
 * @verion 1.0
 */
public class ResultSetShowCache {
	private static Map<String, SoftReference<List<BasePacket>>> ResultSetShowCache = new ConcurrentHashMap<String, SoftReference<List<BasePacket>>>();

	public static List<BasePacket> get(String key) {
		SoftReference<List<BasePacket>> sr = ResultSetShowCache.get(MD5.getMD5(key));
		if (sr != null) {
			return sr.get();
		} else {
			return null;
		}
	}

	public static boolean remove(String key) {
		ResultSetShowCache.remove(MD5.getMD5(key));
		return false;
	}

	public static List<BasePacket> getAndSet(String key, List<BasePacket> value) {
		ResultSetShowCache.put(MD5.getMD5(key), new SoftReference<List<BasePacket>>(value));
		return value;
	}

}
