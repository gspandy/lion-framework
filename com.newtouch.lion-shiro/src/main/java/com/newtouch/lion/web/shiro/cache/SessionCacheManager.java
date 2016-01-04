/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: SessionControlCache.java 9552 2015年2月27日 下午8:36:52 WangLijun$
*/
package com.newtouch.lion.web.shiro.cache; 

import java.io.Serializable;
import java.util.Deque;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

/**
 * <p>
 * Title:Session管理器
 * </p>
 * <p>
 * Description: Session管理器
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
public class SessionCacheManager {
	/**Shiro 缓存管理*/
	private CacheManager cacheManager;
	/***
	 * 默认构造函数	
	 */
	public SessionCacheManager() {
	    super();
	}
	
	/**
	 * @return the cacheManager
	 */
	public CacheManager getCacheManager() {
		return cacheManager;
	}

	/**
	 * @param cacheManager the cacheManager to set
	 */
	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	
	 /***
	  * 删除并发控制的缓存对象
	  * @param cacheName
	  * @param key
	  */
	public void  removeSessionController(String cacheName,String key){
		Cache<String, Deque<Serializable>>  cache=this.getCacheManager().getCache(cacheName);
		cache.remove(key);
	}
}

	