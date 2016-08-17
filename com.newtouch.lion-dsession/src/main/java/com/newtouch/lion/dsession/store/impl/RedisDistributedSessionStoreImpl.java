/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisDistributedSessionStore.java 9552 2015年6月15日 下午7:51:54 WangLijun$
*/
package com.newtouch.lion.dsession.store.impl;

import com.newtouch.lion.dsession.config.DistributedSessionAttributeConfig;
import com.newtouch.lion.dsession.context.DistributedSessionContext;
import com.newtouch.lion.dsession.store.DistributedSessionStore;
import com.newtouch.lion.redis.client.IBinaryRedisClient;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
public class RedisDistributedSessionStoreImpl   implements DistributedSessionStore {
	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(RedisDistributedSessionStoreImpl.class);
	
	@Autowired
	private DistributedSessionAttributeConfig distributedSessionAttributeConfig;
	
    /**
     * Redis client infterface
     * */
	@Autowired
	private IBinaryRedisClient binaryRedisClient;

	/**会话保持时间，单位，秒*/
	private int maxInactiveInterval;
	
	public RedisDistributedSessionStoreImpl() {
		 super();
	}
	
	
	
	/**
	 * @return the maxInactiveInterval
	 */
	public int getMaxInactiveInterval() {
		return maxInactiveInterval;
	}



	/**
	 * 
	 * @param maxInactiveInterval the maxInactiveInterval to set
	 */
	public void setMaxInactiveInterval(int maxInactiveInterval) {
		this.maxInactiveInterval = maxInactiveInterval;
	}



	/**
	 * @param distributedSessionAttributeConfig the distributedSessionAttributeConfig to set
	 */
	public void setDistributedSessionAttributeConfig(
			DistributedSessionAttributeConfig distributedSessionAttributeConfig) {
		this.distributedSessionAttributeConfig = distributedSessionAttributeConfig;
	}



	/* (non-Javadoc)
	 * @see com.newtouch.lion.dsession.store.DistributedSessionStore#invalidate(com.newtouch.lion.dsession.context.DistributedRequestContext)
	 */
	@Override
	public void invalidate(DistributedSessionContext sessionContext) {
		if(sessionContext.getSessionId()!=null){
			logger.info("inavlidate redis store");
			this.binaryRedisClient.removeObject(sessionContext.getSessionId());
		}
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.dsession.store.DistributedSessionStore#getAttribute(com.newtouch.lion.dsession.context.DistributedRequestContext, java.lang.String)
	 */
	@Override
	public Object getAttribute(DistributedSessionContext sessionContext, String key) {
		String id=sessionContext.getSessionId();
		Object object=ObjectUtils.NULL;
		if(StringUtils.isNotEmpty(id)){
			logger.info("jsessionId={}",id);
			object=this.binaryRedisClient.hgetObject(id,key);
			logger.info("session attr's {} = {},expired after {} seconds",key,object,this.maxInactiveInterval);
			this.binaryRedisClient.expire(id,this.maxInactiveInterval);
		}
		return object;
	}



	/* (non-Javadoc)
	 * @see com.newtouch.lion.dsession.store.DistributedSessionStore#setAttribute(com.newtouch.lion.dsession.context.DistributedSessionContext, java.lang.String, java.lang.Object)
	 */
	@Override
	public void setAttribute(DistributedSessionContext sessionContext, String key, Object value) {
		String id=sessionContext.getSessionId();
		if(StringUtils.isNotEmpty(id)){
			this.binaryRedisClient.hsetObject(id, key, value);
			this.binaryRedisClient.expire(id,this.maxInactiveInterval);
		}
	}



	/* (non-Javadoc)
	 * @see com.newtouch.lion.dsession.store.DistributedSessionStore#removeAttribute(com.newtouch.lion.dsession.context.DistributedSessionContext, java.lang.String)
	 */
	@Override
	public void removeAttribute(DistributedSessionContext sessionContext, String key) {
		String id=sessionContext.getSessionId();
		if(StringUtils.isNotEmpty(id)&&StringUtils.isNotEmpty(key)){
			this.binaryRedisClient.hdelObject(id, key);
			this.binaryRedisClient.expire(id,this.maxInactiveInterval);
		}
	}



	/* (non-Javadoc)
	 * @see com.newtouch.lion.dsession.store.DistributedSessionStore#getAllAttributeNames(com.newtouch.lion.dsession.context.DistributedSessionContext)
	 */
	@Override
	public List<String> getAllAttributeNames(DistributedSessionContext sessionContext) {
		List<String> result = new ArrayList<String>();
		String id=sessionContext.getSessionId();
        if (StringUtils.isNotEmpty(id)) {          
            Map<String, Object> attributes = this.binaryRedisClient.hgetAllObjects(id);
            for (String attr : attributes.keySet()) {
                result.add(attr);
            }
            binaryRedisClient.expire(id, maxInactiveInterval);
        }
        return result;
	}



	/* (non-Javadoc)
	 * @see com.newtouch.lion.dsession.store.DistributedSessionStore#getDistributedSessionAttributeConfig()
	 */
	@Override
	public DistributedSessionAttributeConfig getDistributedSessionAttributeConfig() {
		return this.distributedSessionAttributeConfig;
	}

	
}

	