/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: DefaultEncoder.java 9552 2015年5月21日 下午2:20:49 WangLijun$
*/
package com.newtouch.lion.dsession.encoder; 
/**
 * <p>
 * Title: 
 * </p>
 * <p>
 * Description: 
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
public class DefaultEncoder implements Encoder {

	/* (non-Javadoc)
	 * @see com.newtouch.lion.dsession.encoder.Encoder#encodeName(java.lang.String)
	 */
	@Override
	public String encodeName(String key) {
		return key;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.dsession.encoder.Encoder#decodeName(java.lang.String)
	 */
	@Override
	public String decodeName(String key) {
	 
		return key;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.dsession.encoder.Encoder#encodeValue(java.lang.String)
	 */
	@Override
	public Object encodeValue(Object value) {
	 
		return value;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.dsession.encoder.Encoder#decodeValue(java.lang.String)
	 */
	@Override
	public Object decodeValue(Object value) {
		return value;
	}

}

	