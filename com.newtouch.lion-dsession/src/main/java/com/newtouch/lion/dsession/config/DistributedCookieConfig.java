/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: CookieConfig.java 9552 2015年5月21日 下午2:39:34 WangLijun$
*/
package com.newtouch.lion.dsession.config;

import com.newtouch.lion.dsession.store.DistributedCookieStore;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * Title: Cookie Config
 * </p>
 * <p>
 * Description: Cookie Config
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
public class DistributedCookieConfig {
	 /**
     * cookieStores
     */
    private List<DistributedCookieStore> distributedCookieStores = new ArrayList<DistributedCookieStore>();

    /**
     * 
     * 功能描述: getCookieStores<br>
     * 
     * @return cookieStores
     */
    public List<DistributedCookieStore> getCookieStores() {
        return this.distributedCookieStores;
    }

    /**
     * 
     * 功能描述:setCookieStores <br>
     * 
     * @param distributedCookieStores cookieStores
     */
    public void setCookieStores(List<DistributedCookieStore> distributedCookieStores) {
        this.distributedCookieStores = distributedCookieStores;
    }

    /**
     * 
     * 功能描述:getCookieAttributes <br>
     * @return cookieAttributes
     */
    public List<DistributedCookieAttributeConfig> getCookieAttributes() {
        List<DistributedCookieAttributeConfig> cookieAttributes = new ArrayList<DistributedCookieAttributeConfig>();
        if (distributedCookieStores.size() > 0) {
            for (DistributedCookieStore stores : distributedCookieStores) {
            	DistributedCookieAttributeConfig cookieAttr = stores.getDistributedCookieAttributeConfig();
                cookieAttributes.add(cookieAttr);
            }
        }
        return cookieAttributes;
    }

    /**
     * 
     * 功能描述: getCookieStore<br>
     * 〈功能详细描述〉
     * 
     * @param key key
     * @return cookieStores
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public DistributedCookieStore getCookieStore(String key) {
        if (distributedCookieStores.size() > 0) {
            for (DistributedCookieStore store : distributedCookieStores) {
            	DistributedCookieAttributeConfig cookieAttr = store.getDistributedCookieAttributeConfig();
                if (cookieAttr.isMatch(key)) {
                    return store;
                }
            }
        }
        return null;
    }
}

	