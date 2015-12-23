package com.newtouch.lion.common;

import org.junit.Test;

/**
 * Created by wanglijun on 15/12/23.
 */
public class AssertTest {

    @Test
    public void isNull(){
        Assert.isNull(null);
    }


    @Test
    public void notNull(){
        Assert.notNull(null);
    }

    @Test
    public void notNullMessage(){
        Assert.notNull(null,"不能为空");
    }
}
