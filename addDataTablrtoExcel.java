package supportExam.until;

import java.util.*;
import java.io.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class addDataTablrtoExcel {
	@SuppressWarnings({ "deprecation", "resource" })
	public static void main(String[] args) throws IOException {
		WebDriver driver = null;
		String reference_path="src/test/references/";
		System.setProperty("webdriver.chrome.driver", reference_path + "chromedriver.exe");
		
        driver = new ChromeDriver();
		driver.get("http://www.w3schools.com/html/html_tables.asp");
		driver.manage().window().maximize();
		System.out.println(driver.getTitle() + " - WebPage has been launched");

		List<WebElement> irows = driver.findElements(By.xpath("//*[@id='customers']/tbody/tr"));
		int iRowsCount = irows.size();
		List<WebElement> icols = driver.findElements(By.xpath("//*[@id='customers']/tbody/tr[1]/th"));
		int iColsCount = icols.size();
		System.out.println("Selected web table has " + iRowsCount + " Rows and " + iColsCount + " Columns");
		System.out.println();

		FileOutputStream fos = new FileOutputStream("E://Workspace2//auto_sharing_su3_vuongdd//src//test//resources//DataIn//ElementsTravel - Copy.xlsx");

		XSSFWorkbook wkb = new XSSFWorkbook();
		XSSFSheet sheet1 = wkb.createSheet("DataStorage1");

		for (int i = 1; i <= iRowsCount; i++) {
			 XSSFRow excelRow = sheet1.createRow(i);
			for (int j = 1; j <= iColsCount; j++) {
				if (i == 1) {
					WebElement val = driver
							.findElement(By.xpath("//*[@id='customers']/tbody/tr[" + i + "]/th[" + j + "]"));
					String a = val.getText();
					System.out.print(a);

					XSSFCell excelCell = excelRow.createCell(j);
					excelCell.setCellType(XSSFCell.CELL_TYPE_STRING);
					excelCell.setCellValue(a);

					// wkb.write(fos);
				} else {
					WebElement val = driver
							.findElement(By.xpath("//*[@id='customers']/tbody/tr[" + i + "]/td[" + j + "]"));
					String a = val.getText();
					System.out.print(a);

					XSSFCell excelCell = excelRow.createCell(j);
					excelCell.setCellType(XSSFCell.CELL_TYPE_STRING);
					excelCell.setCellValue(a);

					// wkb.write(fos);
				}
			}
			System.out.println();
		}
		fos.flush();
		wkb.write(fos);
		fos.close();
	}
}