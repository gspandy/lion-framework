/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: SessionStore.java 9552 2015年5月21日 下午2:23:16 WangLijun$
*/
package com.newtouch.lion.dsession.store;

import com.newtouch.lion.dsession.config.DistributedSessionAttributeConfig;
import com.newtouch.lion.dsession.context.DistributedSessionContext;

import java.util.List;


/**
 * <p>
 * Title: Session存取操作
 * </p>
 * <p>
 * Description: Session存取操作
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
public interface DistributedSessionStore {
	 /**
     * 
     * 功能描述:清除相关数据 <br>
     * @param sessionContext
     */
	public void invalidate(DistributedSessionContext sessionContext);	
	
 

    /**
     * 
     * 功能描述:getAttribute<br>
     * @param sessionContext
     * @param key key
     * @return Object
     */
    Object getAttribute(DistributedSessionContext sessionContext, String key);
    

    /**
     * 
     * 功能描述: setAttribute<br>
     * @param  sessionContext
     * @param key key
     * @param value value
     */
    void setAttribute(DistributedSessionContext sessionContext, String key, Object value);

    /**
     * 
     * 功能描述: removeAttribute<br>
     * @param sessionContext
     * @param key key
     */
    void removeAttribute(DistributedSessionContext sessionContext, String key);

    /**
     * 
     * 功能描述: 只获取所有在config中配置的cookie的name<br>
     * @param sessionContext
     * @return list
     */
    List<String> getAllAttributeNames(DistributedSessionContext sessionContext);

    /**
     * 
     * 功能描述: getDistributedSessionAttributeConfig<br>
     * @return DistributedSessionAttributeConfig
     */
    DistributedSessionAttributeConfig getDistributedSessionAttributeConfig();
}

	