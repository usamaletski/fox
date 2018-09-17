package supportMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
    private static final File src = new File("./src/test/java/testData/Result.xlsx");

    public static String read(int row, int cell) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(src);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        return sheet.getRow(row).getCell(cell).getStringCellValue();
    }

    public static void write(int row, int cell, String value) {
        try {
            FileInputStream fileInputStream = new FileInputStream(src);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow sheetrow = sheet.getRow(row);
            if (sheetrow == null) {
                sheetrow = sheet.createRow(row);
            }
            XSSFCell icell = sheetrow.getCell(cell);
            if (icell == null) {
                icell = sheetrow.createCell(cell);
            }
            icell.setCellValue(value);
            FileOutputStream fileOutputStream = new FileOutputStream(src);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static CellAddress searchStringInXslx(String value) throws IOException {
        FileInputStream inputStream = new FileInputStream(src);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        CellAddress columnNumber = null;
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String text = cell.getStringCellValue();
                if (value.equals(text)) {
                    columnNumber = cell.getAddress();
                    break;
                }
            }
        }
        workbook.close();
        return columnNumber;
    }

    public static void clear(int fromRow) {
        try {
            FileInputStream fileInputStream = new FileInputStream(src);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int numberOfRows = sheet.getPhysicalNumberOfRows();
            int lastRowNum = sheet.getLastRowNum();
            if (numberOfRows > 0) {
                for (int i = 0 + fromRow; i <= lastRowNum; i++) {
                    if (sheet.getRow(i) != null) {
                        sheet.removeRow(sheet.getRow(i));
                        FileOutputStream fileOutputStream = new FileOutputStream(src);
                        workbook.write(fileOutputStream);
                        fileOutputStream.close();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
