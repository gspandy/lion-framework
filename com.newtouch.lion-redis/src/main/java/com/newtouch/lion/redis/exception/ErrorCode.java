/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisCacheableMethodAdvice.java 9552 2015年4月11日 下午2:30:25 WangLijun$
*/
package com.newtouch.lion.redis.exception;

/**
 * Cache 错误接口定义 <br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface ErrorCode {
    /**
     * 功能描述: <br>
     * 获取枚举类型编码.
     *
     * @return 类型编码
     * @since [商品视图与服务/version=1]
     */
    String code();
    
    /**
     * 功能描述: <br>
     * 获取枚举类型字符描述.
     *
     * @return 类型字符描述
     * @since [商品视图与服务/version=1]
     */
    String message();
}
