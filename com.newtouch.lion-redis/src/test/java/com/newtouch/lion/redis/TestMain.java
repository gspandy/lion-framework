package com.newtouch.lion.redis;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 *
 * @author wanglijun
 * @version 1.0
 *          2015-12-04 22:25.
 */
public class TestMain {

    private  String name;

    private String age;

    /**
     * @param age
     * @param name
     */
    public TestMain(String age, String name) {
        this.age = age;
        this.name = name;
    }

    public static void main(String[] args) {


        String city="city";


        System.out.println("args = [" + args + "]");
        System.out.println("city = " + city);


    }

    /**
     * Sets new name.
     *
     * @param name New value of name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return Value of name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets new age.
     *
     * @param age New value of age.
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * Gets age.
     *
     * @return Value of age.
     */
    public String getAge() {
        return age;
    }






}
