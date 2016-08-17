/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: DistributedSessionContext.java 9552 2015年5月21日 下午2:50:41 WangLijun$
 */
package com.newtouch.lion.dsession.context;

import com.newtouch.lion.dsession.config.DistributedCookieConfig;
import com.newtouch.lion.dsession.config.DistributedSessionConfig;

/**
 * <p>
 * Title: 分布式会话上下文
 * </p>
 * <p>
 * Description: 分布式会话上下文
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
public interface DistributedSessionContext extends DistributedRequestContext {
	/**
	 * 取得Request中的SessionId.
	 * @return String
	 */
	String getSessionId();
	/***
	 * 获取SessionConfig信息
	 * @return DistributedSessionConfig
	 */
	 DistributedSessionConfig getDistributedSessionConfig();
	/***
	 * 获取CookieConfig信息
	 * @return DistributedCookieConfig
	 */
	 DistributedCookieConfig getDistributedCookieConfig();
	
	 /**
     * 判断session是否已经作废。
     * 
     * @return 如已作废，则返回<code>true</code>
     */
    boolean isSessionInvalidated();

    /**
     * 清除session。类似<code>invalidate()</code>方法，但支持后续操作，而不会抛出
     * <code>IllegalStateException</code>。
     */
    void clear();
}
