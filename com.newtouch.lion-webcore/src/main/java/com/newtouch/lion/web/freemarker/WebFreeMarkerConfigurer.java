/*
 * Copyright (c)  2014, Newtouch
 * All rights reserved. 
 *
 * $id: ShiroFreeMarkerConfigurer.java 9552 2014年12月31日 上午11:19:10 WangLijun$
 */
package com.newtouch.lion.web.freemarker;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.newtouch.lion.web.tags.FreemarkerHashTag;

import freemarker.template.TemplateException;

/**
 * <p>
 * Title: 在Freemarker中，添加Shiro的标签，
 * </p>
 * <p>
 * Description: 将Shiro标签添加到Freemarker中
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class WebFreeMarkerConfigurer extends FreeMarkerConfigurer {
	
	
	
	@Override
	public void afterPropertiesSet() throws IOException, TemplateException {
		super.afterPropertiesSet();
		
	}
	
	/**
	 * 设置标签库
	 * @param shiroTag
	 */
	public  void setSharedVariable(HashMap<String,FreemarkerHashTag> params){
		for(Entry<String,FreemarkerHashTag> entry:params.entrySet()){
			this.getConfiguration().setSharedVariable(entry.getKey(),entry.getValue());
		}
	}
}
