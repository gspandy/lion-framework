/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: Session.java 9552 2015年5月21日 下午2:06:15 WangLijun$
*/
package com.newtouch.lion.dsession.model; 

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.lion.dsession.DistributedHttpSession;
import com.newtouch.lion.dsession.config.DistributedSessionConfig;

/**
 * <p>
 * Title: Session 会话模型
 * </p>
 * <p>
 * Description:  Session 会话模型
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
public class SessionModel  implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -5602248594306197918L;
	

    /**
     * 日志
     */
    private static final  Logger LOGGER = LoggerFactory.getLogger(SessionModel.class);
	  /**
     * 1000
     */
    private static final int THOUNSD =1000;
   
    /**
     * session config
     */
    private transient DistributedSessionConfig distributedSessionConfig;

    /**
     * session id
     */
    private String sessionId;

    /**
     * 创建时间
     */
    private long creationTime;

    /**
     * last access time
     */
    private long lastAccessedTime;

    /**
     * max inactive interval
     */
    private int maxInactiveInterval;
    
    /**
     * @param distributedHttpSession
     */
    public SessionModel(DistributedHttpSession distributedHttpSession) {
    	 this.setDistributedHttpSession(distributedHttpSession);
    	 this.reset();
	}
    
    

	/**
	 * @param distributedSessionConfig
	 * @param sessionId
	 * @param createdTime
	 * @param lastAccessedTime
	 * @param maxInactiveInterval
	 */
	public SessionModel(DistributedSessionConfig distributedSessionConfig,
			String sessionId, long creationTime, long lastAccessedTime,
			int maxInactiveInterval) {
		this.distributedSessionConfig = distributedSessionConfig;
		this.sessionId = sessionId;
		this.creationTime = creationTime;
		this.lastAccessedTime = lastAccessedTime;
		this.maxInactiveInterval = maxInactiveInterval;
	}



	@Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            LOGGER.error("clone failed", e);
            return null;
        }
    }

    /**
     * 
     * 功能描述: get session config<br>
     * @return session config
     */
    private DistributedSessionConfig getDistributedSessionConfig() {
        if (distributedSessionConfig == null) {
            throw new NullPointerException("sessionConfig is null");
        }
        return distributedSessionConfig;
    }

    /**
     * 
     * 功能描述: reset<br>
     */
    public void reset() {
    	getDistributedSessionConfig();
        this.creationTime = System.currentTimeMillis();
        this.lastAccessedTime =creationTime;
    }

    /**
     * 
     * 功能描述:设置model所在的session。 <br>
     * @param session session
     */
    public void setDistributedHttpSession(DistributedHttpSession distributedHttpSession) {
    	//TODO
    	//this.distributedSessionConfig = distributedHttpSession;
        this.sessionId = distributedHttpSession.getId();
       // this.maxInactiveInterval = this.distributedSessionConfig.getMaxInactiveInterval();
    }

   
    
    
    /**
     * 判断session有没有过期。
     * 
     * @return 如果过期了，则返回<code>true</code>
     */
    public boolean isExpired() {

        int maxInactiveInterval = getMaxInactiveInterval();
        long current = System.currentTimeMillis();

        // 如果从上次访问时间算起，已经超过maxInactiveInterval没动静了，则作废。
        if (maxInactiveInterval > 0) {
            long expires = getLastAccessedTime() + maxInactiveInterval * THOUNSD;

            if (expires < current) {
                return true;
            }
        }

        return false;
    }

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	 

	/**
	 * @return the creationTime
	 */
	public long getCreationTime() {
		return creationTime;
	}



	/**
	 * @param creationTime the creationTime to set
	 */
	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}



	/**
	 * @param distributedSessionConfig the distributedSessionConfig to set
	 */
	public void setDistributedSessionConfig(
			DistributedSessionConfig distributedSessionConfig) {
		this.distributedSessionConfig = distributedSessionConfig;
	}



	/**
	 * @return the lastAccessedTime
	 */
	public long getLastAccessedTime() {
		return lastAccessedTime;
	}

	/**
	 * @param lastAccessedTime the lastAccessedTime to set
	 */
	public void setLastAccessedTime(long lastAccessedTime) {
		this.lastAccessedTime = lastAccessedTime;
	}

	/**
	 * @return the maxInactiveInterval
	 */
	public int getMaxInactiveInterval() {
		return maxInactiveInterval;
	}

	/**
	 * @param maxInactiveInterval the maxInactiveInterval to set
	 */
	public void setMaxInactiveInterval(int maxInactiveInterval) {
		this.maxInactiveInterval = maxInactiveInterval;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the thounsd
	 */
	public static int getThounsd() {
		return THOUNSD;
	}
    
    
}

	