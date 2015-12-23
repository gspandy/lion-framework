/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: ExcelExportSimple.java 9552 2015年1月27日 下午6:10:31 WangLijun$
 */
package com.newtouch.lion.common.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import com.newtouch.lion.common.number.NumberUtils;

/**
 * <p>
 * Title: 简单Excel导出功能
 * </p>
 * <p>
 * Description: 简单Excel导出功能
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
public class ExcelExportSimple<T> extends ExcelExport<T> {

	/***
	 * 根据ExcelSheet,数据集，输出流，IM转换、数据格式导出Excel
	 * 
	 * @param excelSheet 工作表定义，包含标题、标题栏、宽度
	 * @param dataset   数据集
	 * @param out  输出流
	 * @param codeTypes    IM转换
	 * @param dataFormats  数据格式
	 * @throws IOException 
	 */
	public void export(ExcelSheet excelSheet, Collection<T> dataset,
			OutputStream out, Map<String, Map<Object, Object>> codeTypes,
			Map<String, String> dataFormats) throws IOException {
		// 声明一个工作薄
		this.createWorkBook();
		// 生成一个表格
		HSSFSheet sheet = this.createSheet(excelSheet.getTitle());
		// 设置行的样式
		HSSFCellStyle rowStyle = this.getRowStyle();
		// **画图的顶级管理器***
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		Integer index = 0;
		// 设置标题和标题栏的样式
		HSSFRow row;
		// 设置标题和标题样式
		this.setHeader(sheet,excelSheet.getTitle(),excelSheet.getHeaders(), index);
		index++;
		// 遍历集合数据，产生数据行
		Iterator<T> it = dataset.iterator();
	 
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			T obj = (T) it.next();
			for (int i = 0; i < excelSheet.getHeaders().size(); i++) {
				HSSFCell cell = row.createCell(i);
				Header  header=excelSheet.getHeaders().get(i);
				String fieldName = header.getFieldName();
				Object value = this.getProperty(obj, fieldName);
				// 判断值的类型后进行强制类型转换
				String textValue = null;
				if (value instanceof Boolean) {
					textValue = this.getCellValueForBoolean(value, fieldName,
							codeTypes);
				} else if (value instanceof Date) {
					textValue = this.getCallValueForDate(value, fieldName,
							dataFormats);
				} else if (value instanceof byte[]) {
					this.setCellForImage(sheet, patriarch, i, index, row, cell,
							value, rowStyle);
				} else {
					// 其它数据类型都当作字符串简单处理
					textValue = value.toString();
				}
				// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
				if (StringUtils.isNotEmpty(textValue)) {
					if (NumberUtils.isNumeric(textValue)) {
						this.setCellForNumber(cell, textValue, rowStyle);
					} else {
						this.setCellForString(cell, textValue, rowStyle);
					}
				} else {
					cell.setCellStyle(rowStyle);
				}
			}
		}
		this.workbook.write(out);
	}
	/***
	 * 根据标签设置标题
	 * @param sheet Excel工作表
	 * @param title 标签
	 * @param headers 标签头
	 * @param index 行索引
	 */
	protected void setHeader(HSSFSheet sheet, String title, List<Header> headers,
			Integer index) {

		// 设置标题栏样式
		HSSFCellStyle headerStyle = this.getHeaderStyle();
		// 产生表格标题栏
		Row rowTitle = sheet.createRow(index++);
		HSSFRow row = sheet.createRow(index++);
		for (int i = 0; i < headers.size(); i++) {
			Header header=headers.get(i);
			Cell titleCell = rowTitle.createCell(i);
			if (i == 0) {
				rowTitle.createCell(i).setCellValue(new HSSFRichTextString(title));
				titleCell.setCellValue(title);
			}
			// 设置标签样式
			titleCell.setCellStyle(this.getHeaderTitleStytle());
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(headerStyle);
		    //设置宽度
			if(header.getWidth()!=null&&header.getWidth()>0){
				sheet.setColumnWidth(cell.getColumnIndex(),header.getWidth());
			 
			}
			HSSFRichTextString text = new HSSFRichTextString(header.getName());
			cell.setCellValue(text);
			
		}
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,(headers.size() - 1)));
	}
}
