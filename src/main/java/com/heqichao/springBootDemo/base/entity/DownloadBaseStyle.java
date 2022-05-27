package com.heqichao.springBootDemo.base.entity;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentFontStyle;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.annotation.write.style.HeadStyle;

/**
 * easyExcel样式entity
 * @author Muzzy
 *
 */
//头部高度
@HeadRowHeight(15)
//头部样式，fillPatternType默认FillPatternType.SOLID_FOREGROUND，颜色是IndexedColors.SKY_BLUE.getIndex()（40）
@HeadStyle(fillForegroundColor = 40)
//头部字体
@HeadFontStyle(fontHeightInPoints = 12)
//列宽
@ColumnWidth(25)
//单元格高度
@ContentRowHeight(15)
//单元格样式，固定填充，颜色IndexedColors.LIGHT_YELLOW.getIndex()（43），水平居中、固定列宽然后自动换行可以防止单元格溢出
@ContentStyle(fillPatternType = FillPatternType.SOLID_FOREGROUND,//固定填充
fillForegroundColor = 43,//填充颜色
horizontalAlignment = HorizontalAlignment.CENTER,//水平居中
wrapped = true,//自动换行
borderLeft = BorderStyle.THIN,borderRight = BorderStyle.THIN,
borderTop = BorderStyle.THIN,borderBottom = BorderStyle.THIN)
//单元格字体
@ContentFontStyle(fontHeightInPoints=12)
public class DownloadBaseStyle {
	
}
