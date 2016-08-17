/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: CookieStore.java 9552 2015年5月21日 下午2:24:09 WangLijun$
*/
package com.newtouch.lion.dsession.store; 

import com.newtouch.lion.dsession.config.DistributedCookieAttributeConfig;
import com.newtouch.lion.dsession.context.DistributedSessionContext;

import java.util.List;

/**
 * <p>
 * Title: Cookie存取
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
public interface DistributedCookieStore {
	 /**
     * 
     * 功能描述: 清除相关数据<br>
     * @param sessionContext DistributedSessionContext
     */
    void invalidate(DistributedSessionContext sessionContext);

    /**
     * 
     * 功能描述:getAttribute <br>
     * @param sessionContext DistributedSessionContext
     * @param key String
     * @return Object
     */
    Object getAttribute(DistributedSessionContext sessionContext, String key);

    /**
     * 
     * 功能描述:setAttribute <br>
     * @param sessionContext DistributedSessionContext
     * @param key key
     * @param value value
     */
    void setAttribute(DistributedSessionContext sessionContext, String key, Object value);

    /**
     * 
     * 功能描述: removeAttribute<br>
     * @param sessionContext DistributedSessionContext
     * @param key key
     */
    void removeAttribute(DistributedSessionContext sessionContext, String key);

    /**
     * 
     * 功能描述: getAllAttributeNames<br>
     * @param sessionContext DistributedSessionContext
     * @return list
     */
    List<String> getAllAttributeNames(DistributedSessionContext sessionContext);

    /**
     * 
     * 功能描述: getDistributedCookieAttributeConfig<br>
     * @return DistributedCookieAttributeConfig
     */
    DistributedCookieAttributeConfig getDistributedCookieAttributeConfig();
}

	
