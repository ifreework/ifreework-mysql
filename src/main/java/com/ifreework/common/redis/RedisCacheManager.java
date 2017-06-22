package com.ifreework.common.redis;


import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

public class RedisCacheManager<K, V> implements CacheManager {


	private RedisManager redisManager;

	public RedisManager getRedisManager() {
		return redisManager;
	}
	

	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Cache getCache(String name) throws CacheException {
		return new RedisCache(redisManager, name);
	}

}
