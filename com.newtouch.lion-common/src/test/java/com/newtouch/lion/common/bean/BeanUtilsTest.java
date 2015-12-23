package com.newtouch.lion.common.bean;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by wanglijun on 15/12/23.
 */
public class BeanUtilsTest {
    /**
     * 日志
     */
    private static final Logger logger= LoggerFactory.getLogger(BeanUtilsTest.class);

    @Test
    public void convertBean(){

        Person  person=new Person("name",15,"15502512629");
        try {
          Map<String,String> params=BeanUtils.convertBean(person);

          for(Map.Entry<String,String> entry:params.entrySet()){
              logger.info("key:{},value:{}",entry.getKey(),entry.getValue());
          }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }



    @Test
    public  void convertBeanToMap(){
        Person  person=new Person("name",15,"15502512629");
        Map<String,Object> params=BeanUtils.convertBeanToMap(person);

        for(Map.Entry<String,Object> entry:params.entrySet()){
            logger.info("key:{},value:{}",entry.getKey(),entry.getValue());
        }

    }



}
