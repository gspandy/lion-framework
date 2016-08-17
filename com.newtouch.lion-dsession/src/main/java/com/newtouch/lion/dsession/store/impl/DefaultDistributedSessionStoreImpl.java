/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: DefaultSessionStore.java 9552 2015年5月21日 下午2:33:06 WangLijun$
*/
package com.newtouch.lion.dsession.store.impl;

import com.newtouch.lion.dsession.config.DistributedSessionAttributeConfig;
import com.newtouch.lion.dsession.context.DistributedSessionContext;
import com.newtouch.lion.dsession.store.DistributedSessionStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

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
public class DefaultDistributedSessionStoreImpl  implements DistributedSessionStore {
	
	/**日志*/
	private static final Logger  logger=LoggerFactory.getLogger(DefaultDistributedSessionStoreImpl.class);
	
	 /**
     * SessionAttributeConfig
     */
    private DistributedSessionAttributeConfig distributedSessionAttributeConfig;

	/* (non-Javadoc)
	 * @see com.newtouch.lion.dsession.store.DistributedSessionStore#invalidate(com.newtouch.lion.dsession.context.DistributedSessionContext)
	 */
	@Override
	public void invalidate(DistributedSessionContext sessionContext) {
		sessionContext.getOriginalRequest().getSession().invalidate();
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.dsession.store.DistributedSessionStore#getAttribute(com.newtouch.lion.dsession.context.DistributedSessionContext, java.lang.String)
	 */
	@Override
	public Object getAttribute(DistributedSessionContext sessionContext,
			String key) {
		 	return sessionContext.getOriginalRequest().getSession().getAttribute(key);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.dsession.store.DistributedSessionStore#setAttribute(com.newtouch.lion.dsession.context.DistributedSessionContext, java.lang.String, java.lang.Object)
	 */
	@Override
	public void setAttribute(DistributedSessionContext sessionContext, String key, Object value) {
		logger.debug("Set-JVM:{}-{}",key,value);
		sessionContext.getOriginalRequest().getSession().setAttribute(key, value);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.dsession.store.DistributedSessionStore#removeAttribute(com.newtouch.lion.dsession.context.DistributedSessionContext, java.lang.String)
	 */
	@Override
	public void removeAttribute(DistributedSessionContext sessionContext, String key) {
		logger.debug("Remove-JVM:{}",key);
		sessionContext.getOriginalRequest().getSession().removeAttribute(key);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.dsession.store.DistributedSessionStore#getAllAttributeNames(com.newtouch.lion.dsession.context.DistributedSessionContext)
	 */
	@Override
	public List<String> getAllAttributeNames(DistributedSessionContext sessionContext) {
		@SuppressWarnings("unchecked")
		Enumeration<String> attributes=sessionContext.getOriginalRequest().getSession().getAttributeNames();
		return Collections.list(attributes);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.dsession.store.DistributedSessionStore#getDistributedSessionAttributeConfig()
	 */
	@Override
	public DistributedSessionAttributeConfig getDistributedSessionAttributeConfig() {
		return this.distributedSessionAttributeConfig;
	}

	/**
	 * @param distributedSessionAttributeConfig the distributedSessionAttributeConfig to set
	 */
	public void setDistributedSessionAttributeConfig(DistributedSessionAttributeConfig distributedSessionAttributeConfig) {
		this.distributedSessionAttributeConfig = distributedSessionAttributeConfig;
	}

	
	
}

	