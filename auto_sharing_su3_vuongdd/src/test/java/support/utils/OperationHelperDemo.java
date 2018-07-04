package support.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class OperationHelperDemo {
	public WebDriver driver;
	private static final String PARENT_URL="src/test/references/";
	public void launch(String browser) {		
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", PARENT_URL + "chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "IE":
			System.setProperty("webdriver.IE.driver", PARENT_URL + "IEDriverServer_win_x64.exe");
			driver = new InternetExplorerDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.firefox.driver", PARENT_URL + "geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		}
	}

	//method check By...
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
	public void getButton(String elementName, String elementLocator){
		WebElement element = driver.findElement(getIdentifier(elementName, elementLocator));
		element.click();
	}
	// method get element
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
	// method select Dropdown
	
	// method verify text
	public String verifyText(String elementName, String elementLocator, String textCompare){
		WebElement elementText = driver.findElement(getIdentifier(elementName, elementLocator));
		String getText = elementText.getText();
		if (getText.equals(textCompare)) {
			return "Macth with textCompare" + getText;
		}
		else return "The resutl is not same";
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
		return null;
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
	// Deefine input value is index
	public void selectDropDownList(String eName, String eLocator, String value){
		WebElement element = driver.findElement(getIdentifier(eName, eLocator));
		Select dropdown = new Select(element);
		if (dropdown.getAllSelectedOptions().equals(value)) {
			dropdown.selectByValue(value);
		}else if(dropdown.getAllSelectedOptions().equals(value)){
			dropdown.selectByVisibleText(value);
		}
		
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
		sheet = wb.getSheet(sheetName); //use import org.apache.poi.ss.usermodel.Sheet; 
										//check cho xls va xlsx
		
	}
	
	String sheetNameTest = "";
	public void changeSheet(String sheetName){
		sheetNameTest = sheetName;
		Sheet sheet = wb.getSheet(sheetNameTest);
		System.out.println(sheet);
		
	}
	/**check case input row and colum empty*/
	public String getValue_FromExcel(int rowIndex, int columIndex){
		row = sheet.getRow(rowIndex);
		cell = row.getCell(columIndex);
		return cell.getStringCellValue().toString();
	}
//------------------------------------------------------------------------------------------//
	FileInputStream fileFile;
	FileOutputStream outputStream;
	String fileDir;
	XSSFWorkbook workbook;
	String sheetNameName = "";
	XSSFSheet sheetsheet;
	XSSFRow rowrow;
	XSSFCell cellcell;
	public void openFileExcelCell(String filePath, String fileName, String sheetName) throws Throwable{
		System.out.println(filePath);
		System.out.println(fileName);
		String fileDir = System.getProperty("user.dir") + filePath + fileName;
		
		fileFile = new FileInputStream(fileDir);
		workbook = new XSSFWorkbook(fileFile);
		sheetNameName = sheetName;
		System.out.println(sheetName);
		sheetsheet = workbook.getSheet(sheetNameName);
	}
	
	public void changeSheetSheet(String sheetName){
		sheetNameTest = sheetName;
		sheetsheet = workbook.getSheet(sheetNameTest);
		
		
	}
	/**check case input row and colum empty*/
	public void getValue_FromExcelCell(int indexRow, int indexCol){
		rowrow = sheetsheet.getRow(indexRow);
		if (rowrow == null) {
			System.out.println("Nó null má ơi");
		} else {
			cellcell = rowrow.getCell(indexCol, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
			if (cellcell == null || cellcell.getCellTypeEnum() == CellType.BLANK) {
				System.out.println("No element");
			}
			printCellValue( cellcell);
		}
	}
	private static void printCellValue(Cell cellcell) {
	    switch (cellcell.getCellTypeEnum()) {
	        case BOOLEAN:
	            System.out.print(cellcell.getBooleanCellValue());
	            break;
	        case STRING:
	            System.out.print(cellcell.getRichStringCellValue().getString());
	            break;
	        case NUMERIC:
	        	SimpleDateFormat day = new SimpleDateFormat("MM/dd/YYYY");
				String  getDate =  day.format(cellcell.getDateCellValue());
				System.out.println(getDate);
	            break;
	        case FORMULA:
	            System.out.print(cellcell.getCellFormula());
	            break;
	        default:
	            System.out.print("");
	    }

	    System.out.print("\t");
	
	} 
	// Write to excel
	public void openFileExcelWrite(String filePath, String fileName, String sheetName) throws Throwable{
		System.out.println(filePath);
		System.out.println(fileName);
		fileDir = System.getProperty("user.dir") + filePath + fileName;
		
		fileFile = new FileInputStream(fileDir);
		workbook = new XSSFWorkbook(fileFile);
		sheetNameName = sheetName;
		System.out.println(sheetName);
		sheetsheet = workbook.getSheet(sheetNameName);
		
	}
	public void writeValue_FromExcel(int indexRow, int indexCol, String name) throws IOException{
		rowrow = sheetsheet.createRow(indexRow);
		cellcell = rowrow.createCell(indexCol);
		cellcell.setCellValue(name);       
		outputStream = new FileOutputStream(fileDir);
		workbook.write(outputStream);
	}

	
}
