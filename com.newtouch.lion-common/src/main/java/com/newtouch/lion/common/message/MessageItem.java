/**
 * 
 */
package com.newtouch.lion.common.message;

/**
 * @author wanglijun
 * 
 */
public class MessageItem {
	private String keyOrMsg;
	private Object[] params;
	private int level;

	public MessageItem(int level, String keyOrMsg, Object[] params) {
		this.keyOrMsg = keyOrMsg;
		this.params = params;
		this.level = level;
	}

	public String getKeyOrMsg() {
		return this.keyOrMsg;
	}

	public void setKeyOrMsg(String keyOrMsg) {
		this.keyOrMsg = keyOrMsg;
	}

	public Object[] getParams() {
		return this.params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
