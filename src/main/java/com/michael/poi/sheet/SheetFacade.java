package com.michael.poi.sheet;

import com.michael.utils.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael
 */
public class SheetFacade {
    private String fileName;
    private Workbook workbook;
    private int sheetCounts = 0;
    private List<Sheet> sheets = new ArrayList<Sheet>();

    public SheetFacade(String fileName, InputStream inputStream) {
        if (StringUtils.isEmpty(fileName)) {
            throw new RuntimeException("文件名不能为空!");
        }
        if (inputStream == null) {
            throw new RuntimeException("没有获得文件流!");
        }
        this.fileName = fileName;
        try {
            if (fileName.endsWith(".xls")) {
                this.workbook = new HSSFWorkbook(inputStream);
            } else if (fileName.endsWith(".xlsx")) {
                this.workbook = new XSSFWorkbook(inputStream);
            } else {
                throw new RuntimeException("不能识别的文件!只支持xls和xlsx类型的文档!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheetCounts = workbook.getNumberOfSheets();
        for (int i = 0; i < sheetCounts; i++) {
            sheets.add(workbook.getSheetAt(i));
        }
    }

    public String getFileName() {
        return fileName;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public List<Sheet> getSheets() {
        return sheets;
    }

    public int getSheetCounts() {
        return sheetCounts;
    }
}
