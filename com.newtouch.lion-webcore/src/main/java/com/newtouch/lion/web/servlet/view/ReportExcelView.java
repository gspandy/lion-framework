/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ReportExcelView.java 9552 2015年1月28日 下午1:22:25 WangLijun$
*/
package com.newtouch.lion.web.servlet.view; 

import java.io.File;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.lion.common.file.FileUtil;

/**
 * <p>
 * Title: Excel报表视图抽像类
 * </p>
 * <p>
 * Description:  Excel报表视图抽像类
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
public class ReportExcelView extends AbstractReportView{
	
	/**日志*/
	private static final Logger logger=LoggerFactory.getLogger(ReportExcelView.class);
	
	/** The content type for an excel response Excel文件类型 */
	private static final String CONTENT_TYPE = "application/vnd.ms-excel";
	/**The  content disposition for an excel response*/
	private static final String CONTENT_DISPOSITION="Content-disposition";
	/** The extension to look for existing templates*/
	private static final String EXTENSION =FileUtil.EXCEL_EXTENSION;
	/**attachment*/
	private static final String ATTACHMENT="attachment;filename=";
	/**参数名称*/
	private static final String FILENAME="fileName";
	/***文件目录*/
	private String directory;
	

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("in ReportExcel文件视图");
		String filePath=(String) model.get(FILENAME);
		File exportFile=new File(filePath);
		String title=(String) model.get("title");
		if(StringUtils.isEmpty(title)){
			title=exportFile.getName();
		}
		logger.info("filePath:{},fileName:{}",filePath,exportFile.getName());
		response.setContentType(CONTENT_TYPE);
        response.setHeader(CONTENT_DISPOSITION,this.getAttachmentFileName(ATTACHMENT, EXTENSION,title));
        OutputStream outputStream=response.getOutputStream();
        outputStream.write(FileUtils.readFileToByteArray(exportFile));
        outputStream.flush();
        outputStream.close();
        FileUtils.deleteQuietly(exportFile);
        logger.info("out ReportExcel文件视图");
	}


	/**
	 * @return 获取文件目录
	 */
	public String getDirectory() {
		return directory;
	}


	/**
	 * @param 设置文件目录
	 */
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	
	
	
	
}

	