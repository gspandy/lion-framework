package com.newtouch.lion.dsession.exception;

import com.newtouch.lion.exception.BaseException;

/**
 * Created by wanglijun on 16/8/17.
 */
public class DistributedSessionException extends BaseException{

    /***
     * 代码消息为空
     *
     * @param code 错误代码
     */
    public DistributedSessionException(String code) {
        super(code);
    }

    /***
     * @param code 错误代码
     * @param msg  错误消息
     */
    public DistributedSessionException(String code, String msg) {
        super(code, msg);
    }

    /***
     * @param code
     * @param msg
     * @param cause
     */
    public DistributedSessionException(String code, String msg, Throwable cause) {
        super(code, msg, cause);
    }

    /**
     * Construct a {@code NestedRuntimeException} with the specified detail message
     * and nested exception.
     *
     * @param msg   the detail message
     * @param cause the nested exception
     */
    public DistributedSessionException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
