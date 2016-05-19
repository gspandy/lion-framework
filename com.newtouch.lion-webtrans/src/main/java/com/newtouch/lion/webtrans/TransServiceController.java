package com.newtouch.lion.webtrans;

import com.alibaba.fastjson.JSON;

import com.newtouch.lion.common.SpringContextUtil;
import com.newtouch.lion.webtrans.domain.TransDefinition;
import com.newtouch.lion.webtrans.domain.TransRequest;
import com.newtouch.lion.webtrans.domain.TransResponse;
import com.newtouch.lion.webtrans.exception.TransNotFoundException;
import com.newtouch.lion.webtrans.manager.TransDefinitionManager;
import com.newtouch.lion.webtrans.security.TransSecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by wanglijun on 16/2/5.
 */
@Controller
@RequestMapping(value ="/api.do")
public class TransServiceController {
    /***
     * 交易类
     */
    private static final Logger logger= LoggerFactory.getLogger(TransServiceController.class);

    private static  ThreadLocal<Map>  threadLocal=new ThreadLocal<>();


    @Autowired
    private TransDefinitionManager definitionManager;


    private Collection<TransSecurity>  transSecurities;


    public TransServiceController() {
        super();
    }

    @RequestMapping
    @ResponseBody
    public TransResponse service(@RequestBody TransRequest request, HttpServletRequest  servletRequest, HttpServletResponse  servletResponse){
        String requestNo = String.valueOf(System.currentTimeMillis() / 1000L) + buildRandom(5);
        MDC.clear();
        MDC.put("RequestNo", requestNo);
        Assert.notNull(request, "ApiRequest must not be null!");
        String transCode = request.getTransCode();
        Assert.hasText(transCode, "TransCode must has text!");
        Object requestBody = request.getRequestBody();
        String jsonBody = requestBody.toString();
        this.logger.info("transCode:{},requestBody:{}", transCode, jsonBody);
        TransDefinition definition = this.definitionManager.attribute(transCode);
        if(definition == null) {
            throw new TransNotFoundException("Not Register ApiService, Which TransCode is : [" + transCode + "]");
        } else {
            Method method = definition.getMethod();
            Object bean = definition.getBean();
            Class requestBodyType = definition.getRequestBodyType();
            Object parameter = JSON.parseObject(jsonBody, requestBodyType);
            TransResponse response= new TransResponse();
            response.setStatus(Integer.valueOf(200));
            response.setTransCode(transCode);
            Iterator remoteHost = this.getApiServiceSecurity().iterator();

            TransSecurity params;
            do {
                if(!remoteHost.hasNext()) {
                    String remoteHost1 = servletRequest.getHeader("X-Real-IP");
                    MDC.put("remoteHost", remoteHost1);
                    HashMap params1 = new HashMap();
                    params1.put("remoteHost", remoteHost1);
                    threadLocal.set(params1);
                    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
                    Validator validator = validatorFactory.getValidator();

                    try {
                        validator.validate(parameter, new Class[0]);
                        Object responseBody = null;
                        if(definition.isServletResponse()) {
                            responseBody = method.invoke(bean, new Object[]{parameter, servletResponse});
                        } else {
                            responseBody = method.invoke(bean, new Object[]{parameter});
                        }

                        response.setResponseBody(responseBody);
                    } catch (Exception e) {
                        String  errorMsg = e.getMessage();
                        if(errorMsg == null) {
                            errorMsg = e.getCause().getMessage();
                        }
                        logger.error("接口调用异常：{}", errorMsg, e);
                        response.setErrorMsg(errorMsg);
                        response.setStatus(500);
                    } finally {
                        threadLocal.remove();
                    }

                    this.logger.info("responseBody:{}", JSON.toJSONString(response));
                    MDC.clear();
                    return response;
                }

                params = (TransSecurity)remoteHost.next();
            } while(params.hasPermission(transCode));

            response.setStatus(Integer.valueOf(403));
            MDC.clear();
            return response;
        }

    }

    private Collection<TransSecurity> getApiServiceSecurity() {
        if(this.transSecurities != null) {
            return this.transSecurities;
        } else {
            Map map = SpringContextUtil.getApplicationContext().getBeansOfType(TransSecurity.class);
            if(map != null && !map.values().isEmpty()) {
                this.transSecurities = map.values();
                return this.transSecurities;
            } else {
                return new ArrayList();
            }
        }
    }


    private static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if(random < 0.1D) {
            random += 0.1D;
        }

        for(int i = 0; i < length; ++i) {
            num *= 10;
        }

        return (int)(random * (double)num);
    }
}
