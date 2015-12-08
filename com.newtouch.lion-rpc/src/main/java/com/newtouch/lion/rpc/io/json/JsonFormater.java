package com.newtouch.lion.rpc.io.json;

import com.newtouch.lion.rpc.io.Formatter;

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
 *          2015-12-05 12:01.
 */
public class JsonFormater implements Formatter {


    /**
     * @param clazz  请求的接口
     * @param method 请求的方法
     * @param param  请求参数
     * @return
     */
    @Override
    public <T> String reqFormat(Class<T> clazz, String method, Object param) {
        Request request = new Request(clazz, Smethod, param);
       // return JSON.toString(request);
        return null;
    }

    /**
     * @param param 响应结果
     * @return
     */
    @Override
    public String rsbFormat(Object param) {
        return null;
    }
}
