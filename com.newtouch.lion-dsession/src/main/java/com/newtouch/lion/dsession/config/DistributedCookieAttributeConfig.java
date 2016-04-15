/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: CookieAttributeConfig.java 9552 2015年5月21日 下午2:40:22 WangLijun$
*/
package com.newtouch.lion.dsession.config; 

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.newtouch.lion.dsession.encoder.Encoder;

/**
 * <p>
 * Title: Cookie属性配置
 * </p>
 * <p>
 * Description:  Cookie属性配置
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
public class DistributedCookieAttributeConfig {
	/**
     * key group
     */
    private String keyGroup;
    
    /**
     * key pattern
     */
    private Pattern keyPattern;
    
    /**
     * value的转换工具
     */
    private Encoder encoder; 
    
    /**
     * domain
     */
    private String domain;
    
    /**
     * path
     */
    private String path;
    
    /**
     * max age
     */
    private int maxAge;
    
    /**
     * secure
     */
    private boolean secure;

    /**
     * 功能描述: get key groop<br>
     * @return String
     */
    public String getKeyGroup() {
        return keyGroup;
    }

    /**
     * 
     * 功能描述: set <br>
     * @param keyGroup key group
     */
    public void setKeyGroup(String keyGroup) {
        this.keyGroup = keyGroup;
    }

    /**
     * get
     * 功能描述: <br>
     * @return pattern
     */
    public Pattern getKeyPattern() {
        if(keyPattern == null){
            keyPattern = Pattern.compile(keyGroup);
        }
        return keyPattern;
    }

    /**
     * 
     * 功能描述:set <br>
     * @param keyPattern keyPattern
     */
    public void setKeyPattern(Pattern keyPattern) {
        this.keyPattern = keyPattern;
    }

    /**
     * 
     * 功能描述:get <br>
     * @return encoder
     */
    public Encoder getEncoder() {
        return encoder;
    }

    /**
     * 
     * 功能描述:set <br>
     * @param encoder encoder
     */
    public void setEncoder(Encoder encoder) {
        this.encoder = encoder;
    }

    /**
     * 
     * 功能描述: getDomain<br>
     * @return domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * 
     * 功能描述: setDomain<br>
     * @param domain domain
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * 
     * 功能描述: domain<br>
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * 
     * 功能描述:set path <br>
     * @param path path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 
     * 功能描述:getMaxAge <br>
     * @return maxAge
     */
    public int getMaxAge() {
        return maxAge;
    }

    /**
     * 
     * 功能描述:setMaxAge <br>
     * @param maxAge maxAge
     */
    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    /**
     * 
     * 功能描述: isSecure<br>
     * @return boolean
     */
    public boolean isSecure() {
        return secure;
    }

    /**
     * 
     * 功能描述:setSecure <br>
     * @param secure secure
     */
    public void setSecure(boolean secure) {
        this.secure = secure;
    }
    
    /**
     * 
     * 功能描述: isMatch<br>
     *
     * @param key key
     * @return boolean
     */
    public boolean isMatch(String key){
        if(!StringUtils.hasLength(key)){
            return false;
        }
        Matcher matcher = getKeyPattern().matcher(key);
        return matcher.matches();
    }
}

	
