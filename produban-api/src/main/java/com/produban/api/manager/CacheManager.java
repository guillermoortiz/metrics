package com.produban.api.manager;

import java.util.List;

import org.apache.commons.math3.util.Pair;


/**
 * Interface with the basic operations for cache.
 * @author ortizg1
 *
 */
public interface CacheManager {
	public String get(final String key, final String table);
	public String get(final String key);
	public void set(final String key, final String value);
	public void setBulkLoad(final List<Pair<String, String>> values);
}
