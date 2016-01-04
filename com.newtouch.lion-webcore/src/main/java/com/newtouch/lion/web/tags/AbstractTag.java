/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: AbstractTag.java 9552 2015年1月6日 下午6:12:59 WangLijun$
*/
package com.newtouch.lion.web.tags; 

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import freemarker.template.TemplateDirectiveModel;

/**
 * <p>
 * Title: Freemarker自定义标签抽象定义
 * </p>
 * <p>
 * Description: Freemarker自定义标签抽象定义，可以获取Request
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
public  abstract class AbstractTag implements TemplateDirectiveModel {
	
	/***
	 * 获取Request对象
	 * @return  HttpServletRequest
	 */
	protected HttpServletRequest  getRequest(){
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();  
        return servletRequestAttributes.getRequest();     
	}
	/***
	 * 根据Request请求对象获取URL(上下文根+URL)
	 * @return 请求URL
	 */
	protected String getRequestURL(){
		HttpServletRequest request=this.getRequest();
	    return request.getRequestURI();
	}
	/***
	 * 获取请求的上下文根
	 * @return  请求上下文根
	 */
	protected String  getContextPath(){
		HttpServletRequest request=this.getRequest();
		return request.getContextPath();
	}
}

	