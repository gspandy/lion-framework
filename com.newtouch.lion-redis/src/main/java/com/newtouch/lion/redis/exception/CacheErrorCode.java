/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisCacheableMethodAdvice.java 9552 2015年4月11日 下午2:30:25 WangLijun$
*/
package com.newtouch.lion.redis.exception;

/**
 * 缓存错误代码定义<br> 
 * 异常错误代码有：EC00001,缓存的KEY不 能为空
 * @author wanglijun
 */
public enum CacheErrorCode  implements ErrorCode{
    KEY_IS_NULL("EC00001","缓存的KEY不 能为空"),
    TARGET_NOTFOUND("E0000002","未找缓存目标类或方法"),
    PARAMETER_NOTFOND("E0000003","未找方法参数名称"),
    TYPE_ERROR("E0000004","集合类型转换失败"),
    CACHE_REFERENCE_NULL("E0000005","集合类型转换失败");
    /**错误代码*/
    private String code;
    /**错误消息*/
    private String message;
    
    /**默认构造*/
    private CacheErrorCode() {
        
    }
    /***
     * 
     * @param code 错误（异常）代码
     * @param message 错误信息
     */
    private CacheErrorCode(String code,String message){
        this.code=code;
        this.message=message;
    }
    /**
     * {@inheritDoc} 错误（异常）代码
     */
    @Override
    public String code() {
        return this.code;
    }
    /**
     * {@inheritDoc} 错误信息
     */
    @Override
    public String message() {
        return this.message;
    }
    
    
}
