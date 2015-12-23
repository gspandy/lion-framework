/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: MessageResource.java 9552 2013-4-7 上午11:28:50 WangLijun$
 */
package com.newtouch.lion.common.resource;

/**
 * <p>
 * Title:消息接口类
 * </p>
 * <p>
 * Description:消息接口类
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
public interface MessageResource {
	/**根据KEY获取消息接口类
	 * @param code 消息KEY
	 * @return String 消息
	 * */
	public  String getMessage(String code);
	/**根据KEY获取消息，并参数插入到消息中
	 * @param code 消息的KEY
	 * @objs 参数数组
	 * @return String 消息
	 * */
	public  String getMessage(String code, Object[] objs);
}
