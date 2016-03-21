package com.newtouch.lion.webtrans.domain;

import java.lang.reflect.Method;

/**
 * Created by wanglijun on 16/2/4.
 */
public class TransDefinition {
    /**交易号*/
    private String value;
    /**BeanName*/
    private String beanName;
    /**交易方法*/
    private Method method;
    /**交易类*/
    private Object bean;
    /**交易请求类*/
    private Class<?> requestBodyType;
    /**TODO */
    private boolean servletResponse;

    /***
     *
     * @param value
     * @param bean
     * @param beanName
     * @param method
     */
    public TransDefinition(String value, Object bean, String beanName, Method method) {
        this.value = value;
        this.bean = bean;
        this.beanName = beanName;
        this.method = method;
    }


    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Class<?> getRequestBodyType() {
        return requestBodyType;
    }

    public void setRequestBodyType(Class<?> requestBodyType) {
        this.requestBodyType = requestBodyType;
    }

    public boolean isServletResponse() {
        return servletResponse;
    }

    public void setServletResponse(boolean servletResponse) {
        this.servletResponse = servletResponse;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
