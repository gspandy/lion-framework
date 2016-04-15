/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: DistributedContext.java 9552 2015年5月21日 下午2:35:59 WangLijun$
*/
package com.newtouch.lion.dsession.context; 

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * Title: 分布会话上下文
 * </p>
 * <p>
 * Description: 分布会话上下文获取,DistributedContext,ServletContext,HttpServletRequest,HttpServletResponse
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
public interface DistributedRequestContext {
	
	  /**
     * 取得被包装的context。
     * 
     * @return 被包装的<code>DistributedContext</code>对象
     */
	public DistributedRequestContext getDdistributedContext();
	 /**
     * 取得servletContext对象。
     * 
     * @return <code>ServletContext</code>对象
     */
    public ServletContext  getServletContext();

    /**
     * 取得request对象。
     * 
     * @return <code>HttpServletRequest</code>对象
     */
    public HttpServletRequest getRequest();

    /**
     * 取得response对象。
     * 
     * @return <code>HttpServletResponse</code>对象
     */
    public HttpServletResponse getResponse();
    
    /**
     * 取得request对象。
     * 
     * @return <code>HttpServletRequest</code>对象
     */
    public HttpServletRequest getOriginalRequest();

    /**
     * 取得response对象。
     * 
     * @return <code>HttpServletResponse</code>对象
     */
    public HttpServletResponse getOriginalResponse();

}

	