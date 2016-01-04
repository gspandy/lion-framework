/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: StringView.java 9552 2015年2月4日 下午3:57:09 WangLijun$
*/
package com.newtouch.lion.web.servlet.view; 

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractView;

/**
 * <p>
 * Title: 直接输出字符串
 * </p>
 * <p>
 * Description: 直接输出字符串
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
public class StringJsonView  extends AbstractView {
	
	/**日志*/
	private final static Logger logger=LoggerFactory.getLogger(JsonView.class);
	/**JSON字符串参数属性名称*/
	protected final String STR_JSON__KEY="json";

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("in JSON字符串视图");
		response.setContentType(this.getContentType());
		String jsonStr=(String) model.get(STR_JSON__KEY);
		response.getWriter().write(jsonStr);
		response.getWriter().flush();
		logger.info("out JSON字符串视图");
	}

	
	public String getContentType() {
		return "text/html;charset=utf-8";
	}
}

	