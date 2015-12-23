
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: ImageCut.java 9552 Mar 2, 2015 3:58:45 PM MaoJiaWei$
*/
package com.newtouch.lion.common.image; 

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * <p>
 * Title: 图片截取类
 * </p>
 * <p>
 * Description: 用于Jcrop截取
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author MaoJiaWei
 * @version 1.0
 */
public class ImageCut {
	/**
     * 截取图片
     * @param srcImageFile  原图片地址
     * @param x    截取时的x坐标
     * @param y    截取时的y坐标
     * @param desWidth   截取的宽度
     * @param desHeight   截取的高度
	 * @throws IOException 
     */
    public static void imgCut(String srcImageFile, int x, int y, int w,
                              int h) throws IOException {
    	try {
            Image img;
            ImageFilter cropFilter;
            // 读取源图像
            BufferedImage bi = ImageIO.read(new File(srcImageFile+"_src.jpg"));
            int srcWidth = bi.getWidth();      // 源图宽度
    		int srcHeight = bi.getHeight();    // 源图高度
    		             
    		//若原图大小大于切片大小，则进行切割
    	    if (srcWidth >= w && srcHeight >= h) {
               Image image = bi.getScaledInstance(srcWidth, srcHeight,Image.SCALE_DEFAULT);
		                
		       int x1 = x*srcWidth/300;
		       int y1 = y*srcHeight/300;
		       int w1 = w*srcWidth/300;
	           int h1 = h*srcHeight/300;
		                
	           cropFilter = new CropImageFilter(x1, y1, w1, h1);
               img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
               BufferedImage tag = new BufferedImage(w1, h1,BufferedImage.TYPE_INT_RGB);
               Graphics g = tag.getGraphics();
               g.drawImage(img, 0, 0, null); // 绘制缩小后的图
               g.dispose();
               // 输出为文件
               System.out.println(srcImageFile+"_cut.jpg===========================");
               ImageIO.write(tag, "JPEG", new File(srcImageFile+"_cut.jpg"));
	       }
    	} catch (IOException e) {
	         e.printStackTrace();
    	}
    }
}

	