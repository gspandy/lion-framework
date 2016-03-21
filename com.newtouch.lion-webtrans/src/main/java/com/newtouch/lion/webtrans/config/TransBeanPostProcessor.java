package com.newtouch.lion.webtrans.config;


import com.newtouch.lion.webtrans.domain.TransDefinition;
import com.newtouch.lion.webtrans.manager.TransDefinitionManager;
import com.newtouch.lion.webtrans.trans.Trans;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Method;

/**
 * Created by wanglijun on 16/2/4.
 */
public class TransBeanPostProcessor implements BeanPostProcessor {

    private TransDefinitionManager definitionManager;

    @Override
    public Object postProcessAfterInitialization(Object obj, String objName) throws BeansException {
        Class clazz=obj.getClass();

        if(clazz.isAnnotationPresent(Controller.class)){

            for(Method method:clazz.getMethods()){
                if(method.isAnnotationPresent(Trans.class)){
                    Trans trans=method.getAnnotation(Trans.class);
                    TransDefinition definition=this.definition(trans,obj,objName,method);
                    //注册
                    this.definitionManager.register(definition);
                }
            }
        }

        return obj;
    }

    @Override
    public Object postProcessBeforeInitialization(Object obj, String objName) throws BeansException {
        return obj;
    }

    /**
     *
     * @param trans
     * @param bean
     * @param objectName
     * @param method
     * @return
     */
    protected TransDefinition definition(Trans trans,Object bean,String objectName,Method method){
        String value=trans.value();

        boolean servletResponse=trans.servletResponse();

        TransDefinition definition=new TransDefinition(value,bean,objectName,method);
        definition.setServletResponse(servletResponse);
        return definition;
    }


    public void setDefinitionManager(TransDefinitionManager definitionManager) {
        this.definitionManager = definitionManager;
    }
}
