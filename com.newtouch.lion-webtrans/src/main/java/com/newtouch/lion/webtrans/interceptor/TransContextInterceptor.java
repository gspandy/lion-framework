package com.newtouch.lion.webtrans.interceptor;


import com.newtouch.lion.webtrans.context.TransContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wanglijun on 16/2/4.
 */
public class TransContextInterceptor extends HandlerInterceptorAdapter {

    public TransContextInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        TransContextHolder.bindRequest(request);
        TransContextHolder.bindResponse(response);

        response.addHeader("Access-Control-Allow-Origin","*");
        return super.preHandle(request, response, handler);
    }
}
