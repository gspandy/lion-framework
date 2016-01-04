/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: EhCacheBasedObjectCache.java 9552 2013-4-7 下午2:18:58 WangLijun$
 */
package com.newtouch.lion.cache.impl;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataRetrievalFailureException;

import com.newtouch.lion.cache.ObjectCache;
import com.newtouch.lion.common.Assert;

/**
 * <p>
 * Title:缓存管理
 * </p>
 * <p>
 * Description:缓存管理
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class EhCacheBasedObjectCache<T> implements InitializingBean,ObjectCache<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1483784519338185923L;

	protected final Logger log = LoggerFactory.getLogger(super.getClass());

	private Ehcache cache = null;

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.cache, "cache mandatory");
	}

	/**
	 * @return the cache
	 */
	public Ehcache getCache() {
		return cache;
	}

	/**
	 * @param cache
	 *            the cache to set
	 */
	public void setCache(Ehcache cache) {
		this.cache = cache;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.honbang.framework.cache.ObjectCache#getObjectFromCache(java.lang.
	 * Object)
	 */
	@SuppressWarnings({ "unchecked"})
	@Override
	public T getObjectFromCache(Object key) {
		Element element = null;
		try {
			element = this.cache.get(key);
		} catch (CacheException cacheException) {
			throw new DataRetrievalFailureException("Cache failure: "
					+ cacheException.getMessage());
		}

		if (log.isDebugEnabled()) {
			log.debug("Cache hit: " + (element != null) + "; key: " + key);
		}

		if (element == null) {
			return null;
		}
		return (T) element.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.honbang.framework.cache.ObjectCache#putObjectInCache(java.lang.Object
	 * , java.lang.Object)
	 */
 
	@Override
	public void putObjectInCache(Object key, T obj) {
		Element element = new Element(key,obj);

		if (log.isDebugEnabled()) {
			log.debug("Cache put: " + element.getKey());
		}

		this.cache.put(element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.honbang.framework.cache.ObjectCache#removeObjectFromCache(java.lang
	 * .Object)
	 */
	@Override
	public void removeObjectFromCache(Object key) {
		if (log.isDebugEnabled()) {
			log.debug("Cache remove: " + key);
		}
		this.cache.remove(key);
	}
}
