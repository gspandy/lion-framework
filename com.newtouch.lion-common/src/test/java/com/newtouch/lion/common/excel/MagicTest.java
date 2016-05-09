package com.newtouch.lion.common.excel;

import net.sf.jmimemagic.*;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by wanglijun on 16/4/15.
 */
public class MagicTest {

    @Test
    public void parse(){

        try {
            long  start=System.currentTimeMillis();
           MagicMatch  magicMatch= Magic.getMagicMatch(new File("./工作簿1.xlsx"),true);
            System.out.println(magicMatch.getExtension());
            Long end=System.currentTimeMillis()-start;

            System.out.println("end:"+end);

            start=System.currentTimeMillis();



            magicMatch= Magic.getMagicMatch(new File("./pom.xml"),true);
            System.out.println(magicMatch.getExtension());

             end=System.currentTimeMillis()-start;

            System.out.println("end:"+end);

        } catch (MagicParseException e) {
            e.printStackTrace();
        } catch (MagicMatchNotFoundException e) {
            e.printStackTrace();
        } catch (MagicException e) {
            e.printStackTrace();
        }
    }
}
