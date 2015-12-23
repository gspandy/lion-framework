/**
 * 
 */
package com.newtouch.lion.common.message;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanglijun
 * 
 */
public class AppMessage {
	
	public static final int MESSAGE_LEVEL_SYS_SEVERE = 0;
	
	public static final int MESSAGE_LEVEL_BIZ_SEVERE = 1;
	
	public static final int MESSAGE_LEVEL_WARNING = 2;
	
	public static final int MESSAGE_LEVEL_NOTICE = 3;
	
	private List<MessageItem> msgList = new ArrayList<MessageItem>();

	public List<MessageItem> getMsgList() {
		return this.msgList;
	}

	public void addMsg(int level, String keyOrMsg, Object[] params) {
		MessageItem mi = new MessageItem(level, keyOrMsg, params);
		this.msgList.add(mi);
	}
}
