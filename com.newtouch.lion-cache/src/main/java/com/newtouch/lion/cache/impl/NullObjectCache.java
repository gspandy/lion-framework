/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: NullObjectCache.java 9552 2013-4-7 下午2:43:02 WangLijun$
 */
package com.newtouch.lion.cache.impl;

import com.newtouch.lion.cache.ObjectCache;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
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
public class NullObjectCache<T> implements ObjectCache<T> {

	/**
		 * 
		 */
	private static final long serialVersionUID = 2625934682789882187L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.honbang.framework.cache.ObjectCache#getObjectFromCache(java.lang.
	 * Object)
	 */
	@Override
	public T getObjectFromCache(Object key) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

}
