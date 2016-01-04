/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: AjaxFormAuthenticationFilter.java 9552 2015年3月5日 上午11:31:15 WangLijun$
*/
package com.newtouch.lion.web.shiro.filter; 

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.lion.common.web.LionWebUtils;
import com.newtouch.lion.web.shiro.constant.Constants;

/**
 * <p>
 * Title: 扩展Shiro的FormAuthenticationFilter,用于拦截处理Ajax的请求
 * </p>
 * <p>
 * Description: 针对Ajax请求登录请求情况，拦截信息并返回998的状态
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
public class AjaxFormAuthenticationFilter extends FormAuthenticationFilter{
	
	private static final Logger logger=LoggerFactory.getLogger(AjaxFormAuthenticationFilter.class);
	
	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		
		if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {                
            	logger.trace("Login submission detected.  Attempting to execute login.");                
                return executeLogin(request, response);
            } else {
            	if(LionWebUtils.isAjax(request)){
    				logger.trace("Ajax登录请求未登录,拦截{}",this.getPathWithinApplication(request));
    				WebUtils.toHttp(response).setHeader(Constants.AJAX_UNLOGIN,"true");
    				WebUtils.toHttp(response).setStatus(Constants.AJAX_UNLOGIN_STATUS);
            	}
            	logger.trace("Login page view.");
                return true;
            }
        } else { 
        	if(LionWebUtils.isAjax(request)){
				logger.info("Ajax 请求未登录{}",this.getPathWithinApplication(request));
				WebUtils.toHttp(response).setHeader(Constants.AJAX_UNLOGIN,"true");
				WebUtils.toHttp(response).setStatus(Constants.AJAX_UNLOGIN_STATUS);
        	}else{
        		logger.trace("Attempting to access a path which requires authentication.  Forwarding to the " + "Authentication url [" + getLoginUrl() + "]");
                saveRequestAndRedirectToLogin(request, response);
             
        	}
        	return false;
        }
	}
}

	