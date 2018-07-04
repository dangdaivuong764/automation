/**
 * 
 */
package support.steps;

import support.utils.functionExcel;
import support.utils.functionExcel.setUpBrowsers;

/**
 * @author vuong.dd
 *
 */
public class mainFunction {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		functionExcel support = new functionExcel();
		String[][] table = {
				{"Col_Index"}
				,{"1"}
		};
		support.launch(setUpBrowsers.CHROME);
		support.open("Elements.xlsx", "expedia", "0", table);
		String[][] table1 = {
				{"Row_index"},
				{"3"},
				{"4"},
				{"5"}
		};
		support.click(table1, "1");
		support.senkey(table1, "5");
		support.clickAutoSuggets(table1, "6");
		support.senkey(table1,"7");
		support.clickAutoSuggets(table1,"8");
		support.clickWait(table1,"10");
		support.click(table1,"11");
		support.clickWait(table1, "12");
		support.click(table1,"13");

		support.click(table1,"14");
		support.click(table1,"15");
		support.click(table1,"16");
		support.selectDropDown(table1, "17", "value", "10");
		support.click(table1,"18");
		support.attributeInput(table1,"19");
		
		
	}
}
