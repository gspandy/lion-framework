/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: CacheManagerModel.java 9552 2014-4-12 上午10:45:23 WangLijun$
 */
package com.newtouch.lion.model.cache;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Title:缓存管理对象
 * </p>
 * <p>
 * Description:
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
public class CacheManagerModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3800193739664208505L;
	/** 缓存管理对象名称 */
	private String name;
	/** 总的缓存对象数量 */
	private int size;
	/** 缓存状态－Int值 */
	private int statusIntValue;
	/** 缓存状状态-名称 */
	private String statusName;
	/** 缓存对象统计信息 */
	private List<CacheModel> cacheModels;

	/** defualt Constructor */
	public CacheManagerModel() {
		super();
	}

	/**
	 * @param size
	 * @param statusIntValue
	 * @param statusName
	 * @param cacheModels
	 */
	public CacheManagerModel(int size, int statusIntValue, String statusName,
			List<CacheModel> cacheModels) {
		this.size = size;
		this.statusIntValue = statusIntValue;
		this.statusName = statusName;
		this.cacheModels = cacheModels;
	}

	/**
	 * @param name
	 * @param size
	 * @param statusIntValue
	 * @param statusName
	 * @param cacheModels
	 */
	public CacheManagerModel(String name, int size, int statusIntValue,
			String statusName, List<CacheModel> cacheModels) {
		this.name = name;
		this.size = size;
		this.statusIntValue = statusIntValue;
		this.statusName = statusName;
		this.cacheModels = cacheModels;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the statusIntValue
	 */
	public int getStatusIntValue() {
		return statusIntValue;
	}

	/**
	 * @param statusIntValue
	 *            the statusIntValue to set
	 */
	public void setStatusIntValue(int statusIntValue) {
		this.statusIntValue = statusIntValue;
	}

	/**
	 * @return the statusName
	 */
	public String getStatusName() {
		return statusName;
	}

	/**
	 * @param statusName
	 *            the statusName to set
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	/**
	 * @return the cacheModels
	 */
	public List<CacheModel> getCacheModels() {
		return cacheModels;
	}

	/**
	 * @param cacheModels
	 *            the cacheModels to set
	 */
	public void setCacheModels(List<CacheModel> cacheModels) {
		this.cacheModels = cacheModels;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
