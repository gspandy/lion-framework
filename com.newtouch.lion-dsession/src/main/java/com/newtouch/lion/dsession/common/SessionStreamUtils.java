package com.newtouch.lion.dsession.common;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * SessionStreamUtils<br>
 * 
 * @author wanglijun
 */
public class SessionStreamUtils {

    /**日志*/
    private static final Logger logger= LoggerFactory.getLogger(SessionStreamUtils.class);

    /**
     * 5000 constant
     */
    private static final int FIVE =5000;
    
    /**
     * constructor
     */
    private SessionStreamUtils() {
    }


    
    /**
     * 
     * 功能描述:对象转换成自己数组 <br>
     * @param obj obj
     * @return byte
     * @throws IOException io exception
     */
    public static byte[] objectToByteArray(Object obj) throws IOException {
        if (obj == null) {
            return null;
        }

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(FIVE);
        ObjectOutputStream  os = new ObjectOutputStream(new BufferedOutputStream(byteStream));
        os.flush();
        os.writeObject(obj);
        os.flush();
        byte[] sendBuf = byteStream.toByteArray();
        os.close();
        return sendBuf;
    }

    /**
     * 
     * 功能描述:自己数组转换成对象 <br>
     * @param bytes bytes
     * @return object
     * @throws IOException ioexception
     */
    public static Object byteArrayToObject(byte[] bytes) {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        Object obj = null;
        ByteArrayInputStream bis=null;
        ObjectInputStream ois=null;
        try {
              bis = new ByteArrayInputStream(bytes);
              ois = new ObjectInputStream(new BufferedInputStream(bis));
              obj = ois.readObject();
        } catch (ClassNotFoundException e) {
           logger.error(e.getMessage(),e);
        } catch (IOException e) {
           logger.error(e.getMessage(),e);
        } finally{
            IOUtils.closeQuietly(bis);
            IOUtils.closeQuietly(ois);
        }
        return obj;
    }
}
