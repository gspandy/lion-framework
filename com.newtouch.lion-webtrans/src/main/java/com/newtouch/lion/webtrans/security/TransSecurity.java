package com.newtouch.lion.webtrans.security;

/**
 * Created by wanglijun on 16/2/5.
 */
public  interface TransSecurity {
    /***
     * 是否有权限
     * @param value
     * @return
     */
    boolean hasPermission(String value);
}
