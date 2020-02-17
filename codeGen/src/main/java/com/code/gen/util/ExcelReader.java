package com.code.gen.util;

import com.sun.xml.internal.fastinfoset.tools.XML_SAX_StAX_FI;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {

    public static Map<Integer, Map> read(String excelFile) throws IOException {
        Workbook workbook = getWorkBook(new File(excelFile));
        Sheet sheet = workbook.getSheetAt(0);
        return readSheet(sheet, 0, 0);
    }

    //getWorkBook
    private static Workbook getWorkBook(File file) throws IOException {
        return new XSSFWorkbook(new FileInputStream(file));
    }

    //readSheet
    public static Map<Integer, Map> readSheet(Sheet sheet, int skipRow, int skipCol) {
        Map<Integer, Map> rowDataMap = new HashMap<>();
        for (int i = skipRow ; i < sheet.getLastRowNum() ; i++) {
            if (sheet.getRow(i) == null) {
                break;
            }

            rowDataMap.put(i, readRow(sheet.getRow(i)));
        }

        return rowDataMap;
    }

    //readRow
    private static Map readRow(Row row) {
        Map<Integer, String> cellDataMap = new HashMap<>();
        if (row == null) {
            return cellDataMap;
        }

        for (int i = 0; i < row.getLastCellNum() ; i++) {
            if (StringUtils.isEmpty(row.getCell(i))) {
                break;
            }

            cellDataMap.put(i, row.getCell(i).getStringCellValue());
        }

        return cellDataMap;
    }
}
