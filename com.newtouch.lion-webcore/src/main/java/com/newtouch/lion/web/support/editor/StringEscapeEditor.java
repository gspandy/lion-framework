/*
 * Copyright (c)  2014, Newtouch
 * All rights reserved. 
 *
 * $id: StringEscapeEditor.java 9552 2014-2-15 下午10:36:12 WangLijun$
 */
package com.newtouch.lion.web.support.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.JavaScriptUtils;

/**
 * <p>
 * Title:防止XSS攻击
 * </p>
 * <p>
 * Description:防止XSS攻击
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author wanglijun
 * @version 1.0
 */
public class StringEscapeEditor extends PropertyEditorSupport {

	/**过滤编码HTML*/
	private boolean escapeHTML;
	/**编码javascript*/
	private boolean escapeJavaScript;

	public StringEscapeEditor() {
		super();
	}

	public StringEscapeEditor(boolean escapeHTML, boolean escapeJavaScript) {
		super();
		this.escapeHTML = escapeHTML;
		this.escapeJavaScript = escapeJavaScript;
	}

	@Override
	public String getAsText() {
		Object value = getValue();
		return value != null ? value.toString() : "";
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null) {
			setValue(null);
		} else {
			String value = text;
			if (escapeHTML) {
				value = HtmlUtils.htmlEscape(value);
			}
			if (escapeJavaScript) {
				value = JavaScriptUtils.javaScriptEscape(value);
			}
			setValue(value);
		}
	}

}
