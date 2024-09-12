package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class ExcelLogger {

    private static final String FILE_NAME = "log.xlsx";
    private static Workbook workbook = new XSSFWorkbook();
    private static Sheet sheet = workbook.createSheet("Log");

    static {
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Number");
        header.createCell(1).setCellValue("Timestamp");
        header.createCell(2).setCellValue("Memory Leak Percentage");
    }

    public static void logData(int number, LocalDateTime timestamp, double memoryLeakPercentage) throws IOException {
        int rowCount = sheet.getLastRowNum();
        Row row = sheet.createRow(++rowCount);
        row.createCell(0).setCellValue(number);
        row.createCell(1).setCellValue(timestamp.toString());
        row.createCell(2).setCellValue(memoryLeakPercentage);

        try (FileOutputStream outputStream = new FileOutputStream(FILE_NAME)) {
            workbook.write(outputStream);
        }
    }
}
