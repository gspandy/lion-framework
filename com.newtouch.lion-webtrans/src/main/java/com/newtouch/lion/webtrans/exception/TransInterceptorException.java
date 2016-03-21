package com.newtouch.lion.webtrans.exception;

import org.apache.commons.lang.exception.NestableRuntimeException;

/**
 * Created by wanglijun on 16/2/4.
 */
public class TransInterceptorException extends NestableRuntimeException {

    public TransInterceptorException(String msg) {
        super(msg);
    }

    public TransInterceptorException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
