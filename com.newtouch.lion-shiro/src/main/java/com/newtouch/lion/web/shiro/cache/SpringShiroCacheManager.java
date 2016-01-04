/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: SpringShiroCacheManager.java 9552 2015年3月4日 下午1:43:10 WangLijun$
*/
package com.newtouch.lion.web.shiro.cache; 

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;

/**
 * <p>
 * Title: Spring Shiro  cache抽象
 * </p>
 * <p>
 * Description: Spring Shiro  cache抽象
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class SpringShiroCacheManager  implements CacheManager{
	
	/***
	 * Spring CacheManager
	 */
	private org.springframework.cache.CacheManager cacheManager;


    /**
     * 设置spring cache manager
     *
     * @param cacheManager
     */
    public void setCacheManager(org.springframework.cache.CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
    
	@SuppressWarnings("unchecked")
	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		org.springframework.cache.Cache cache=cacheManager.getCache(name);
		return new SpringShiroCache(cache);
	}
	
	@SuppressWarnings("rawtypes")
	static class SpringShiroCache implements Cache {
        private org.springframework.cache.Cache springCache;

        SpringShiroCache(org.springframework.cache.Cache springCache) {
            this.springCache = springCache;
        }

        @Override
        public Object get(Object key) throws CacheException {
            Object value = springCache.get(key);
            if (value instanceof SimpleValueWrapper) {
                return ((SimpleValueWrapper) value).get();
            }
            return value;
        }

        @Override
        public Object put(Object key, Object value) throws CacheException {
            springCache.put(key, value);
            return value;
        }

        @Override
        public Object remove(Object key) throws CacheException {
            springCache.evict(key);
            return null;
        }

        @Override
        public void clear() throws CacheException {
            springCache.clear();
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException("invoke spring cache abstract size method not supported");
        }


		@Override
        public Set keys() {
            throw new UnsupportedOperationException("invoke spring cache abstract keys method not supported");
        }
		@Override
        public Collection values() {
            throw new UnsupportedOperationException("invoke spring cache abstract values method not supported");
        }
    }
}

	