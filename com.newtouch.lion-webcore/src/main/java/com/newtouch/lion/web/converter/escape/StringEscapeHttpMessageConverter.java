/*
 * Copyright (c)  2014, Newtouch
 * All rights reserved. 
 *
 * $id: StringEscapeHttpMessageConverter.java 9552 2014年12月29日 上午10:15:50 WangLijun$
 */
package com.newtouch.lion.web.converter.escape;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.lang.CharEncoding;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.StreamUtils;
import org.springframework.web.util.HtmlUtils;

/**
 * <p>
 * Title: String Escape
 * </p>
 * <p>
 * Description: String Escape
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
public class StringEscapeHttpMessageConverter extends StringHttpMessageConverter {
	/**编写定义*/
	private static final Charset DEFAULT_CHARSET = Charset.forName(CharEncoding.UTF_8);

	protected void writeInternal(String s, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		s = HtmlUtils.htmlEscape(s);
		StreamUtils.copy(s, DEFAULT_CHARSET, outputMessage.getBody());
	}
}
