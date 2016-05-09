package com.newtouch.lion.common.grid;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kui.yang
 * @since 1.0
 * create date: 15/8/18 下午2:54
 * 定义一个与前端dbGrid匹配的page对象用于与后端VO类匹配使用
 */
public class GridPage {
    private int pageNumber;
    private int pageSize;
    private String keyWord;
    /**
     * 需要查询的字段，支持级联属性
     */
    private List<String> queryFields;
    /**
     * 需要查询的对象
     */
    private Class queryObject;

    private boolean hasInitFields;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }


    public List<String> getQueryFields() {
        return queryFields;
    }

    public void setQueryFields(List<String> queryFields) {
        this.queryFields = queryFields;
    }

    /**
     * 增加指定的字段名称
     * @param fieldName
     */
    public void addQueryField(String fieldName) {
        this.initGetField();
        this.queryFields.add(fieldName);
    }

   public void addQueryField(String[] fieldNames){
       this.initGetField();
       for(String name:fieldNames){
           this.queryFields.add(name);
       }
   }

    /**
     * 移除指定的字段名称，不参与查询
     * @param fieldName
     */
    public void removeQueryField(String fieldName){
        this.initGetField();
        if(this.queryFields.isEmpty()){
            return;
        }
        for(int i=0;i<this.queryFields.size();i++){
            String key = this.queryFields.get(i);
            if(fieldName.equals(key)){
                this.queryFields.remove(key);
            }
        }
    }

    public void removeQueryField(String[] fieldNames) {
        this.initGetField();
        for(String name:fieldNames){
            removeQueryField(name);
        }
    }

        public Class getQueryObject() {
        return queryObject;
    }

    public void setQueryObject(Class queryObject) {
        this.queryObject = queryObject;
    }

    /**
     * 返回根据查询字段拼装好的字符串如：name,code
     *
     * @return
     */
    public String getQueryString() {
        this.initGetField();

        if(this.queryFields.isEmpty()){
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (String s : queryFields) {
            sb.append(s).append(",");
        }
        String as = sb.toString();
        as = as.substring(0, as.length() - 1);
        return as;
    }


    /**
     * 初始化获取指定对象所有属性名称
     */
    private void initGetField() {
        if (hasInitFields) {
            return;
        }
        if (this.queryFields == null) {
            this.queryFields = new ArrayList<String>();
        }
        if (this.queryObject == null) {
            return;
        }
        Field[] fs = this.queryObject.getDeclaredFields();
        for (Field f : fs) {
            this.queryFields.add(f.getName());
        }
        this.hasInitFields = true;
    }

    /**
     * 根据属性名称将查询结果转换成map
     *
     * @param queryResult 查询结果数组
     * @return
     */
    public Map<String, Object> convertResult(Object[] queryResult) {
        int index = 0;
        Map map = new HashMap();
        for (String key : this.queryFields) {
            map.put(key, queryResult[index++]);
        }
        return map;
    }

}
