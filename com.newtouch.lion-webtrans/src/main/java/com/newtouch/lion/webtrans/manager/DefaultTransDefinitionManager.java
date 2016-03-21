package com.newtouch.lion.webtrans.manager;


import com.newtouch.lion.common.Assert;
import com.newtouch.lion.webtrans.domain.TransDefinition;
import com.newtouch.lion.webtrans.exception.TransRuntimeException;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wanglijun on 16/2/4.
 */
public class DefaultTransDefinitionManager implements  TransDefinitionManager{
    /***
     * 交易列表
     */
    protected Map<String,TransDefinition> definitions=new ConcurrentHashMap<String,TransDefinition>();


    /***
     * 获取交易对象
     *
     * @param value
     * @return
     */
    @Override
    public TransDefinition attribute(String value) {
        Assert.notNull(value,"TransCode must not be null!");
        TransDefinition definition= this.definitions.get(value);
        Assert.notNull(definition,"Trans has not registered!");
        return definition;
    }

    /***
     * 注册交易
     *
     * @param definition
     */
    @Override
    public void register(TransDefinition definition) {
        Assert.notNull(definition,"Trans must not be null!");
        String  value=definition.getValue();
        Assert.notNull(value,"TransCode must has text!");

        if(this.definitions.containsKey(value)){
            throw new TransRuntimeException("Trans code must not duplicate,the trans Code is " + value + " the className is " + definition.getBeanName());
        }

        Method method=definition.getMethod();

        Class[] parameterTypes=method.getParameterTypes();

        if(parameterTypes!=null&&parameterTypes.length!=0){
            Class parameterType=parameterTypes[0];
            definition.setRequestBodyType(parameterType);
            this.definitions.put(value,definition);
        }else{
            throw new TransRuntimeException("Trans controller must have  one parameter!");
        }
    }
}
