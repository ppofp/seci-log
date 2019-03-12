/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.service.log;

import java.io.BufferedOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.secisland.log.web.entity.SmLog;

@Component
public class LogDownloadService {
	@Autowired
	private LogService logService;
	
	public boolean writeSystemLog(Map<String, Object> searchParams, BufferedOutputStream out) {
		List<SmLog> logList = logService.getSystemLogList(searchParams);
		if ( logList == null || logList.size() == 0 ){
			return true;
		}
		
        boolean isCreateSuccess = false;
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook();//HSSFWorkbook();//WorkbookFactory.create(inputStream);
        }catch(Exception e) {
            System.out.println("It cause Error on CREATING excel workbook: ");
            e.printStackTrace();
        }
        if(workbook != null) {
            Sheet sheet = workbook.createSheet("systemlog");
            Row row0 = sheet.createRow(0);
            setCellHeader(workbook,sheet,row0,0,"操作账号");
            setCellHeader(workbook,sheet,row0,1,"操作人");
            setCellHeader(workbook,sheet,row0,2,"操作时间");
            setCellHeader(workbook,sheet,row0,3,"操作IP");
            setCellHeader(workbook,sheet,row0,4,"操作类型");
            setCellHeader(workbook,sheet,row0,5,"结果");
            setCellHeader(workbook,sheet,row0,6,"备注");

            for (int rowNum = 1; rowNum <= logList.size(); rowNum++) {
            	if ( rowNum > 1000){ 
            		break;
            	}
                Row row = sheet.createRow(rowNum);
                SmLog log = logList.get(rowNum-1);
                Cell cell0 = row.createCell(0, Cell.CELL_TYPE_STRING);
                cell0.setCellValue(log.getOperateCode());
                Cell cell1 = row.createCell(1, Cell.CELL_TYPE_STRING);
                cell1.setCellValue(log.getOperateName());
                Cell cell2 = row.createCell(2, Cell.CELL_TYPE_STRING);
                cell2.setCellValue((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(log.getOperateDate()).toString());
                Cell cell3 = row.createCell(3, Cell.CELL_TYPE_STRING);
                cell3.setCellValue(log.getOperateIp());
                Cell cell4 = row.createCell(4, Cell.CELL_TYPE_STRING);
                cell4.setCellValue(log.getOperateType());
                Cell cell5 = row.createCell(5, Cell.CELL_TYPE_STRING);
                cell5.setCellValue(log.getResult());
                Cell cell6 = row.createCell(6, Cell.CELL_TYPE_STRING);
                cell6.setCellValue(log.getContent());
            }
            try {
                workbook.write(out);
                isCreateSuccess = true;
            } catch (Exception e) {
                System.out.println("It cause Error on WRITTING excel workbook: ");
                e.printStackTrace();
            }
        }	
        return isCreateSuccess;
	}
    
    private void setCellHeader(Workbook workbook,Sheet sheet,Row row0,int column,String cellName){
        Cell cell_1 = row0.createCell(column, Cell.CELL_TYPE_STRING);
        CellStyle style = getStyle(workbook);
        cell_1.setCellStyle(style);
        cell_1.setCellValue(cellName);
        sheet.autoSizeColumn(column);
    }
    
    private CellStyle getStyle(Workbook workbook){
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER); 
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        Font headerFont = workbook.createFont(); 
        headerFont.setFontHeightInPoints((short)14);
        headerFont.setFontName("宋体");
        style.setFont(headerFont);
        style.setWrapText(true);
        style.setBorderBottom((short)1);
        style.setBorderLeft((short)1);
        style.setBorderRight((short)1);
        style.setBorderTop((short)1);
        style.setWrapText(true);
        return style;
    }
}
