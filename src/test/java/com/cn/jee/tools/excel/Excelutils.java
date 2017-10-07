package com.cn.jee.tools.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cn.jee.common.mapper.JsonMapper;
import com.cn.jee.tools.excel.dto.ExcelBean;

/**
 * Excel 导入导出
 * 
 * @author admin
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-context.xml" })
public class Excelutils {

    private Logger logger = LoggerFactory.getLogger(Excelutils.class);

    @Test
    public void loggerTest() {
        logger.debug("debug");
    }

    @Test
    public void parseExcel() {

        String fileName = "D://excel.xlsx";

        // 1.准备返回的变量
        List<ExcelBean> stones = new ArrayList<ExcelBean>();

        boolean isE2007 = false; // 判断是否是excel2007格式
        if (fileName.endsWith("xlsx")) {
            isE2007 = true;
        }

        // 2.准备workbook
        // 同时支持Excel 2003、2007
        File excelFile = new File(fileName); // 创建文件对象
        Workbook workbook = null;
        // 根据文件格式(2003或者2007)来初始化
        try {
            FileInputStream is = new FileInputStream(excelFile); // 文件流
            if (isE2007) {
                workbook = new XSSFWorkbook(is);
            } else {
                workbook = new HSSFWorkbook(is);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3.遍历集合，组装结果
        int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量
        // 遍历每个Sheet
        for (int s = 0; s < sheetCount; s++) {
            Sheet sheet = workbook.getSheetAt(s);
            int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数
            // 遍历每一行
            for (int r = 0; r < rowCount; r++) {
                ExcelBean stone = new ExcelBean();
                Row row = sheet.getRow(r);
                int cellCount = row.getPhysicalNumberOfCells(); // 获取总列数
                // 遍历每一列
                for (int c = 0; c < cellCount; c++) {
                    Cell cell = row.getCell(c);
                    // int cellType = cell.getCellType();
                    String cellStringValue = cell.getStringCellValue();
                    stone.setName(cellStringValue);
                }
                stones.add(stone);
            }
        }

        logger.debug("{}",JsonMapper.toJsonString(stones));
    }

}
