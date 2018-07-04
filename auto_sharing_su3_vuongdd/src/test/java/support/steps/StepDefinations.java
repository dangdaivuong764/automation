package support.steps;

import support.utils.Demotoday;
import support.utils.NameLocatorfromExcel;
import support.utils.OperationHelper;
import support.utils.OperationHelperDemo;



public class StepDefinations {
	public static void main(String[] args) throws Throwable {
//		OperationHelper support = new OperationHelper();
//		support.openFileExcel("/src/test/resources/DataIn/", "Elements.xlsx", "Login_Page");
//		System.out.println(support.getValue_FromExcel(1,1));
/**
		System.out.println(support.getValue_FromExcel(1,1));
		System.out.println(support.getValue_FromExcel(2,1));
		System.out.println(support.getIndex_FromTable(5,5));
		
		int tableRow = Integer.parseInt(support.getIndex_FromTable(0,0));
		int tableCol = Integer.parseInt(support.getIndex_FromTable(0,1));
		System.out.println(support.getValue_FromExcel(tableRow, tableCol));
		
		System.out.println(support.dataTable(support.getValue_FromExcel(tableRow, tableCol)));
		support.launch("chrome");

		String[][] myArray = {
				 {"0","1","2"},
				 {"1","2","1"},
				 {"2","3","0"}
				 }; 
		
		
		String mm= support.getValue_FromDataTable(0, 1, myArray);
		System.out.println(mm);
		int excel1 = Integer.parseInt(mm);
		System.out.println(support.getValue_FromExcel(excel1, excel1));
*/		
		//Demotoday demoToday = new Demotoday();
	    //demoToday.launch("chrome");
	    //System.out.println(demoToday.httpResponseCodeViaGet("http://acb.com.vn/sldfjsldfjsldfjsdfl"));
//		OperationHelper support = new OperationHelper();
//		support.launch("chrome");
//		support.open("https://www.ebay.in/");
		//helper.scrollToElement("-xp", "//i[@class='icon-linux']");
		//support.scrollToBottomPage();
		//support.scrollToTopPage();
		//support.close();
		OperationHelperDemo helper = new OperationHelperDemo();
		helper.openFileExcelCell("/src/test/resources/DataIn/", "Elements.xlsx", "Login_Page");
		helper.getValue_FromExcelCell(1,1);
		    //helper.openFileExcelWrite("/src/test/resources/DataIn/", "Elements.xlsx", "Login_Page");
		    //helper.writeValue_FromExcel(0,1, "Never give up");	
		    //demoToday.open("http://demo.guru99.com/test/guru99home/");
		    //demoToday.scrollToElement("-xp", "//i[@class='icon-linux']");
		    //demoToday.scrollTo();
		    //demoToday.checkUrl("http://acb.com.vn/sldfjsldfjsldfjsdfl");
//		    String[][] arr = {
//		    		{"0", "1"}
//		    };
//		    demoToday.openExcel("Elements.xlsx","Demo",arr, 2);
//		String[][] table = { { "0", "1" }, 
//							 { "1", "2" } };
//		NameLocatorfromExcel name = new NameLocatorfromExcel();
//		name.getUrl_OpenonBrowser("Elements.xlsx", "Demo", table, 0);
		//support.selectDropDown("acb-id", "gh-cat", "value", "293");
	}

	
}
