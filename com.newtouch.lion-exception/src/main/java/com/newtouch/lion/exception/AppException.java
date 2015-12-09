/**
 * 
 */
package com.newtouch.lion.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author wanglijun
 * 
 */
public class AppException  extends BaseException {
		
	private static final long serialVersionUID = 5439915454935047935L;
	
	public static final String copyright = "";
	
	private String errorCode;
	
	private Collection<AppException> exceptions = new ArrayList<AppException>();

	private String messageKey = null;

	private Object[] messageArgs = null;

	protected Throwable rootCause = null;

	public AppException(String message) {
		super(message);
	}

	public AppException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	
	

	public String getErrorCode() {
		return this.errorCode;
	}


	public Collection<AppException> getCollection() {
		return this.exceptions;
	}

	public void addException(AppException ex) {
		this.exceptions.add(ex);
	}

	public void setMessageKey(String key) {
		this.messageKey = key;
	}

	public String getMessageKey() {
		return this.messageKey;
	}

	public void setMessageArgs(Object[] args) {
		this.messageArgs = args;
	}

	public Object[] getMessageArgs() {
		return this.messageArgs;
	}

	public void setRootCause(Throwable anException) {
		this.rootCause = anException;
	}

	public Throwable getRootCause() {
		return this.rootCause;
	}

	public void printStackTrace() {
		printStackTrace(System.err);
	}

	public void printStackTrace(PrintStream outStream) {
		printStackTrace(new PrintWriter(outStream));
	}

	public void printStackTrace(PrintWriter writer) {
		super.printStackTrace(writer);
		if (getRootCause() != null) {
			getRootCause().printStackTrace(writer);
		}
		writer.flush();
	}
}