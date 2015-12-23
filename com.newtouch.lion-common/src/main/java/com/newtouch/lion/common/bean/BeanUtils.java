/*
 * Copyright (c)  2016, Newtouch
 * All rights reserved. 
 *
 * $id: BeanUtils.java 9552 2013-6-5 下午4:17:43 WangLijun$
 */
package com.newtouch.lion.common.bean;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
/**
 * <p>
 * Title:BeanUtils
 * </p>
 * <p>
 * Description: 获取Bean的属性，将Bean转换为Map<String,Object>,调用Bean的方法。
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 *
 * @author WangLijun
 * @version 1.0
 */
public class BeanUtils {

    private final static Logger log = LoggerFactory.getLogger(BeanUtils.class);

    /***
     * bean
     *
     * @return Map<String, String>
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Map<String, String> convertBean(Object bean)
            throws IntrospectionException, IllegalAccessException,
            InvocationTargetException {
        Class<?> type = bean.getClass();

        Map<String, String> returnMap = new HashMap<String, String>();

        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result.toString());
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }

    /***
     * 获取对象的属性值
     *
     * @param Object bean
     * @return PropertyDescriptor[]
     */
    public static PropertyDescriptor[] getPropertyDescriptors(Object bean) {
        PropertyDescriptor[] propertyDescriptors = null;
        Class<?> beanClass = bean.getClass();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
            propertyDescriptors = beanInfo.getPropertyDescriptors();
        } catch (IntrospectionException e) {
            // TODO
            log.error(e.getMessage(), e);
        }
        return propertyDescriptors;
    }

    /***
     * 根据对象的属性调用该对象的Get方法。
     *
     * @param Object             bean
     * @param PropertyDescriptor propertyDescriptor
     * @return
     */
    public static Object methodInvoke(Object bean, PropertyDescriptor propertyDescriptor) {
        Object result = null;
        Method readMethod = propertyDescriptor.getReadMethod();
        try {
            result = readMethod.invoke(bean, new Object[0]);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 将对象转换为Map对象
     *
     * @param Object bean
     * @return Map<String,Object>
     */
    public static Map<String,Object> convertBeanToMap(Object bean) {

        Map<String, Object> returnMap = new HashMap<String,Object>();

        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(bean);

        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Object result = BeanUtils.methodInvoke(bean, descriptor);
                returnMap.put(propertyName, result);
            }
        }
        return returnMap;
    }

}
