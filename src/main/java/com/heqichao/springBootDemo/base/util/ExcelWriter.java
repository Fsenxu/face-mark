package com.heqichao.springBootDemo.base.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * 功能描述：导出EXCEL辅助类
 *
 * @version
 * @since 1.0
 * create on: 8/25/2013 
 *
 */
@SuppressWarnings({ "rawtypes" })
public class ExcelWriter {

	// 声明一个工作薄
	public static HSSFWorkbook createWorkBook(){
		return new HSSFWorkbook();
	}
	
	/**
	 * 
	 * 
	 * 功能描述：
	 * 
	 * @param title		导出的EXCEL文件中的sheet名称
	 * @param headers	导出列名数组
	 * @param data		待导出的数据集合
	 * @param keys		列名数组(非必须参数,该参数不为空时按该数组中的列名顺序取data map中的值)
	 * @return 
	 */
	public static HSSFWorkbook export(HSSFWorkbook workbook,String title,String [] headers,List data,String [] keys){

		//HSSFWorkbook workbook = new HSSFWorkbook();	// 声明一个工作薄
		HSSFSheet sheet = workbook.createSheet(title);	// 生成一个表格
		HSSFCellStyle style = workbook.createCellStyle();	//标题样式
		HSSFCellStyle style2 = workbook.createCellStyle();	//文本单元格样式
		HSSFCellStyle style3 = workbook.createCellStyle();	//常规单元格样式
		HSSFFont font = workbook.createFont();	//字体
		HSSFFont font2 = workbook.createFont();	//生成另一个字体
		HSSFRow row;

		//workbook.setSheetName(0, title);
		sheet.setDefaultColumnWidth(20);	// 设置表格默认列宽度为15个字节
		font.setColor(IndexedColors.VIOLET.getIndex());
		font.setFontHeightInPoints((short) 12);
//		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setBold(true);
		
//		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font2.setBold(false);

		style.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());//设置样式
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setFont(font);	//把字体应用到当前的样式

		style2.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style2.setBorderBottom(BorderStyle.THIN);
		style2.setBorderLeft(BorderStyle.THIN);
		style2.setBorderRight(BorderStyle.THIN);
		style2.setBorderTop(BorderStyle.THIN);
		style2.setAlignment(HorizontalAlignment.CENTER);
		style2.setVerticalAlignment(VerticalAlignment.CENTER);
		style2.setFont(font2);// 把字体应用到当前的样式
		style2.setWrapText(true);
		HSSFDataFormat format = workbook.createDataFormat();
		style2.setDataFormat(format.getFormat("@"));

		style3.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		style3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style3.setBorderBottom(BorderStyle.THIN);
		style3.setBorderLeft(BorderStyle.THIN);
		style3.setBorderRight(BorderStyle.THIN);
		style3.setBorderTop(BorderStyle.THIN);
		style3.setAlignment(HorizontalAlignment.CENTER);
		style3.setVerticalAlignment(VerticalAlignment.CENTER);
		style3.setFont(font2);// 把字体应用到当前的样式
		style3.setWrapText(true);
		style3.setDataFormat(format.getFormat("General"));

