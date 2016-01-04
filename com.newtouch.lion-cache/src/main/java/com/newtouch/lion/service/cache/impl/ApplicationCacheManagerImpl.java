/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: ApplicationCacheManagerImpl.java 9552 2014-4-9 上午01:14:09 WangLijun$
 */
package com.newtouch.lion.service.cache.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Statistics;
import net.sf.ehcache.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.newtouch.lion.model.cache.CacheManagerModel;
import com.newtouch.lion.model.cache.CacheModel;
import com.newtouch.lion.service.cache.ApplicationCacheManager;

/**
 * <p>
 * Title: 应用缓存管理实现类
 * </p>
 * <p>
 * Description: 应用缓存管理实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Service
public class ApplicationCacheManagerImpl implements ApplicationCacheManager {

	private final Logger logger = LoggerFactory.getLogger(super.getClass());

	@Autowired
	private CacheManager cacheManager;

	public Status getCacheStatus() {
		return cacheManager.getStatus();
	}

	public CacheManagerModel getCacheManager() {
	 
		String[] cacheNames = cacheManager.getCacheNames();
		CacheManagerModel cacheManagerModel = new CacheManagerModel();
		cacheManagerModel.setName(cacheManager.getName());
		cacheManagerModel.setSize(cacheNames.length);
		cacheManagerModel.setStatusName(this.getCacheStatus().toString());
		cacheManagerModel.setStatusIntValue(this.getCacheStatus().intValue());

		return cacheManagerModel;
	}

	@SuppressWarnings({"static-access" })
	public CacheManagerModel getCaches() {
		String[] cacheNames = cacheManager.getCacheNames();
		CacheManager  cacheManagerShiro=cacheManager.getCacheManager("shirocache");
		String[] cacheShiroNames=cacheManagerShiro.getCacheNames();
		
		CacheManagerModel cacheManagerModel = new CacheManagerModel();
		cacheManagerModel.setName(cacheManager.getName());
		cacheManagerModel.setSize(cacheNames.length);
		cacheManagerModel.setStatusName(this.getCacheStatus().toString());
		cacheManagerModel.setStatusIntValue(this.getCacheStatus().intValue());
		 
		List<CacheModel> list = null;
		CacheModel cacheModel = null;
	
		for (String cacheName : cacheNames) {
		
			Cache cache = this.cacheManager.getCache(cacheName);

			if (cache == null) {
				logger.warn("This cache '{}' is null,Please analysis of log  and query reason",
						cacheName);
				continue;
			}

			if (CollectionUtils.isEmpty(list)) {
				list = new ArrayList<CacheModel>();
			}
			// 缓存对象
			cacheModel = new CacheModel();
			cacheModel.setName(cacheName);

			cacheModel.setSize(cache.getSize());
			cacheModel.setMemoryStoreSize(cache.calculateInMemorySize());
			// cacheModel.setMemoryStoreEvictionPolicy(cache.getMemoryStoreEvictionPolicy().getName());
			cacheModel.setDiskStoreSize((long) cache.getDiskStoreSize());
			Statistics statistics = cache.getStatistics();
			cacheModel.setInMemoryHits(statistics.getInMemoryHits());
			cacheModel.setCacheHits(statistics.getCacheHits());
			cacheModel.setOnDiskHits(statistics.getOnDiskHits());
		 
			list.add(cacheModel);
		}
		
		
		for (String cacheName : cacheShiroNames) {
			
			Cache cache = cacheManagerShiro.getCache(cacheName);

			if (cache == null) {
				logger.warn("This cache '{}' is null,Please analysis of log  and query reason",
						cacheName);
				continue;
			}

			if (CollectionUtils.isEmpty(list)) {
				list = new ArrayList<CacheModel>();
			}
			// 缓存对象
			cacheModel = new CacheModel();
			cacheModel.setName(cacheName);

			cacheModel.setSize(cache.getSize());
			cacheModel.setMemoryStoreSize(cache.calculateInMemorySize());
			cacheModel.setMemoryStoreEvictionPolicy(cache.getMemoryStoreEvictionPolicy().getName());
			cacheModel.setDiskStoreSize((long) cache.getDiskStoreSize());
			Statistics statistics = cache.getStatistics();
			cacheModel.setInMemoryHits(statistics.getInMemoryHits());
			cacheModel.setCacheHits(statistics.getCacheHits());
			cacheModel.setOnDiskHits(statistics.getOnDiskHits());
		 
			list.add(cacheModel);
		}
		cacheManagerModel.setCacheModels(list);
		return cacheManagerModel;
	}

}
