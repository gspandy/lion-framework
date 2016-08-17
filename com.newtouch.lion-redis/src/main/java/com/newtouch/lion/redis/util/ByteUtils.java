/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ByteUtils.java 9552 2015年7月11日 下午11:23:02 WangLijun$
*/
package com.newtouch.lion.redis.util;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * <p>
 * Title: Byte 工具类
 * </p>
 * <p>
 * Description: Byte 工具类，用于对象之间转换使用
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public  final class ByteUtils {
	
	private final static Logger logger = LoggerFactory.getLogger(ByteUtils.class);
    /**
     * 5000 constant
     */
    private static final int FIVE =5000;
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
    
}

	