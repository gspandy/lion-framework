package com.newtouch.lion.webtrans.security;

import org.springframework.stereotype.Component;

/**
 * Created by wanglijun on 16/2/5.
 */
@Component
public class DefaultTransSecurity implements  TransSecurity{

    public DefaultTransSecurity() {
        super();
    }

    /***
     * 是否有权限
     *
     * @param value
     * @return
     */
    @Override
    public boolean hasPermission(String value) {
        return true;
    }
}
