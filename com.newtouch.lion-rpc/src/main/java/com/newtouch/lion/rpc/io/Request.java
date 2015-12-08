package com.newtouch.lion.rpc.io;

import java.io.Serializable;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 *
 * @author wanglijun
 * @version 1.0
 *          2015-12-05 11:45.
 */
public class Request implements Serializable{

    /**序列化*/
    private static final long serialVersionUID = 8721824752065516595L;


    private Class clazz;

    private String method;

    private Object param;

    public Request() {

    }

    public Request(Class clazz, String method, Object param) {
        this.clazz = clazz;
        this.method = method;
        this.param = param;
    }

    /**
     * Gets param.
     *
     * @return Value of param.
     */
    public Object getParam() {
        return param;
    }

    /**
     * Sets new param.
     *
     * @param param New value of param.
     */
    public void setParam(Object param) {
        this.param = param;
    }

    /**
     * Gets clazz.
     *
     * @return Value of clazz.
     */
    public Class getClazz() {
        return clazz;
    }

    /**
     * Sets new clazz.
     *
     * @param clazz New value of clazz.
     */
    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    /**
     * Gets method.
     *
     * @return Value of method.
     */
    public String getMethod() {
        return method;
    }

    /**
     * Sets new method.
     *
     * @param method New value of method.
     */
    public void setMethod(String method) {
        this.method = method;
    }
}
