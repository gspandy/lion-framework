/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: DefaultDistributedContext.java 9552 2015年5月21日 下午3:02:58 WangLijun$
*/
package com.newtouch.lion.dsession.context;

import com.newtouch.lion.dsession.DistributedHttpSession;
import com.newtouch.lion.dsession.common.UUIDGenerator;
import com.newtouch.lion.dsession.config.DistributedCookieConfig;
import com.newtouch.lion.dsession.config.DistributedSessionConfig;
import com.newtouch.lion.dsession.constant.SessionConstant;
import com.newtouch.lion.dsession.util.DistributedContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.*;

/**
 * <p>
 * Title: 分布式会话上下文默认实现
 * </p>
 * <p>
 * Description: 分布式会话上下文默认实现
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
public class DefaultDistributedSessionContext extends AbstractDistributedRequestContext implements DistributedSessionContext {
	/**
	 * 日志
	 */
	private static final Logger logger=LoggerFactory.getLogger(DefaultDistributedSessionContext.class);
	
	/***会话ID*/
	private String sessionId;
	/**会话消息SessionIDFromCookie;*/
	private boolean sessionIdFromCookie;
	/**会话URL*/
	private boolean sessionIdFormURL;
	/**会话*/
	private DistributedSessionConfig distributedSessionConfig;
	/**Cookie*/
	private DistributedCookieConfig distributedCookieConfig;
	/**分布式Session*/
	private DistributedHttpSession distributedHttpSession;
	
	/***
	 * 默认构造函数
	 * @param distributedContext
	 */
	public DefaultDistributedSessionContext(DistributedRequestContext distributedContext) {
		 super(distributedContext);
	}
	
	/**
	 * @param servletContext
	 * @param request
	 * @param response
	 * @param distributedSessionConfig
	 * @param distributedCookieConfig
	 */
	public DefaultDistributedSessionContext(ServletContext servletContext,
											HttpServletRequest request, HttpServletResponse response,
											DistributedSessionConfig distributedSessionConfig, DistributedCookieConfig distributedCookieConfig) {
		super(servletContext, request, response);		
		super.setRequest(new DistributedHttpServletRequestWrapper(request));
		super.setResponse(new DistributedHttpServletResponseWrapper(response));
		this.distributedSessionConfig = distributedSessionConfig;
		this.distributedCookieConfig = distributedCookieConfig;
	}

	
	 /**
     * 
     * 功能描述: 取得当前的session，如果不存在，则创建一个新的。<br>
     * 〈功能详细描述〉
     *
     * @param create boolean
     * @return 当前的session或新的session， 如果不存在则返回
     */
    public HttpSession getSession(boolean create) {
 
    	if(this.distributedHttpSession!=null&&this.distributedHttpSession.isExpired()){
    		logger.info("session has been expired");
    		//如果session过期，则清空和重置session
    		this.distributedHttpSession.invalidate();
 
    	}
 
    	//如getSession方法已经执行过，且没有超过过期时间，那么直接返回。
    	if(this.distributedHttpSession!=null){
    		this.distributedHttpSession.reset();
    		logger.info("{}'s last access time updated",this.distributedHttpSession.getSessionId());
    		return this.distributedHttpSession;
    	}
    	//创建session，
    	if(this.distributedHttpSession==null){
    		//从request中取得sessionId
    		  boolean isNew = false;
    		if(this.getSessionId()==null){
    			if(!create){
    				return null;
    			}
    			this.sessionId= UUIDGenerator.getUUID();
    			this.encodeSessionIDIntoCookie(this.sessionId);
    			isNew=true;
    		}
    		this.distributedHttpSession=new DistributedHttpSession(this.sessionId,this,isNew);
    	}
    	return null;
    }

	@Override
	public DistributedSessionConfig getDistributedSessionConfig() {
		return this.distributedSessionConfig;
	}

	

	/* (non-Javadoc)
	 * @see com.newtouch.lion.dsession.context.DistributedSessionContext#getDistributedCookieConfig()
	 */
	@Override
	public DistributedCookieConfig getDistributedCookieConfig() {
		return this.distributedCookieConfig;
	}

	@Override
	public boolean isSessionInvalidated() {
		 return distributedHttpSession == null ? false : distributedHttpSession.isInvalidated();
	}



	@Override
	public DistributedRequestContext getDdistributedContext() {
		
		return null;
	}

 
	
	

	/**
	 * @return the sessionIdFromCookie
	 */
	public boolean isSessionIdFromCookie() {
		return sessionIdFromCookie;
	}

	/**
	 * @param sessionIdFromCookie the sessionIdFromCookie to set
	 */
	public void setSessionIdFromCookie(boolean sessionIdFromCookie) {
		this.sessionIdFromCookie = sessionIdFromCookie;
	}

	/**
	 * @return the sessionIdFormURL
	 */
	public boolean isSessionIdFormURL() {
		return sessionIdFormURL;
	}

	/**
	 * @param sessionIdFormURL the sessionIdFormURL to set
	 */
	public void setSessionIdFormURL(boolean sessionIdFormURL) {
		this.sessionIdFormURL = sessionIdFormURL;
	}
	
	

	 

	@Override
	public String getSessionId() {
		if(StringUtils.hasLength(this.sessionId)){
			return this.sessionId;
		}
	     // 首先从URL中获取sessionId
        this.sessionId = decodeSessionIdFromURL();
        this.sessionIdFormURL=(this.sessionId!=null);
        if(StringUtils.isEmpty(this.sessionId)){
        	this.sessionId=this.decodeSessionIDFromCookie();
        	this.sessionIdFromCookie=(this.sessionId!=null);
        }
		return this.sessionId;
	}
	
	 /**
     * 功能描述:将session ID编码到Cookie中去。 <br>
     * @param sessionId String
     */
    public void encodeSessionIDIntoCookie(String sessionId) {
        writeSessionIDToCookie(sessionId);
    }
    
    
    public String encodeSessionIDIntoURL(String url) {
        return DistributedContextUtil.encodeSessionIDIntoURL(this, url);
    }

    /**
     * 
     * 功能描述:写cookie。 <br>
     *
     * @param cookieValue String

     */
    private void writeSessionIDToCookie(String cookieValue) {
    	DistributedContextUtil.writeKeyValueToCookie(this, SessionConstant.SESSION_ID, cookieValue);
    }
	
	 /**
     * 从URL中取得session ID。
     * @return 如果存在，则返回session ID，否则返回<code>null</code>
     */
    public String decodeSessionIdFromURL() {
        return  DistributedContextUtil.decodeSessionIdFromURL(this);
    }
    
    /**
     * 从cookie中取得session ID。
     * @return 如果存在，则返回session ID，否则返回<code>null</code>
     */
    public String decodeSessionIDFromCookie() {
        return DistributedContextUtil.decodeSessionIdFromCookie(this);
    }

	 

	@Override
	public void clear() {
		
	}
	
	public boolean isSessionIdValid(){
		HttpSession session=this.getSession(false);
		return session!=null&&session.getId().equals(this.getSessionId());
	}
	/***
	 * 内部类－分布式请求处理
	 * @author wanglijun
	 *
	 */
	private class DistributedHttpServletRequestWrapper extends HttpServletRequestWrapper{
		/***
		 * 构造函数
		 * @param request
		 */
		public DistributedHttpServletRequestWrapper(HttpServletRequest request) {
			super(request);
			 
		}
		
		@Override
		public String getRequestedSessionId() {			 
			return  DefaultDistributedSessionContext.this.getSessionId();
		}
		
		  /**
         * 当前的session ID是从URL中取得的吗？
         * 
         * @return 如果是，则返回<code>true</code>
         */
        @Override
        public boolean isRequestedSessionIdFromURL() {
            return DefaultDistributedSessionContext.this.isSessionIdFormURL();
        }

        /**
         * 判断当前的session ID是否仍然合法。
         * 
         * @return 如果是，则返回<code>true</code>
         */
        @Override
        public boolean isRequestedSessionIdValid() {
            return DefaultDistributedSessionContext.this.isSessionIdValid();
        }

        @Override
        public HttpSession getSession() {
            return DefaultDistributedSessionContext.this.getSession(true);
        }

        /**
         * 取得当前的session，如果不存在，且<code>create</code>为<code>true</code>，则创建一个新的。
         * 
         * @param create 必要时是否创建新的session
         * @return 当前的session或新的session，如果不存在，且<code>create</code>为 <code>false</code>，则返回<code>null</code>
         */
        @Override
        public HttpSession getSession(boolean create) {
            return DefaultDistributedSessionContext.this.getSession(create);
        }

        /**
         * @return isRequestedSessionIdFromURL
         */
        @Override
        @Deprecated
        public boolean isRequestedSessionIdFromUrl() {
            return isRequestedSessionIdFromURL();
        }
		
	}
	
	/***
	 * 内部类－分布式响应处理
	 * @author wanglijun
	 *
	 */
	private class DistributedHttpServletResponseWrapper  extends HttpServletResponseWrapper{
		/***
		 * 构造函数
		 * @param response
		 */
		public DistributedHttpServletResponseWrapper(
				HttpServletResponse response) {
			super(response);
			
		}
		
		
		@Override
		public String encodeUrl(String url){
			return super.encodeUrl(url);
		}
		
		@Override
		public String encodeURL(String url) {
			return super.encodeURL(url);
		}
		
		@Override
		public String encodeRedirectUrl(String url) {			
			return  DefaultDistributedSessionContext.this.encodeSessionIDIntoURL(url);
		}
		
		 /**
         * 将session ID编码到URL中。
         * @param url 要编码的URL
         * @return 包含session ID的URL
         */
		@Override
		public String encodeRedirectURL(String url) {
			return super.encodeRedirectURL(url);
		}
		
	}

	
	
}

	
