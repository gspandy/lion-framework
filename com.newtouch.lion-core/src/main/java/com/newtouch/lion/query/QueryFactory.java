package com.newtouch.lion.query;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanglijun on 15/12/25.
 */
public class QueryFactory {



    public  static  Builder builder() {
        return null;
    }




    public class  Builder{
        public static final String ASC = "ASC";
        /** 降序 */
        public static final String DESC = "DESC";
        /**单例对象*/
        private  QueryCriteria queryCriteria;
        /** 首页索引，默认值为：0 */
        private int startIndex = 0;
        /** 页面显示记录数 默认为：10 */
        private int pageSize = 10;
        /** 排序字段 */
        private String orderField = null;
        /** 排序方面，默认为：升序 */
        private String orderDirection = QueryCriteria.ASC;

        /** 查询条件 */
        private Map<String, Object> queryCondition = new HashMap<String, Object>();
        /***
         * 添加查询条件，key-value
         *
         * @param key
         *            String
         * @param value
         *            Value
         */
        public void addQueryCondition(String key, Object value) {
            this.queryCondition.put(key, value);
        }

        /***
         * 添加条件,返回自身对象
         * @param key 查询参数名称
         * @param value 查询参值
         * @return Builder
         */
        public Builder condition(String key,Object value){
            this.queryCondition.put(key,value);
            return this;
        }

        /***
         * 设置排序字段和排序方向(升序,降序)
         * @param orderField
         * @param orderDirection
         * @return Builder
         */
        public Builder order(String orderField,String orderDirection){
            this.orderField=orderField;
            this.orderDirection=orderDirection;
            return this;
        }

        /***
         * 设置排序字段
         * @param orderField 排序字段名称
         * @return Builder
         */
        public Builder orderField(String orderField){
            this.orderField=orderField;
            return this;
        }

        /***
         *
         * @param orderDirection
         * @return Builder
         */
        public Builder orderDirection(String orderDirection){
            this.orderDirection=orderDirection;
            return this;
        }
    }
}
