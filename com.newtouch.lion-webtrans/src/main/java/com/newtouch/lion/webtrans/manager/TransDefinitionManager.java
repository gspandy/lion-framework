package com.newtouch.lion.webtrans.manager;


import com.newtouch.lion.webtrans.domain.TransDefinition;

/**
 * Created by wanglijun on 16/2/4.
 */
public interface TransDefinitionManager {
    /***
     * 注册交易
     * @param definition
     */
    public void register(TransDefinition definition);

    /***
     * 获取交易对象
     * @param value
     * @return
     */
    public TransDefinition attribute(String value);

}
