/**
 * 
 */
package support.steps;

import support.utils.demoElement;

/**
 * @author vuong.dd
 *
 */
public class demomain {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		demoElement support = new demoElement();
		//support.launch("firefox");
		/*
		support.open("https://www.ebay.in/");
		support.input("bc-id", "gh-ac", "Automotive");
		support.selectDropDown("abc-id", "gh-cat", "value", "131090");
		support.getButton("abc-id", "gh-btn");
		support.click("abc-xp", "//td[@class='cat lw']/a[@class='rt'][@class=contains(text(), 'Cameras')]");
		
		support.open("http://author.dev.genesis.monkapps.com:4502/editor.html/content/genesis/us/en/Test.html");
		support.closeBrowser();
		support.input("abc-id", "username", "admin");
		support.input("abc-id", "password", "admin123");
		support.getButton("abc-id", "submit-button");
		
		String[][] table = {
				{"Row_Index","Column_Index"},
				{"1","1"}
		};
		*/
		String[][] table1 = {
				{"Col_Index"}
				,{"1"}
		};
//		support.openFileExcel("/src/test/resources/DataIn/", "Elements.xlsx", "Login_Page");
//		System.out.println(support.getValueExcel(1, 1));
		support.launch("chrome");
		support.open("Elements.xlsx", "URL_List", "1", table1);
		//support.open("Elements.xlsx", "URL_List", "1", table);
		//support.verify_element_with_text(table, "4", "bjasdskna");

	}

}
