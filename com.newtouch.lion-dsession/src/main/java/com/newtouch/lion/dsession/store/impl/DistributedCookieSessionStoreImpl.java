/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: DistributedCookieSessionStore.java 9552 2015年6月15日 下午5:07:52 WangLijun$
*/
package com.newtouch.lion.dsession.store.impl; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.newtouch.lion.dsession.config.DistributedSessionAttributeConfig;
import com.newtouch.lion.dsession.context.DistributedSessionContext;
import com.newtouch.lion.dsession.store.DistributedCookieStore;
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
public class DistributedCookieSessionStoreImpl  implements   DistributedSessionStore{
	
	  /**
     * SessionAttributeConfig
     */
    @Autowired
    private DistributedSessionAttributeConfig distributedSessionAttributeConfig;
    @Autowired
    private  DistributedCookieStore  distributedCookieStore;

	/* (non-Javadoc)
	 * @see com.newtouch.lion.dsession.store.DistributedSessionStore#invalidate(com.newtouch.lion.dsession.context.DistributedSessionContext)
	 */
	@Override
	public void invalidate(DistributedSessionContext sessionContext) {
		distributedCookieStore.invalidate(sessionContext);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.dsession.store.DistributedSessionStore#getAttribute(com.newtouch.lion.dsession.context.DistributedSessionContext, java.lang.String)
	 */
	@Override
	public Object getAttribute(DistributedSessionContext sessionContext,
			String key) {
		 return this.distributedCookieStore.getAttribute(sessionContext,key);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.dsession.store.DistributedSessionStore#setAttribute(com.newtouch.lion.dsession.context.DistributedSessionContext, java.lang.String, java.lang.Object)
	 */
	@Override
	public void setAttribute(DistributedSessionContext sessionContext,String key, Object value) {
		this.distributedCookieStore.setAttribute(sessionContext, key, value);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.dsession.store.DistributedSessionStore#removeAttribute(com.newtouch.lion.dsession.context.DistributedSessionContext, java.lang.String)
	 */
	@Override
	public void removeAttribute(DistributedSessionContext sessionContext,
			String key) {
		this.distributedCookieStore.removeAttribute(sessionContext, key);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.dsession.store.DistributedSessionStore#getAllAttributeNames(com.newtouch.lion.dsession.context.DistributedSessionContext)
	 */
	@Override
	public List<String> getAllAttributeNames(
			DistributedSessionContext sessionContext) {
	 
		return this.getAllAttributeNames(sessionContext);
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
	public void setDistributedSessionAttributeConfig(
			DistributedSessionAttributeConfig distributedSessionAttributeConfig) {
		this.distributedSessionAttributeConfig = distributedSessionAttributeConfig;
	}
    
    
	
}

	