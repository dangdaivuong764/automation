package support.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import com.jayway.restassured.RestAssured;

public class Demotoday {
	private static WebDriver driver;
	private final String referencesPath = "src/test/references/";
	public void launch(String browser) {
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", referencesPath + "chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "ie":
			System.setProperty("webdriver.IE.driver", referencesPath + "IEDriverServer_win_x64.exe");
			driver = new InternetExplorerDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.firefox.driver", referencesPath + "geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		}
	}

	public void open(String url) {
		driver.get(url);
	}

	public void closeBrowser() {
		driver.close();
	}

	FileInputStream fileIn;
	FileOutputStream fileOut;
	XSSFWorkbook workbook;
	String sheetNameTest = "";
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	private String filePath = "/src/test/resources/DataIn/";
	public void openExcel(String fileName, String sheetName, String[][] dataTable, int dataRowIndex) throws Throwable {
		System.out.println(filePath);
		System.out.println(fileName);
		String fileDir;
		fileDir = System.getProperty("user.dir") + filePath + fileName;
		fileIn = new FileInputStream(fileDir);
		workbook = new XSSFWorkbook(fileIn);
		changeTo_sheetName(sheetName);
		String checkUrl = getValue_FromExcel(dataTable, dataRowIndex);
		if (checkUrl == "") {
			closeBrowser();
			System.out.println("URL null, cannot open this URL");

		} else {
			open(checkUrl);
		}

	}

	public void changeTo_sheetName(String sheetName) {
		sheet = workbook.getSheet(sheetName);
	}

	public String getValue_FromExcel(String[][] dataTable, int dataRowIndex) {
		String col = dataTable[0][1];
		int data_ColIndex = Integer.parseInt(col);
		row = sheet.getRow(dataRowIndex);
		if (row == null) {
			return "";
		} else {
			cell = row.getCell(data_ColIndex);
			if (cell == null) {
				return "";
			} else {
				String url = cell.getStringCellValue();
				return url;
			}
		}
	}

	public By getIdentifier(String elementName, String elementLocator) {
		String element = elementName.toLowerCase();
		if (element.endsWith("-id")) {
			return By.id(elementLocator);
		} else if (element.endsWith("-xp")) {
			return By.xpath(elementLocator);
		} else if (element.endsWith("-name")) {
			return By.name(elementLocator);
		} else if (element.endsWith("-link")) {
			return By.linkText(elementLocator);
		} else
			return null;

	}
	public WebElement getElement(String elementName, String elementLocator) {
		return driver.findElement(getIdentifier(elementName, elementLocator));
	}
	
	public void scroll(String url) {
		// WebElement Element = driver.findElement(By.linkText("Linux"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.location='open(url)'");
		/** To scroll down the web page by pixel. */
		// js.executeScript("window.scrollBy(0,1000)");

		/**
		 * scroll to an partcular Element: WebElement Element =
		 * driver.findElement(By.linkText("Linux"));
		 */
		// js.executeScript("arguments[0].scrollIntoView();", Element);

		/** scroll down the web page at the bottom of the page. */
		// js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		/** Horizontal scroll on the web page. */
		// WebElement horizonElement = driver.findElement(By.linkText("VBScript"));
		// js.executeScript("arguments[0].scrollIntoView();", horizonElement);
	}

	public void scrollToElement(String eName, String eLocator) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// WebElement element = getElement(eName, eLocator);
		js.executeScript("arguments[0].scrollIntoView();", getElement(eName, eLocator));
		// ("arguments[0].scrollIntoView();", WebElement)---> scroll to element visible
	}
	public void actionElement(String eName, String eLocator){
		Actions actions = new Actions(driver);
		actions.moveToElement(getElement(eName, eLocator));
		actions.perform();
		/*
		 * IAction scrollDown = new Actions(Driver) .MoveToElement(body,
		 * body.Size.Width - 10, 15) // position mouse over scrollbar
		 * .ClickAndHold() .MoveByOffset(0, 50) // scroll down .Release()
		 * .Build();
		 * 
		 * scrollDown.Perform();
		 */
	}

	public void scrollTo() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(5000);
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
		//js.executeScript("scroll(0,440)");
		/**
		 * jse.executeScript("window.scrollBy(0,-250)", ""); OR,
		 * jse.executeScript("scroll(0, -250);");
		 */
		//documentElement.clientHeight--> do daiphan tu
		//document.body.clientHeight
		//document.body.scrollHeight ----> returns the complete height of the body i.e web page.
	}

		// get eName, eLocator from Excel follow your FORMAT
	public WebElement getElement(int col_index) {
		// eName row
		int row_eName_index = 1;
		int row_eLocator_index = 2;
		String eName = getValue_FromExcel(row_eName_index, col_index);
		String eLocator = getValue_FromExcel(row_eLocator_index, col_index);
		return getElement(eName, eLocator); // main
	}

	String[][] table = { { "eName-xp", "//abc" } };
	public WebElement getElement(String[][] table) {
		String eName = table[0][0];
		String eLocator = table[0][1];
		return getElement(eName, eLocator); // main
	}

	int[][] table2 = { { 1, 1 } // row, col
			, { 2, 1 } };

	public WebElement getElement2(int[][] table2) {
		int row_Excel_index_eName = table2[0][0];
		int col_Excel_index_eName = table2[0][1];
		String eName = getValue_FromExcel(row_Excel_index_eName, col_Excel_index_eName);

		int row_Excel_index_eLocator = table2[1][0];
		int col_Excel_index_eLocator = table2[1][1];
		String eLocator = getValue_FromExcel(row_Excel_index_eLocator, col_Excel_index_eLocator);

		return getElement(eName, eLocator); // main
	}

	int[][] table3 = { { 1, 1 } }; // row, col of eName, row+1 -> eLocator

	public WebElement getElement3(int[][] table3) {
		int row_Excel_index_eName = table2[0][0];
		int col_Excel_index_eName = table2[0][1];
		String eName = getValue_FromExcel(row_Excel_index_eName, col_Excel_index_eName);

		int row_Excel_index_eLocator = row_Excel_index_eName + 1;
		int col_Excel_index_eLocator = col_Excel_index_eName;
		String eLocator = getValue_FromExcel(row_Excel_index_eLocator, col_Excel_index_eLocator);

		return getElement(eName, eLocator); // main
	}

	int[][] table4 = { { 1, 1 }, // element 1: eName1_row, eName1_col
			{ 1, 2 }, // element 2: eName2_row, eName2_col
			{ 1, 3 }, // element 3: eName3_row, eName3_col
	};

	// row_table4_index --> xac dinh duoc cap (eName_row, eName_col) trong table
	// getElement4(table4, 0); //element 1
	// getElement4(table4, 1); //element 2
	// getElement4(table4, 2); //element 3
	public WebElement getElement4(int[][] table4, int row_table4_index) {
		int row_Excel_index_eName = table2[row_table4_index][0];
		int col_Excel_index_eName = table2[row_table4_index][1];
		String eName = getValue_FromExcel(row_Excel_index_eName, col_Excel_index_eName);

		int row_Excel_index_eLocator = row_Excel_index_eName + 1;
		int col_Excel_index_eLocator = col_Excel_index_eName;
		String eLocator = getValue_FromExcel(row_Excel_index_eLocator, col_Excel_index_eLocator);

		return getElement(eName, eLocator); // main
	}

	// get value from excel
	public String getValue_FromExcel(int row_index, int col_index) {
		row = sheet.getRow(row_index);
		cell = row.getCell(col_index);
		return cell.getStringCellValue().toString();
	}
	public void checkUrl(String url){
		 open(url);
		 driver.getTitle();
		 if (driver.getTitle().equals("404 Not Found"))
			{
				System.out.println("This page not found");
			}
			else
			{
				System.out.println("It living");
			}		
	}
	public int httpResponseCodeViaGet(String url) {
		return RestAssured.get(url).getStatusCode();
	}
}

