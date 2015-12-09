/**
 * 
 */
package com.newtouch.lion.exception;


/**
 * @author wanglijun
 * 
 */
public class BusinessException extends BaseException{
	private static final long serialVersionUID = 8717800943293251036L;
	private String code;
	private Object[] params;

	public BusinessException(String code) {
		this(code, null, null);
	}

	public BusinessException(String code, String param) {
		this(code, new String[] { param });
	}

	public BusinessException(String code, String[] params) {
		this(code, params, null);
	}

	public BusinessException(String code, String[] params, Throwable cause) {
		super(code, cause);
		this.code = code;
		this.params = params;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object[] getParams() {
		return this.params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}
}
