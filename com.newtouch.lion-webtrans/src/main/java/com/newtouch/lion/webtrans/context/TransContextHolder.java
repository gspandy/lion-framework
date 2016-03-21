package com.newtouch.lion.webtrans.context;

import org.springframework.core.NamedThreadLocal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by wanglijun on 16/2/4.
 */
public class TransContextHolder {

    private static final ThreadLocal<HttpServletRequest> requestsHolder = new NamedThreadLocal("Request");

    private static final ThreadLocal<HttpServletResponse> responsesHolder = new NamedThreadLocal("Response");

    public TransContextHolder() {

    }

    public static void bindRequest(HttpServletRequest request){
        requestsHolder.set(request);
    }

    public static void bindResponse(HttpServletResponse response){

    }

    public static HttpServletResponse currentResponse(){
        return  responsesHolder.get();
    }

    public static HttpServletRequest currentRequest(){
        return requestsHolder.get();
    }

    public static HttpSession currentSession(){
        HttpServletRequest request=currentRequest();
        return request==null?null:request.getSession();
    }
}
