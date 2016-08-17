package com.newtouch.lion.dsession.common;

import java.util.UUID;

/**
 * 
 * 〈uuid generator〉<br>
 * 〈功能详细描述〉
 * 
 * @author wanglijun
 */
public class UUIDGenerator {



    /**
     * 8
     */
    private static final int EIGHT = 8;

    /**
     * 9
     */
    private static final int NINE = 9;

    /**
     * 13
     */
    private static final int THR = 13;

    /**
     * 14
     */
    private static final int FOURTEEN = 14;

    /**
     * 18
     */
    private static final int EIGTEEN = 18;

    /**
     * 19
     */
    private static final int NITEEN = 19;

    /**
     * 23
     */
    private static final int THWEN_THREE = 23;

    /**
     * 24
     */
    private static final int THEWN_FOUR = 24;

    /**
     * constructor
     */
    private UUIDGenerator() {
    }
    
    /**
     * 获得一个UUID
     * 
     * @return String UUID
     */
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        // 去掉“-”符号
        return s.substring(0, EIGHT) + s.substring(NINE, THR)
                +  s.substring(FOURTEEN, EIGTEEN)
                + s.substring(NITEEN, THWEN_THREE) + s.substring(THEWN_FOUR);
    }

    /**
     * 获得指定数目的UUID
     * 
     * @param number int 需要获得的UUID数量
     * @return String[] UUID数组
     */
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUID();
        }
        return ss;
    }

}
