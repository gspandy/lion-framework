/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisCacheableMethodAdvice.java 9552 2015年4月11日 下午2:30:25 WangLijun$
*/
package com.newtouch.lion.redis.exception;

/**
 * 接口异常处理<br> 
 *
 * @author wanglijun
 */
public class CacheException extends RuntimeException{

    /**
     */
    private static final long serialVersionUID = 6892794888177533754L;
    
    /**错误代码*/
    private  CacheErrorCode cacheErrorCode;
    /**默认构造函数*/
    public CacheException() {
         super();
    }
    /**
     * @param errorCode 错误代码
     */
    public CacheException(CacheErrorCode cacheErrorCode) {
        super(cacheErrorCode.code());
        this.cacheErrorCode = cacheErrorCode;
    }
    
    public  CacheException(CacheErrorCode cacheErrorCode,Throwable e){
        super(cacheErrorCode.code(),e);
        this.cacheErrorCode=cacheErrorCode;
    }
    
    
    
    /* (non-Javadoc)
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        StringBuilder sb=new StringBuilder();
        sb.append(super.getMessage());
        sb.append(",");
        sb.append(this.cacheErrorCode.message());
        return sb.toString();
    }
    /**
     * @return the 缓存错误代码
     */
    public CacheErrorCode getCacheErrorCode() {
        return cacheErrorCode;
    }
    
    
    
     
}
