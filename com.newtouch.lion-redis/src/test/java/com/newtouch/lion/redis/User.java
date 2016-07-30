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
 *          2015-12-07 14:43.
 */
public class User {
    private String name;

    private int old;


    public User() {
    }

    public User(String name, int old) {
        this.name = name;
        this.old = old;
    }

    /**
     * Gets old.
     *
     * @return Value of old.
     */
    public int getOld() {
        return old;
    }

    /**
     * Sets new old.
     *
     * @param old New value of old.
     */
    public void setOld(int old) {
        this.old = old;
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
     * Sets new name.
     *
     * @param name New value of name.
     */
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", old=" + old +
                '}';
    }
}
