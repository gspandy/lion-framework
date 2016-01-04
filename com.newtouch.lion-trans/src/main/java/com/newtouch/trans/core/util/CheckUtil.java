package com.newtouch.trans.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil {
	public static boolean checkEmail(String email){
		boolean flag=false;
		try{
			String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)？\\.)+[a-zA-Z]{2,}$";	
			Pattern regex = Pattern.compile(check);	
			Matcher matcher = regex.matcher(email);	
			flag = matcher.matches();
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	
	public static boolean isTelphone(String telphone){
		boolean flag=false;
		try{
			String check = "^1\\d{10}$";	
			Pattern regex = Pattern.compile(check);	
			Matcher matcher = regex.matcher(telphone);	
			flag = matcher.matches();
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	




}
