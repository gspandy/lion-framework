/*
 * Copyright (c)  2014, Newtouch
 * All rights reserved. 
 *
 * $id: ShiroTag.java 9552 2014年12月31日 上午11:31:15 WangLijun$
 */
package com.newtouch.lion.web.tags;

import java.util.Map;
import java.util.Map.Entry;

import freemarker.template.SimpleHash;

/**
 * <p>
 * Title: 扩展Shiro以支持在Freemarker中使用
 * </p>
 * <p>
 * Description: 扩展Shiro以支持在Freemarker中使用,引用的Apache Shiro tags for
 * Freemarker，具体使用方法可参考Shiro JSP Tags
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
public class FreemarkerHashTag extends SimpleHash {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -8452498774839515503L;

	/**
	 * 在构造函数中初始化Shiro标签库
	 * */
	public FreemarkerHashTag(Map<String,Object> params) {
		for(Entry<String, Object> enrty:params.entrySet()){
			put(enrty.getKey(),enrty.getValue());
		}
	}
}
