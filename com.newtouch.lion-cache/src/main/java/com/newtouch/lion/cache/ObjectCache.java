/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: ObjectCache.java 9552 2013-4-7 下午2:00:51 WangLijun$
 */
package com.newtouch.lion.cache;

import java.io.Serializable;

/**
 * <p>
 * Title:缓存接口定义
 * </p>
 * <p>
 * Description:缓存接口定义，包括缓存获取、删除、存放
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
public interface ObjectCache<T> extends Serializable {
	
	
	
	/**获取缓存对象
	 *@param key
	 *@return T
	 */
	public T getObjectFromCache(Object key);
	/**存放缓存对象
	 * @param key
	 * @param obj
	 */
	public void putObjectInCache(Object key, T obj);
	/**删除缓存对象
	 */
	public void removeObjectFromCache(Object key);
}
