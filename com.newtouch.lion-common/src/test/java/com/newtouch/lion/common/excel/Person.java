package com.newtouch.lion.common.excel;

import com.newtouch.lion.common.excel.annotation.MapperCell;

/**
 * Created by wanglijun on 16/4/15.
 */
public class Person {

    @MapperCell(cellName = "名称")
    private String name;

    @MapperCell(cellName = "联系电话")
    private String phone;

    @MapperCell(cellName = "地址")
    private String address;

    @MapperCell(cellName = "一级分类ID")
    private int type;

    @MapperCell(cellName = "经度")
    private double lat;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Person{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", type=" + type +
                ", lat=" + lat +
                '}';
    }
}
