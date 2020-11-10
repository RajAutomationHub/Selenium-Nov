package SeleniumDemo.SeleniumDemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DemoSelenium {
	
	public static WebDriver driver;
	public WebDriverWait wait;
	String appURL = "https://www.linkedin.com/";
	
	//Locators
	private By byEmail = By.id("session_key-login");
	private By byPassword = By.id("session_password-login");
	private By bySubmit = By.id("signin");
	private By byError = By.id("global-alert-queue");
	
	@BeforeClass
	public void testSetup() {
		System.setProperty("webdriver.gecko.driver", "E:\\geckodriver\\geckodriver.exe");
		driver=new FirefoxDriver();
		wait = new WebDriverWait(driver, 5);
		//driver.navigate().to("https://www.google.com");
	}
	

	@Test(dataProvider="empLogin")
	public void VerifyInvalidLogin(String userName, String password) {
		//driver.navigate().to(appURL);
		driver.navigate().to("https://www.google.com");
		driver.findElement(byEmail).sendKeys(userName);
		driver.findElement(byPassword).sendKeys(password);
		//wait for element to be visible and perform click
		wait.until(ExpectedConditions.visibilityOfElementLocated(bySubmit));
		driver.findElement(bySubmit).click();
		
		//Check for error message
		wait.until(ExpectedConditions.presenceOfElementLocated(byError));
		String actualErrorDisplayed = driver.findElement(byError).getText();
		String requiredErrorMessage = "Please correct the marked field(s) below.";
		Assert.assertEquals(requiredErrorMessage, actualErrorDisplayed);
		
	}
	
	@DataProvider(name="empLogin")
	public Object[][] loginData() {
		Object[][] arrayObject = getExcelData("C:\\Users\\USER\\Desktop\\Test.xlsx","Sheet1");
		return arrayObject;
	}

	/**
	 * @param File Name
	 * @param Sheet Name
	 * @return
	 */
	public String[][] getExcelData(String fileName, String sheetName) {
		String[][] arrayExcelData = null;
		try {
			FileInputStream fs = new FileInputStream(fileName);
			XSSFWorkbook wb = new XSSFWorkbook(fs);
			XSSFSheet sh = wb.getSheet(sheetName);

			int totalNoOfRows = sh.getLastRowNum();
			int totalNoOfCols = sh.getRow(0).getLastCellNum();
			
			arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];
			
			for (int i= 1 ; i < totalNoOfRows; i++) {

				for (int j=0; j < totalNoOfCols; j++) {
					//String value = sh.getRow(i).getCell(j).getStringCellValue();
					arrayExcelData[i-1][j] = sh.getRow(i).getCell(j).getStringCellValue();
					//System.out.println(value);
				}

			}
			
			wb.close();
			fs.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
		return arrayExcelData;
	}

	@Test
	public void tearDown() {
		//driver.quit();
	}
}
