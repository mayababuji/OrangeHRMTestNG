package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;

public class ExcelReader {

    public static Object[][] getData(String filePath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            int rows = sheet.getPhysicalNumberOfRows();
            int cols = sheet.getRow(0).getLastCellNum();

            Object[][] data = new Object[rows - 1][cols];

            for (int i = 1; i < rows; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < cols; j++) {
                    Cell cell = row.getCell(j);
                    data[i - 1][j] = (cell == null) ? "" : getCellValueAsString(cell);
                }
            }
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file", e);
        }
    }

    private static String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case BLANK:
                return "";
            default:
                return cell.toString();
        }
    }
}
