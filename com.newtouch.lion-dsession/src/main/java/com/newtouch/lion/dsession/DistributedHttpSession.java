/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: DistributedSession.java 9552 2015年5月21日 下午2:01:30 WangLijun$
 */
package com.newtouch.lion.dsession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.newtouch.lion.common.Assert;
import com.newtouch.lion.dsession.config.DistributedSessionConfig;
import com.newtouch.lion.dsession.constant.SessionConstant;
import com.newtouch.lion.dsession.context.DistributedSessionContext;
import com.newtouch.lion.dsession.model.SessionAttribute;
import com.newtouch.lion.dsession.model.SessionModel;
import com.newtouch.lion.dsession.store.DistributedSessionStore;
import com.newtouch.lion.dsession.util.DistributedContextUtil;
 

/**
 * <p>
 * Title: 分布式会话session
 * </p>
 * <p>
 * Description: 实现HttpSession
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
@SuppressWarnings("deprecation")
public class DistributedHttpSession implements HttpSession {
	/***
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(DistributedHttpSession.class);
	/**
	 * sessionId
	 */
	private String sessionId;
	/**
	 * 是否有效invalidated
	 */
	private boolean invalidated = false;
	  /**
     * is new
     */
    private boolean isNew;
    /**是否有效*/
    @SuppressWarnings("unused")
	private boolean expired;

    /**
     * model
     */
    private SessionModel model;

	/** Session配置信息 */
	private DistributedSessionConfig distributedSessionConfig;
	/** 请求上下文根 */
	private DistributedSessionContext distributedSessionContext;
	
	
	/***
	 * 默认构造函数 
	 */
	public DistributedHttpSession() {
		super();
	}
	  
	/**
	 * constructor
	 * @param sessionId
	 * @param isNew
	 * @param distributedSessionContext
	 */
	public DistributedHttpSession(String sessionId,DistributedSessionContext distributedSessionContext, boolean isNew) {
		Assert.notNull(distributedSessionContext);
		this.sessionId = sessionId;
		this.isNew = isNew;
		this.distributedSessionContext = distributedSessionContext;
		if(this.isNew){
			//Request sessionId为空，创建新的model,并保存。
			logger.info("no session Id was found in cookie or URL. A new session will be created.");
			this.invalidate(true);
		}else if(this.model==null){
			long creationTime=System.currentTimeMillis();
			long lastAccessedTime=creationTime;
			//Request sessionId不为空的，但是model不存在（会话过程中新的请求）
			//获取 Cookie中的session的创建时间和上次访问时间，若取不到则直接当前时间；
			List<String> keys=new ArrayList<String>();
			keys.add(SessionConstant.CREATE_TIME);
			keys.add(SessionConstant.LASTACCESS_TIME);
			
			Map<String,Cookie> keyCookies=DistributedContextUtil.getCookiesFromCookie(this.distributedSessionContext, keys);
			if(!CollectionUtils.isEmpty(keyCookies)){
				logger.info("{}={}",SessionConstant.CREATE_TIME,keyCookies.get(SessionConstant.CREATE_TIME));
				logger.info("{}={}",SessionConstant.LASTACCESS_TIME,keyCookies.get(SessionConstant.LASTACCESS_TIME));
				if(keyCookies.get(SessionConstant.CREATE_TIME)!=null&&keyCookies.get(SessionConstant.LASTACCESS_TIME)!=null){
					logger.info("update session {} using cookie value.",this.sessionId);
					creationTime=Long.valueOf(keyCookies.get(SessionConstant.CREATE_TIME).getValue());
					lastAccessedTime=Long.valueOf(keyCookies.get(SessionConstant.LASTACCESS_TIME).getValue());
				}else{
					logger.info("update createdTime and  lastAccessedTime to current time.");
					creationTime=System.currentTimeMillis();
					lastAccessedTime=creationTime;
				}
				this.model=new SessionModel(distributedSessionConfig, sessionId, creationTime, lastAccessedTime,this.getMaxInactiveInterval());
			}
		}else{
			throw new IllegalArgumentException("create session fail");
		}
		this.isNew=isNew;
	}
	
	/**
	 * @return the distributedSessionConfig
	 */
	public DistributedSessionConfig getDistributedSessionConfig() {
		return distributedSessionConfig;
	}

	/**
	 * @param distributedSessionConfig the distributedSessionConfig to set
	 */
	public void setDistributedSessionConfig(
			DistributedSessionConfig distributedSessionConfig) {
		this.distributedSessionConfig = distributedSessionConfig;
	}

	/**
	 * @return the distributedSessionContext
	 */
	public DistributedSessionContext getDistributedSessionContext() {
		return distributedSessionContext;
	}

	/**
	 * @param distributedSessionContext the distributedSessionContext to set
	 */
	public void setDistributedSessionContext(
			DistributedSessionContext distributedSessionContext) {
		this.distributedSessionContext = distributedSessionContext;
	}

	@Override
	public long getCreationTime() {
		
		return this.model==null?0:this.model.getCreationTime();
	}

	@Override
	public String getId() {
		return this.sessionId;
	}

	@Override
	public long getLastAccessedTime() {
		 return model == null ? 0 : model.getLastAccessedTime();
	}

	@Override
	public ServletContext getServletContext() {		 
		return this.distributedSessionContext.getServletContext();
	}

	@Override
	public void setMaxInactiveInterval(int interval) {
		
	}

	@Override
	public int getMaxInactiveInterval() {
		return this.model==null?0:this.model.getMaxInactiveInterval();
	}

	@Override
	public HttpSessionContext getSessionContext() {
		throw new UnsupportedOperationException("No longer suppored method:getSessionContext");
	}

	@Override
	public Object getAttribute(String name) {
		if (isInvalidated()) {
			logger.info("{} is invalidated.",this.sessionId);
			return null;
		}
		DistributedSessionStore store = distributedSessionConfig.getDistributedSessionStore(name);
		logger.info("DdsSessionStore class {} ",store.getClass().getCanonicalName());
		SessionAttribute attr = new SessionAttribute(name, this, store, null);
		return attr.getValue();
	}

	@Override
	public Object getValue(String name) {
		return this.getAttribute(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		Set<String> attributes=new HashSet<String>();
		if(!this.isInvalidated()){
			List<DistributedSessionStore> distributedSessionStores=this.getDistributedSessionConfig().getSessionStores();
			for(DistributedSessionStore distributedSessionStore:distributedSessionStores){
				attributes.addAll(distributedSessionStore.getAllAttributeNames(this.getDistributedSessionContext()));
			}
		}
		
		final Iterator<String> iterator=attributes.iterator();
		
		return new Enumeration<String>(){
			public boolean hasMoreElements(){
				return iterator.hasNext();
			}
			
			public String nextElement(){
				return iterator.next();
			}
		};
	}
	
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String[] getValueNames() {
		Enumeration  enumeration=this.getAttributeNames();
		ArrayList<Integer> list=Collections.list(enumeration);
		String[] array=list.toArray(new String[0]);
		return array;
	}

	@Override
	public void setAttribute(String name, Object value) {
		 if(this.isInvalidated()){
			 return;
		 }
		 
		DistributedSessionStore distributedSessionStore=this.getDistributedSessionConfig().getDistributedSessionStore(name);
		SessionAttribute attribute=new SessionAttribute(name, this, distributedSessionStore, null);
		attribute.setValue(value);
	}
	

	@Override
	public void putValue(String name, Object value) {
		this.setAttribute(name, value);
	}

	@Override
	public void removeAttribute(String name) {
		if (isInvalidated()) {
            return;
        }
        DistributedSessionStore store =this.distributedSessionConfig.getDistributedSessionStore(name);
        SessionAttribute attr = new SessionAttribute(name, this, store, null);
        attr.setValue(null);
	}

	@Override
	public void removeValue(String name) {
		 this.removeAttribute(name);
	}

	@Override
	public void invalidate() {
		this.invalidate(true);
	}
	
	private void invalidate(boolean invalidate){
		//通过所有的store过期数据
		List<DistributedSessionStore> distributedSessionStores=this.getDistributedSessionConfig().getSessionStores();
		for(DistributedSessionStore distributedSessionStore:distributedSessionStores){
			distributedSessionStore.invalidate(this.getDistributedSessionContext());
		}
		
		//清除model
		if(this.model==null){
			model=new SessionModel(DistributedHttpSession.this);
		}else{
			model.reset();
		}
		
		DistributedContextUtil.writeKeyValueToCookie(this.distributedSessionContext,SessionConstant.CREATE_TIME, String.valueOf(model.getCreationTime()));
		DistributedContextUtil.writeKeyValueToCookie(this.distributedSessionContext,SessionConstant.LASTACCESS_TIME, String.valueOf(model.getLastAccessedTime()));
		this.invalidated=invalidate;
	}

	@Override
	public boolean isNew() {
		return false;
	}

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId
	 *            the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @param expired the expired to set
	 */
	@SuppressWarnings("unused")
	private void setExpired(boolean expired) {
		this.expired = expired;
	}

	/**
	 * @return the invalidated
	 */
	public boolean isInvalidated() {
		return invalidated;
	}

	/**
	 * @param invalidated
	 *            the invalidated to set
	 */
	public void setInvalidated(boolean invalidated) {
		this.invalidated = invalidated;
	}

	/**
	 * @return the expired
	 */
	public boolean isExpired() {
		return this.model.isExpired();
	}
	
	public void reset(){
		this.model.reset();
	}
}
