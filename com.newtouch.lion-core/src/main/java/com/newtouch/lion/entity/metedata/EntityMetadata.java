package com.newtouch.lion.entity.metedata;

/**
 * Created by wanglijun on 16/4/15.
 */
public interface  EntityMetadata<T> {

    /**
     * 返回领域对象的类型.
     *
     * @return
     */
    Class<T> getJavaType();

}
