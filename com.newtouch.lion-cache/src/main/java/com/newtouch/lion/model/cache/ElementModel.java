/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: ElementModel.java 9552 2014-4-12 上午10:28:22 WangLijun$
 */
package com.newtouch.lion.model.cache;

import java.io.Serializable;

/**
 * <p>
 * Title: 缓存对象明细
 * </p>
 * <p>
 * Description: 缓存对象明细
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
public class ElementModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7633741058203962210L;
	/** 缓存对象－key */
	private String key;
	/** 缓存对象值 */
	private Object value;
	/** 缓存对象创建时间 */
	private String creationTime;
	/** 缓存对象最后访问时间 */
	private String lastAccessTime;
	/** 缓存对象过期时间 */
	private String expirationTime;
	/** 命中次数 */
	private Long hitCount;
	/** 存活时间(秒) */
	private Long timeToLive;
	/** 空闲时间(秒) */
	private Long timeToIdle;

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * @return the creationTime
	 */
	public String getCreationTime() {
		return creationTime;
	}

	/**
	 * @param creationTime
	 *            the creationTime to set
	 */
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	/**
	 * @return the lastAccessTime
	 */
	public String getLastAccessTime() {
		return lastAccessTime;
	}

	/**
	 * @param lastAccessTime
	 *            the lastAccessTime to set
	 */
	public void setLastAccessTime(String lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	/**
	 * @return the expirationTime
	 */
	public String getExpirationTime() {
		return expirationTime;
	}

	/**
	 * @param expirationTime
	 *            the expirationTime to set
	 */
	public void setExpirationTime(String expirationTime) {
		this.expirationTime = expirationTime;
	}

	/**
	 * @return the hitCount
	 */
	public Long getHitCount() {
		return hitCount;
	}

	/**
	 * @param hitCount
	 *            the hitCount to set
	 */
	public void setHitCount(Long hitCount) {
		this.hitCount = hitCount;
	}

	/**
	 * @return the timeToLive
	 */
	public Long getTimeToLive() {
		return timeToLive;
	}

	/**
	 * @param timeToLive
	 *            the timeToLive to set
	 */
	public void setTimeToLive(Long timeToLive) {
		this.timeToLive = timeToLive;
	}

	/**
	 * @return the timeToIdle
	 */
	public Long getTimeToIdle() {
		return timeToIdle;
	}

	/**
	 * @param timeToIdle
	 *            the timeToIdle to set
	 */
	public void setTimeToIdle(Long timeToIdle) {
		this.timeToIdle = timeToIdle;
	}
}
