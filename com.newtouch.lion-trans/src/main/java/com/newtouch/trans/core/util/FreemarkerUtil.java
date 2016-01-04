/* 文档密级：秘密 */
/*
 *  Copyright 2012, NEWTOUCH Co., Ltd.  All right reserved.
 *
 *  THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF NEWTOUCH CO., LTD.
 *  THE CONTENTS OF THIS FILE MAY NOT BE DISCLOSED
 *  TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM, IN WHOLE OR IN PART,
 *  WITHOUT THE PRIOR WRITTEN PERMISSION OF NEWTOUCH CO., LTD.
 */
package com.newtouch.trans.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * freemarker 处理工具类
 * 
 * @author dongfeng.zhang last modify：2012-2-20
 * @version 1.0
 */
public class FreemarkerUtil {
	private static final String ENCODING_UTF_8 = "UTF-8";
	public static final String ENCODING_GBK = "GBK";
	public static final String ENCODING_ISO8859_1 = "ISO8859-1";
	public static final String ENCODING_GB2312 = "GB2312";

	private static Logger logger = LoggerFactory.getLogger(FreemarkerUtil.class);
	private static Configuration cfg;

	static {
		cfg = new Configuration();

		// extend template loader
		cfg.setTemplateLoader(new TemplateLoader() {
			public Object findTemplateSource(String key) throws IOException {
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				InputStream inputStream = classLoader.getResourceAsStream(key);

				return inputStream;
			}

			public Reader getReader(Object templateSource, String encodeType) throws IOException {
				return new InputStreamReader((InputStream) templateSource);
			}

			public void closeTemplateSource(Object templateSource) throws IOException {
				((InputStream) templateSource).close();
			}

			public long getLastModified(Object templateSource) {
				return -1L;
			}
		});
	}

	/**
	 * 通过模板生成字符串
	 * 
	 * @param templatePath
	 *            模板路径
	 * @param values
	 *            数据项
	 * @param values
	 *            数据项
	 * @return 数据填充后的字符串
	 */
	public static String getString(String templatePath, Map<String, Object> values, String charset) {
		Writer writer = null;
		try {
			// Get the templat object
			Template template = cfg.getTemplate(templatePath, charset);
			writer = new StringWriter();
			// Merge the data-model and the template
			template.process(values, writer);
			writer.flush();

			return writer.toString();
		} catch (Exception e) {
			logger.error("template incorrent, exception : ", e);
			throw new RuntimeException("template incorrent, exception : ", e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception e) {
					logger.error("template incorrent, exception : ", e);
					throw new RuntimeException("template incorrent, exception : ", e);
				}
			}

		}
	}

	/**
	 * 通过模板生成字符串
	 * 
	 * @param templatePath
	 *            模板路径
	 * @param values
	 *            数据项
	 * 
	 * @return 数据填充后的字符串
	 */
	public static String getString(String templatePath, Map<String, Object> values) {
		return FreemarkerUtil.getString(templatePath, values, ENCODING_UTF_8);
	}
}
