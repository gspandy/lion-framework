package com.newtouch.lion.webtrans.exception;

import org.apache.commons.lang.exception.NestableRuntimeException;

/**
 * Created by wanglijun on 16/2/4.
 */
public class TransRuntimeException extends NestableRuntimeException {

    public TransRuntimeException(String msg) {
        super(msg);
    }

    public TransRuntimeException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
