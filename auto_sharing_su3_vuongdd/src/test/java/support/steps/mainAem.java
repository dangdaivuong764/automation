/**
 * 
 */
package support.steps;

import support.utils.methodAem;
import support.utils.methodAem.setUpBrowser;
import support.utils.methodAem.setUpOption;
import support.utils.methodAem.setUpType;

/**
 * @author vuong.dd
 *
 */
public class mainAem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		methodAem support = new methodAem();
		support.launch(setUpBrowser.CHROME);
		support.openPage("http://author.dev.genesis.monkapps.com:4502/editor.html/content/genesis/us/en/Test.html");
		support.input(setUpOption.ID, "username", "admin");
		support.input(setUpOption.ID, "password", "admin123");
		support.submitButton(setUpOption.ID, "submit-button");
		support.openMenuLeft(setUpOption.XPATH, "//button[@title='Toggle Side Panel']");
		support.click(setUpOption.XPATH, "//div[@title='C-04 Text module']");
		support.selectDropDown(setUpOption.XPATH, "sdsd", setUpType.INDEX , "dasdas");
	}

}
