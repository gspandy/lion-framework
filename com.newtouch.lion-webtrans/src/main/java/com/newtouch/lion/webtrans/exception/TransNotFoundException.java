package com.newtouch.lion.webtrans.exception;

import org.apache.commons.lang.exception.NestableRuntimeException;

/**
 * Created by wanglijun on 16/2/4.
 */
public class TransNotFoundException extends NestableRuntimeException {

    public TransNotFoundException(String msg) {
        super(msg);
    }

    public TransNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
