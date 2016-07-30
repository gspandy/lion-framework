package com.newtouch.lion.redis.util;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 
 * 〈SessionStreamUtils〉<br>
 * 〈功能详细描述〉
 * 
 * @author wanglijun
 */
public class SessionStreamUtils {
    
    private final static Logger logger = LoggerFactory.getLogger(SessionStreamUtils.class);

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
     * 功能描述:对象转换成自己数组 <br>
     * @param obj obj
     * @return byte
     * @throws IOException io exception
     */
    public static byte[] objectToBytes(Object obj){
    	 if (obj == null) {
             return null;
         }
    	 byte[] sendBuf=null;
         ObjectOutputStream os = null;
         ByteArrayOutputStream byteStream=null;
         try {
        	 byteStream= new ByteArrayOutputStream(FIVE);
        	 os = new ObjectOutputStream(new BufferedOutputStream(byteStream));
			 os.flush();
	         os.writeObject(obj);
	         os.flush();
	         sendBuf = byteStream.toByteArray();
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}finally{
			IOUtils.closeQuietly(os);
			IOUtils.closeQuietly(byteStream);
		}
        return sendBuf;
       
    }
    
    /**
     * 
     * 自己数组转换成对象 <br>
     * @param bytes bytes
     * @return object
     * @throws IOException ioexception

     */
    public static Object bytesToObject(byte[] bytes){
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        Object obj = null;
        ObjectInputStream ois=null;
        ByteArrayInputStream bis=null;
        try {
            bis = new ByteArrayInputStream(bytes);
            ois= new ObjectInputStream(new BufferedInputStream(bis));
            obj = ois.readObject();
          
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage(),e);
        } catch (IOException e) {
        	logger.error(e.getMessage(),e);
		}finally{
			IOUtils.closeQuietly(ois);
			IOUtils.closeQuietly(bis);
        }
        return obj;
    }
    
    /**
     * 功能描述:对象转换成自己数组 <br>
     * @param obj obj
     * @return byte
     * @throws IOException io exception
     */
    public static byte[] objectToByteArray(Object obj) throws IOException {
        if (obj == null) {
            return null;
        }
        ObjectOutputStream os = null;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(FIVE);
        os = new ObjectOutputStream(new BufferedOutputStream(byteStream));
        os.flush();
        os.writeObject(obj);
        os.flush();
        byte[] sendBuf = byteStream.toByteArray();
        os.close();
        return sendBuf;
    }

    /**
     * 
     * 
     * @param bytes
     * @return
     * @throws IOException
     */
    /**
     * 
     * 自己数组转换成对象 <br>
     * @param bytes bytes
     * @return object
     * @throws IOException ioexception

     */
    public static Object byteArrayToObject(byte[] bytes) throws IOException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(bis));
            obj = ois.readObject();
            bis.close();
            ois.close();
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
        return obj;
    }
}
