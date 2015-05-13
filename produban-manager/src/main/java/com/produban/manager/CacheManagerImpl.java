package com.produban.manager;

import java.util.List;

import org.apache.commons.math3.util.Pair;

import com.produban.api.general.Factory;
import com.produban.api.manager.CacheManager;
import com.produban.cache.Cache;

public class CacheManagerImpl implements CacheManager {
	Cache cache;

	@Override
	public String get(String key, String prefix) {
		return cache.get(prefix + key);
	}

	@Override
	public String get(String key) {
		return cache.get(key);
	}

	@Override
	public void set(String key, String value) {
		cache.set(key, value);
	}

	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	@Override
	public void setBulkLoad(List<Pair<String, String>> values) {
		cache.setBulkLoad(values);

	}
	
	public static void main(String[] args) {
		//Factory.getA();
		Factory.getCacheManager().set("aa","bb");
		System.out.println(Factory.getCacheManager().get("aa"));
	}
}
