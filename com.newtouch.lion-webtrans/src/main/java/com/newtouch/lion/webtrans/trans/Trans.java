package com.newtouch.lion.webtrans.trans;

import java.lang.annotation.*;

/**
 * Created by wanglijun on 16/2/4.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Trans {
    /***
     * 交易号
     * @return
     */
    String  value();

    /***
     * 是否servlet
     * @return
     */
    boolean servletResponse() default false;
}
