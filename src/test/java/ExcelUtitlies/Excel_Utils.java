package ExcelUtitlies;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class Excel_Utils {
    public static FileInputStream fi;
    public static XSSFWorkbook wb;
    public static XSSFSheet sh;
    public static XSSFRow ro;
    public static XSSFCell ce;

    public int getRowCount(String xlFile, String xlSheet) throws IOException {
        fi = new FileInputStream(xlFile);
        wb = new XSSFWorkbook(fi);
        sh = wb.getSheet(xlSheet);
        int rowCount = sh.getLastRowNum();
        wb.close();
        fi.close();
        return rowCount+1;
    }

    public int getCellCount(String xlFile, String xlSheet) throws IOException {
        fi = new FileInputStream(xlFile);
        wb = new XSSFWorkbook(fi);
        sh = wb.getSheet(xlSheet);
        ro = sh.getRow(0);
        int cellCount = ro.getLastCellNum();
        wb.close();
        fi.close();
        return cellCount;
    }

    public String getValue(String xlFile, String xlSheet, int row, int cell) throws IOException {
        fi = new FileInputStream(xlFile);
        wb = new XSSFWorkbook(fi);
        sh = wb.getSheet(xlSheet);
        ro = sh.getRow(row);
        ce = ro.getCell(cell);
        String value = "";
        try {
            DataFormatter formate = new DataFormatter();
            value = formate.formatCellValue(ce);
        } catch (Exception e) {
            value = "";
        }
        return value;
    }
    public static void main (String[] args) throws IOException {
        Excel_Utils exc = new Excel_Utils();
        String path = System.getProperty("user.dir")+"//testData//Data_for_API.xlsx";
        int rowCount = exc.getRowCount(path, "Book_Details");
        int cellCount = exc.getCellCount(path, "Book_Details");
        System.out.println(rowCount);
        System.out.println(cellCount);
    }
}
