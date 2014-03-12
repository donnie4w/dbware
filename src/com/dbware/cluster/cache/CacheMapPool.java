package com.dbware.cluster.cache;

import java.lang.ref.SoftReference;
import java.util.List;

import com.dbware.db.BasePacket;
/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-18
 * @verion 1.0
 */
public abstract class CacheMapPool<K, V> {

	public abstract V get(String key);

	public abstract boolean remove(String key);

	public abstract void put(String key, SoftReference<List<BasePacket>> value);
}
