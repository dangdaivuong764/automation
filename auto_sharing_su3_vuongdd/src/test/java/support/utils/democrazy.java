package support.utils;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
public class democrazy {

	public void readExcel() throws BiffException, IOException {
		String FilePath = "/src/test/resources/DataIn/Elements.xlsx";
		FileInputStream fs = new FileInputStream(FilePath);
		Workbook wb = Workbook.getWorkbook(fs);

		// TO get the access to the sheet
		Sheet sh = wb.getSheet("Sheet1");

		// To get the number of rows present in sheet
		int totalNoOfRows = sh.getRows();

		// To get the number of columns present in sheet
		int totalNoOfCols = sh.getColumns();

		for (int row = 0; row < totalNoOfRows; row++) {

			for (int col = 0; col < totalNoOfCols; col++) {
				System.out.print(sh.getCell(col, row).getContents() + "\t");
			}
			System.out.println();
		}
	}
	
//=======================================================================================================//
		FileInputStream file;
		XSSFWorkbook workbook;
	public void openFileExcel(String filePath, String fileName) throws IOException{
		// Set up filepath find file excel
		String fileDirection = System.getProperty("user.dir") + fileName + filePath;
		
		// khởi tạo đầu vào mới để check đầu vào
		file = new FileInputStream(fileDirection);
		/** khỏi tạo workbook, tại đây có thể check file thuộc loại XSSF hay HSSF với điều kiện đuôi file
		 *  sau dấu chấmsử dụng indexOf(.)
		 String fileExtensionName = fileName.substring(fileName.indexOf(".")); */
		workbook = new XSSFWorkbook(file);
	}
}
























