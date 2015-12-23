/**
 * 
 */
package com.newtouch.lion.common.message;

/**
 * @author wanglijun
 * 
 */
public class Information extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private String keyOrMsg;
	
	private Object[] params;

	public Information(String keyOrMsg) {
		this.keyOrMsg = keyOrMsg;
	}

	public Information(String keyOrMsg, Object[] params) {
		this.keyOrMsg = keyOrMsg;
		this.params = params;
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
}
