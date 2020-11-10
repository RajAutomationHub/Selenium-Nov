package SeleniumDemo.SeleniumDemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataFromExcel {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String path = "C:\\Users\\USER\\Desktop\\Test.xlsx";
		FileInputStream fis = new FileInputStream(path);
		@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheetAt(0);
		int lastRow = sheet.getLastRowNum();
		System.out.println("Last row- "+lastRow);
		for(int i=0; i<=lastRow; i++)
		{
			Row row = sheet.getRow(i);
			int lastCell = row.getLastCellNum();
			for(int j=0; j<lastCell; j++)
			{
				Cell cell = row.getCell(j);
				String value = cell.getStringCellValue();
				System.out.println(value);
			}
			System.out.println();
	
		}

	}
}
