/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ApplicationCacheManager.java 9552 2015年1月7日 下午1:51:05 WangLijun$
*/
package com.newtouch.lion.service.cache; 

import net.sf.ehcache.Status;

import com.newtouch.lion.model.cache.CacheManagerModel;

import java.util.List;

/**
 * <p>
 * Title: 应用缓存管理类
 * </p>
 * <p>
 * Description: 应用缓存管理类
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
public interface ApplicationCacheManager {
	/***
	 * 定义ShiroCache的名称
	 */
	public static final String SHIRO_CAHCE="shirocache";

	/***获取缓存状态*/
	public  Status  getCacheStatus();
	/**缓存对象*/
	public CacheManagerModel getCaches();
	/**缓存管理对象*/
	public CacheManagerModel getCacheManager();

	/***
	 * 查询缓存列表信息
	 * @return
     */
	public List<CacheManagerModel> findCacheMangers();
}

	