package support.steps;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;

public class todo {
	WebDriver driver;
	WebDriverWait wait;
	String referencesPath = "src/test/references/";
	Actions action;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "libs/" + "chromedriver.exe");
		driver = new ChromeDriver();
//		 System.setProperty("webdriver.gecko.driver", referencesPath +
//		 "geckodriver.exe");
//		 driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		wait = new WebDriverWait(driver, 5);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(enabled = false)
	public void f() throws Exception {

		driver.get("http://automationpractice.com/index.php");
		WebElement parentElement = driver.findElement(By.id("homefeatured"));
		List<WebElement> items = parentElement.findElements(By.tagName("li"));
		for (WebElement element : items) {
			System.out.print(element.getText() + "||");
		}
		System.out.println();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement elementto = driver.findElement(By.xpath("//*[@id='homepage-slider']/div/div[2]/div/a[2]"));
		js.executeScript("arguments[0].scrollIntoView(true);", elementto);
		action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[@id='homefeatured']/li[1]/div/div[1]/div"))).click()
				.build().perform();

		Thread.sleep(5000);
		driver.switchTo().frame(driver.findElement(By.className("fancybox-iframe")));
		String element = driver.findElement(By.id("our_price_display")).getText();
		System.out.println(element);
		driver.switchTo().defaultContent();
		// click on close
		action.moveToElement(driver.findElement(By.xpath("//*[@id='index']/div[2]/div/div/a"))).click().build()
				.perform();
		// js.executeScript("window.scrollBy(0,-100)");
		Thread.sleep(2000);
		action.moveToElement(driver.findElement(By.xpath("//*[@id='homefeatured']/li[2]/div/div[1]/div"))).click()
				.build().perform();
		Thread.sleep(5000);
		driver.switchTo().frame(driver.findElement(By.className("fancybox-iframe")));
		String element1 = driver.findElement(By.id("our_price_display")).getText();
		System.out.println(element1);
		driver.switchTo().defaultContent();
		action.moveToElement(driver.findElement(By.xpath("//*[@id='index']/div[2]/div/div/a"))).click().build()
				.perform();

		WebElement element111 = driver.findElement(By.id("htmlcontent_home"));
		js.executeScript("arguments[0].scrollIntoView(true);", element111);
		WebElement parentElement1 = driver.findElement(By.id("htmlcontent_home"));
		List<WebElement> elements = parentElement1.findElements(By.tagName("li"));
		for (WebElement ele : elements) {
			System.out.println(ele.getText());
		}
	}

	public void getTextListElement(By byElement, By byTagname) {
		WebElement parentElement = driver.findElement(byElement);
		List<WebElement> items = parentElement.findElements(byTagname);
		for (WebElement element : items) {
			System.out.print(element.getText() + "||");
		}
		System.out.println();
	}

	public void jsScrollto(By byElementTo) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement elementto = driver.findElement(byElementTo);
		js.executeScript("arguments[0].scrollIntoView(true);", elementto);
	}

	/**
	 * Method moveto an element in Iframe
	 * 
	 * @param element1,
	 *            element2, elementPrice, elementClose find element1--> click-->
	 *            open POPUP(iframe)--> find element2---> Close
	 */
	
	public void actionMoveto(By element1, By element2, By elementPrice, String expect, By elementPrice1, By elementClose) throws Exception {
		action = new Actions(driver);
		action.moveToElement(driver.findElement(element1)).click().build().perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element2));
		waitForPageLoaded();
		String getPrice = wait.until(ExpectedConditions.presenceOfElementLocated(elementPrice)).getText();
		System.out.println(getPrice);
		if (getPrice.equals(expect)) {
			System.out.println("Element correct" + "Actual price:" +getPrice+ "||" + "Expected price:" + expect);	
		}else{
			System.out.println("Element incorect" + "Actual price:" +getPrice+ "||" + "Expected price:" + expect);
		}
		WebElement addTocart = driver.findElement(elementPrice1);
		addTocart.click();
		driver.switchTo().defaultContent();
		waitForPageLoaded();
		WebElement close = driver.findElement(elementClose);
		close.click();
		close.click();
		
	}
	public void actionMove(By element1, By element2) throws Exception {
		action = new Actions(driver);
		action.moveToElement(driver.findElement(element1)).click(driver.findElement(element2)).build().perform();
		
	}
	
	public enum optionSelect {
		TEXT, INDEX, VALUE;
	}

	/**
	 * Method select dropdown
	 * 
	 * @param elementSelect
	 * @param valueSelect
	 * @optionSelect value
	 */
	public void dropdown(By elementSelect, optionSelect value, String valueSelect) {
		WebElement selectDropDown = driver.findElement(elementSelect);
		Select dropdown = new Select(selectDropDown);
		switch (value) {
		case TEXT:
			dropdown.selectByVisibleText(valueSelect);
			break;
		case INDEX:
			int newValueSelect = Integer.parseInt(valueSelect);
			dropdown.selectByIndex(newValueSelect);
		case VALUE:
			dropdown.selectByValue(valueSelect);
			break;
		}
	}

	// waitfor ajax
	public void WaitForAjax() throws InterruptedException {
		while (true) // Handle timeout somewhere
		{
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			boolean ajaxIsComplete = (boolean) executor.executeScript("return jQuery.active == 0");
			if (ajaxIsComplete)
				break;
			Thread.sleep(1000);
		}
	}
	// https://www.testingexcellence.com/webdriver-wait-page-load-example-java/
    public void waitForPageLoaded() {
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

	// Random String
	public static String getRandomString(int length) {
		StringBuilder sb = new StringBuilder();
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		for (int i = 0; i < length; i++) {
			int index = (int) (Math.random() * characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
	}

	// Robot class upload file
	/**
	 * @param elementButtonUpload
	 * @param pathFileName:
	 *            location of file
	 */
	public void robotClass(By elementButtonUpload, String pathFileName) throws Exception {
		Robot robot = new Robot();
		driver.findElement(elementButtonUpload).click();

		robot.setAutoDelay(2000);

		StringSelection stringselection = new StringSelection(pathFileName);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringselection, null);

		robot.setAutoDelay(1000);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);

		robot.setAutoDelay(1000);

		robot.keyPress(KeyEvent.VK_END);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	// Method check brokentlink
	public static void verifyUrlActive(String linkUrl) throws IOException {
		try {
			URL url = new URL(linkUrl);
			HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
			httpURLConnect.setConnectTimeout(3000);
			httpURLConnect.connect();
			if (httpURLConnect.getResponseCode() == 200) {
				System.out.println(linkUrl + "-" + httpURLConnect.getResponseCode());
			}
			if (httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
				System.out.println(
						linkUrl + "-" + httpURLConnect.getResponseMessage() + "-" + HttpURLConnection.HTTP_NOT_FOUND);

			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(enabled = true)
	public void checkURL() throws IOException {
		driver.get("https://www.softwaretestinghelp.com/selenium-tutorial-1/");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Total links are " + links.size());
		String fileEx = System.getProperty("user.dir") + "//src//test//resources//DataIn//ElementsTravel.xlsx";

		for (int i = 0; i < links.size(); i++) {

			WebElement ele = links.get(i);

			String url = ele.getAttribute("href");
			writeXLSXFile(fileEx, "write", url, i, 1);
			try {
				verifyUrlActive(url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	public static FileInputStream fis;
	public static FileOutputStream fos;
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

	public void checkCurrentURL() {
		String ExaclyURL = driver.getCurrentUrl();
		LogEntries logs = driver.manage().logs().get("performance");
		int status = -1;
		for (Iterator<LogEntry> it = logs.iterator(); it.hasNext();) {
			LogEntry entry = it.next();

			try {
				JSONObject json = new JSONObject(entry.getMessage());

				System.out.println(json.toString());

				JSONObject message = json.getJSONObject("message");
				String method = message.getString("method");

				if (method != null && "Network.responseReceived".equals(method)) {
					JSONObject params = message.getJSONObject("params");

					JSONObject response = params.getJSONObject("response");
					String messageUrl = response.getString("url");

					if (ExaclyURL.equals(messageUrl)) {
						status = response.getInt("status");

						System.out.println(
								"---------- bingo !!!!!!!!!!!!!! returned response for " + messageUrl + ": " + status);

						System.out.println("---------- bingo !!!!!!!!!!!!!! headers: " + response.get("headers"));
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("\nstatus code: " + status);
	}

	// Method getcookies
	public void getCookies() {
		File file = new File("getcookies.data");
		try {
			file.createNewFile();
			FileWriter fileWrite = new FileWriter(file);
			BufferedWriter Bwrite = new BufferedWriter(fileWrite);
			for (Cookie ck : driver.manage().getCookies()) {
				Bwrite.write((ck.getName() + ";" + ck.getValue() + ";" + ck.getDomain() + ";" + ck.getPath() + ";"
						+ ck.getExpiry() + ";" + ck.isSecure()));
				Bwrite.newLine();
			}
			Bwrite.close();
			fileWrite.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void makeBorder(String object, By by) throws Exception {
		WebElement element = driver.findElement(by);
		setAttribute(element, "style", "border-style: solid; border-width:3px; border-color: red;");
	}

	public void setAttribute(WebElement element, String attName, String attValue) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attName, attValue);
	}
	public class HttpResponseCode {
	    public int httpResponseCodeViaGet(String url) {
	            return RestAssured.get(url).statusCode();
	    }
	    public int httpResponseCodeViaPost(String url) {
	        return RestAssured.post(url).statusCode();
	    }    
	}
	public void moveToWomentab(String pathElement){
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement elements = driver.findElement(By.xpath(pathElement));
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(elements));
		ele.click();
	}
	
	public void scrollDown(){
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,1000)");
	}
	
	@Test(enabled= false)
	public void checkcurentURl(){
		System.out.println(new HttpResponseCode().httpResponseCodeViaGet("http://intranet.sutrix.com/auth/login/redirect_to/Lw%3D%3D")
		);
	}
	@Test(enabled = false)
	public void testIntranet() {
		driver.get("http://intranet.sutrix.com/auth/login/redirect_to/Lw%3D%3D");
		driver.findElement(By.id("txtUserName")).click();
		driver.findElement(By.id("txtUserName")).sendKeys("vuong.dd");
		driver.findElement(By.id("txtPassword")).click();
		driver.findElement(By.id("txtPassword")).sendKeys("V40ng!Dd$");
		getCookies();
	}
	

	@Test(enabled = false, priority = 1)
	public void testing() throws Exception {
		driver.get("http://automationpractice.com/index.php");
		By byElement = By.id("homefeatured");
		By byTagname = By.tagName("li");
		getTextListElement(byElement, byTagname);
		scrollDown();
		By element1 = By.xpath("//*[@id='homefeatured']/li[1]/div/div[1]/div");
		//By element1 = By.xpath("//*[@id='homefeatured']/li[5]//a[1]/span");
		System.out.println("tui do");
		By element2 = By.className("fancybox-iframe");

		By elementPrice = By.id("our_price_display");
		
		String expectPrice = "$16.51";
		By elementprice = By.id("add_to_cart");
		By elementClose = By.xpath("//*[@id='layer_cart']/div[1]/div[2]/div[4]/a/span");
		actionMoveto(element1, element2, elementPrice, expectPrice, elementprice, elementClose);

	/*	waitForPageLoaded();
		By element1 = By.xpath("//*[@id='homefeatured']/li[1]/div/div[2]/div[2]");
		By element2 = By.xpath("//*[@id='homefeatured']/li[1]/div/div[2]/div[2]/a[1]/span");
		actionMove(element1, element2);
	*/
//		By element11 = By.xpath("//*[@id='homefeatured']/li[2]/div/div[1]/div");
//		By element22 = By.className("fancybox-iframe");
//		By elementPrice1 = By.id("our_price_display");
//		By elementClose1 = By.xpath("//*[@id='index']/div[2]/div/div/a");
//		String expectPrice1 = "$27.00";
//		actionMoveto(element11, element22, elementPrice1, elementClose1, expectPrice1);

	}
	@Test(enabled = false, priority = 1)
	public void testIframe(){
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
	}
	@Test(enabled = false, priority = 2)
	public void robot() throws Exception {
		driver.switchTo().window("http://chercher.tech/files/minion.zip");

		Robot robot = new Robot();
		// press tab first time
		robot.keyPress(KeyEvent.VK_TAB);
		// press tab second time
		robot.keyPress(KeyEvent.VK_TAB);
		// press enter key
		robot.keyPress(KeyEvent.VK_ENTER);
	}
}
