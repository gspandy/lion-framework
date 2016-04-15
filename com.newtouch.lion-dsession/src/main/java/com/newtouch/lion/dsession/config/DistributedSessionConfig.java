/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: SessionConfig.java 9552 2015年5月21日 下午2:22:20 WangLijun$
*/
package com.newtouch.lion.dsession.config; 

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSessionListener;

import com.newtouch.lion.dsession.store.DistributedSessionStore;
import com.newtouch.lion.dsession.store.impl.DefaultDistributedSessionStoreImpl;
 
 

/**
 * <p>
 * Title: 会话存取
 * </p>
 * <p>
 * Description: 会话存取
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
public class DistributedSessionConfig {
	   /***
	  * 会话监控听器列表
	  */
	   private List<HttpSessionListener> listerners = new ArrayList<HttpSessionListener>();
	  /***
	   * 会话存取列表
	   */
	  private List<DistributedSessionStore> distributedSessionStores = new ArrayList<DistributedSessionStore>();
	  
	   /**
	    * 会话保持时间，单位。秒
	    */
	   private int maxInactiveInterval;
	   
	   public DistributedSessionStore getDistributedSessionStore(String key) {
	        if (distributedSessionStores.size() > 0) {
	            for (DistributedSessionStore store : distributedSessionStores) {
	            	DistributedSessionAttributeConfig cookieAttr = store.getDistributedSessionAttributeConfig();
	                if (cookieAttr.isMatch(key)) {
	                    return store;
	                }
	            }
	        }
	        return new DefaultDistributedSessionStoreImpl();
	    }

	    public List<HttpSessionListener> getListerners() {
	        return listerners;
	    }

	    public void setListerners(List<HttpSessionListener> listerners) {
	        this.listerners = listerners;
	    }

	    public List<DistributedSessionStore> getSessionStores() {
	        return distributedSessionStores;
	    }

	    public void setSessionStores(List<DistributedSessionStore> sessionStores) {
	        this.distributedSessionStores = sessionStores;
	        DistributedSessionAttributeConfig sessionAttrConfig = new DistributedSessionAttributeConfig();
	        sessionAttrConfig.setKeyGroup(".*");
	        sessionAttrConfig.setKeyPattern(null);
	        
	    }

	    public int getMaxInactiveInterval() {
	        return maxInactiveInterval;
	    }

	    public void setMaxInactiveInterval(int maxInactiveInterval) {
	        this.maxInactiveInterval = maxInactiveInterval;
	    }
}

	