package com.newtouch.lion.query;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wanglijun on 15/12/25.
 */
public class QueryBuilberTest {

    private static final Logger logger= LoggerFactory.getLogger(QueryBuilberTest.class);


    @Test
    public void builder(){

       QueryBuilder  queryBuilder=QueryBuilder.builder().condition("name","wanglijun").startIndex(10).pageSize(10).orderField("name").orderDirection(QueryBuilder.DESC);


        logger.info("toString():{}",queryBuilder.toString());




    }
}
