package com.newtouch.lion.webtrans.exception;

import org.apache.commons.lang.exception.NestableRuntimeException;

/**
 * Created by wanglijun on 16/2/4.
 */
public class TransDefintionException extends NestableRuntimeException {

    public TransDefintionException(String msg) {
        super(msg);
    }

    public TransDefintionException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
