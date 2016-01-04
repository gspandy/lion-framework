/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: CacheModel.java 9552 2014-4-12 上午10:17:28 WangLijun$
 */
package com.newtouch.lion.model.cache;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Title: 缓存对象
 * </p>
 * <p>
 * Description: 缓存定义名称、总数量、内存数量大小、磁盘数量、缓存对象信息、统计信息、命中率
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
public class CacheModel implements Serializable {

	private static final long serialVersionUID = -1738586242652155973L;
	/** 缓存名称 */
	private String name;
	/** 总数量大小 */
	private int size;
	/** 内存数量大小 */
	private Long memoryStoreSize;
	/** 磁盘数量 */
	private Long diskStoreSize;
	/** 缓存对象－值对象（统计信息） */
	private List<ElementModel> elementModels;
	/** 缓存对象统计信息－命中次数 */
	private Long cacheHits;
	/** 缓存对象统计信息－内存命中次数 */
	private Long inMemoryHits;
	/** 缓存对象统计信息－磁盘命中次数 */
	private Long onDiskHits;
	/** 缓存策略 */
	private String memoryStoreEvictionPolicy;

	/**
	 * 
	 */
	public CacheModel() {
		super();
	}

	/**
	 * @param name
	 * @param size
	 * @param memoryStoreSize
	 * @param diskStoreSize
	 * @param elementModels
	 * @param cacheHits
	 * @param inMemoryHits
	 * @param onDiskHits
	 */
	public CacheModel(String name, int size, Long memoryStoreSize,
			Long diskStoreSize, List<ElementModel> elementModels,
			Long cacheHits, Long inMemoryHits, Long onDiskHits) {
		this.name = name;
		this.size = size;
		this.memoryStoreSize = memoryStoreSize;
		this.diskStoreSize = diskStoreSize;
		this.elementModels = elementModels;
		this.cacheHits = cacheHits;
		this.inMemoryHits = inMemoryHits;
		this.onDiskHits = onDiskHits;
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
	 * @return the memoryStoreSize
	 */
	public Long getMemoryStoreSize() {
		return memoryStoreSize;
	}

	/**
	 * @param memoryStoreSize
	 *            the memoryStoreSize to set
	 */
	public void setMemoryStoreSize(Long memoryStoreSize) {
		this.memoryStoreSize = memoryStoreSize;
	}

	/**
	 * @return the diskStoreSize
	 */
	public Long getDiskStoreSize() {
		return diskStoreSize;
	}

	/**
	 * @param diskStoreSize
	 *            the diskStoreSize to set
	 */
	public void setDiskStoreSize(Long diskStoreSize) {
		this.diskStoreSize = diskStoreSize;
	}

	/**
	 * @return the elementModels
	 */
	public List<ElementModel> getElementModels() {
		return elementModels;
	}

	/**
	 * @param elementModels
	 *            the elementModels to set
	 */
	public void setElementModels(List<ElementModel> elementModels) {
		this.elementModels = elementModels;
	}

	/**
	 * @return the cacheHits
	 */
	public Long getCacheHits() {
		return cacheHits;
	}

	/**
	 * @param cacheHits
	 *            the cacheHits to set
	 */
	public void setCacheHits(Long cacheHits) {
		this.cacheHits = cacheHits;
	}

	/**
	 * @return the inMemoryHits
	 */
	public Long getInMemoryHits() {
		return inMemoryHits;
	}

	/**
	 * @param inMemoryHits
	 *            the inMemoryHits to set
	 */
	public void setInMemoryHits(Long inMemoryHits) {
		this.inMemoryHits = inMemoryHits;
	}

	/**
	 * @return the onDiskHits
	 */
	public Long getOnDiskHits() {
		return onDiskHits;
	}

	/**
	 * @param onDiskHits
	 *            the onDiskHits to set
	 */
	public void setOnDiskHits(Long onDiskHits) {
		this.onDiskHits = onDiskHits;
	}

	/**
	 * @return the memoryStoreEvictionPolicy
	 */
	public String getMemoryStoreEvictionPolicy() {
		return memoryStoreEvictionPolicy;
	}

	/**
	 * @param memoryStoreEvictionPolicy
	 *            the memoryStoreEvictionPolicy to set
	 */
	public void setMemoryStoreEvictionPolicy(String memoryStoreEvictionPolicy) {
		this.memoryStoreEvictionPolicy = memoryStoreEvictionPolicy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CacheModel [name=" + name + ", size=" + size
				+ ", memoryStoreSize=" + memoryStoreSize + ", diskStoreSize="
				+ diskStoreSize + ", elementModels=" + elementModels
				+ ", cacheHits=" + cacheHits + ", inMemoryHits=" + inMemoryHits
				+ ", onDiskHits=" + onDiskHits + ", memoryStoreEvictionPolicy="
				+ memoryStoreEvictionPolicy + "]";
	}

}
