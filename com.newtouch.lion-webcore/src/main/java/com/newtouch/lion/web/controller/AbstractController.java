
/*
* Copyright (c)  2014, Newtouch
* All rights reserved. 
*
* $id: AbstractController.java 9552 2014年12月29日 上午11:43:57 WangLijun$
*/
package com.newtouch.lion.web.controller; 

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.web.support.editor.StringEscapeEditor;

/**
 * <p>
 * Title: Controller抽像类
 * </p>
 * <p>
 * Description: Controller抽像类
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
public class AbstractController {
	
	/**日志*/
	protected final Logger logger=LoggerFactory.getLogger(super.getClass());
	/**JsonView*/
	protected final String JSON_VIEW="jsonView";
	/**excelView*/
	protected final String EXCEL_VIEW="reportExcelView";
	/**参数名称*/
	protected static final String FILENAME="fileName";
	/**添加成功**/
	protected static final String ADD_SUCCESS_MSG="";
	/**JSON字符串*/
	protected final String STR_JSON_VIEW="stringJsonView";
	/**JSON字符串参数属性名称*/
	protected final String STR_JSON__KEY="json";
	
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat(DateUtil.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS), true));
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(DateUtil.FORMAT_DATE_YYYY_MM_DD), true));
		/**
		 * 防止XSS攻击
		 */
		binder.registerCustomEditor(String.class, new StringEscapeEditor(true,false));
	}

	/**
	 * 用户跳转view页面
	 * @param folder   路径
	 * @param view 名称(不加后缀)
	 * @return 指定view页面
	 */
	//@RequestMapping("/{folder}/{jspName}")
	public String redirectJsp(@PathVariable String folder,
			@PathVariable String jspName) {
		return "/" + folder + "/" + jspName;
	}
	
	/***
	 * 转发到URL
	 * @param url 转发到的URL
	 * @return 转发路径
	 */
	public String forward(String url){
		StringBuilder sb=new StringBuilder();
		sb.append("forward:");
		sb.append(url);
		return sb.toString();
	}
	
	/***
	 * 重定向到相关页面
	 * @param url
	 * @return
	 */
	protected String redirect(String url){
		StringBuilder sb=new StringBuilder();
		sb.append("redirect:");
		sb.append(url);
		return sb.toString();
	}
	/***
	 * 获取Request对象
	 * @return  HttpServletRequest
	 */
	protected HttpServletRequest  getRequest(){
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();  
        return servletRequestAttributes.getRequest();     
	}
	/****
	 * 返回JSON图
	 * @param modelAndView
	 * @return
	 */
	protected ModelAndView  getJsonView(ModelAndView modelAndView){
		 modelAndView.setViewName(JSON_VIEW);
		 return modelAndView;
	}
	/***
	 * 返回Excel视图
	 * @param modelAndView
	 * @return ModelAndView
	 */  
	protected ModelAndView getExcelView(ModelAndView modelAndView){
		 modelAndView.setViewName(EXCEL_VIEW);
		 return modelAndView;
	}
	/***
	 * 设置JSON字符串视图
	 * @param str JSON字符串
	 * @param modelAndView  
	 * @return ModelAndView
	 */
	protected ModelAndView getStrJsonView(String str,ModelAndView modelAndView){
		modelAndView.setViewName(this.STR_JSON_VIEW);
		modelAndView.addObject(STR_JSON__KEY,str);
		return modelAndView;
	}
	
	/***
	 * 设置JSON字符串视图
	 * @param str JSON字符串
	 * @param modelAndView  
	 * @return ModelAndView
	 */
	protected ModelAndView getStrJsonView(ModelAndView modelAndView){
		modelAndView.setViewName(this.STR_JSON_VIEW);		 
		return modelAndView;
	}
	
	/***
	 * 格式为：UUID+时分少毫秒+后缀名
	 * @param extension 后缀名
	 * @return 文件名
	 */
	protected String createFileName(String extension){
		StringBuilder sb=new StringBuilder();
		sb.append(UUID.randomUUID().toString().replace("-","").toUpperCase());
		sb.append(DateUtil.formatDate(new Date(),DateUtil.FORMAT_TIME_HHMMSS_SSS));
		sb.append(extension);
		return sb.toString();
	}
}

	