/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: MappingJsonHttpMessageConverter.java 9552 2015年2月3日 下午2:11:49 WangLijun$
*/
package com.newtouch.lion.web.json; 

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.core.JsonEncoding;


/**
 * <p>
 * Title: JSON转换扩展
 * </p>
 * <p>
 * Description: 主要针对MappingJackson2HttpMessageConverter进行扩展，当返回字符串（String类型且方式带@ResponseBody）是直接输出内容
 * 将特定JSON字符串功能转换放到Controller层中转换，提供JSON使用灵活性
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
public class MappingJsonHttpMessageConverter extends MappingJackson2HttpMessageConverter{

	/* (non-Javadoc)
	 * @see org.springframework.http.converter.json.MappingJacksonHttpMessageConverter#writeInternal(java.lang.Object, org.springframework.http.HttpOutputMessage)
	 */
	 
	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		if(object instanceof String){
			JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());
			byte[] bytes = ((String) object).getBytes(encoding.getJavaName());
			OutputStream out=outputMessage.getBody();
			out.write(bytes);
			out.flush();
		}else{
			super.writeInternal(object, outputMessage);
		}
	}
	
 
	
}

	