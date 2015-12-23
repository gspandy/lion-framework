/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: ExportExcel.java 9552 2015年1月26日 下午5:14:22 WangLijun$
 */
package com.newtouch.lion.common.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.common.number.NumberUtils;

/**
 * <p>
 * Title: Excel 通用导出功能
 * </p>
 * <p>
 * Description: Excel 通用导出功能，根据泛型导出
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
public class ExcelExport<T> {
	/** 日志 */
	private final static Logger logger = LoggerFactory.getLogger(ExcelExport.class);
	/**默认*/
	public  final static String  DEFAULT_FONTNAME="微软雅黑";
	/** 工作薄 */
	protected HSSFWorkbook workbook;
	/** 字体 默认为：微软雅黑 **/
	protected String fontName =DEFAULT_FONTNAME;
	/**标题栏字体大小 */
	protected Short titleFontSize=14;
	/**字体大小*/
	protected Short fontSize=10;
	/**小数格式:默认保留两位小数*/
	protected String decimalFmt="#,##0.00";
	/**全局日期格式:默认格式为：yyyy-MM-dd HH:mm:ss*/
	protected String dataPattern=DateUtil.FORMAT_DATETIME_DEFAULT;
	/**默认Boolean转换*/
	protected static Map<Object,Object> DEFAULT_BOOLEAN_MAP;
	/**默认宽度 15*/
	protected Integer defaultColWidth=15;
	/**Excel 全局配置*/
	protected ExcelConfig excelConfig;
	/***
	 * 默认配置
	 */
	public ExcelExport() {
		excelConfig=new ExcelConfig();
		this.init();
	}
	/***
	 * 创建导出对象将Excel全局配置设置
	 * @param excelConfig Excel全局配置
	 */
	public ExcelExport(ExcelConfig  excelConfig){
		this.excelConfig=excelConfig;
		this.init();
	}
	/**初始化参数*/
	private void init(){
		this.fontName=excelConfig.getFontName();
		this.titleFontSize=excelConfig.getTitleFontSize();
		this.fontSize=excelConfig.getFontSize();
		this.decimalFmt=excelConfig.getDecimalFmt();
		this.dataPattern=excelConfig.getDataPattern();
		DEFAULT_BOOLEAN_MAP=this.excelConfig.getBooleanMap();
	}
	
	/**
	 * @return Excel 全局配置
	 */
	public ExcelConfig getExcelConfig() {
		return excelConfig;
	}

	
	/**
	 * @param excelConfig 设置Excel全局配置
	 */
	public void setExcelConfig(ExcelConfig excelConfig) {
		this.excelConfig = excelConfig;
	}



	/***
	 * 根据标题、标题栏、数据集、输出流导出Excel文件 字体默认为微软雅黑
	 * 
	 * @param title    表格标题
	 * @param headers  标题栏
	 * @param dataset  数据
	 * @param out      输出流
	 * @throws IOException  异常类
	 */
	public void export(String title, String[] headers, Collection<T> dataset,
			OutputStream out) throws IOException {
		this.export(title, headers, dataset, out,null,null);
	}

	/***
	 * 创建工作薄
	 */
	protected void createWorkBook() {
		this.workbook = new HSSFWorkbook();
	}

	/***
	 * 
	 * @param title
	 *            表格标题
	 * @return
	 */
	protected HSSFSheet createSheet(String title) {
		HSSFSheet sheet = this.workbook.createSheet(title);
		// 设置表格默认列宽度为15
		sheet.setDefaultColumnWidth(this.defaultColWidth);
		return sheet;
	}
	/***
	 * 表格标题样式
	 * @return HSSFCellStyle
	 */
	protected HSSFCellStyle getHeaderTitleStytle() {
		// 生成一个样式
		HSSFCellStyle titleStyle = this.workbook.createCellStyle();
		// 设置这些样式
		titleStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成一个字体
		HSSFFont font =this.getFont(this.fontName,this.titleFontSize);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		titleStyle.setFont(font);
		return titleStyle;
	}

	/**
	 * 设置标题栏的样式
	 * 
	 * @return 标题栏的样式
	 */
	protected HSSFCellStyle getHeaderStyle() {
		// 生成一个样式
		HSSFCellStyle style = this.workbook.createCellStyle();
		HSSFPalette customPalette =this.workbook.getCustomPalette();  
		customPalette.setColorAtIndex(HSSFColor.YELLOW.index,(byte)216, (byte)216, (byte)216);
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.YELLOW.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成一个字体
		HSSFFont font =this.getFont(this.fontName,this.fontSize);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		return style;
	}

	/***
	 * 设置行的样式
	 * 
	 * @return HSSFCellStyle 行的样式
	 */
	protected HSSFCellStyle getRowStyle() {
		// 生成并设置另一个样式
		HSSFCellStyle styleRow = this.workbook.createCellStyle();
		styleRow.setFillForegroundColor(HSSFColor.WHITE.index);
		styleRow.setFillBackgroundColor(HSSFColor.WHITE.index);
		styleRow.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		styleRow.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleRow.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleRow.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleRow.setBorderTop(HSSFCellStyle.BORDER_THIN);
		//styleRow.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleRow.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		// 把字体应用到当前的样式
		styleRow.setFont(this.getFont(this.fontName,this.fontSize));
		return styleRow;
	}
	/***
	 * 
	 * @param fontName
	 * @param fontSize
	 * @return
	 */
	protected HSSFFont getFont(String fontName,Short fontSize){
		HSSFFont font = this.workbook.createFont();
		font.setFontName(fontName);
		font.setColor(HSSFColor.BLACK.index);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font.setFontHeightInPoints(fontSize);
		return font;
	}

	/****
	 * 设置标题栏
	 * 
	 * @param title
	 * @param headers
	 */
	protected void setHeader(HSSFSheet sheet, String title, String[] headers,
			Integer rowIndex) {

		// 设置标题栏样式
		HSSFCellStyle headerStyle = this.getHeaderStyle();
		// 产生表格标题栏
		Row rowTitle = sheet.createRow(rowIndex++);
		HSSFRow row = sheet.createRow(rowIndex++);
		for (int i = 0; i < headers.length; i++) {
			Cell titleCell = rowTitle.createCell(i);
			if (i == 0) {
				rowTitle.createCell(i).setCellValue(new HSSFRichTextString(title));
				titleCell.setCellValue(title);
			}
			// 设置标签样式
			titleCell.setCellStyle(this.getHeaderTitleStytle());
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(headerStyle);
			sheet.autoSizeColumn(i + 1);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,(headers.length - 1)));
	}

	/***
	 * 根据对象的字段转为转换数据
	 * 
	 * @param t  对象
	 * @return Map<String,Field>
	 */
	protected Map<String, Field> getFiled(T t) {
		Field[] fields = t.getClass().getDeclaredFields();
		Map<String, Field> map = new HashMap<String, Field>();
		for (Field field : fields) {
			map.put(field.getName(), field);
		}
		return map;
	}
	/****
	 * 根据格式设置单元格格式
	 * @param pattern 格式;
	 * @return  HSSFCellStyle
	 */
	public HSSFCellStyle getCallDataStyle(String pattern){
		HSSFDataFormat df= this.workbook.createDataFormat();
		HSSFCellStyle cellStyle=this.getRowStyle();
		cellStyle.setDataFormat(df.getFormat(pattern));
		return cellStyle;
	}

	/**
	 * 根据标题、标题栏、数据集、输出流、日期格式导出Excel文件 字体默认为微软雅黑,简单导出功能
	 * @param title        表格标题
	 * @param headers      标题栏
	 * @param dataset      数据
	 * @param out          输出流
	 * @param codeTypes    IM转换
	 * @param DataFormats  单元格数据格式转换,如果该字段是Date则则于日期格式转换
	 * @throws IOException  IO 异常
	 */
	public void export(String title, String[] headers, Collection<T> dataset,
			OutputStream out,Map<String,Map<Object,Object>> codeTypes,Map<String,String> dataFormats) throws IOException {
		// 声明一个工作薄
		this.createWorkBook();
		// 生成一个表格
		HSSFSheet sheet = this.createSheet(title);
		// 设置行的样式
		HSSFCellStyle rowStyle = this.getRowStyle();
		//**画图的顶级管理器***
		HSSFPatriarch patriarch=sheet.createDrawingPatriarch();
		Integer index = 0;
		// 设置标题和标题栏的样式
		HSSFRow row;
		// 设置标题和标题样式
		this.setHeader(sheet, title, headers, index);
		index++;
		// 遍历集合数据，产生数据行
		Iterator<T> it = dataset.iterator();
		Field[] fields = null;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			T obj = (T) it.next();
			if (fields == null || fields.length == 0) {
				fields = obj.getClass().getDeclaredFields();
			}
			for (int i = 0; i < fields.length; i++) {
				HSSFCell cell = row.createCell(i);
				Field field = fields[i];
				String fieldName=field.getName();
				Object value =this.getProperty(obj, field);
				// 判断值的类型后进行强制类型转换
				String textValue = null;				
				if (value instanceof Boolean) {
					textValue=this.getCellValueForBoolean(value,fieldName,codeTypes);
				} else if (value instanceof Date) {
					textValue =this.getCallValueForDate(value,fieldName,dataFormats);
				} else if (value instanceof byte[]) {
					 this.setCellForImage(sheet, patriarch, i,index, row, cell, value,rowStyle);
				} else {
					//其它数据类型都当作字符串简单处理
					textValue = value.toString();
				}
				// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
				if (StringUtils.isNotEmpty(textValue)) {					 
					if (NumberUtils.isNumeric(textValue)) {
						this.setCellForNumber(cell, textValue, rowStyle);
					} else {
						this.setCellForString(cell,textValue,rowStyle);
					}
				}else{
					 cell.setCellStyle(rowStyle);
				}
			}
		}
		workbook.write(out);
	}
	/***
	 * 
	 * @param value 单元格的值
	 * @param fieldName 字段名称
	 * @param dataFormats 格式化
	 * @return
	 */
	protected String getCallValueForDate(Object value,String fieldName,Map<String,String> dataFormats){
		  Date date = (Date) value;
	      if(!CollectionUtils.isEmpty(dataFormats)&&dataFormats.containsKey(fieldName)){
	    	   String pattern=dataFormats.get(fieldName);
	    	   if(StringUtils.isNotEmpty(pattern)){
	    		   return DateUtil.formatDate(date,pattern);
	    	   }
	      }
	      return DateUtil.formatDate(date,this.dataPattern);
	}
	
	
	/***
	 * 获取数据样式
	 * @param value Object
	 * @param mapType Map类型转换
	 * @return  
	 */
	protected String getCellValueForBoolean(Object value,String fieldName,Map<String,Map<Object,Object>> codeTypes){
		Boolean tempVal=(Boolean) value;
		if(!CollectionUtils.isEmpty(codeTypes)&&codeTypes.containsKey(fieldName)){
			Map<Object,Object> types=codeTypes.get(fieldName);
			if(!CollectionUtils.isEmpty(types)){
				return (String) (types.containsKey(tempVal)?types.get(tempVal):StringUtils.EMPTY);
			}
		}
		return  (String) (DEFAULT_BOOLEAN_MAP.containsKey(tempVal)?DEFAULT_BOOLEAN_MAP.get(tempVal):StringUtils.EMPTY);
	}
	
	/***
	 * 根据byte数据组设置单元格的值的及样式
	 * @param sheet 工作样
	 * @param patriarch 画图的顶级管理器
	 * @param colIndex 列索引
	 * @param rowIndex 行索引
	 * @param row  行
	 * @param cell 单元格
	 * @param value  单元格的值
	 * @param cellStyle 单元格样式
	 */
	protected void setCellForImage(HSSFSheet sheet,HSSFPatriarch patriarch,Integer colIndex,Integer rowIndex,HSSFRow row,HSSFCell cell,Object value,HSSFCellStyle cellStyle){
		// 有图片时，设置行高为60px;
		row.setHeightInPoints(60);
		// 设置图片所在列宽度为80px,注意这里单位的一个换算
		sheet.setColumnWidth(colIndex, (short) (35.7 * 80));
		byte[] bsValue = (byte[]) value;
		HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,1023, 255, (short) 6, rowIndex, (short) 6, rowIndex);
		anchor.setAnchorType(2);
		patriarch.createPicture(anchor, this.workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
		cell.setCellStyle(cellStyle);
	}
	
	/***
	 * 数字类型：根据字符串设置单元格的值和样式；
	 * @param cell 单元格
	 * @param str  单元格值
	 * @param cellStyle 单元格样式
	 */
	protected void setCellForNumber(HSSFCell cell,String str,HSSFCellStyle cellStyle){
		//是数字当作double处理
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(Double.parseDouble(str));
		if(NumberUtils.isDecimal(str)){
			cell.setCellStyle(this.getCallDataStyle(this.decimalFmt));
		}else{
			cell.setCellStyle(cellStyle);
		}
	}
	
	/***
	 * 字符类型：根据字符串设置单元格的值和样式。
	 * @param cell  单元格
	 * @param str   单元格值
	 * @param cellStyle 单元格样式
	 */
	protected  void setCellForString(HSSFCell cell,String str,HSSFCellStyle cellStyle){
		HSSFRichTextString richString = new HSSFRichTextString(str);
		cell.setCellValue(richString);
		cell.setCellStyle(cellStyle);
	}
	
	/***
	 * 根据对象获取字段的值，获取Value
	 * @param obj 对象
	 * @return Object的Value
	 */
	protected Object getProperty(T obj,Field field){
		Object value=null;
		try {
			value = PropertyUtils.getProperty(obj,field.getName());
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(),e);
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage(),e);
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage(),e);
		}
		return value;
	}
	
	/***
	 * 根据对象获取字段的值，获取Value
	 * @param obj 对象
	 * @return Object的Value
	 */
	protected Object getProperty(T obj,String fieldName){
		Object value=null;
		try {
			value = PropertyUtils.getProperty(obj,fieldName);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(),e);
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage(),e);
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage(),e);
		}
		return value;
	}
	

	/**
	 * @return 工作薄
	 */
	public HSSFWorkbook getWorkbook() {
		return workbook;
	}

	/**
	 * @param workbook
	 *            设置一个工作薄
	 */
	public void setWorkbook(HSSFWorkbook workbook) {
		this.workbook = workbook;
	}
	
	
	
}