		//产生表格标题行
		row = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
	//		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellStyle(style);
			cell.setCellValue(headers[i]);
		}

		//遍历集合数据，产生数据行
		Iterator it = data.iterator();
		int index = 0;
		//列名数组不为空按列名从map中取值，否则按顺序迭代map取值
		if(keys!=null && keys.length>0){
			while (it.hasNext()) {
				index++;
				row = sheet.createRow(index);
				row.setHeightInPoints(20);
				Map map = (Map) it.next();
				int cellNum = 0;
				for(int j=0;j<keys.length;j++){
					HSSFCell cell = row.createCell(cellNum);
					if (isNotEmpty(map.get(keys[j]))) {
						cell.setCellValue(map.get(keys[j]).toString());
						if("S_RULE_CONTENT".equals(keys[j])||"ruleXmlDesc".equals(keys[j])){
							cell.setCellStyle(style3);
						}else{
							cell.setCellStyle(style2);
						}
					}else{
						cell.setCellStyle(style2);
					}
					cellNum++;
				}
			}
		}else{
			while (it.hasNext()) {
				index++;
				row = sheet.createRow(index);
				Map map = (Map) it.next();
				Iterator itr = map.entrySet().iterator();
				Short cellNum = 0;
				while (itr.hasNext()) {
					Entry entry = (Entry) itr.next();
					HSSFCell cell = row.createCell(cellNum);
					cell.setCellStyle(style2);
					if (isNotEmpty(entry.getValue())) {
						cell.setCellValue(entry.getValue().toString());
					}
					cellNum++;
				}
			}
		}
		return workbook;
	}
	public static SXSSFWorkbook exportX(SXSSFWorkbook workbook,String title,String [] headers,List data,String [] keys){
		
//		workbook.setCompressTempFiles(true);//是否压缩临时文件
		Sheet sheet = workbook.createSheet(StringUtil.stringFilter(title));	// 生成一个表格
		CellStyle style = workbook.createCellStyle();	//标题样式
		CellStyle style2 = workbook.createCellStyle();	//文本单元格样式
		CellStyle style3 = workbook.createCellStyle();	//常规单元格样式
		Font font = workbook.createFont();	//字体
		Font font2 = workbook.createFont();	//生成另一个字体
		Row row;
		
		sheet.setDefaultColumnWidth(20);	// 设置表格默认列宽度为15个字节
		font.setColor(IndexedColors.VIOLET.getIndex());
		font.setFontHeightInPoints((short) 12);
//		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setBold(true);
		
//		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font2.setBold(false);
		
		style.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());//设置样式
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setFont(font);	//把字体应用到当前的样式
		
		style2.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style2.setBorderBottom(BorderStyle.THIN);
		style2.setBorderLeft(BorderStyle.THIN);
		style2.setBorderRight(BorderStyle.THIN);
		style2.setBorderTop(BorderStyle.THIN);
		style2.setAlignment(HorizontalAlignment.CENTER);
		style2.setVerticalAlignment(VerticalAlignment.CENTER);
		style2.setFont(font2);// 把字体应用到当前的样式
		style2.setWrapText(true);
		DataFormat format = workbook.createDataFormat();
		style2.setDataFormat(format.getFormat("@"));
		
		style3.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		style3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style3.setBorderBottom(BorderStyle.THIN);
		style3.setBorderLeft(BorderStyle.THIN);
		style3.setBorderRight(BorderStyle.THIN);
		style3.setBorderTop(BorderStyle.THIN);
		style3.setAlignment(HorizontalAlignment.CENTER);
		style3.setVerticalAlignment(VerticalAlignment.CENTER);
		style3.setFont(font2);// 把字体应用到当前的样式
		style3.setWrapText(true);
		style3.setDataFormat(format.getFormat("General"));
		
		//产生表格标题行
		row = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			Cell cell = row.createCell(i);
			//		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellStyle(style);
			cell.setCellValue(headers[i]);
		}
		
		//遍历集合数据，产生数据行
		Iterator it = data.iterator();
		int index = 0;
		//列名数组不为空按列名从map中取值，否则按顺序迭代map取值
		if(keys!=null && keys.length>0){
			while (it.hasNext()) {
				index++;
				row = sheet.createRow(index);
				row.setHeightInPoints(20);
				Map map = (Map) it.next();
				int cellNum = 0;
				for(int j=0;j<keys.length;j++){
					Cell cell = row.createCell(cellNum);
					if (isNotEmpty(map.get(keys[j]))) {
						cell.setCellValue(map.get(keys[j]).toString());
						if("S_RULE_CONTENT".equals(keys[j])||"ruleXmlDesc".equals(keys[j])){
							cell.setCellStyle(style3);
						}else{
							cell.setCellStyle(style2);
						}
					}else{
						cell.setCellStyle(style2);
					}
					cellNum++;
				}
			}
		}else{
			while (it.hasNext()) {
				index++;
				row = sheet.createRow(index);
				Map map = (Map) it.next();
				Iterator itr = map.entrySet().iterator();
				Short cellNum = 0;
				while (itr.hasNext()) {
					Entry entry = (Entry) itr.next();
					Cell cell = row.createCell(cellNum);
					cell.setCellStyle(style2);
					if (isNotEmpty(entry.getValue())) {
						cell.setCellValue(entry.getValue().toString());
					}
					cellNum++;
				}
			}
		}
		return workbook;
	}
	
	public static String exportToCvs(String title,String [] headers,List data,String [] keys){
		StringBuffer sb = new StringBuffer(data.size() * 500); //初定每一行大概有500字符
		
		//产生表格标题行
		for (short i = 0; i < headers.length; i++) 
			sb.append(headers[i]).append(",");
		sb.append("\n");
        
		//遍历集合数据，产生数据行
		Iterator it = data.iterator();
		//列名数组不为空按列名从map中取值，否则按顺序迭代map取值
		Map map = null;
		if(keys!=null && keys.length>0){
			while (it.hasNext()) {
				map = (Map) it.next(); 
				for(int j=0;j<keys.length;j++){
					if (isNotEmpty(map.get(keys[j]))) 
						sb.append(filter(map.get(keys[j]).toString()));
					sb.append(",");
				}
				sb.append("\n");
			}
		}else{
			while (it.hasNext()) {
				map = (Map) it.next(); 
				Iterator itr = map.entrySet().iterator();
				while (itr.hasNext()) {
					Entry entry = (Entry) itr.next();
					if (isNotEmpty(entry.getValue())){
						sb.append(filter(entry.getValue().toString()));
					}
					sb.append(",");
				}
				sb.append("\n");
			}
		}

		return   sb.toString(); 
	}
	
	private static String filter(String value) {
		value = value.replaceAll("\"", "\"\"");
		value = "\"" + value + "\"";
		return value + "\t";
	}
	
	/**
	 * 判断对象是否不为空
	 * @param obj 检查对象
	 * @return boolean
	 */
	public static boolean isNotEmpty(Object obj){
		if(obj==null){
			return false;
		}
		if(obj instanceof String){
			return ((String)obj).trim().length()!=0;
		}
		return true;
	}
}
