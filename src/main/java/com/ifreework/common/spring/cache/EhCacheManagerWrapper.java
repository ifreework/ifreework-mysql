/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ifreework.common.spring.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;

import java.util.Collection;
import java.util.Set;


/**
 * 
 * 描述：    缓存管理
 * @author：wangyh
 * @createDate：2017年4月30日
 * @modify：wangyh    
 * @modifyDate：2017年4月30日 
 * @version 1.0
 */
public class EhCacheManagerWrapper implements CacheManager {

    private EhCacheManager  cacheManager;

    /**
     * 设置spring cache manager
     *
     * @param cacheManager
     */
    public void setCacheManager(EhCacheManager  cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
    	Cache<K, V> cache = cacheManager.getCache(name);
        return new EhCacheWrapper<K, V>(cache);
    }

    
    
    
    static class EhCacheWrapper<K, V> implements Cache<K, V> {
    	private Cache<K, V> cache = null;

    	EhCacheWrapper(Cache<K, V> cache) {
            this.cache = cache;
        }

        @Override
        public V get(K key) throws CacheException {
            V value = cache.get(key);
            return value;
        }

        @Override
        public V put(K key, V value) throws CacheException {
        	cache.put(key, value);
            return value;
        }

        @Override
        public V remove(K key) throws CacheException {
            return cache.remove(key);
        }

        @Override
        public void clear() throws CacheException {
        	cache.clear();
        }

        @Override
        public int size() {
        	return cache.size();
        }

        @Override
        public Set<K> keys() {
        	return cache.keys();
        }

        @SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
        public Collection values() {
            return cache.values();
        }
    }
}
