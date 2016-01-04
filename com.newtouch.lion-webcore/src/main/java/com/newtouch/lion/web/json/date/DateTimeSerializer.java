/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: DateTimeDeserializer.java 9552 2015年1月9日 下午5:03:51 WangLijun$
 */
package com.newtouch.lion.web.json.date;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.newtouch.lion.common.date.DateUtil;

/**
 * <p>
 * Title: 日期格式化
 * </p>
 * <p>
 * Description: 日期格式化
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
public class DateTimeSerializer extends JsonSerializer<Date> {

 
	@Override
	public void serialize(Date value, JsonGenerator generator,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		generator.writeString(DateUtil.formatDate(value, DateUtil.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS));
		
	}

}
