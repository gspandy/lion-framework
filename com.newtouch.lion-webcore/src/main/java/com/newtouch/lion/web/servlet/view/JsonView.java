/*
 * Copyright (c)  2014, Newtouch
 * All rights reserved. 
 *
 * $id: GsonView.java 9552 2014年12月29日 上午10:24:23 WangLijun$
 */
package com.newtouch.lion.web.servlet.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.AbstractView;

import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.common.json.JSONParser;
import com.newtouch.lion.exception.BaseException;
import com.newtouch.lion.web.servlet.view.support.BindMessage;
import com.newtouch.lion.web.servlet.view.support.BindResult;
import com.newtouch.lion.web.servlet.view.support.BindStatus;
import com.newtouch.lion.web.servlet.view.support.RequestContext;

/**
 * <p>
 * Title: JsonView
 * </p>
 * <p>
 * Description: JsonView
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
public class JsonView extends AbstractView {
	/**日志*/
	private final static Logger logger=LoggerFactory.getLogger(JsonView.class);
	/**日期格式*/
	private String datePattern =DateUtil.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS;
	
	private int responseStatus = HttpStatus.OK.value();
	
	private BindStatus bindStatus;

	private RequestContext requestContext;
	
	/**获取ModelAndView对象的名称*/
	private final static String  MODEL_AND_VEIW="modelAndView";

	public JsonView() {
		super();
	}

	 

	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setStatus(getResponseStatus());
		response.setContentType(getContentType());
		String jsonStr;
		//获取视图
		Object object=model.get(MODEL_AND_VEIW);
		Object objException=model.get(SimpleMappingExceptionResolver.DEFAULT_EXCEPTION_ATTRIBUTE);
		if(object instanceof ModelAndView){
			ModelAndView modelAndView=(ModelAndView) object;
			jsonStr=this.initBindStatus(request, response, modelAndView);
		}else if(objException instanceof Throwable){
			//异常处理
			ModelAndView  modelAndView = null;
			if(object==null){
				  modelAndView=new ModelAndView();
				  BaseException baseException=(BaseException) objException;
			      Errors errors=new DirectFieldBindingResult(baseException,"jsonView");
				  errors.reject("",this.getExceptionMessage(baseException));
				  logger.info("baseException:{}",this.getExceptionMessage(baseException));
				  modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);				  
			}
			jsonStr=this.initBindStatus(request, response,modelAndView);
		}else{
			jsonStr=JSONParser.toJSONString(model);
			
		}
		logger.info("jsonStr:{}",jsonStr);
		response.getWriter().write(jsonStr);
		
	}
	/***
	 * 
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 */
	protected String initBindStatus(HttpServletRequest request,HttpServletResponse response,ModelAndView  modelAndView){
	   
		Errors errors=(Errors) modelAndView.getModelMap().get(BindMessage.ERRORS_MODEL_KEY);		 
		this.initRequestContext(request, null, modelAndView.getModel());
		String jsonStr=null;
		if(errors!=null&&errors.hasErrors()){			
			this.bindStatus = new BindStatus(requestContext,errors);
			BindMessage bindMessage = new BindMessage(this.getBindStatus().getObjectName(), this.getBindStatus().getGlobalErrorMessages(), this.getBindStatus().getFieldValidateMessages());
			jsonStr=JSONParser.toJSONString(bindMessage);
		}else{
			@SuppressWarnings("unchecked")
			Map<String,String>  params=(Map<String, String>)modelAndView.getModel().get(BindMessage.SUCCESS);
			String message=this.requestContext.getMessage(params.get(BindResult.SUCCESS));
			BindMessage bindMessage=new BindMessage(message);
			jsonStr=JSONParser.toJSONString(bindMessage,datePattern);
		}
		return jsonStr;
	}
	/**获取异常信息*/
	protected String getExceptionMessage(BaseException baseException){
		StringBuilder builder=new StringBuilder();
		builder.append(baseException.getCode());
		builder.append(":");
		builder.append(baseException.getMessage());
		return builder.toString();
	}
	
	/***
	 * 初始化 RequestContext
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param Map
	 *            <String, Object>
	 */
	protected void initRequestContext(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		this.requestContext = new RequestContext(request, response, request
				.getSession().getServletContext(), model);
	}

 
	public void setDatePattern(String pattern) {
		this.datePattern = pattern;
	}
	
	/**
	 * @return the bindStatus
	 */
	public BindStatus getBindStatus() {
		return bindStatus;
	}

	/**
	 * @param bindStatus
	 *            the bindStatus to set
	 */
	public void setBindStatus(BindStatus bindStatus) {
		this.bindStatus = bindStatus;
	}

	/**
	 * @return the requestContext
	 */
	public RequestContext getRequestContext() {
		return requestContext;
	}

	/**
	 * @param requestContext
	 *            the requestContext to set
	 */
	public void setRequestContext(RequestContext requestContext) {
		this.requestContext = requestContext;
	}

	public String getContentType() {
		return "text/html;charset=utf-8";
	}

	public int getResponseStatus() {
		return this.responseStatus;
	}

	public void setResponseStatus(int responseStatus) {
		this.responseStatus = responseStatus;
	}
}
