package com.newtouch.lion.common.excel;

/**
 * Created by wanglijun on 16/4/15.
 */
public class ExcelUtil {

    /***
     * 是否是2003的excel，返回true是2003
     * @param filePath
     * @return
     */
    public static boolean isExcel2003(String filePath) {

        return filePath.matches("^.+\\.(?i)(xls)$");

    }

    /***
     * 是否是2007的excel，返回true是2007
     * @param filePath
     * @return
     */
    public static boolean isExcel2007(String filePath)
    {

        return filePath.matches("^.+\\.(?i)(xlsx)$");

    }

}
