package com.newtouch.lion.rpc.io;

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
public interface Formatter {
    /**
     * @param clazz  请求的接口
     * @param method 请求的方法
     * @param param  请求参数
     * @param <T>
     * @return
     */
    <T> String reqFormat(Class<T> clazz, String method, Object param);


    /**
     * @param param 响应结果
     * @return
     */
    String rsbFormat(Object param);

}
