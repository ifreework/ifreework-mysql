package com.ifreework.common.redis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ifreework.util.SerializeUtils;

import redis.clients.jedis.Jedis;

class RedisCache<K, V> implements Cache<K, V>, Serializable {

	private static Logger logger = LoggerFactory.getLogger(RedisCache.class);

	private static final long serialVersionUID = -3239675522496957694L;

	private RedisManager redisManager;
	private String redisKey;

	public RedisCache(RedisManager redisManager, String redisKey) {
		super();
		this.redisManager = redisManager;
		this.redisKey = redisKey;
	}

	public String getRedisKey() {
		return redisKey;
	}

	/**
	 * 描述：通过key获取cache中的对象
	 * @param key 键
	 * @return   value 值，如果key == null ,则返回null
	 */
	@SuppressWarnings("unchecked")
	@Override
	public V get(K key) {
		V value;
		if (key == null) {
			logger.debug("{} get value has the key null!", redisKey);
			return null;
		} else {
			Jedis jedis = redisManager.getJedis();
			try {
				byte[] bytes = jedis.hget(getByte(redisKey), SerializeUtils.serialize(key));
				value = (V) SerializeUtils.deserialize(bytes);

				logger.debug("{} get key: {} , value : {}!", redisKey, key, value);
			} finally {
				jedis.close();
			}
			return value;
		}

	}

	/**
	 * 描述：通过key:value形式存储值
	 * @param key 键
	 * @param value 值
	 * @return   
	 */
	@Override
	public V put(K key, V value) {
		Jedis jedis = redisManager.getJedis();
		try {
			jedis.hset(getByte(redisKey), SerializeUtils.serialize(key), SerializeUtils.serialize(value));

			logger.debug("{} put key : {} , value : {}", redisKey, key, value);
		} finally {
			jedis.close();
		}
		return value;
	}

	/**
	 * 
	 * 描述：通过key删除元素
	 * @param key 键
	 * @return   
	 */
	@Override
	public V remove(K key) throws CacheException {
		V value;
		Jedis jedis = redisManager.getJedis();
		try {
			value = get(key);
			jedis.hdel(getByte(redisKey), SerializeUtils.serialize(key));

			logger.debug("{} remove key : {} , value : {}!", redisKey, key, value);
		} finally {
			jedis.close();
		}

		return value;
	}

	/**
	 * 描述：清楚缓存中所有元素
	 */
	@Override
	public void clear() throws CacheException {
		Jedis jedis = redisManager.getJedis();
		try {
			Set<byte[]> keys = jedis.hkeys(getByte(redisKey));
			for (byte[] key : keys) {
				jedis.hdel(getByte(redisKey), key);
			}
			logger.debug("{} is cleared!", redisKey);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 
	 * 描述：获取缓存中所有对象的数量
	 * @return  int
	 */
	@Override
	public int size() {
		int size;
		Jedis jedis = redisManager.getJedis();
		try {
			size = jedis.hlen(getByte(redisKey)).intValue();
		} finally {
			jedis.close();
		}

		return size;
	}

	/**
	 * 描述：获取缓存所有的key
	 * @param 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<K> keys() {
		Jedis jedis = redisManager.getJedis();
		Set<K> keys;
		try {
			Set<byte[]> byteKeys = jedis.hkeys(getByte(redisKey));
			keys = new HashSet<K>();
			for (byte[] b : byteKeys) {
				K k = (K) SerializeUtils.deserialize(b);
				keys.add(k);
			}
		} finally {
			jedis.close();
		}

		return keys;
	}

	/**
	 * 描述：获取缓存所有的value
	 * @param 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<V> values() {
		Collection<V> values;
		Jedis jedis = redisManager.getJedis();
		try {
			List<byte[]> byteKeys = jedis.hvals(getByte(redisKey));
			values = new ArrayList<V>();
			for (byte[] b : byteKeys) {
				V v = (V) SerializeUtils.deserialize(b);
				values.add(v);
			}
		} finally {
			jedis.close();
		}
		
		return values;
	}

	/**
	 * 获得byte[]型的key
	 * @param key
	 * @return
	 */
	private byte[] getByte(Object obj) {
		if (obj instanceof String) {
			return ((String) obj).getBytes();
		} else {
			return SerializeUtils.serialize(obj);
		}
	}

}
