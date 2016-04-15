/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: SessionAttribute.java 9552 2015年6月11日 下午3:24:55 WangLijun$
 */
package com.newtouch.lion.dsession.model;

import com.newtouch.lion.dsession.DistributedHttpSession;
import com.newtouch.lion.dsession.encoder.Encoder;
import com.newtouch.lion.dsession.store.DistributedSessionStore;

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
public class SessionAttribute {
	/**
	 * store
	 */
	private DistributedSessionStore distributedSessionStore;

	/**
	 * encoder
	 */
	private Encoder encoder;

	/**
	 * session impl
	 */
	private DistributedHttpSession distributedHttpSession;

	/**
	 * key
	 */
	private String key;

	/**
	 * value
	 */
	private Object value;

	/**
	 * loaded
	 */
	@SuppressWarnings("unused")
	private boolean loaded = false;

	/**
	 * 创建一个attribute。
	 * 
	 * @param key
	 *            key
	 * @param session
	 *            session
	 * @param store
	 *            store
	 * @param encoder
	 *            encoder
	 */
	public SessionAttribute(String key, DistributedHttpSession distributedHttpSession,
			DistributedSessionStore distributedSessionStore, Encoder encoder) {
		this.key = key;
		this.distributedHttpSession = distributedHttpSession;
		this.distributedSessionStore = distributedSessionStore;
		this.encoder = encoder;
	}

	/**
	 * 取得attribute的名字。
	 * 
	 * @return attribute的名字
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 取得attribute的值。
	 * 
	 * @return attribute的值
	 */
	public Object getValue() {
		Object value = this.distributedSessionStore.getAttribute(this.distributedHttpSession.getDistributedSessionContext(),getKey());
		if (encoder != null) {
			this.value = encoder.decodeValue(value);
		}
		return this.value;
	}

	/**
	 * 设置attribute的值。
	 * <p>
	 * 当值为<code>null</code>时，表示该属性将被删除。
	 * </p>
	 * 
	 * @param value
	 *            attribute的值
	 */
	public void setValue(Object value) {
		if (value == null) {
			distributedSessionStore.removeAttribute(distributedHttpSession.getDistributedSessionContext(),key);
		} else {
			distributedSessionStore.setAttribute(distributedHttpSession.getDistributedSessionContext(), key, value);
		}
	}
}
