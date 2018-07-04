/**
 * 
 */
package support.utils;

import java.io.FileInputStream;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * @author vuong.dd
 *
 */
public class demoElement {
	public WebDriver driver;
	private static final String reference_path="src/test/references/";
	public void launch(String browser) {		
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", reference_path + "chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "IE":
			System.setProperty("webdriver.IE.driver", reference_path + "IEDriverServer_win_x64.exe");
			driver = new InternetExplorerDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", reference_path + "geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		}
		
	}
	public void openpage(String url) {
		driver.get(url);
	}

	public void closeBrowser() {
		driver.close();
	}
	public By getIdentifier(String elementName, String elementLocator){
		String element = elementName.toLowerCase();
		if (element.endsWith("-id")) {
			return By.id(elementLocator);		
		}else if(element.endsWith("-xp")){
			return By.xpath(elementLocator);
		}else if(element.endsWith("-name")){
			return By.name(elementLocator);
		}else if(element.endsWith("-link")){
			return By.linkText(elementLocator);
		}else
		return null;
		
	}
	public WebElement getElement(String elementName, String elementLocator){	
		return driver.findElement(getIdentifier(elementName, elementLocator));
	}
	// method click
	public void click(String elementName, String elementLocator){
		WebElement element = driver.findElement(getIdentifier(elementName, elementLocator));
		element.click();
	}
	// method input
	public void input(String elementName, String elementLocator, String keys){
		WebElement elementInput = driver.findElement(getIdentifier(elementName, elementLocator));
		elementInput.clear();
		elementInput.sendKeys(keys);
	}
	public void verifyText(String elementName, String elementLocator, String textCompare){
		WebElement elementText = driver.findElement(getIdentifier(elementName, elementLocator));
		String getText = elementText.getText();
		if (getText.equals(textCompare)) {
			 System.out.println("Macth with textCompare");
		}
		else  System.out.println("The resutl is not same");
	}
	//method verify URL 
	public void verifyUrl(String elementName, String elementLocator, String compareUrl){
		driver.findElement(getIdentifier(elementName, elementLocator));
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, compareUrl);
		System.out.println("URL matching --> Part executed");
	}
	// method verify Attribute
	public String verifyAttribute(String elementName, String elementLocator, String attribute){
		WebElement eleAttribue = driver.findElement(getIdentifier(elementName, elementLocator));
		String result = eleAttribue.getAttribute(elementLocator);
		if(result != null){
			Assert.assertEquals(result, attribute);
		}
		return "";
	}

	public void selectDropDown(String eName,
							   String eLocator,
							   String type,
							   String value) {
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
	public void dragdropimage(String eName, String eLocator1, String eLocator2){
		WebDriverWait myWait = new WebDriverWait(driver, 20);
		WebElement element1 = myWait.until(ExpectedConditions.elementToBeClickable(getIdentifier(eName, eLocator1)));
		WebElement element2 = myWait.until(ExpectedConditions.elementToBeClickable(getIdentifier(eName, eLocator2)));
		Actions act = new Actions(driver);
		act.dragAndDrop(element1, element2).build().perform();	
	}
//-----------------------------------------------------------------------------------------------------------//
	FileInputStream file;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	public void openFileExcel(String filePath, String fileName, String sheetName) throws Exception{
		String fileDir = System.getProperty("user.dir") + filePath + fileName;
		file = new FileInputStream(fileDir);
		workbook = new XSSFWorkbook(file);
		changeTo_sheetName(sheetName);
	}
	public void changeTo_sheetName(String sheetName) {
		sheet = workbook.getSheet(sheetName);
	}
	public String getValueExcel(int rowExcel, int colExcel){
		row = sheet.getRow(rowExcel);
		cell = row.getCell(colExcel);
		return cell.getStringCellValue().toString();
	}
	public WebElement getElementTable(String[][] table){
		int row_table_default = 1;
		int row_excel_index_eName = Integer.parseInt(table[row_table_default][0]);
		int col_excel_index_eName = Integer.parseInt(table[row_table_default][1]);
		
		String eName = getValueExcel(row_excel_index_eName, col_excel_index_eName);
		String eLocator = getValueExcel(row_excel_index_eName + 1, col_excel_index_eName);
		System.out.println(eName + eLocator);
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
	public String getElementTable(String[][] table, String data_row){
		int row_excel_index = Integer.parseInt(data_row);// 1 ---> row 1
		int col_excel_index = Integer.parseInt(table[1][0]);//-> cột 1 ở excel
		return getValueExcel(row_excel_index, col_excel_index);
		
	}
	
	public void open(String fileName, String sheetName, String data_row, String[][] table) throws Exception{
		openFileExcel("/src/test/resources/DataIn/", fileName, sheetName);
		String URL = getElementTable(table, data_row);
		if (URL == "") {
			System.out.println("This cell null");
		}else {
			openpage(URL);	
		}
		
	}
	public void verify_element_with_text(String[][] table, String data_row) {
		int row_eName = Integer.parseInt(table[1][0]);
		int col_eName = Integer.parseInt(table[1][1]);
		int row_eData = Integer.parseInt(data_row);
		String eName = getValueExcel(row_eName, col_eName);
		String eData = getValueExcel(row_eData, col_eName);
		
		int row_eLocator = row_eName+1;
		int col_eLocator = col_eName;
		String eLocator = getValueExcel(row_eLocator, col_eLocator);
		
		WebElement e = getElement(eName, eLocator);
		verify_element_displayed(eName, eLocator);
		String eText = e.getText();
		Assert.assertEquals(eData, eText);
	}
	
	public void verify_element_displayed(String eName, String eLocator) {
		WebElement e = getElement(eName, eLocator);
		Assert.assertTrue(e.isDisplayed());
	}
	public void open1(String fileName, String sheetName, String data_row, String[][] table) throws Exception{
		openFileExcel("/src/test/resources/DataIn/", fileName, sheetName);
		String ur1 = getElementTable(table, data_row);
		String ur2 = getElementTable(table, data_row + 3);
		System.out.println(ur1 + ur2);
		
	}
	
}














































