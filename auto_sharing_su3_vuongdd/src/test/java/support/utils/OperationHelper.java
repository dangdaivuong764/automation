package support.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class OperationHelper {
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
	public void open(String url){
		driver.get(url);
	}
	public void close(){
		driver.close();
	}
	FileInputStream file;
	Sheet sheet;
	Row row;
	Cell cell;
	Workbook wb;
	public void openFileExcel(String filePath, String fileName, String sheetName) throws Throwable{
		System.out.println(filePath);
		System.out.println(fileName);
		String fileDir = System.getProperty("user.dir") + filePath + fileName;
		
		file = new FileInputStream(fileDir);
		String fileCheck = fileName.substring(fileName.indexOf("."));
		
		if (fileCheck.equals(".xlsx")) {
			wb = new XSSFWorkbook(file);
		}else if (fileCheck.equals(".xls")) {
			wb = new HSSFWorkbook(file);
		}
		//System.out.println(sheetName);
		sheet = wb.getSheet(sheetName); //use import org.apache.poi.ss.usermodel.Sheet; check cho xls va xlsx
	}
	
/**	public void changeSheet(String sheetName){
		sheetNameTest = sheetName;
		Sheet sheet = wb.getSheet(sheetNameTest);
		System.out.println(sheet);
		
	}
*/
	/**check case input row and colum empty*/
	public String getValue_FromExcel(int rowIndex, int columIndex){
		row = sheet.getRow(rowIndex);
		cell = row.getCell(columIndex);
		return cell.getStringCellValue().toString();
	}
	
	FileInputStream fileIn;
	FileOutputStream fileOut;
	XSSFWorkbook workbook;
	String sheetNameTest = "";
	
	private String filePath = "/src/test/resources/DataIn/";

	public void openExcel( String fileName, String sheetName) throws Throwable {
		System.out.println(filePath);
		System.out.println(fileName);
		String fileDir;
		fileDir = System.getProperty("user.dir") + filePath + fileName;
		fileIn = new FileInputStream(fileDir);
		workbook = new XSSFWorkbook(fileIn);
		changeTo_sheetName(sheetName);
	}
	public void changeTo_sheetName(String sheetName) {
		sheet = workbook.getSheet(sheetName);
	}
	public String getValue_fromExcel(int rowExcel, int colExcel){
		row = sheet.getRow(rowExcel);
		cell = row.getCell(colExcel);
		return cell.getStringCellValue().toString();
	}
	
	//get element with input dataTable
	
	public WebElement get_element(String[][] table){
		int row_eName =  Integer.parseInt(table[0][1]);// ---> row=1 in Excel
		int col_eName = Integer.parseInt(table[0][1]);// ---> col=1 in Excel
		String eName = getValue_fromExcel(row_eName, col_eName);//---> result value_eName in excel
		
		int row_eLocator = Integer.parseInt(table[1][1]);// --? row=2 in Excel
		int col_eLocator = Integer.parseInt(table[1][0]);// ---> col=1 in Excel
		String eLocator = getValue_fromExcel(row_eLocator, col_eLocator);//--> result value_eLocator in Excel
		
		return getElement(eName, eLocator);//---> tra ve gia tri call toi getElement handle eName and eLocator
	}
	public WebElement geElement(int col_index){
		// col_index thay doi, row(eName, eLocator) khong thay doi nen set cung.
		int row_eName_index = 1;
		int row_eLocator_index = 2;
		
		String eName = getValue_fromExcel(row_eName_index, col_index);
		String eLocator = getValue_fromExcel(row_eLocator_index, col_index);
		
		return getElement(eName, eLocator);
	}
	public WebElement getElement(String[][] table, int row_index){
		int row_eName = Integer.parseInt(table[row_index][0]);
		int col_eName = Integer.parseInt(table[row_index][1]);
		String eName = getValue_fromExcel(row_eName, col_eName);
		
		int row_eLocator = row_eName +1;//--> row_element namer +1 = element Locator
		int col_eLocator = col_eName;//---> colum o vi tri khong doi.
		String eLocator = getValue_fromExcel(row_eLocator, col_eLocator);
		return getElement(eName, eLocator);
	}
	
	public void getUrl_OpenonBrowser( String fileName,
									  String sheetName,
									  String[][] dataTable,
									  int dataRowIndex ) throws Throwable{
		openExcel(fileName, sheetName);
		int rowExcel = Integer.parseInt(dataTable[dataRowIndex][0]);
		int colExcel = Integer.parseInt(dataTable[dataRowIndex][1]);
		String valueUrl = getValue_fromExcel(rowExcel, colExcel);
		open(valueUrl);
	
	}
	public void scrollToElement1(String eName, String eLocator){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//WebElement element = getElement(eName, eLocator);
		//js.executeScript("arguments[0].scrollIntoView();",getElement(eName, eLocator));
		js.executeScript("window.scrollTo(0, Math.max(document.documentElement.scrollHeight, document.body.scrollHeight, document.documentElement.clientHeight));",getElement(eName, eLocator));

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
//---------------------------------------------------------------------------------------------------//
//	public static void main(String[] args) throws Throwable {
//		OperationHelper support = new OperationHelper();
//		String[][] table = {
//				{"Row_Index","Column_Index"},
//				{"1","1"}
//				public void open(String url) {
//					driver.get(url);
//				}
//		};
//	}
	public WebElement getElement(String[][] table) {
		//1. From a datatable -> get row_eName, col_eName
		int row_table_default = 1; //Get row(1) of datatable
		int row_excel_index_eName = Integer.parseInt(table[row_table_default][0]);
		int col_excel_index_eName = Integer.parseInt(table[row_table_default][1]);
		
		//int row_excel_index = Integer.parseInt(table.get(row_table_default).get(0));
		//int col_excel_index = Integer.parseInt(table.get(row_table_default).get(1));
		
		String eName = getValue_FromExcel(row_excel_index_eName, col_excel_index_eName);
		String eLocator = getValue_FromExcel(row_excel_index_eName + 1, col_excel_index_eName);
		
		WebElement e = getElement(eName, eLocator);
		return e;
	}
//---------------------------------------------------------------------------------------------------//


/**
 *  extend: How to difference between 
 *  	document.documentElement.clientHeight 
 *  and document.body.clientHeight
 * */
//-------------------------------------------------------------------------------------------------//

/**
 * Author: vuong.dd
 * Write 3 functions for scrolling to Element:
	scrollToTopPage() : Scroll to the top of current page
	scrollToBottomPage() : Scroll to the bottom of current page
	scrollToElement(….) : Scroll to a web element with unknown param
 * SU 3 Automation Team
 * Create: 25/6/2018
 * */
	
	public WebElement getElement(String eName, String eLocator) {
		return driver.findElement(getIdentifier(eName, eLocator));
	}
	
	public By getIdentifier(String eName, String eLocator) {
		String element = eName.toLowerCase();
		if (element.endsWith("-id")) {
			return By.id(eLocator);
		} else if (element.endsWith("-xp")) {
			return By.xpath(eLocator);
		} else if (element.endsWith("-name")) {
			return By.name(eLocator);
		} else if (element.endsWith("-link")) {
			return By.linkText(eLocator);
		} else
			return null;
	}   
		
	public void scrollToTopPage() throws Exception{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		scrollToBottomPage();
		Thread.sleep(5000);
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
	}
	//Scroll to an Element, begin on the Top page.
	public void scrollToElement(String eName, String eLocator){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = getElement(eName, eLocator);
		js.executeScript("arguments[0].scrollIntoView();", element);
		
	}
	public void scrollToBottomPage(){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	

	public void selectDropDown(String eName, String eLocator, String type, String value) {
		WebElement element = driver.findElement(getIdentifier(eName, eLocator));
		Select dropdown = new Select(element);
		if (type.endsWith("value")) {
			dropdown.selectByValue(value);
		} else if (type.endsWith("index")) {
			int valueSelected = Integer.parseInt(value);
			dropdown.selectByIndex(valueSelected);
		} else if (type.endsWith("text")) {
			dropdown.selectByVisibleText(value);
		} else
			System.out.println("input incorrect");
	}

	// Define input value is value
	public void selectDropDownList(String eName, String eLocator, String value){
		WebElement element = driver.findElement(getIdentifier(eName, eLocator));
		Select dropdown = new Select(element);
		dropdown.selectByValue(value);
	}
	// Define input value is index
	public void selectDropDownList(String eName, String eLocator, int index){
		WebElement element = driver.findElement(getIdentifier(eName, eLocator));
		Select dropdown = new Select(element);
		dropdown.selectByIndex(index);		
	}
	public void selectDropDown(String eName, String eLocator, String text){
		WebElement element = driver.findElement(getIdentifier(eName, eLocator));
		Select dropdown = new Select(element);
		dropdown.selectByValue(text);
	}
//----------------------------------------------------------------------------------------------//
	
	// String[][] table = { { "eName-xp", "//abc" } };
	public WebElement getElementTable(String[][] table){
		String eName = table[0][0];// RowIndex
		String eLocator = table[0][1];// ColumIndex
		return getElement(eName, eLocator);
	}
	public void click(String[][] table){
		WebElement element = getElementTable(table);
		element.click();
	}
	public void input(String[][] table, String value){
		WebElement element = getElementTable(table);
		element.clear();
		element.sendKeys(value);
	}
	public void selectDropDown(String[][] table, String type, String value){
		WebElement element = getElementTable(table);
		Select dropdown = new Select(element);
		if (type.endsWith("value")) {
			dropdown.selectByValue(value);
		} else if (type.endsWith("index")) {
			int valueSelected = Integer.parseInt(value);
			dropdown.selectByIndex(valueSelected);
		} else if (type.endsWith("text")) {
			dropdown.selectByVisibleText(value);
		} else
			System.out.println("input incorrect");
	}

}













