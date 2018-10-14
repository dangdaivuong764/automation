package support.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class testURLss {

	public static WebDriver driver;
	
	//public static XSSFSheet sheet;
	
	public static FileInputStream fis;
	public static FileOutputStream fos;

	@SuppressWarnings("resource")
	public static void main(String[] args) throws InterruptedException, IOException {
		/*
		 * open file excel, get URL run url -> get code write code to file excel
		 */
		// khoi tao driver

		// set property cua driver Chrome
		System.setProperty("webdriver.chrome.driver", "libs/" + "chromedriver.exe");
		// focus den chrome driver
		String fileEx = System.getProperty("user.dir") + "//src//test//resources//DataIn//ElementsTravel.xlsx";
		String sheetname = "write";
		File file = new File(fileEx);
		fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetname);
		
		Iterator<Row> iterators = sheet.rowIterator();

		while (iterators.hasNext()) {
			Row r = iterators.next();
			r.getRowNum();
			// do whatever with cell...
			System.out.println(r.getCell(0));
			Cell o = r.getCell(0);
			int i = getResponse(o.toString());
			System.out.println(i);
			writeXLSXFile(fileEx, sheetname, i + "", r.getRowNum(), 1);
		}

		// driver.close();
	}

	///
	public static int getResponse(String urlString) throws MalformedURLException, IOException {
		int reponseCode = 0;
		try {
			URL url = new URL(urlString);
			HttpURLConnection huc = (HttpURLConnection) url.openConnection();

			huc.setRequestMethod("GET");
			huc.connect();
			reponseCode = huc.getResponseCode();
		} catch (Exception e) {
			e.getMessage();
		}
		return reponseCode;
	}
	public static XSSFWorkbook workbook; 
	public static XSSFRow sheetrow = null;
	public static void writeXLSXFile(String FileName, String SheetName, String text, int row, int col)throws IOException {
		XSSFSheet sheet = null;
		try {
			fis = new FileInputStream(FileName);

			workbook = new XSSFWorkbook(fis);

			if (workbook.getSheet(SheetName) == null) {
				sheet = workbook.createSheet();
			} else {
				sheet = workbook.getSheet(SheetName);
			}
			Cell cell = null;

			// Retrieve the row and check for null

			if (sheet.getRow(row) == null) {
				sheetrow = sheet.createRow(row);
			} else {
				sheetrow = sheet.getRow(row);
			}
			// Update the value of cell
			cell = sheetrow.getCell(col);
			if (cell == null) {
				cell = sheetrow.createCell(col);
			}
			cell.setCellValue(text);
			fis.close();

			FileOutputStream outFile = new FileOutputStream(new File(FileName));
			workbook.write(outFile);
			outFile.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
