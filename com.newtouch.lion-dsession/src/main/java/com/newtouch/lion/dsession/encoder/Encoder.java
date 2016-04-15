/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: Encoder.java 9552 2015年5月21日 下午2:12:17 WangLijun$
*/
package com.newtouch.lion.dsession.encoder; 
/**
 * <p>
 * Title: 编码和解析定义
 * </p>
 * <p>
 * Description: 编码和解析定义
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
public interface Encoder {
	/***
	 * 对key进行编码
	 * @param key 编码
	 * @return String
	 */
	public String encodeName(String key);
	
	/***
	 * 对key进行解码
	 * @param name
	 * @return
	 */
	public String decodeName(String key);
	
	/***
	 * 对value进行编码
	 * @param value Object
	 * @return
	 */
	public Object encodeValue(Object value);
	/***
	 * 对value进行解码
	 * @param value Object
	 * @return
	 */
	public Object decodeValue(Object value);
}

	