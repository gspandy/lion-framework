package com.newtouch.lion.common.excel;

import java.io.IOException;
import java.util.List;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wanglijun on 16/4/15.
 */
public class FastExcelTest {

    private static final Logger LOG    = LoggerFactory.getLogger(FastExcel.class);

    @Test
    public void  parse(){
        FastExcel fastExcel = null;
        try {
            fastExcel = new FastExcel("./工作簿1.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
        fastExcel.setSheetName("活动信息数据");
        List<Person> tests = fastExcel.parse(Person.class);
        if(null != tests && !tests.isEmpty()) {
            for(Person  myTest : tests) {
                LOG.debug("记录:{}", myTest.toString());
            }
        } else {
            LOG.debug("没有结果");
        }
    }
}
