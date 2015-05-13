package com.produban.cache;

import java.util.List;

import org.apache.commons.math3.util.Pair;

public interface Cache {

	/**
	 * Get a data from REDIS
	 * @param key to query in REDIS
	 * @return String with the value for a key
	 */
	public abstract String get(String key);

	/**
	 * Set a data in REDIS
	 * @param key for the new value
	 * @param value value for the key
	 */
	public abstract void set(String key, String value);
	
	/**
	 * BulkLoad
	 * @param values list with values
	 */
	public abstract void setBulkLoad(List<Pair<String,String>> values);

}