package SeleniumDemo.SeleniumDemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteDataToExcel {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		String path = "C:\\\\Users\\\\USER\\\\Desktop\\Test.xlsx";
		FileInputStream fis = new FileInputStream(path);
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheetAt(0);
		Row row = sheet.getRow(0);
		Cell cell = row.createCell(5);
		cell.setCellValue("Age");
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();

	}
}


