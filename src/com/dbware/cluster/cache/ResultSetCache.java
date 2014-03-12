package com.dbware.cluster.cache;

import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import com.dbware.db.BasePacket;
import com.dbware.db.DistribExce;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @verion 1.0.4
 */
public class ResultSetCache {
	private static Map<String, SoftReference<List<BasePacket>>> ResultSetCache = new ConcurrentHashMap<String, SoftReference<List<BasePacket>>>();

	private ResultSetCache() {
	}

	public static void put(String k, List<BasePacket> v) {
		ResultSetCache.put(k, new SoftReference<List<BasePacket>>(v));
	}

	public static List<BasePacket> get(String key) {
		SoftReference<List<BasePacket>> sr = ResultSetCache.get(key);
		if (sr != null) {
			return sr.get();
		} else {
			return null;
		}
	}

	public static boolean containsKey(String k) {
		return ResultSetCache.containsKey(k);
	}

	public static String containsListKey(List<String> list) {
		if (list != null)
			for (String s : list) {
				if (containsKey(s)) {
					return s;
				}
			}
		return null;
	}

	public static void putList(List<String> keyList, List<BasePacket> v) {
		if (keyList != null && v != null) {
			if (keyList.size() == 1) {
				put(keyList.get(0), v);
			} else {
				for (String s : keyList) {
					put(s, v);
				}
			}
		}
	}

	public static int mu(String k) {
		int i = 0;
		if (containsKey(k)) {
			List<BasePacket> list = ResultSetCache.get(k).get();
			if (list != null) {
				for (BasePacket bp : list) {
					i += bp.toBytes().length;
				}
			}
		}
		return i;
	}

	public static boolean remove(String key) {
		ResultSetCache.remove(key);
		return true;
	}

	public static boolean remove(List<String> list) {
		if (list != null && list.size() > 0) {
			for (String s : list) {
				remove(s);
			}
		}
		return true;
	}

	public static boolean remove(String... strings) {
		if (strings != null)
			for (String s : strings) {
				remove(s);
			}
		return true;
	}

	public static boolean clear() {
		try {
			ResultSetCache.clear();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void SyncRemoveList(List<String> list) {
		remove(list);
		DistribExce.exeuteAsync(list);
	}
	
	public static void SyncRemove(String key) {
		List<String> list = Arrays.asList(key);
		remove(list);
		DistribExce.exeuteAsync(list);
	}


	public static void SyncClear() {
		clear();
		DistribExce.exeuteAsync(null);
	}

	public static List<BasePacket> getAndSet(String key, List<BasePacket> value) {
		put(key, value);
		return value;
	}

	public static List<BasePacket> getAndSet(List<String> list, List<BasePacket> value) {
		if (list != null && value != null) {
			if (list.size() == 1) {
				return getAndSet(list.get(0), value);
			} else {
				for (String s : list) {
					put(s, value);
				}
				return value;
			}
		}
		return value;
	}

	public static Set<String> getKeys() {
		return ResultSetCache.keySet();
	}
}