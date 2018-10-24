package supportExam.until;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.media.Time;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class EExam20 {

	public static void main(String[] args) throws Exception {
//		luach("http://the-internet.herokuapp.com/exit_intent");
//		waiPopUp();
		 
		
	   /*	//cau 20 
		 luach("http://the-internet.herokuapp.com/javascript_alerts");
		 By element = By.xpath("//*[@id='content']/div/ul/li[3]/button");
		 alerts(element); 
	  */
		/*
		 * cau 21 luach("http://the-internet.herokuapp.com/key_presses"); By
		 * element = By.id("result"); press5Key1(element);
		 */
		
		/*
		 * cau 22 luach("http://the-internet.herokuapp.com/windows"); By element
		 * = By.xpath("//*[@id='content']/div/a"); By getElemtnWd =
		 * By.xpath("//h3"); clickHere(element, getElemtnWd);
		 */
		
		/* cau 19
		 * luach("http://the-internet.herokuapp.com/jqueryui/menu"); hover();
		 */
		
		//cau 24
		 luach("http://the-internet.herokuapp.com/status_codes"); allLink();
		 
		
		/*luach("http://the-internet.herokuapp.com/tinymce");
		iFrameContent();
		*/
	}
	public static WebDriver driver;
	public static void luach(String url){
		String reference_path="src/test/references/";
		System.setProperty("webdriver.chrome.driver", reference_path + "chromedriver.exe");
		
        driver = new ChromeDriver();
        driver.get(url);
	}
	
	public static  void alerts(By element) throws Exception{
		WebElement elementAlert = driver.findElement(element);
				elementAlert.click();
				Alert alert=driver.switchTo().alert();
				alert.sendKeys("My name Vuong");	
				alert.accept();
		WebElement getResult = driver.findElement(By.id("result"));
		String latsResult = getResult.getText();
		System.out.println(latsResult);
	}
	// Cau 21
	
	public static void press5Key1(By elementKey1) throws Exception{
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
	
		robot.keyPress(KeyEvent.VK_H); 
		WebElement element = driver.findElement(elementKey1);
		String elementResult = element.getText();
		System.out.println(elementResult);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_O); 
		System.out.println(elementResult);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_A); 
		System.out.println(elementResult);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_B); 
		System.out.println(elementResult);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_W); 
		System.out.println(elementResult);
	}
	// Cau 22
	public static void clickHere(By elementclick, By elementWindown) throws InterruptedException{
		WebElement element = driver.findElement(elementclick);
		element.click();
		Thread.sleep(1000);
		String winHandleBefore = driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		    
		}
		WebElement getElementWd = driver.findElement(elementWindown);
		System.out.println(getElementWd.getText());

		driver.switchTo().window(winHandleBefore);	
	}
	//cau 23
	public static void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                    	//linl reference: https://developer.mozilla.org/en-US/docs/Web/API/Document/readyState
                    	// return document.readyState == complete--> page load full
                    	// return document.readyState == interactive-->  
                    	// The document has finished loading. We can now access the DOM elements.
                        // But sub-resources such as images, stylesheets and frames are still loading.
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
	public static void allIframe(){
		driver.get("http://the-internet.herokuapp.com/nested_frames");

		driver.switchTo().frame(driver.findElement(By.name("frame-top")));
		waitForPageLoaded();
			driver.switchTo().frame(driver.findElement(By.name("frame-left")));
			String text = driver.findElement(By.xpath("//body")).getText();
			System.out.println(text);
		driver.switchTo().defaultContent();
		waitForPageLoaded();
		
		driver.switchTo().frame(driver.findElement(By.name("frame-top")));
		waitForPageLoaded();
			driver.switchTo().frame(driver.findElement(By.name("frame-middle")));
			String text1 = driver.findElement(By.xpath("//body")).getText();
			System.out.println(text1);
		driver.switchTo().defaultContent();
		waitForPageLoaded();
		
		driver.switchTo().frame(driver.findElement(By.name("frame-top")));
		waitForPageLoaded();
			driver.switchTo().frame(driver.findElement(By.name("frame-right")));
			String text2 = driver.findElement(By.xpath("//body")).getText();
			System.out.println(text2);
		driver.switchTo().defaultContent();
		waitForPageLoaded();
		driver.switchTo().frame(driver.findElement(By.name("frame-bottom")));
		String text3 = driver.findElement(By.xpath("//body")).getText();
		System.out.println(text3);
		driver.switchTo().defaultContent();
	}
	
	
	public static int getResponse(String urlString) throws MalformedURLException, IOException {
		int reponseCode = 0;
		try {
			URL url = new URL(urlString);
			HttpURLConnection huc = (HttpURLConnection) url.openConnection();

			huc.setRequestMethod("GET");
			huc.connect();
			reponseCode = huc.getResponseCode();
			System.out.println("Link:" + " " + url + " " + "CodeReponse:"  + reponseCode);
		} catch (Exception e) {
			e.getMessage();
			System.out.println(e.getMessage());
		}
		return reponseCode;
	}
	
	public static void getTextListElement(By byElement, By byTagname) {
		WebElement parentElement = driver.findElement(byElement);
		List<WebElement> items = parentElement.findElements(byTagname);
		for (WebElement element : items) {
			System.out.print(element.getText() + "||");
		}
		System.out.println();
	}
	// cau 24
	// This is better because you will always take the WebElement refreshed, 
	// even the previous click had some effects on it.
	public static void allLink() throws Exception {
		int numberOfElementFound = getNumberOfElementsFound(By.xpath("//*[@id='content']/div/ul/li/a"));
		System.out.println(numberOfElementFound);
		for (int i = 0; i < numberOfElementFound; i++) {
			getElementWithIndex(By.xpath("//*[@id='content']/div/ul/li/a"), i).click();
			waitForPageLoaded();
			String currentUrl = driver.getCurrentUrl();
			getResponse(currentUrl);
			WebElement goback = driver.findElement(By.xpath("//*[@id='content']/div/p/a"));
			goback.click();
			waitForPageLoaded();
			driver.close();
		}
	}

	public static int getNumberOfElementsFound(By by) {
		return driver.findElements(by).size();
	}

	public static WebElement getElementWithIndex(By by, int pos) {
		return driver.findElements(by).get(pos);
	}
	//cau 19
	public static void hover(){
		Actions actions = new Actions(driver);
		WebElement moveOnImage = driver.findElement(By.xpath("//*[@id='ui-id-2']"));
		actions.moveToElement(moveOnImage);
		actions.perform();
		waitForPageLoaded();
		WebElement moveOnImage1 = driver.findElement(By.xpath("//*[@id='ui-id-4']"));
		actions.moveToElement(moveOnImage1);
		actions.perform();
		waitForPageLoaded();
		WebElement moveOnImage2 = driver.findElement(By.xpath("//*[@id='ui-id-8']"));
		actions.moveToElement(moveOnImage2);
		actions.perform();
		actions.build();
		actions.doubleClick(moveOnImage2);
		actions.perform();
	}
	// cau 25
	public static void iFrameContent(){
		WebElement buttonFormat = driver.findElement(By.id("mceu_2-open"));
		buttonFormat.click();
		Actions actions = new Actions(driver);
		WebElement moveHeading = driver.findElement(By.className("mce-text"));
		actions.moveToElement(moveHeading);
		actions.perform();
		waitForPageLoaded();
		waitForPageLoaded();
		
//		//WebElement moveOnImage2 = driver.findElement(By.xpath("//span[@id='mceu_46-text']"));
//		 List<WebElement> elements = driver.findElements(By.id("mceu_46-text"));
//	        if (elements.isEmpty()) {
//	            throw new RuntimeException("No text area found");
//	        }else{
//	        	for (int i = 0; i < elements.size(); i++) {
//					elements.get(i).click();
//				}
//	        }
	    
		driver.switchTo().frame(driver.findElement(By.id("mce_0_ifr")));
		waitForPageLoaded();
		WebElement formats = driver.findElement(By.id("tinymce"));
		formats.clear();
		driver.switchTo().defaultContent();
		WebElement bold = driver.findElement(By.id("mceu_3"));
	    actions.moveToElement(bold);
	    actions.doubleClick(bold);
	    actions.build().perform();
	    waitForPageLoaded();
	    
	    driver.switchTo().frame(driver.findElement(By.id("mce_0_ifr")));
	    WebElement format = driver.findElement(By.id("tinymce"));
		format.sendKeys("dkalf;la;fkfad,a;f;alf;");
	}
	public void waitForAjax() throws InterruptedException {
		try {
			boolean statusReturn = false;
			boolean statusReturn1 = false;
			while (true) // Handle timeout somewhere
			{
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				boolean ajaxIsComplete = (boolean) executor.executeScript("return jQuery.active == 0");
				Long ajaxState = (Long) executor.executeScript("return jQuery.active");
				String readyState = (String) executor.executeScript("return document.readyState");

				if (null == ajaxState) {
					statusReturn = true;
				} else if (null != ajaxState && ajaxIsComplete) {
					statusReturn = true;
				} else {
					statusReturn = false;
				}
				if (null == readyState) {
					statusReturn1 = true;
				} else if (null != readyState && readyState.equals("complete")) {
					statusReturn1 = true;
				} else {
					statusReturn1 = false;
				}

				if (statusReturn && statusReturn1) {
					Thread.sleep(1000);
					break;
				}
				Thread.sleep(100);
			}
		} catch (Exception e) {
			waitForWebState();
		}
	}
	public void waitForWebState() throws InterruptedException {

		while (true) // Handle timeout somewhere
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String readyState = (String) js.executeScript("return document.readyState");
			if (readyState.equals("complete")) {
				Thread.sleep(1000);
				break;
			}
			Thread.sleep(100);
		}
	}
	public static void waiPopUp(){
		FluentWait<WebDriver> wait = new FluentWait<>(driver);		
		wait.withTimeout(200, TimeUnit.SECONDS);
		wait.pollingEvery(250, TimeUnit.SECONDS);
		wait.ignoring(NoSuchMethodException.class);
		WebElement element = driver.findElement(By.xpath("//*[@id='ouibounce-modal']/div[2]/div[3]/p"));
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	
	}
}
