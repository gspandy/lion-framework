package com.newtouch.lion.web.shiro.trans;

import com.newtouch.lion.webtrans.security.TransSecurity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



/**
 * Created by wanglijun on 16/5/27.
 */
@Component
public class ShiroTransServiceSecurity implements TransSecurity {

    /***日志*/
    private static final Logger logger= LoggerFactory.getLogger(ShiroTransServiceSecurity.class);

    /***
     * 是否有权限
     *
     * @param value
     * @return
     */
    @Override
    public boolean hasPermission(String value) {
        Subject subject= SecurityUtils.getSubject();
        if(subject.isPermitted(value)){
            return true;
        }
        logger.error("当前登录用户{},没有对{}交易访问的权限",subject.getPrincipal().toString(),value);
        return false;
    }
}
