package com.newtouch.lion.common.bean;

/**
 * Created by wanglijun on 15/12/23.
 */
public class Person {

    private String name;

    private Integer age;

    private String mobile;


    public Person() {
    }


    /***
     *
     * @param name
     * @param age
     * @param mobile
     */
    public Person(String name, Integer age, String mobile) {
        this.name = name;
        this.age = age;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
