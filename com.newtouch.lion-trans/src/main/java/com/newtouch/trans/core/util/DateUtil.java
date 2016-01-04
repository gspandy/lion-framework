package com.newtouch.trans.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static void main(String[] args) {
		String accesssToken="cpic.com.cn"+"wangyanli"+new Date().getTime();
		// 创建一个MD5Tool类
		DateUtil dateUtil = new DateUtil();			

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间格式			
		Calendar c=Calendar.getInstance();
		//获取当前时间
		Date nowDate=c.getTime();
		String invalidStr="2014-09-15 20:03:22";
		Date invalidTime=DateUtil.stringToDate(invalidStr);
		int result=nowDate.compareTo(invalidTime);
		System.out.println(result);

	}

	
	public static String dateToString(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间格式			
		String dateStr = sdf.format(d);
		return dateStr;
	}

	public static Date stringToDate(String s) {
		DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=null;
		try {
			date = fmt.parse(s);
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		return date;

	}
}
