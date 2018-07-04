
package support.utils;

import java.io.FileInputStream;
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

/**
 * @author vuong.dd
 *
 */
public class total {
	private WebDriver driver;
	private static final String reference_path="src/test/references/";
	public void launch(String browser) {		
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", reference_path + "chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();

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
	public String getvalueFromFileExcel(int rowExcel, int colExcel){
		row = sheet.getRow(rowExcel);
		cell = row.getCell(colExcel);
		return cell.getStringCellValue().toString();
	}

	public String getElementTable(String[][] table, String data_row){
		int row_excel_index = Integer.parseInt(data_row);// 1 ---> row 1
		int col_excel_index = Integer.parseInt(table[1][0]);//-> cột 1 ở excel
		return getvalueFromFileExcel(row_excel_index, col_excel_index);
		
	}
	public void open(String fileName, String sheetName, String data_row, String[][] table) throws Exception{
		openFileExcel("/src/test/resources/DataIn/", fileName, sheetName);
		String URL = getElementTable(table, data_row);
		if (URL == "") {
			System.out.println("This cell null");
		}else {
			openPage(URL);	
		}
	}
	
	public WebElement getElementFromExcel(String[][] table, String colIndex){
		int col_Index = Integer.parseInt(colIndex);
		int row_eName = Integer.parseInt(table[1][0]);
		String eName = getvalueFromFileExcel(row_eName, col_Index);
		
		int row_eLocator = Integer.parseInt(table[2][0]);
		String eLocator = getvalueFromFileExcel(row_eLocator , col_Index);
		System.out.println(eName + "||" + eLocator);
		return getElement(eName, eLocator);
	}
	public List<WebElement> getElementFromExcels(String[][] table, String colIndex){
		int col_Index = Integer.parseInt(colIndex);
		int row_eName = Integer.parseInt(table[1][0]);
		String eName = getvalueFromFileExcel(row_eName, col_Index);
		
		int row_eLocator = Integer.parseInt(table[2][0]);
		String eLocator = getvalueFromFileExcel(row_eLocator , col_Index);
		System.out.println(eName + "||" + eLocator);
		return getElements(eName, eLocator);
	}
	
	public void click(String[][] table, String colIndex){
		WebElement element = getElementFromExcel(table, colIndex);
		element.click();
		
	}
	public void senkey(String[][] table, String colIndex){
		WebElement element = getElementFromExcel(table, colIndex);
		element.sendKeys(getDatakeys(table, colIndex));
	}
	
	public void clickAutoSuggets(String[][] table, String colIndex){
		try {
			List<WebElement> autoSuggest = getElementFromExcels(table, colIndex);
		    for (WebElement a : autoSuggest)
		    	  a.click();
		} catch (Exception e) {
			List<WebElement> autoSuggest = getElementFromExcels(table, colIndex);
		    for (WebElement a : autoSuggest)
		    	  a.click();
		}
	  
	}
	public void chooseAutoSugget(String eName, String eLocator){
		List<WebElement> autoSuggest = getElements(eName, eLocator);
	    for (WebElement a : autoSuggest)
	    a.click();
	}
	public String getDatakeys(String[][] table, String colIndex){
		int col_Index = Integer.parseInt(colIndex);
		int row_eName = Integer.parseInt(table[3][0]);
		String datakeys = getvalueFromFileExcel(row_eName, col_Index);
		System.out.println(datakeys);
		return datakeys;
		
	
	}
}






