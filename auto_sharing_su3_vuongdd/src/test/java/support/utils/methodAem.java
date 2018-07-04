/**
 * 
 */
package support.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author vuong.dd
 *
 */
public class methodAem {
	public WebDriver driver;
	private static final String PARENT_URL="src/test/references/";
	public enum setUpBrowser{
		CHROME, FIREFOX, IE;
	}
	public void launch(setUpBrowser browser) {		
		switch (browser) {
		case CHROME:
			System.setProperty("webdriver.chrome.driver", PARENT_URL + "chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;
		case IE:
			System.setProperty("webdriver.IE.driver", PARENT_URL + "IEDriverServer_win_x64.exe");
			driver = new InternetExplorerDriver();
			break;
		case FIREFOX:
			System.setProperty("webdriver.firefox.driver", PARENT_URL + "geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		}
	}
	public void openPage(String url){
		driver.get(url);
	}
	public void navigateTo(String url){
		driver.navigate();
	}
	public enum setUpOption{
		ID, XPATH, NAME, LINK;
	}
	public By getIdentifier(setUpOption option, String eLocator){
		if (option == setUpOption.ID) {
			return By.id(eLocator);		
		}else if(option == setUpOption.XPATH){
			return By.xpath(eLocator);
		}else if(option == setUpOption.NAME){
			return By.name(eLocator);
		}else if(option == setUpOption.LINK){
			return By.linkText(eLocator);
		}else
		return null;
	}
	public void openMenuLeft(setUpOption option, String eLocator){
		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement element = driver.findElement(getIdentifier(option, eLocator));
		WebElement getelement = wait.until(ExpectedConditions.elementToBeClickable(element));
		getelement.click();
	}
	public void submitButton(setUpOption option, String eLocator){
		WebElement element = driver.findElement(getIdentifier(option, eLocator));
		element.click();
	}
	// method input
	public void input(setUpOption option, String eLocator, String keys){
		WebElement elementInput = driver.findElement(getIdentifier(option, eLocator));
		elementInput.clear();
		elementInput.sendKeys(keys);
	}
	public enum setUpType{
		VALUE, INDEX, TEXT; 
	}
	public void selectDropDown(setUpOption option, String eLocator, setUpType type, String value) {
		WebElement element = driver.findElement(getIdentifier(option, eLocator));
		Select dropdown = new Select(element);
		if (type == setUpType.VALUE) {
			dropdown.selectByValue(value);
		} else if (type == setUpType.INDEX) {
			int valueSelected = Integer.parseInt(value);
			dropdown.selectByIndex(valueSelected);
		} else if (type == setUpType.TEXT) {
			dropdown.selectByVisibleText(value);
		} else
			System.out.println("input incorrect");
	}
	
	public void click(setUpOption option, String eLocator){
		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement element = driver.findElement(getIdentifier(option, eLocator));
		WebElement getelement = wait.until(ExpectedConditions.elementToBeClickable(element));
		getelement.click();
	}
	
	
	public boolean checkBrokenLink(String url, String sMethod){
		  int respCode = 200;
		  String respMes = "";
		  HttpURLConnection conn = null;
		
		  try{
		   conn = (HttpURLConnection)(new URL(url).openConnection());
		   conn.setRequestMethod(sMethod);
		   conn.connect();
		   respCode = conn.getResponseCode();
		   respMes = conn.getResponseMessage();
		   conn.disconnect();
		  }
		   catch(IOException e) {
		   e.printStackTrace(); 
		  }
		  if (respCode>=400) {
		   System.out.println(url +" - Response Message: " + respMes);
		   return false;
		  }
		  return true;  
		 }
	
}
