package com.produban.cache.redis;

import java.util.List;




import org.apache.commons.math3.util.Pair;

import com.produban.cache.Cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;


/**
 * Class to get and set data in REDIS.
 * 
 * @author ortizg1
 *
 */
public class RedisCache implements Cache {

	private JedisPool jedisPool;

	public RedisCache() {
		super();		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.produban.cache.redis.Cache#get(java.lang.String)
	 */
	@Override
	public String get(final String key) {
		Jedis redis = null;
		try {
			redis = jedisPool.getResource();
			return redis.get(key);
		} catch (JedisConnectionException e) {
			if (redis != null) {
				jedisPool.returnBrokenResource(redis);
				redis = null;
			}
			throw e;
		} finally {
			if (redis != null) {
				jedisPool.returnResource(redis);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.produban.cache.redis.Cache#set(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void set(final String key, final String value) {
		Jedis redis = null;
		try {
			redis = jedisPool.getResource();
			redis.set(key, value);
		} catch (JedisConnectionException e) {
			if (redis != null) {
				jedisPool.returnBrokenResource(redis);
				redis = null;
			}
			throw e;
		} finally {
			if (redis != null) {
				jedisPool.returnResource(redis);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.produban.cache.redis.Cache#set(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void setBulkLoad(final List<Pair<String, String>> values) {
		Jedis redis = null;
		try {
			redis = jedisPool.getResource();

			for (Pair<String, String> pair : values) {
				redis.set(pair.getKey(), pair.getValue());
			}

		} catch (JedisConnectionException e) {
			if (redis != null) {
				jedisPool.returnBrokenResource(redis);
				redis = null;
			}
			throw e;
		} finally {
			if (redis != null) {
				jedisPool.returnResource(redis);
			}
		}
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

}