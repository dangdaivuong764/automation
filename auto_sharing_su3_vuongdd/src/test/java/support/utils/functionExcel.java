package support.utils;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author vuong.dd
 *
 */
public class functionExcel {
	FileInputStream file;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	private WebDriver driver;
	private static final String reference_path="src/test/references/";
	public enum setUpBrowsers{
		CHROME, FIREFOX, IE;
	}
	public void launch(setUpBrowsers browser) {		
		switch (browser) {
		case CHROME:
			System.setProperty("webdriver.chrome.driver", reference_path + "chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;
		case IE:
			System.setProperty("webdriver.IE.driver", reference_path + "IEDriverServer_win_x64.exe");
			driver = new InternetExplorerDriver();
			break;
		case FIREFOX:
			System.setProperty("webdriver.firefox.driver", reference_path + "geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		}
	}
	public By getIndentifer(String eName, String eLocator){
		if (eName.endsWith("-id")) {
			return By.id(eLocator);
		}
		if (eName.endsWith("-xp")) {
			return By.xpath(eLocator);
		}
		if (eName.endsWith("-name")){
			return By.name(eLocator);
		}
		return null;
	}
	public WebElement getElement(String eName, String eLocator){
		return driver.findElement(getIndentifer(eName, eLocator));
	}
	public List<WebElement> getElements(String eName, String eLocator){
		return  driver.findElements(getIndentifer(eName, eLocator));
	}
	public void openPage(String url){
		driver.get(url);
	}
	public void closePage(){
		driver.close();
	}
	// Method open file excel
	public void openFileExcel(String filePath, String fileName, String sheetName) throws Exception{
		String fileDir = System.getProperty("user.dir") + filePath + fileName;
		file = new FileInputStream(fileDir);
		workbook = new XSSFWorkbook(file);
		changeTo_sheetName(sheetName);
	}
	// Method change sheet
	public void changeTo_sheetName(String sheetName) {
		sheet = workbook.getSheet(sheetName);
	}
	//Method check value Cell and getValue
	public String getvalueFromFileExcel(int indexRow, int indexCol){
		row = sheet.getRow(indexRow);
		cell = row.getCell(indexCol);
		 switch (cell.getCellTypeEnum()) {
	        case STRING:
	            return cell.getRichStringCellValue().getString();
	        case NUMERIC:
	        	SimpleDateFormat day = new SimpleDateFormat("mm/dd/yyyy");
				String  getDate =  day.format(cell.getDateCellValue());
				return getDate;
	        case FORMULA:
	            return cell.getCellFormula();
		default:
			break;
	    } 
		 return cell.getStringCellValue().toString();
	}
	
	// Get index frome data table
	public String getElementTable(String[][] table, String data_row){
		int row_excel_index = Integer.parseInt(data_row);// 1 ---> row 1
		int col_excel_index = Integer.parseInt(table[1][0]);//-> cột 1 ở excel
		return getvalueFromFileExcel(row_excel_index, col_excel_index);
		
	}
	// Method get URL frome excel and open it
	public void open(String fileName, String sheetName, String data_row, String[][] table) throws Exception{
		openFileExcel("/src/test/resources/DataIn/", fileName, sheetName);
		String URL = getElementTable(table, data_row);
		if (URL == "") {
			System.out.println("This cell null");
		}else {
			openPage(URL);	
		}
	}
	// WebElement from excel
	public WebElement getElementFromExcel(String[][] table, String colIndex){
		int col_Index = Integer.parseInt(colIndex);
		int row_eName = Integer.parseInt(table[1][0]);
		String eName = getvalueFromFileExcel(row_eName, col_Index);
		
		int row_eLocator = Integer.parseInt(table[2][0]);
		String eLocator = getvalueFromFileExcel(row_eLocator , col_Index);
		System.out.println(eName + "||" + eLocator);
		return getElement(eName, eLocator);
	}
	// List webElement from Excel
	public List<WebElement> getElementFromExcels(String[][] table, String colIndex){
		int col_Index = Integer.parseInt(colIndex);
		int row_eName = Integer.parseInt(table[1][0]);
		String eName = getvalueFromFileExcel(row_eName, col_Index);
		
		int row_eLocator = Integer.parseInt(table[2][0]);
		String eLocator = getvalueFromFileExcel(row_eLocator , col_Index);
		System.out.println(eName + "||" + eLocator);
		return getElements(eName, eLocator);
	}
	// Click an element
	public void click(String[][] table, String colIndex){
		WebElement element = getElementFromExcel(table, colIndex);
		element.click();
		
	}
	// Wait an element and click
	public void clickWait(String[][] table, String colIndex){
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement element = 
									wait.until(ExpectedConditions.visibilityOf(
									getElementFromExcel(table, colIndex)));
			element.click();
		} catch (Exception e) {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement element = 
									wait.until(ExpectedConditions.visibilityOf(
									getElementFromExcel(table, colIndex)));
			element.click();
		}
	}
	// Get data keys from excel
	public void senkey(String[][] table, String colIndex){
		WebElement element = getElementFromExcel(table, colIndex);
		element.sendKeys(getDatakeys(table, colIndex));
	}
	public void attributeInput(String[][] table, String colIndex){
		WebElement element = getElementFromExcel(table, colIndex);
		System.out.println(element.getAttribute("value"));
	}
	public String getDatakeys(String[][] table, String colIndex){
		int col_Index = Integer.parseInt(colIndex);
		int row_eName = Integer.parseInt(table[3][0]);
		String datakeys = getvalueFromFileExcel(row_eName, col_Index);
		System.out.println(datakeys);
		return datakeys;	
	}
	// choose an element in auto complete
	public void clickAutoSuggets(String[][] table, String colIndex){
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			List<WebElement> element = 
					(List<WebElement>) wait.until(ExpectedConditions.visibilityOfAllElements
															(getElementFromExcels(table, colIndex)));
			
		    for (WebElement a : element)
		    	  a.click();
		} catch (Exception e) {
			List<WebElement> autoSuggest = getElementFromExcels(table, colIndex);
		    for (WebElement a : autoSuggest)
		    	  a.click();
		}
	  
	}
	/**
	public void chooseAutoSugget(String eName, String eLocator){
		List<WebElement> autoSuggest = getElements(eName, eLocator);
	    for (WebElement a : autoSuggest)
	    a.click();
	}
	*/
	// Method select drop-down
	public void selectDropDown(String[][] table, String colIndex, String type, String value){
		WebElement element = getElementFromExcel(table, colIndex);
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
