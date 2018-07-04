/**
 * 
 */
package support.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author vuong.dd
 *
 */
public class NameLocatorfromExcel {
	private WebDriver driver;
	FileInputStream fileIn;
	FileOutputStream fileOut;
	XSSFWorkbook workbook;
	String sheetNameTest = "";
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	private String filePath = "/src/test/resources/DataIn/";
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
	public void changeTo_sheetName(String sheetName) {
		sheet = workbook.getSheet(sheetName);
	}
	public void openExcel( String fileName, String sheetName) throws Throwable {
		System.out.println(filePath);
		System.out.println(fileName);
		String fileDir;
		fileDir = System.getProperty("user.dir") + filePath + fileName;
		fileIn = new FileInputStream(fileDir);
		workbook = new XSSFWorkbook(fileIn);
		changeTo_sheetName(sheetName);
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
	public void open(String url) {
		driver.get(url);
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
		// run faile row 100 anf 110;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
