/**
 * 
 */
package com.newtouch.lion.session;

import java.util.Locale;

import com.newtouch.lion.common.message.AppMessage;
import com.newtouch.lion.common.user.UserInfo;

/**
 * @author wanglijun
 * 
 */
public class AppContext {

	private static final ThreadLocal<UserInfo> currentUser = new ThreadLocal<UserInfo>();

	private static final ThreadLocal<AppMessage> messageList = new ThreadLocal<AppMessage>();

	private static Locale DEFAULT_LOCALE = Locale.CHINA;

	public static AppMessage getMessages() {
		AppMessage result = (AppMessage) messageList.get();
		if (result == null) {
			result = new AppMessage();
			messageList.set(result);
		}
		return result;
	}

	public static void setMessages(AppMessage msg) {
		messageList.set(msg);
	}

	public Locale getCurrentLocale() {
		if (getUserInfo() == null) {
			return DEFAULT_LOCALE;
		}
		Locale locale = getUserInfo().getPreferredLocale();
		return ((locale == null) ? DEFAULT_LOCALE : locale);
	}

	public static void setUserInfo(UserInfo userInfo) {
		currentUser.set(userInfo);
	}

	public static UserInfo getUserInfo() {
		if (currentUser.get() == null) {
			UserInfo userInfo = new UserInfo("admin", 1L);
			currentUser.set(userInfo);
		}
		return  currentUser.get();
	}

	public static void cleanUser() {
		currentUser.set(null);
	}

	public static void cleanMessage() {
		messageList.set(null);
	}
}
