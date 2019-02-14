package support.utils;

import gherkin.deps.net.iharder.Base64;
//import gherkin.lexer.Th;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UncheckedIOException;
//import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
/*import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;*/
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.Point;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
/*import java.util.jar.Attributes.Name;
import java.util.logging.Level;*/
import java.util.regex.Pattern;

//import org.json.JSONException;
import org.json.JSONObject;
/*import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;*/
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.Color;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
/*import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;*/
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
/*import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;*/
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

//import org.apache.http.util.Asserts;

import java.util.Date;

import javax.imageio.ImageIO;

//import junit.framework.ComparisonFailure;
import net.arnx.jsonic.JSON;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

//import codecLib.mpa.Constants;

//import com.gargoylesoftware.htmlunit.javascript.host.Element;
import com.jayway.restassured.RestAssured;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import support.datas.Constant;
import support.datas.DataProvider;
import support.steps.ImageComparison;
import support.steps.ParentStepsSupport;
import support.testAPI.ParamsPostMethod;
import support.testAPI.ReadAPI;
import cucumber.api.DataTable;
import cucumber.api.Scenario;
import data.api.testing.JiraTicketsList;

public class OperationHelper extends ParentStepsSupport {

	public static String strValue;
	public static String excelName;
	boolean isCheckDisplay = false;
	Scenario myScenario;
	Long value;
	String patName = null;
	public static boolean sameImages = false;

	public static int tmpProperty = 0;
	public static String fileNameMetaTag;
	public static Map<String, Integer> headerKeys = new HashMap<>();
	public static Map<String, Object> rowsData = new LinkedHashMap<>();
	public static Map<String, Integer> dataHeader = new HashMap<>();

	private int invalidImageCount;

	List<Object> link = new ArrayList<>();
	JavascriptExecutor je = (JavascriptExecutor) driver;
	int countRowExcel = 1; // count row in excel file
	private DataProvider data = new DataProvider();
	public static HashMap<String, HashMap<String, String>> hm1 = new HashMap<>();
	public static FileInputStream fis;
	public static FileOutputStream fos;

	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static XSSFCell cell;
	static XSSFRow row;

	AssertionError error;
	public static JiraTicketsList usercase = new JiraTicketsList();
	public static String messageAlert = "";
	public static String errorMessage = "";
	public String userJira = ReadProperties.getConfigSelenium("jiraUserName");
	public String pwJira = ReadProperties.getConfigSelenium("jiraPassword");

	public static SimpleDateFormat df = new SimpleDateFormat("HH:mm dd_MMM_YYYY");
	public static Date da = new Date();
	public static String timeToCheck = df.format(da);

	private static String baseHost = ReadProperties.getElementsAndUrls("migrationBaseHost");

	/**
	 * Return By id,name, tagname, xpath, css, classname, linktext, paralinktext
	 * 
	 * @param objectName
	 * @return
	 * @throws Exception
	 */
	private By getIdentifier(String objectName) throws Exception {
		if (null != ReadProperties.getElementsAndUrls(objectName + "-id")) {
			return By.id(ReadProperties.getElementsAndUrls(objectName + "-id"));
		} else if (null != ReadProperties.getElementsAndUrls(objectName + "-xp")) {
			return By.xpath(ReadProperties.getElementsAndUrls(objectName + "-xp"));
		} else if (null != ReadProperties.getElementsAndUrls(objectName + "-css")) {
			return By.cssSelector(ReadProperties.getElementsAndUrls(objectName + "-css"));
		} else if (null != ReadProperties.getElementsAndUrls(objectName + "-cln")) {
			return By.className(ReadProperties.getElementsAndUrls(objectName + "-cln"));
		} else if (null != ReadProperties.getElementsAndUrls(objectName + "-link")) {
			return By.linkText(ReadProperties.getElementsAndUrls(objectName + "-link"));
		} else if (null != ReadProperties.getElementsAndUrls(objectName + "-plink")) {
			return By.partialLinkText(ReadProperties.getElementsAndUrls(objectName + "-plink"));
		} else if (null != ReadProperties.getElementsAndUrls(objectName + "-tag")) {
			return By.tagName(ReadProperties.getElementsAndUrls(objectName + "-tag"));
		} else if (null != ReadProperties.getElementsAndUrls(objectName + "-name")) {
			return By.name(ReadProperties.getElementsAndUrls(objectName + "-name"));
		} else {
			try {
				if (driver.findElement(By.linkText(objectName)).isDisplayed()) {
					return By.linkText(objectName);
				} else {
					return null;
				}
			} catch (Exception e) {
				return null;
			}
		}
	}

	private WebElement getElement(String objectName) throws Exception {
		if (null != ReadProperties.getElementsAndUrls(objectName + "-id")) {
			return driver.findElement(By.id(ReadProperties.getElementsAndUrls(objectName + "-id")));
		} else if (null != ReadProperties.getElementsAndUrls(objectName + "-xp")) {
			return driver.findElement(By.xpath(ReadProperties.getElementsAndUrls(objectName + "-xp")));
		} else if (null != ReadProperties.getElementsAndUrls(objectName + "-css")) {
			return driver.findElement(By.cssSelector(ReadProperties.getElementsAndUrls(objectName + "-css")));
		} else if (null != ReadProperties.getElementsAndUrls(objectName + "-cln")) {
			return driver.findElement(By.className(ReadProperties.getElementsAndUrls(objectName + "-cln")));
		} else if (null != ReadProperties.getElementsAndUrls(objectName + "-link")) {
			return driver.findElement(By.linkText(ReadProperties.getElementsAndUrls(objectName + "-link")));
		} else if (null != ReadProperties.getElementsAndUrls(objectName + "-plink")) {
			return driver.findElement(By.partialLinkText(ReadProperties.getElementsAndUrls(objectName + "-plink")));
		} else if (null != ReadProperties.getElementsAndUrls(objectName + "-tag")) {
			return driver.findElement(By.tagName(ReadProperties.getElementsAndUrls(objectName + "-tag")));
		} else if (null != ReadProperties.getElementsAndUrls(objectName + "-name")) {
			return driver.findElement(By.name(ReadProperties.getElementsAndUrls(objectName + "-name")));
		} else {
			try {
				if (driver.findElement(By.linkText(objectName)).isDisplayed()) {
					return driver.findElement(By.linkText(objectName));
				} else {
					return null;
				}
			} catch (Exception e) {
				return null;
			}
		}
	}

	private List<WebElement> getElementsList(String objectName) throws Exception {
		if (null != ReadProperties.getElementsAndUrls(objectName + "-id")) {
			return driver.findElements(By.id(ReadProperties.getElementsAndUrls(objectName + "-id")));
		} else if (null != ReadProperties.getElementsAndUrls(objectName + "-xp")) {
			return driver.findElements(By.xpath(ReadProperties.getElementsAndUrls(objectName + "-xp")));
		} else if (null != ReadProperties.getElementsAndUrls(objectName + "-css")) {
			return driver.findElements(By.cssSelector(ReadProperties.getElementsAndUrls(objectName + "-css")));
		} else if (null != ReadProperties.getElementsAndUrls(objectName + "-cln")) {
			return driver.findElements(By.className(ReadProperties.getElementsAndUrls(objectName + "-cln")));
		} else if (null != ReadProperties.getElementsAndUrls(objectName + "-link")) {
			return driver.findElements(By.linkText(ReadProperties.getElementsAndUrls(objectName + "-link")));
		} else if (null != ReadProperties.getElementsAndUrls(objectName + "-plink")) {
			return driver.findElements(By.partialLinkText(ReadProperties.getElementsAndUrls(objectName + "-plink")));
		} else if (null != ReadProperties.getElementsAndUrls(objectName + "-tag")) {
			return driver.findElements(By.tagName(ReadProperties.getElementsAndUrls(objectName + "-tag")));
		} else if (null != ReadProperties.getElementsAndUrls(objectName + "-name")) {
			return driver.findElements(By.name(ReadProperties.getElementsAndUrls(objectName + "-name")));
		} else {
			try {
				if (driver.findElement(By.linkText(objectName)).isDisplayed()) {
					return driver.findElements(By.linkText(objectName));
				} else {
					return null;
				}
			} catch (Exception e) {
				return null;
			}
		}
	}

	/**
	 * click on button
	 * 
	 * @param objectName
	 * @param position
	 * @param doThisStep
	 * @throws Exception
	 */
	public void clickObject(String objectName, String position) throws Exception {
		try {
			if (position != null) {
				driver.findElements(getIdentifier(objectName)).get(Integer.parseInt(position)).click();
			}
			driver.findElement(getIdentifier(objectName)).click();
			waitForWebState();
		} catch (Exception e) {
			if (position != null) {
				executeScript(driver.findElements(getIdentifier(objectName)).get(Integer.parseInt(position)),
						"arguments[0].click();");
			}
			Thread.sleep(500);
			executeScript(driver.findElement(getIdentifier(objectName)), "arguments[0].click();");
			waitForWebState();
		}

	}

	public void takeScreenExpect(String screenshotName, Scenario myScenario) throws InterruptedException, IOException {
		waitForAjax();
		DataProvider data = new DataProvider();
		DateFormat dateFormat = new SimpleDateFormat("MMM d yyyy  hh:mm:ss a");
		Date date = new Date();
		screenshotName = screenshotName + dateFormat.format(date).toString();
		screenshotName = screenshotName.replaceAll(":", "");
		Thread.sleep(2000);
		File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		BufferedImage img = ImageIO.read(screen);
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(data.getOutputScreenshot(screenshotName));
		} catch (java.io.FileNotFoundException io) {
			io.printStackTrace();
		}
		// ScreenShort full page on firefox so need cut of image fix only view
		// current screen
		if (ReadProperties.getConfigSelenium("run-browser").equals(Constant.BROWSER_FIREFOX)) {
			int y = 0;
			int yS = driver.manage().window().getSize().getHeight();
			if (value != null && value != 0) {
				y = (int) (y + value);
				yS = img.getHeight() - y;
			} else {
				yS = img.getHeight();
			}

			if (driver.manage().window().getSize().getHeight() <= yS) {
				yS = driver.manage().window().getSize().getHeight();
			}
			BufferedImage dest = img.getSubimage(0, y, img.getWidth(), yS);
			ImageIO.write(dest, "png", out);
		} else {
			ImageIO.write(img, "png", out);
		}
		FileUtils.copyFile(screen, new File(data.getOutputScreenshot(screenshotName.replace(" ", "_") + ".png")));
		myScenario.embed(
				(Files.readAllBytes(Paths.get(data.getOutputScreenshot(screenshotName.replace(" ", "_") + ".png")))),
				"image/png");

	}

	/**
	 * verify display
	 * 
	 * @param objectName
	 * @param valueExpect
	 * @param position
	 *            in case list of element
	 * @param doThisStep
	 * @throws Exception
	 */
	public void verifyDisplay(String objectName, String valueExpect, String position) throws Exception {
		Thread.sleep(100);
		if (null != objectName) {
			isCheckDisplay = true;
			position = ReadProperties.getElementsAndUrls(objectName + "-index");
			if (valueExpect.equals("display")) {
				Assert.assertTrue(objectName + " is not present",
						null != driver.findElement(getIdentifier(objectName)));
			} else {
				if ((driver.findElement(getIdentifier(objectName))) != null) {
					Assert.assertTrue(objectName + " is present",
							!driver.findElement(getIdentifier(objectName)).isDisplayed());
				}
			}
			isCheckDisplay = false;
		}
	}

	public void verifyText(String objectName, String valueExpect, String position) throws Exception {
		if (null != objectName) {
			if (null != position) {
				if (valueExpect.equals("displayed")) {
					Assert.assertTrue(objectName + " is not present",
							null != driver.findElement(getIdentifier(objectName)));
				} else if (valueExpect.equals("not displayed")) {
					if ((driver.findElement(getIdentifier(objectName))) != null) {
						Assert.assertTrue(objectName + " is present",
								!driver.findElement(getIdentifier(objectName)).isDisplayed());
					}
				} else {
					Assert.assertEquals(valueExpect, replaceSomeText(returnElement(objectName, position).getText()));
				}
			} else {
				if (valueExpect.equals("displayed")) {
					Assert.assertTrue(objectName + " is not present",
							null != driver.findElement(getIdentifier(objectName)));
				} else if (valueExpect.equals("not displayed")) {
					if ((driver.findElement(getIdentifier(objectName))) != null) {
						Assert.assertTrue(objectName + " is present",
								!driver.findElement(getIdentifier(objectName)).isDisplayed());
					}
				} else {
					Assert.assertEquals(valueExpect, replaceSomeText(returnElement(objectName, null).getText()));

				}
			}
		}

	}

	public void switchTo(String objectName, String number) throws Exception {
		if (objectName.contains("default content")) {
			driver.switchTo().defaultContent();
		} else if (objectName.contains("new page")) {
			Set<String> windows = driver.getWindowHandles();
			Iterator<String> itr = windows.iterator();
			// patName will provide you parent window
			patName = itr.next();
			// chldName will provide you child window
			String chldName = itr.next();
			// Switch to child window
			driver.switchTo().window(chldName);
		} else if (objectName.contains("previous page")) {
			driver.switchTo().window(patName);

		} else {
			if (null != number) {
				driver.switchTo().frame(returnElement(objectName, number));
			} else {
				driver.switchTo().frame(returnElement(objectName, null));
			}
		}
	}

	public String replaceSomeText(String text) {
		String actual = text.replaceAll("\n", "").trim();
		actual = actual.replaceAll("€", "");
		actual = actual.replace("\"", "'");
		return actual.trim();
	}

	public WebElement returnElement(String objectName, String number) throws Exception {
		waitForAjax();
		WebElement ele = null;
		if (number != null) {
			try {
				ele = driver.findElements(getIdentifier(objectName)).get(Integer.parseInt(number) - 1);
			} catch (Exception e) {
				ele = returnElementByText(getIdentifier(objectName), objectName);
			}
		} else {
			try {
				Assert.assertFalse("Object name:'" + objectName + "' is not found in property file!",
						getElement(objectName) == null || objectName.equals(""));
				ele = getElement(objectName);
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		Assert.assertTrue("Element:'" + objectName + "' is not exist", ele != null);
		scrollToView(ele);
		return ele;
	}

	public List<WebElement> returnElementsList(String objectName) throws Exception {
		waitForAjax();
		List<WebElement> eList = null;
		try {
			Assert.assertFalse("Object name:'" + objectName + "' is not found in property file!",
					getElementsList(objectName) == null || objectName.equals(""));
			eList = getElementsList(objectName);
			Assert.assertFalse("Object name:'" + objectName + "' has totally size is 0!",
					getElementsList(objectName).size() == 0);
		} catch (Exception e) {
			// TODO: handle exception
		}
		Assert.assertTrue("Elements list:'" + objectName + "' is not exist", eList != null);
		return eList;
	}

	public WebElement checkMoreElement(String objectName, String value) throws Exception {
		WebElement ele = null;
		if (null != getIdentifier(objectName)) {
			List<WebElement> eles = driver.findElements(getIdentifier(objectName));
			if (eles.size() > 1) {
				// System.out.println(eles.size());
				for (int i = 0; i < eles.size(); i++) {
					if (eles.get(i).isDisplayed() && !"".equals(eles.get(i).getText())
							&& eles.get(i).getText().equals(value)) {
						ele = eles.get(i);
						break;
					} else if (eles.get(i).isDisplayed() && !"".equals(eles.get(i).getAttribute("textContent"))
							&& eles.get(i).getAttribute("textContent").equals(value)) {
						ele = eles.get(i);
						break;
					} else
						ele = null;
				}
			}
		}
		return ele;
	}

	public WebElement returnElementByText(By pathElement, String value) {
		WebElement ele = null;
		List<WebElement> eles = driver.findElements(pathElement);

		for (int i = 0; i < eles.size(); i++) {
			if (eles.get(i).isDisplayed() && !"".equals(eles.get(i).getText()) && eles.get(i).getText().equals(value)) {
				ele = eles.get(i);
				break;
			} else if (eles.get(i).isDisplayed() && !"".equals(eles.get(i).getAttribute("textContent"))
					&& eles.get(i).getAttribute("textContent").equals(value)) {
				ele = eles.get(i);
				break;
			} else
				ele = null;
		}
		return ele;
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

	public void scrollTo(int xPosition, int yPosition) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(" + xPosition + "," + yPosition + ")");
	}

	/**
	 * Scroll to element to view
	 * 
	 * @param element
	 * @throws InterruptedException
	 */
	public void scrollToView(WebElement element) throws InterruptedException {
		if (element.getLocation().getY() > 500) {
			scrollTo(0, element.getLocation().getY() - 200);
		} else {
			scrollTo(0, 0);
		}
		Thread.sleep(200);
	}

	/**
	 * Close page
	 * 
	 * @param scenario2
	 * @throws Exception
	 */

	public void closePage(Scenario scenario) throws Exception {

		if (scenario.isFailed()) {
			try {
				byte[] animation = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(animation, "image/png");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			if (!messageAlert.equals("")) {
				takeScreenExpect("Pass_FinalStep_", scenario);
			}
		}
		link = new ArrayList<>();
		// driver.quit();
		// driver = null;
	}

	/**
	 * 
	 * @param link
	 * @param user
	 * @throws IOException
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	public void openPage(String url, Scenario myScenario) throws IOException, AWTException, InterruptedException {
		String link = ReadProperties.getElementsAndUrls(url);
		//Assert.assertTrue("The status is :'" + getResponse(link) + "' on URL:'" + link + "'", getResponse(link) == 200);
		myScenario.write(link);
		/*if (null != ReadProperties.getElementsAndUrls("auuser")) {
			String part[] = link.split("://");
			Assert.assertTrue(link + " wrong", part.length > 1);
			link = part[0] + "://" + ReadProperties.getElementsAndUrls("auuser") + ":"
					+ ReadProperties.getElementsAndUrls("aupass") + "@" + part[1];
			driver.navigate().to(link);
			Thread.sleep(3000);
			link = ReadProperties.getElementsAndUrls(url);
			driver.navigate().to(link);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Assert.assertFalse("data:,".equals(driver.getTitle()));
		} else {
			Assert.assertFalse("Can't find url: '" + url + "' ", link == null);
			driver.get(link);
		}*/
		driver.navigate().to(link);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		waitForWebState();
	}

	public void openPageToCheckPageSpeed(String link, Scenario myScenario)
			throws IOException, AWTException, InterruptedException {
		if (null != ReadProperties.getElementsAndUrls("auuser")) {
			String part[] = link.split("://");
			link = part[0] + "://" + ReadProperties.getElementsAndUrls("auuser") + ":"
					+ ReadProperties.getElementsAndUrls("aupass") + "@" + part[1];
			System.out.println(link);
			driver.navigate().to(link);
		} else {
			Assert.assertFalse("Can't find url: '" + link + "' ", link == null);
			driver.get(link);
		}
	}

	/**
	 * back page
	 * 
	 * @param doThisStep
	 */
	public void backToPreviousPage() {
		driver.navigate().back();
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

	public void getScenario(Scenario myScenario) {
		this.myScenario = myScenario;
	}

	public void checkPlaceholder(String object, String value) throws Exception {
		Assert.assertEquals(value, (driver.findElement(getIdentifier(object)).getAttribute("placeholder")));
	}

	public void getTitleOfPage(String titlePage) throws InterruptedException {
		waitForAjax();
		Assert.assertEquals("Title of the page:'" + driver.getCurrentUrl() + "' =>", titlePage, driver.getTitle());
	}

	/**
	 * input data to textbox
	 * 
	 * @param objectName
	 * @param position
	 * @param valueExpect
	 * @param doThisStep
	 * @param file
	 * @throws Exception
	 */
	public void inputData(String objectName, String valueExpect, String number) throws Exception {
		if (null != objectName) {
			driver.findElement(getIdentifier(objectName)).clear();
			driver.findElement(getIdentifier(objectName)).sendKeys(valueExpect);
		}
	}

	public void resize(String height, String width) {
		Dimension dimension = new Dimension(Integer.parseInt(width), Integer.parseInt(height));
		driver.manage().window().setSize(dimension);
	}

	private void executeScript(WebElement returnElement, String jscript) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript(jscript, returnElement);
	}

	// Tablet
	public void chooseNewLocationOnDevice(String xOffset, String yOffset, String newLocation) throws Exception {
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("el = document.elementFromPoint(102, 510); el.dblclick();");
		waitForWebState();
	}

	public void DeleteContentExcel(String filePath, String pageName) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int numberOfSheets = workbook.getNumberOfSheets();
		System.err.println(numberOfSheets);
		for (int i = 0; i < numberOfSheets; i++) {
			System.err.println(i);
			workbook.removeSheetAt(numberOfSheets - i - 1);
		}
		workbook.createSheet("Sheet1");
	}

	public int getResponseCode(String url) {
		int status = -1;
		if (url == null || url.isEmpty() || !url.contains("http")) {
			return -1;
		} else {
			// if (t)
			if (url.contains("#")) {
				url = url.replace("#", "");
			}
			if (url.contains("map")) {
				status = 200;
			} else {
				status = RestAssured.get(url).statusCode();
			}

		}
		return status;
	}

	public void highlightElement() throws Exception {

		int getResponse = -1;
		JavascriptExecutor js = (JavascriptExecutor) driver;

		List<WebElement> alllinks = driver.findElements(By.cssSelector("a[href]"));

		for (int i = 0; i < alllinks.size(); i++) {
			getResponse = -1;
			getResponse = this.getResponseCode(alllinks.get(i).toString());
			if (getResponse == -1)
				js.executeScript("arguments[0].style.border = '3px solid red';", alllinks.get(i));

			js.executeScript("arguments[0].style.border = '3px solid green';", alllinks.get(i));
		}
	}

	/**
	 * select dropdowlist
	 * 
	 * @param value
	 * @param objectName
	 * @param position
	 * @param desCriptionStep
	 * @param doThisStep
	 * @throws Exception
	 */
	public void selectDropdowList(String objectName, String value, String number) throws Exception {
		if (null != objectName) {
			if (null != number) {
				Select dr = new Select(returnElement(objectName, number));
				Thread.sleep(500);
				try {
					dr.selectByVisibleText(value);
				} catch (Exception e) {
					try {
						dr.selectByVisibleText(value);
					} catch (Exception e1) {
						dr.selectByValue(value);
					}
				}
			} else {
				Select dr = new Select(returnElement(objectName, null));
				Thread.sleep(500);
				try {
					dr.selectByVisibleText(value);
				} catch (Exception e) {
					try {
						dr.selectByVisibleText(value);
					} catch (Exception e1) {
						dr.selectByValue(value);
					}
				}
			}
		}
	}

	public void selectDropdowList(WebElement element, String value) throws Exception {
		waitForAjax();
		Select dr = new Select(element);
		Thread.sleep(500);
		try {
			dr.selectByVisibleText(value);
		} catch (Exception e) {
			try {
				dr.selectByVisibleText(value);
			} catch (Exception e1) {
				dr.selectByValue(value);
			}
		}
	}

	public void getKey(String url) {
		System.out.println(url.replace(" ", "-"));
	}

	public void selectCheckboxByText(String elementToClick, String selectText, String dropdownList) throws Exception {
		if (!selectText.equals("")) {
			waitForWebState();
			returnElement(elementToClick, null).click();
			waitForWebState();
			List<WebElement> options = driver.findElements(getIdentifier(dropdownList));
			for (int i = 0; i < options.size(); i++) {
				if (options.get(i) instanceof Locatable) {
					Locatable remoteElementDealer = (Locatable) options.get(i);
					remoteElementDealer.getCoordinates().inViewPort();
					String content = options.get(i).getAttribute("innerHTML").replaceAll("<[^>]*>", "").trim()
							.replaceAll("\\s+", " ");
					if (selectText.equalsIgnoreCase(content)) {
						options.get(i).click();
						break;
					}
					Assert.assertFalse("Keyword:[" + selectText + "]can't select in dropdown list!!!",
							i == options.size() - 1);
				}
			}
		}
	}

	// Count number row in Excel file
	public int getRowCount(String sheetName) throws IOException, Exception {
		// FileInputStream fis = new
		// FileInputStream(data.getOutputFile("getURLFromExcel.xlsx"));
		FileInputStream fis = new FileInputStream(sheetName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet("Sheet1");
		int number = sheet.getLastRowNum();
		return number + 1;
	}

	public int getCellCount(String sheetName) throws IOException, Exception {
		FileInputStream fis = new FileInputStream(sheetName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet("Sheet1");
		int number = sheet.getRow(0).getPhysicalNumberOfCells();
		return number;
	}

	public String getCurrentDate() {

		String dateCurrent = "";
		Date date = new Date();

		dateCurrent = date.toString().replace(":", "");
		return dateCurrent;
	}

	public void readDataExcel() throws Exception {

		String fileName = data.getOutputFile("getURLFromExcel.xlsx");
		int rowCount = this.getRowCount(fileName);
		int cellCount = this.getCellCount(fileName);
		// System.out.println("Count is: "+rowCount + cellCount);
		FileInputStream file = new FileInputStream(fileName);

		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Row HeaderRow = sheet.getRow(0);

		for (int row = 0; row <= rowCount; row++) {
			for (int cell = 0; cell < cellCount; cell++) {
				// System.out.println("Data: " +
				// sheet.getRow(row).getCell(cell).toString().trim());
			}
		}
		file.close();
	}

	public void moveSliderCastout() throws Exception {
		// WebElement slider =
		// driver.findElement(By.xpath(".//div[@class='filter__progress']/.//div[@class='pointer
		// low']"));
		WebElement slider = driver.findElement(By.xpath(
				".//*[@id='popup-filter-gallery']/.//div[@class='filter__progress']/.//*[@class='irs-slider from']"));
		Actions move = new Actions(driver);
		Action action = (Action) move.dragAndDropBy(slider, 90, 0).build();
		action.perform();
		Thread.sleep(1000);
	}

	public void movePinOnMap() throws Exception {
		Thread.sleep(10000);
		((JavascriptExecutor) driver).executeScript("el = document.elementFromPoint(885,222); el.click();");
		waitForWebState();
		// this.convertGeoToPixel();
	}

	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
	}

	public void wait(String value, String doThisStep) throws InterruptedException {
		if (doThisStep == null || doThisStep.equals("do this step: yes")) {
			int milisecond = Integer.parseInt(value);
			Thread.sleep(milisecond);
		}
	}

	// Streaming page
	public boolean isElementDisplay(WebElement e) {
		boolean check = false;
		if (e.isDisplayed()) {
			check = true;
		}
		return check;
	}

	public void checkGoToPage(String page) {
		String resultPage = ReadProperties.getElementsAndUrls(page);
		String actualPage = driver.getCurrentUrl();
		Assert.assertEquals(resultPage, actualPage);
	}

	public void captureFullScreen(String screenshotName) throws IOException, InterruptedException {
		waitForAjax();
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);
		ImageIO.write(screenshot.getImage(), "PNG", new File(data.getOutputScreenshot(screenshotName) + ".png"));
	}

	public int getResponse(String urlString) throws MalformedURLException, IOException {
		int reponseCode = 0;
		try {
			URL url = new URL(urlString);
			HttpURLConnection huc = (HttpURLConnection) url.openConnection();
			if (!ReadProperties.getElementsAndUrls("auuser").equals("")
					&& !ReadProperties.getElementsAndUrls("aupass").equals("")) {
				String author = ReadProperties.getElementsAndUrls("auuser") + ":"
						+ ReadProperties.getElementsAndUrls("aupass");
				String encoding = Base64.encodeBytes(author.getBytes());
				huc.setRequestProperty("Authorization", "Basic " + encoding);
			}
			huc.setRequestMethod("GET");
			huc.connect();
			reponseCode = huc.getResponseCode();
		} catch (Exception e) {
			e.getMessage();
		}
		return reponseCode;
	}

	public void setValueInExcel(int rowNum, int colNum, String value, String color) {
		row = sheet.getRow(rowNum);
		if (row == null) {
			row = sheet.createRow(rowNum);
		}

		cell = row.getCell(colNum, org.apache.poi.ss.usermodel.Row.RETURN_BLANK_AS_NULL);
		if (cell == null) {
			cell = row.createCell(colNum);
			cell.setCellValue(value);
		} else {
			cell.setCellValue(value);
		}
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setAlignment(HorizontalAlignment.LEFT);
		style.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
		// Set color
		switch (color) {
		case "PASSED":
			font.setColor(HSSFColor.WHITE.index);
			style.setFillForegroundColor(HSSFColor.GREEN.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setFont(font);
			cell.setCellStyle(style);
			break;
		case "FAIL":
			font.setColor(HSSFColor.WHITE.index);
			style.setFillForegroundColor(HSSFColor.RED.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setFont(font);
			cell.setCellStyle(style);
			break;
		case "YELLOW":
			font.setColor(HSSFColor.BLACK.index);
			style.setFillForegroundColor(HSSFColor.YELLOW.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setFont(font);
			cell.setCellStyle(style);
			break;
		case "ORANGE":
			font.setColor(HSSFColor.BLACK.index);
			style.setFillForegroundColor(HSSFColor.ORANGE.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setFont(font);
			cell.setCellStyle(style);
			break;
		default:
			font.setColor(HSSFColor.BLACK.index);
			style.setFont(font);
			cell.setCellStyle(style);
			break;
		}
	}

	public void checkResponseCode(String sheetName, String excelName) throws IOException {
		FileInputStream file = new FileInputStream(data.getResponseCode(excelName));
		workbook = new XSSFWorkbook(file);
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			if (workbook.getSheetName(i).equals(sheetName)) {
				workbook.removeSheetAt(i);
				break;
			}
		}
		workbook.createSheet(sheetName);
		sheet = workbook.getSheet(sheetName);
		setValueInExcel(0, 0, "URL", "");
		setValueInExcel(0, 1, driver.getCurrentUrl(), "");
		setValueInExcel(1, 0, "NAME", "ORANGE");
		setValueInExcel(1, 1, "URL", "ORANGE");
		setValueInExcel(1, 2, "STATUS", "ORANGE");
		List<WebElement> urlList = driver.findElements(By.tagName("a"));
		List<String> hrefList = new ArrayList<>();
		List<String> urlNameList = new ArrayList<>();
		for (int i = 0; i < urlList.size(); i++) {
			try {
				if (!urlList.get(i).getAttribute("href").trim().equals("javascript:void(0)")
						&& !urlList.get(i).getAttribute("href").trim().equals("")) {
					String urlName = urlList.get(i).getAttribute("innerHTML").replaceAll("<[^>]*>", "").trim();
					String url = urlList.get(i).getAttribute("href").trim();
					urlNameList.add(urlName);
					hrefList.add(url);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		boolean statusScenario = true;
		for (int i = 0; i < hrefList.size(); i++) {
			String urlName = urlNameList.get(i);
			if (urlName.equals("")) {
				urlName = "No name";
				setValueInExcel(i + 2, 0, urlName, "YELLOW");
			} else {
				setValueInExcel(i + 2, 0, urlName, "");
			}

			setValueInExcel(i + 2, 1, hrefList.get(i), "");
			System.out.println(hrefList.get(i));
			int status = getResponse(hrefList.get(i));
			if (status == 200) {
				setValueInExcel(i + 2, 2, Integer.toString(status), "PASSED");
			} else {
				setValueInExcel(i + 2, 2, Integer.toString(status), "FAIL");
				statusScenario = false;
			}
		}
		FileOutputStream streamOut = new FileOutputStream(data.getResponseCode(excelName));
		workbook.write(streamOut);
		streamOut.close();
		Assert.assertFalse("Status of some links are wrong! Please check the file '" + excelName + "'",
				!statusScenario);

	}

	public void checkAltOfImages(String sheetName, String excelName) throws IOException {
		FileInputStream file = new FileInputStream(data.getResponseCode(excelName));
		workbook = new XSSFWorkbook(file);
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			if (workbook.getSheetName(i).equals(sheetName)) {
				workbook.removeSheetAt(i);
				break;
			}
		}
		workbook.createSheet(sheetName);
		sheet = workbook.getSheet(sheetName);
		setValueInExcel(0, 0, "URL", "");
		setValueInExcel(0, 1, driver.getCurrentUrl(), "");
		setValueInExcel(1, 0, "Image URLs", "ORANGE");
		setValueInExcel(1, 1, "ALT content", "ORANGE");
		setValueInExcel(1, 2, "Pass/Fail", "ORANGE");
		List<WebElement> imageUrlsList = driver.findElements(By.tagName("img"));
		List<String> srcList = new ArrayList<>();
		List<String> altList = new ArrayList<>();
		for (int i = 0; i < imageUrlsList.size(); i++) {
			try {
				if (!imageUrlsList.get(i).getAttribute("src").trim().equals("javascript:void(0)")
						&& !imageUrlsList.get(i).getAttribute("src").trim().equals("")) {
					String altImage = imageUrlsList.get(i).getAttribute("alt").trim();
					String imageUrl = imageUrlsList.get(i).getAttribute("src").trim();
					altList.add(altImage);
					srcList.add(imageUrl);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		boolean statusScenario = true;
		for (int i = 0; i < srcList.size(); i++) {
			setValueInExcel(i + 2, 0, srcList.get(i), "");
			System.out.println(srcList.get(i));
			String alt = altList.get(i);
			setValueInExcel(i + 2, 1, alt, "");
			if (!alt.equals("")) {
				setValueInExcel(i + 2, 2, "Pass", "PASSED");
			} else {
				setValueInExcel(i + 2, 2, "Fail", "FAIL");
				statusScenario = false;
			}
		}
		FileOutputStream streamOut = new FileOutputStream(data.getResponseCode(excelName));
		workbook.write(streamOut);
		streamOut.close();
		Assert.assertFalse("Some alts are empty! Please check the file '" + excelName + "'", !statusScenario);
	}

	public void checkAlt_CaseFail(String sheetName, String excelName) throws IOException {
		FileInputStream file = new FileInputStream(data.getResponseCode(excelName));
		workbook = new XSSFWorkbook(file);
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			if (workbook.getSheetName(i).equals(sheetName)) {
				workbook.removeSheetAt(i);
				break;
			}
		}
		workbook.createSheet(sheetName);
		sheet = workbook.getSheet(sheetName);
		setValueInExcel(0, 0, "URL", "");
		setValueInExcel(0, 1, driver.getCurrentUrl(), "");
		setValueInExcel(1, 0, "Image URLs", "ORANGE");
		setValueInExcel(1, 1, "ALT content", "ORANGE");
		setValueInExcel(1, 2, "Pass/Fail", "ORANGE");
		List<WebElement> imageUrlsList = driver.findElements(By.tagName("img"));
		List<String> srcList = new ArrayList<>();
		List<String> altList = new ArrayList<>();
		for (int i = 0; i < imageUrlsList.size(); i++) {
			try {
				if (!imageUrlsList.get(i).getAttribute("src").trim().equals("javascript:void(0)")
						&& !imageUrlsList.get(i).getAttribute("src").trim().equals("")
						&& imageUrlsList.get(i).getAttribute("alt").equals("")) {
					String altImage = imageUrlsList.get(i).getAttribute("alt").trim();
					String imageUrl = imageUrlsList.get(i).getAttribute("src").trim();
					altList.add(altImage);
					srcList.add(imageUrl);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		boolean statusScenario = true;
		for (int i = 0; i < srcList.size(); i++) {
			System.out.println(srcList.get(i));
			String alt = altList.get(i);
			if (alt.equals("")) {
				setValueInExcel(i + 2, 0, srcList.get(i), "");
				setValueInExcel(i + 2, 1, alt, "");
				setValueInExcel(i + 2, 2, "Fail", "FAIL");
				statusScenario = false;
			}
		}
		FileOutputStream streamOut = new FileOutputStream(data.getResponseCode(excelName));
		workbook.write(streamOut);
		streamOut.close();
		Assert.assertFalse("Some alts are empty! Please check the file '" + excelName + "'", !statusScenario);
	}

	public void checkMetaTag(String metaKeyword, String metaDescription) {
		List<WebElement> metaList = driver.findElements(By.tagName("meta"));
		for (int i = 0; i < metaList.size(); i++) {
			if (metaList.get(i).getAttribute("name").equals("keywords")) {
				Assert.assertEquals("URL:" + driver.getCurrentUrl() + " => Meta keywords =>", metaKeyword,
						metaList.get(i).getAttribute("content"));
				break;
			}
			Assert.assertFalse("URL:" + driver.getCurrentUrl() + " => Can't find meta keywords!",
					i == metaList.size() - 1);
		}
		for (int i = 0; i < metaList.size(); i++) {
			if (metaList.get(i).getAttribute("name").equals("description")) {
				Assert.assertEquals("URL:" + driver.getCurrentUrl() + " => Meta description =>", metaDescription,
						metaList.get(i).getAttribute("content"));
				break;
			}
			Assert.assertFalse("URL:" + driver.getCurrentUrl() + " => Can't find meta description!",
					i == metaList.size() - 1);
		}
	}

	public String randomText(int characterNum) {
		RandomStringGenerator randomStringGenerator = new RandomStringGenerator.Builder().withinRange('a', 'z')
				.filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS).build();
		return randomStringGenerator.generate(characterNum);

	}

	public void verifyLengthText(int characterNum, String field, String button, String errorElement,
			String errorMessage) throws Exception {
		waitForAjax();
		String text = randomText(characterNum);
		System.out.println(text.length());
		inputData(field, text, null);
		clickObject(button, null);
		verifyText(errorElement, errorMessage, null);
	}

	public void checkTimePageLoad(String sheetName, String excelName)
			throws IOException, AWTException, InterruptedException {
		FileInputStream file = new FileInputStream(data.getResponseCode(excelName));
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheet(sheetName);
		int totalRow = sheet.getLastRowNum() + 1;
		for (int i = 1; i < totalRow; i++) {
			XSSFRow row = sheet.getRow(i);
			XSSFCell cell = row.getCell(0, Row.RETURN_BLANK_AS_NULL);
			String url = "";
			if (cell != null) {
				url = row.getCell(0, Row.RETURN_BLANK_AS_NULL).getStringCellValue();
			}
			int response = getResponse(url);
			if (!url.equals("")) {
				if (response == 200) {
					openPageToCheckPageSpeed(url, myScenario);
					Long loadtime = (Long) ((JavascriptExecutor) driver).executeScript(
							"return performance.timing.loadEventEnd - performance.timing.navigationStart;");
					System.out.println(loadtime + "(ms)");
					setValueInExcel(i, 1, loadtime + "(ms)", "");
				} else {
					setValueInExcel(i, 1, "The link is broken. Status is " + response, "YELLOW");
				}
			}
		}
		FileOutputStream streamOut = new FileOutputStream(data.getResponseCode(excelName));
		workbook.write(streamOut);
		streamOut.close();
	}

	public static String deAccent(String str) {
		String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(nfdNormalizedString).replaceAll("");
	}

	public void checkUnicodeContent(String sheetName, String excelName) throws IOException {
		boolean isFail = false;
		;
		List<WebElement> contentList = driver.findElements(By.xpath("//*[contains(text(),*)]"));
		List<String> fullContentUTF8 = new ArrayList<>();
		for (int i = 0; i < contentList.size(); i++) {
			String text = contentList.get(i).getText().trim();
			// .getAttribute("innerHTML").replaceAll("<[^>]*>", "").trim();
			if (!text.equals("") && !deAccent(text).equals(text) && text.length() <= 32767) {
				isFail = true;
				System.out.println("=======");
				System.out.println(text);
				fullContentUTF8.add(text);
			}
		}
		if (isFail) {
			FileInputStream file = new FileInputStream(data.getResponseCode(excelName));
			workbook = new XSSFWorkbook(file);
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
				if (workbook.getSheetName(i).equals(sheetName)) {
					workbook.removeSheetAt(i);
					break;
				}
			}
			workbook.createSheet(sheetName);
			sheet = workbook.getSheet(sheetName);
			setValueInExcel(0, 0, "URL", "");
			setValueInExcel(0, 1, driver.getCurrentUrl(), "");
			setValueInExcel(1, 0, "Full Content", "ORANGE");
			setValueInExcel(1, 1, "Pass/Fail", "ORANGE");

			for (int i = 0; i < fullContentUTF8.size(); i++) {
				setValueInExcel(i + 2, 0, fullContentUTF8.get(i), "");
				setValueInExcel(i + 2, 1, "Fail", "FAIL");
			}

			FileOutputStream streamOut = new FileOutputStream(data.getResponseCode(excelName));
			workbook.write(streamOut);
			streamOut.close();
			Assert.assertFalse("Some content are wrong! Please check the file '" + excelName + "'", false);
		}

	}

	public void verifyEmailAddress(String field, String button, String errorElement, String errorMessage)
			throws Exception {
		waitForAjax();
		String[] email = { "abc", "Abc.yopmail.com", "A@b@c@yopmail.com", "k]l@yopmail.com",
				"a<b@yopmail.com" + "f;g@yopmail.com", "this is?notallowed@yopmail.com", "not\\allowed@yopmail.com",
				"1234*x@yopmail.com", "1234&x@yopmail.com", "1234$x@yopmail.com", "1234%x@yopmail.com",
				"1234+x@yopmail.com", "john..doe@yopmail.com", "john.doe@yopmail..com", "Abc@yopmail." + "Abc@yopmail.",
				"Abc@yopmail", "@yopmail", "Abc@yopmail.com1" };
		for (int i = 0; i < email.length; i++) {
			inputData(field, email[i], null);
			clickObject(button, null);
			if (errorMessage.equals("displayed")) {
				Assert.assertTrue("The inputed value: '" + email[i] + "' = > " + errorElement + " is not present",
						null != driver.findElement(getIdentifier(errorElement)));
			} else if (errorMessage.equals("not displayed")) {
				if ((driver.findElement(getIdentifier(errorElement))) != null) {
					Assert.assertTrue("The inputed value: '" + email[i] + "' = > " + errorElement + " is present",
							!driver.findElement(getIdentifier(errorElement)).isDisplayed());
				}
			} else {
				Assert.assertEquals("The inputed value: '" + email[i] + "' = > ", errorMessage,
						replaceSomeText(returnElement(errorElement, null).getText()));

			}
		}
	}

	public WebElement checkElementPaging(String paging) {
		WebElement next = null;
		try {
			next = getElement(paging);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return next;
	}

	public void checkSearch(String value, String eSearchBox, String eSubmit, String resultList, String paging)
			throws Exception {
		waitForAjax();
		inputData(eSearchBox, value, null);
		clickObject(eSubmit, null);
		waitForAjax();
		List<WebElement> list = returnElementsList(resultList);
		Assert.assertTrue("Results list is empty", list.size() > 0);
		for (int i = 0; i < list.size(); i++) {
			String result = list.get(i).getText().trim();
			scrollToView(list.get(i));
			Assert.assertTrue("Expected keyword:'" + value + "' but result is:'" + result + "'",
					result.toLowerCase().contains(value.toLowerCase()));
		}
		while (checkElementPaging(paging) != null) {
			returnElement(paging, null).click();
			waitForAjax();
			list = returnElementsList(resultList);
			for (int i = 0; i < list.size(); i++) {
				String result = list.get(i).getText().trim();
				scrollToView(list.get(i));
				Assert.assertTrue("Expected keyword:'" + value + "' but result is:'" + result + "'",
						result.toLowerCase().contains(value.toLowerCase()));
			}
		}
	}

	public String getExcelLine(int row, int cell, XSSFSheet sheet) {
		XSSFRow rowExcel = sheet.getRow(row);
		XSSFCell cellExcel = rowExcel.getCell(cell, Row.RETURN_BLANK_AS_NULL);
		String name = "";
		if (cellExcel != null) {
			try {
				name = rowExcel.getCell(cell, Row.RETURN_BLANK_AS_NULL).getStringCellValue();
			} catch (Exception e) {
				name = String.valueOf(rowExcel.getCell(cell, Row.RETURN_BLANK_AS_NULL).getNumericCellValue());
			}
		}
		return name;
	}

	public void findOrCreateTemplateInSitecore(int i, DataTable table) throws Exception {
		List<List<String>> data = table.raw();
		List<WebElement> level1List = returnElementsList("Sitecore Level 1 list");
		int path = Integer.valueOf(data.get(1).get(0));
		String pathsExcel[] = getExcelLine(i, path, sheet).split(">");
		String xpath = "";
		int index = 0;
		for (int j = 0; j < pathsExcel.length; j++) {
			waitForAjax();
			if (j != 0)
				xpath += "div[2]/div/";
			if (j != pathsExcel.length - 1) {
				level1List = returnElementsList("Sitecore Level 1 list");
				if (j == 0) {
					for (int k = 0; k < level1List.size(); k++) {
						String nameLevel1 = level1List.get(k).findElement(By.xpath(".//a/span")).getText().trim();
						if (nameLevel1.equals(pathsExcel[j])) {
							if (level1List.get(k).findElement(By.xpath(".//img")).getAttribute("src")
									.contains("collap")) {
								index = k;
								scrollToView(level1List.get(k));
								level1List.get(k).findElement(By.xpath(".//img")).click();
								break;
							}
							break;
						}
						Assert.assertFalse(
								"Can't find:'" + pathsExcel[j] + "' in path folder:'" + getExcelLine(i, path, sheet)
										+ "',row:'" + i + "' in excel file. Please recheck!",
								k == level1List.size() - 1);
					}
				} else {
					List<WebElement> nextLevelList = level1List.get(index)
							.findElements(By.xpath(".//" + xpath + "a/span"));
					List<WebElement> nextLevelList1 = level1List.get(index)
							.findElements(By.xpath(".//" + xpath + "img"));
					for (int k = 0; k < nextLevelList.size(); k++) {
						String nameLevel1 = nextLevelList.get(k).getText().trim();
						if (nameLevel1.equals(pathsExcel[j])) {
							if (nextLevelList1.get(k).getAttribute("src").contains("collap")) {
								scrollToView(nextLevelList1.get(k));
								nextLevelList1.get(k).click();
								break;
							}
							break;
						}
						Assert.assertFalse(
								"Can't find:'" + pathsExcel[j] + "' in path folder:'" + getExcelLine(i, path, sheet)
										+ "',row:'" + i + "' in excel file. Please recheck!",
								k == nextLevelList.size() - 1);
					}
				}
			} else {
				List<WebElement> nextLevelList = level1List.get(index).findElements(By.xpath(".//" + xpath + "a/span"));
				List<WebElement> nextLevelList1 = level1List.get(index).findElements(By.xpath(".//" + xpath + "img"));
				for (int k = 0; k < nextLevelList.size(); k++) {
					String nameLevel1 = nextLevelList.get(k).getText().trim();
					if (nameLevel1.equals(pathsExcel[j])) {
						if (nextLevelList1.get(k).getAttribute("src").contains("collap")) {
							scrollToView(nextLevelList1.get(k));
							nextLevelList1.get(k).click();
							break;
						}
						break;
					}
					Assert.assertFalse(
							"Can't find:'" + pathsExcel[j] + "' in path folder:'" + getExcelLine(i, path, sheet)
									+ "',row:'" + i + "' in excel file. Please recheck!",
							k == nextLevelList.size() - 1);
				}
				String xpathComponent = xpath + "div[2]/div/";
				List<WebElement> componentList = level1List.get(index)
						.findElements(By.xpath(".//" + xpathComponent + "a/span"));
				String component = getExcelLine(i, Integer.valueOf(data.get(1).get(2)), sheet);
				boolean found = false;
				if (componentList.size() > 0) {
					for (int k = 0; k < component.length(); k++) {
						if (componentList.get(k).getText().trim().equals(component)) {
							scrollToView(componentList.get(k));
							componentList.get(k).click();
							found = true;
							break;
						}
					}
				}
				if (!found) {
					List<WebElement> finalLevel = level1List.get(index)
							.findElements(By.xpath(".//" + xpath + "a/span"));
					for (int k = 0; k < finalLevel.size(); k++) {
						String nameLevel1 = finalLevel.get(k).getText().trim();
						if (nameLevel1.equals(pathsExcel[j])) {
							rightClickElement(finalLevel.get(k));
							break;
						}
					}
					waitForAjax();
					returnElement("Sitecore Insert", null).click();
					waitForAjax();
					List<WebElement> contextMenuList = returnElementsList("Sitecore Context Menu list");
					boolean isTemplate = false;
					String templateName = getExcelLine(i, Integer.valueOf(data.get(1).get(3)), sheet);
					for (int k = 0; k < contextMenuList.size(); k++) {
						if (contextMenuList.get(k).getText().trim().equals(templateName)) {
							if (contextMenuList.get(k).getText().trim().equals(Constant.INSERT_FROM_TEMPLATE)) {
								isTemplate = true;
							}
							contextMenuList.get(k).click();
							break;
						}
						Assert.assertFalse(
								"Create new component:'" + component + "' but can't find template name:'" + templateName
										+ "',row:'" + i + "' in excel file. Please recheck!",
								k == contextMenuList.size() - 1);
					}
					waitForAjax();
					if (isTemplate) {
						driver.switchTo().frame(returnElement("Sitecore fromTemplate Iframe", null));
						driver.switchTo().frame(returnElement("Sitecore fromTemplate Iframe1", null));
						String pathTemplate = getExcelLine(i, Integer.valueOf(data.get(1).get(4)), sheet);
						Assert.assertFalse("Creating new template but the Template path is empty,row:'" + i
								+ "' in excel file. Please recheck!", pathTemplate.equals(""));
						inputData("Sitecore fromTemplate Path",
								getExcelLine(i, Integer.valueOf(data.get(1).get(4)), sheet), null);
						inputData("Sitecore fromTemplate Item Name",
								getExcelLine(i, Integer.valueOf(data.get(1).get(2)), sheet), null);
						clickObject("Sitecore fromTemplate Insert button", null);
						waitForAjax();
						driver.switchTo().defaultContent();
						driver.switchTo().frame(returnElement("Sitecore fromTemplate Iframe", null));
						boolean foundError = false;
						try {
							getElement("Sitecore fromTemplate Error Popup").isDisplayed();
							foundError = true;
						} catch (Exception e) {
							// TODO: handle exception
						}
						Assert.assertFalse("Component name or template path are not correct,row:'" + i
								+ "' in excel file. Please recheck!", foundError);
						driver.switchTo().defaultContent();
					} else {
						driver.switchTo().frame(returnElement("Sitecore fromTemplate Iframe", null));
						driver.switchTo().frame(returnElement("Sitecore fromTemplate Iframe1", null));
						inputData("Sitecore-fromTemplate Create Item Name",
								getExcelLine(i, Integer.valueOf(data.get(1).get(2)), sheet), null);
						clickObject("Sitecore fromTemplate Insert button", null);
						driver.switchTo().defaultContent();
					}
				}
			}
		}
	}

	public void inputContent(String sheetName, String excelName, DataTable table) throws Exception {
		waitForAjax();
		FileInputStream file = new FileInputStream(data.getResponseCode(excelName));
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheet(sheetName);
		int totalRow = sheet.getLastRowNum() + 1;
		List<List<String>> data = table.raw();
		for (int i = 1; i < totalRow; i++) {
			findOrCreateTemplateInSitecore(i, table);

			List<WebElement> titleList = returnElementsList("Sitecore Title list");
			for (int j = 0; j < titleList.size(); j++) {
				if (titleList.get(j).getAttribute("class").trim().contains("Collaps")) {
					scrollToView(titleList.get(j));
					titleList.get(j).click();
				}
			}
			int name = Integer.valueOf(data.get(1).get(5));
			String nameExcel = getExcelLine(i, name, sheet);
			System.out.println(nameExcel);
			List<WebElement> fieldsList = returnElementsList("Sitecore Fields list");
			for (int j = 0; j < fieldsList.size(); j++) {
				String fieldName = fieldsList.get(j).findElement(getIdentifier("Sitecore Field Name"))
						.getAttribute("innerHTML").replaceAll("<[^>]*>", "").trim();
				if (fieldName.equals(nameExcel)) {
					scrollToView(fieldsList.get(j));
					int type = Integer.valueOf(data.get(1).get(6));
					String typeField = getExcelLine(i, type, sheet);
					if (typeField.equals("Input")) {
						WebElement e = fieldsList.get(j).findElement(getIdentifier("Sitecore Field Input"));
						e.clear();
						int value = Integer.valueOf(data.get(1).get(7));
						e.sendKeys(getExcelLine(i, value, sheet));
						break;
					} else if (typeField.equals("Image")) {
						WebElement e = fieldsList.get(j).findElement(getIdentifier("Sitecore Select Image"));
						e.click();
						System.exit(0);
						break;

					} else if (typeField.equals("Dropdown")) {
						WebElement e = fieldsList.get(j).findElement(getIdentifier("Sitecore Select Dropdown"));
						selectDropdowList(e, getExcelLine(i, Integer.valueOf(data.get(1).get(7)), sheet));
						break;
					} else if (typeField.equals("Checkbox")) {
						WebElement e = fieldsList.get(j).findElement(getIdentifier("Sitecore Select Checkbox"));
						String value = getExcelLine(i, Integer.valueOf(data.get(1).get(7)), sheet);
						if (value.equals("Yes")) {
							if (e.getAttribute("checked") == null) {
								e.click();
							}
						} else {

							if (e.getAttribute("checked") != null) {
								e.click();
							}
						}
						break;
					} else {
						Assert.assertFalse("Field type is not correct:'"
								+ getExcelLine(i, Integer.valueOf(data.get(1).get(6)), sheet) + "', row:" + i
								+ " in excel file. Please recheck!", true);
					}
					break;
				}
				Assert.assertFalse("Can't find field name:'" + nameExcel + "', row:" + i
						+ " in excel file on Sitecore. Please recheck!", j == fieldsList.size() - 1);
			}
			returnElement("Sitecore Save button", null).click();
		}
	}

	public void rightClickElement(String objectName) throws Exception {
		waitForAjax();
		Actions myAction = new Actions(driver);
		myAction.contextClick(returnElement(objectName, null)).build().perform();
	}

	public void doubleClick(String objectName) throws Exception {
		waitForAjax();
		Actions myAction = new Actions(driver);
		myAction.moveToElement(returnElement(objectName, null)).doubleClick().build().perform();
	}

	public void hoverElement(String objectName) throws Exception {
		waitForAjax();
		Actions myAction = new Actions(driver);
		myAction.moveToElement(returnElement(objectName, null)).perform();
	}

	public void rightClickElement(WebElement element) throws Exception {
		waitForAjax();
		Actions myAction = new Actions(driver);
		myAction.contextClick(element).build().perform();
	}

	public void scrollToElement(WebElement element) throws InterruptedException {
		waitForAjax();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
	}

	public void checkDistance2Element() throws Exception {
		waitForAjax();
		System.out.println(returnElement("", null).getLocation().getX());
		System.out.println(returnElement("", null).getLocation().getX());

	}

	public void takeScreenShort(String screenshotName, Scenario myScenario) throws InterruptedException, IOException {
		waitForAjax();
		DataProvider data = new DataProvider();
		Thread.sleep(2000);
		File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		BufferedImage img = ImageIO.read(screen);
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(data.getOutputScreenshot(screenshotName));
		} catch (java.io.FileNotFoundException io) {
			io.printStackTrace();
		}
		// ScreenShort full page on firefox so need cut of image fix only view
		// current screen
		if (ReadProperties.getConfigSelenium("run-browser").equals(Constant.BROWSER_FIREFOX)) {
			int y = 0;
			int yS = driver.manage().window().getSize().getHeight();
			if (value != null && value != 0) {
				y = (int) (y + value);
				yS = img.getHeight() - y;
			} else {
				yS = img.getHeight();
			}

			if (driver.manage().window().getSize().getHeight() <= yS) {
				yS = driver.manage().window().getSize().getHeight();
			}
			BufferedImage dest = img.getSubimage(0, y, img.getWidth(), yS);
			ImageIO.write(dest, "png", out);
		} else {
			ImageIO.write(img, "png", out);
		}
		FileUtils.copyFile(screen, new File(data.getOutputScreenshot(screenshotName.replaceAll(" ", "_") + ".png")));
	}

	public static void uploadAttachment(String username, String password, String jiraID, String file)
			throws ClientProtocolException, IOException {
		File f = new File(file);// data.getOutputScreenshot("Test.png")
		CloseableHttpClient httpClient = HttpClients.createDefault();
		System.out.println("attachments: " + ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID
				+ "/attachments");
		HttpPost post = new HttpPost(
				ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID + "/attachments");
		if (!username.equals("") && !password.equals("")) {
			String author = username + ":" + password;
			String encoding = Base64.encodeBytes(author.getBytes());
			post.setHeader("Authorization", "Basic " + encoding);
		}
		// Get authorization to connect to JIRA
		// AUF---post.setHeader("Authorization", "Basic
		// cGh1b25nLmRhbmc6QjRvcGh1b245QDEyMw==");
		// post.setHeader("Authorization", "Basic
		// bmhhbi5uZ3V5ZW46SzMzcHdAbGsxbmd0aHV5I24=");
		post.setHeader("X-Atlassian-Token", "nocheck");
		HttpEntity reqEntity = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
				.addBinaryBody("file", new FileInputStream(f), ContentType.APPLICATION_OCTET_STREAM, f.getName())
				.build();
		post.setEntity(reqEntity);
		post.setHeader(reqEntity.getContentType());
		CloseableHttpResponse response = httpClient.execute(post);
	}

	public void getUserCasesListAndRunAuto(String excelName) throws Exception {
		usercase = JiraTicketsList.getJiraTicketsList(ReadAPI.getAPI(ReadProperties.getConfigSelenium("jiraURL")
				+ "/rest/api/2/search?jql=project=" + ReadProperties.getConfigSelenium("projectKey")
				+ "&fields=issue,status,name,issuetype,description,labels"));
		System.out.println(usercase.getUserCasesList().size());
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_YY_HH_mm");
		Date date = new Date();
		String sheetName = "Automation_test_" + sdf.format(date);
		FileInputStream file = new FileInputStream(data.getResponseCode(excelName));
		workbook = new XSSFWorkbook(file);
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			if (workbook.getSheetName(i).equals(sheetName)) {
				workbook.removeSheetAt(i);
				break;
			}
		}
		workbook.createSheet(sheetName);
		sheet = workbook.getSheet(sheetName);
		System.out.println(sheetName);
		setValueInExcel(0, 0, "Jira ID", "ORANGE");
		setValueInExcel(0, 1, "Note", "ORANGE");
		setValueInExcel(0, 2, "Status", "ORANGE");
		for (int i = 0; i < usercase.getUserCasesList().size(); i++) {
			messageAlert = "";
			errorMessage = "";
			String description = usercase.getUserCasesList().get(i).getDescription();
			String jiraID = usercase.getUserCasesList().get(i).getKey();
			setValueInExcel(i + 1, 0, jiraID, "");
			SimpleDateFormat df = new SimpleDateFormat("HH:mm dd_MMM_YYYY");
			Date da = new Date();
			if (description.equals("")) {
				setValueInExcel(i + 1, 1,
						"Can't not run automation test because the description on this jira ticket is empty!", "");
				setValueInExcel(i + 1, 2, "Pending", "YELLOW");
			} else {
				boolean foundStep = false;
				String[] u = description.split("\r\n");
				for (int j = 0; j < u.length; j++) {
					if (u[j].startsWith(" #") || u[j].startsWith("#")) {
						foundStep = true;
						String[] step = u[j].split("#");
						runScenario(step[1].trim(), false);
						if (!messageAlert.equals("")) {
							setValueInExcel(i + 1, 1, messageAlert, "");
							setValueInExcel(i + 1, 2, "Pending", "YELLOW");
							break;
						}
						String timeToCheck = df.format(da);
						if (!errorMessage.equals("")) {
							setValueInExcel(i + 1, 1, messageAlert, "");
							setValueInExcel(i + 1, 2, "Fail", "FAIL");
							String comment = "Hi,\\r\\n\\n" + "This usercase is NOT Ok at " + timeToCheck + "\\r\\n"
									+ "Actual result: " + errorMessage + "\\r\\n" + "Please see attachment("
									+ (jiraID + " " + timeToCheck).replaceAll(":", "").replaceAll(" ", "_") + ".png"
									+ ") for more details\\r\\n" + "It is checked by Automation team.\\r\\n\\n"
									+ "Thanks";
							ReadAPI.postAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID
									+ "/comment", paramsCommentJira(comment));
							ReadAPI.putAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID,
									paramsRemoveLabelJira(Constant.LABEL_PASS));
							ReadAPI.putAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID,
									paramsAddLabelJira(Constant.LABEL_FAIL));
							takeScreenShort((jiraID + " " + timeToCheck).replaceAll(":", ""), myScenario);
							uploadAttachment("", "", jiraID, data.getOutputScreenshot(
									(jiraID + " " + timeToCheck).replaceAll(":", "").replaceAll(" ", "_") + ".png"));
							break;
						}
					}
					if (errorMessage.equals("") && j == u.length - 1) {
						setValueInExcel(i + 1, 1, errorMessage, "");
						setValueInExcel(i + 1, 2, "Pass", "PASSED");
						String comment = "Hi,\\r\\n\\n" + "This usercase is Ok at " + df.format(da) + "\\r\\n"
								+ "It is checked by Automation team\\r\\n\\n" + "Thanks";
						ReadAPI.postAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID
								+ "/comment", paramsCommentJira(comment));
						ReadAPI.putAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID,
								paramsRemoveLabelJira(Constant.LABEL_FAIL));
						ReadAPI.putAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID,
								paramsAddLabelJira(Constant.LABEL_PASS));
						break;
					}
					if (j == u.length - 1 && !foundStep) {
						setValueInExcel(i + 1, 1, "Can't find any step of description in this usercase!", "");
						setValueInExcel(i + 1, 2, "Pending", "YELLOW");
					}
				}
			}
			FileOutputStream streamOut = new FileOutputStream(data.getResponseCode(excelName));
			workbook.write(streamOut);
			streamOut.close();
		}

	}

	private static String paramsAddDescriptionJira(String description) {
		// Get dấu nháy vào json
		String newVal = description.replaceAll("#", "\r\n #");

		Map<String, Map> map = new HashMap<>();
		Map<String, List<Map>> desc = new HashMap<>();
		Map<String, String> map3 = new HashMap<>();

		List<Map> maps = new ArrayList<>();
		map3.put("set", newVal);
		maps.add(map3);
		desc.put("description", maps);
		map.put("update", desc);

		System.out.println(JSON.encode(map));
		return JSON.encode(map);
	}

	private static String paramsCommentJira(String comment) {
		return "{\"body\":\"" + comment + "\"}".toString();
	}

	private static String paramsRemoveLabelJira(String label) {
		return "{\"update\":{ \"labels\":[{\"remove\":\"" + label + "\"}]}}".toString();
	}

	private static String paramsAddLabelJira(String label) {
		return "{\"update\":{ \"labels\":[{\"add\":\"" + label + "\"}]}}".toString();
	}

	// Get each of step on description and we can run it
	public void runScenario(String step, boolean excel) throws Exception {
		waitForAjax();
		Random rad = new Random();

		String[] arrayStep = step.trim().split("\"");
		for (int i = 0; i < arrayStep.length; i++) {
			System.out.println("[arrayStep(" + i + "): " + arrayStep[i] + "]");
		}
		String keyword = arrayStep[0];
		System.out.println("[Keyword:" + keyword + "]");
		switch (keyword.trim()) {
		case "Open url":
			String url = arrayStep[1];
			try {
				if (excel) {
					openPageExcel(url);
				} else {
					openPageJira(url);
				}
			} catch (AssertionError t) {
				messageAlert = t.toString();
			}
			break;

		case "I run":
			String link = arrayStep[1];
			try {
				Assert.assertTrue("The response code is :'" + getResponse(link) + "' on URL:'" + link + "'",
						getResponse(link) == 200);
				if (null != ReadProperties.getElementsAndUrls("auuser")) {
					String part[] = link.split("://");
					Assert.assertTrue(link + " wrong", part.length > 1);
					link = part[0] + "://" + ReadProperties.getElementsAndUrls("auuser") + ":"
							+ ReadProperties.getElementsAndUrls("aupass") + "@" + part[1];
					driver.navigate().to(link);
					Thread.sleep(3000);
					driver.navigate().to(link);
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					Assert.assertFalse("data:,".equals(driver.getTitle()));
				}
				waitForWebState();
			} catch (AssertionError t) {
				messageAlert = t.toString();
			}
			break;
		case "Input into":
			String objectName = arrayStep[1];
			String value = arrayStep[3].trim();
			String valueInput = null;
			try {
				if (value.equals("random value")) {
					System.out.print("usename" + rad.nextInt(100) + "@gmail.com");
					valueInput = "usename" + rad.nextInt(100) + "@gmail.com";
					strValue = valueInput;
				} else if (value.equals("user Email")) {
					valueInput = strValue;
				} else {
					valueInput = value;
				}
				returnElement(objectName, null).clear();
				returnElement(objectName, null).sendKeys(valueInput);

			} catch (AssertionError t) {
				messageAlert = t.toString();
			}
			break;
		case "Click on":
			try {
				Thread.sleep(2000);
				returnElement(arrayStep[1], null).click();
			} catch (AssertionError t) {
				messageAlert = t.toString();
			}
			break;
		case "Right click on":
			try {
				rightClickElement(arrayStep[1].trim());
			} catch (AssertionError t) {
				messageAlert = t.toString();
			}
			break;
		case "Double click on":
			try {
				doubleClick(arrayStep[1].trim());
			} catch (AssertionError t) {
				messageAlert = t.toString();
			}
			break;
		case "Select":
			try {

				returnElement(arrayStep[1], null).click();
				waitForWebState();
				List<WebElement> options = returnElementsList(arrayStep[1]);
				try {

					options = options.get(0).findElements(By.xpath(".//option"));
				} catch (Exception e) {
					options = options.get(0).findElements(By.xpath(".//li"));
				}
				for (int i = 0; i < options.size(); i++) {

					String content = options.get(i).getText().trim();
					System.out.println("[content: " + content + "]");
					if (arrayStep[3].trim().equalsIgnoreCase(content)) {
						options.get(i).click();
						break;
					}
					Assert.assertFalse("Keyword:[" + arrayStep[3].trim() + "] can't find in dropdown list!!!",
							i == options.size() - 1);
				}
			} catch (AssertionError t) {
				messageAlert = t.toString();
			}
			break;
		case "Check on":
			try {
				List<WebElement> options = returnElementsList(arrayStep[1]);
				for (int i = 0; i < options.size(); i++) {
					String content = options.get(i).getText().trim();
					if (arrayStep[3].trim().equalsIgnoreCase(content)) {
						options.get(i).click();
						break;
					}
					Assert.assertFalse("Keyword:[" + arrayStep[3].trim() + "]can't find in checkbox list!!!",
							i == options.size() - 1);
				}
			} catch (AssertionError t) {
				messageAlert = t.toString();
			}
			break;
		case "Hover on":
			try {
				hoverElement(arrayStep[1]);
			} catch (AssertionError t) {
				messageAlert = t.toString();
			}
			break;
		case "Should see":
			try {
				returnElement(arrayStep[1].trim(), null);
			} catch (AssertionError t) {
				messageAlert = t.toString();
			}

			try {
				if (arrayStep[3].trim().equals("display")) {
					Assert.assertTrue(arrayStep[1] + " is not present. It is different with your expected!",
							null != driver.findElement(getIdentifier(arrayStep[1])));
				} else if (arrayStep[3].trim().equals("not display")) {
					if ((driver.findElement(getIdentifier(arrayStep[1]))) != null) {
						Assert.assertTrue(arrayStep[1] + " is present. It is different with your expected!",
								!driver.findElement(getIdentifier(arrayStep[1])).isDisplayed());
					}
				} else {

					System.out.println("Actual: " + arrayStep[3].trim());
					System.out.println("Expected: " + returnElement(arrayStep[1], null).getText());
					if (arrayStep[2].trim().equals("is")) {
						Assert.assertEquals(arrayStep[3].trim(),
								replaceSomeText(returnElement(arrayStep[1], null).getText()));
					} else if (arrayStep[2].trim().equals("is contains")) {
						Assert.assertTrue(returnElement(arrayStep[1], null).getText().contains(arrayStep[3].trim()));
					}

				}
			} catch (AssertionError t) {
				errorMessage = t.toString();
				messageAlert = t.toString();
			}
			break;
		case "Should see the text":
			WebElement element = driver.findElement(getIdentifier(arrayStep[3]));
			String acctual = (String) ((JavascriptExecutor) driver).executeScript("return jQuery(arguments[0]).text();",
					element);
			System.out.println("[acctual: " + acctual + "]");
			Assert.assertTrue(arrayStep[1].toString().contains(acctual));
			break;
		case "Select option":
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(getIdentifier(arrayStep[1])));

			String strExpect = arrayStep[5];
			WebElement parent = driver.findElement(getIdentifier(arrayStep[1]));
			System.out.println(parent);

			// List<WebElement> items = parent.findElements(By.tagName("li"));
			List<WebElement> items = parent.findElements(getIdentifier(arrayStep[3]));
			System.out.println("Items size: " + items.size() + "]");

			for (WebElement item : items) {
				if (item.getText().contains(strExpect)) {
					System.out.println("[I choose: " + item.getText() + "]");
					item.click();
					System.out.println("[I clicked!]");
					break;
				}
			}

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "Should see font size":
			WebElement eleFontSize = driver.findElement(getIdentifier(arrayStep[1]));
			String strFontSize = eleFontSize.getCssValue("font-size");
			System.out.println("[Font Size: " + strFontSize + "]");
			Assert.assertTrue(strFontSize.contains(arrayStep[3].toString()));
			break;
		case "Should see font weight":
			WebElement eleFontWeight = driver.findElement(getIdentifier(arrayStep[1]));
			String strFontWeight = eleFontWeight.getCssValue("font-weight");
			System.out.println("[Font Size: " + strFontWeight + "]");
			Assert.assertTrue(strFontWeight.contains(arrayStep[3].toString()));
			break;
		case "Should see color before mouse hover":
			WebElement eleColorBefore = driver.findElement(getIdentifier(arrayStep[1]));
			String strColorBefore = eleColorBefore.getCssValue("color");
			System.out.println("[color before mouse hover: " + strColorBefore + "]");
			Assert.assertTrue(strColorBefore.contains(arrayStep[3].toString()));
			break;
		case "Should see color when mouse hover":
			WebElement eleColorAfter = driver.findElement(getIdentifier(arrayStep[1]));
			Actions action = new Actions(driver);
			action.moveToElement(eleColorAfter).perform();
			String strColorAfter = eleColorAfter.getCssValue("background");
			System.out.println("[color when mouse hover: " + strColorAfter + "]");
			Assert.assertTrue(strColorAfter.contains(arrayStep[3].toString()));
			break;
		
		case "Should see the CSS as":
			//WebElement eleCSS = driver.findElement(getIdentifier(arrayStep[2]));
			//String strCSS = eleCSS.getCssValue(arrayStep[1]);
			verifyCSS(arrayStep[1].toString(), arrayStep[2].toString(),arrayStep[3].toString());
			break;
		case "Should see the":
			try {
				returnElement(arrayStep[1].trim(), null);
			} catch (AssertionError t) {
				messageAlert = t.toString();
			}

			try {
				if (arrayStep[3].trim().equals("displayed")) {
					Assert.assertTrue(arrayStep[1] + " is not present. It is different with your expected!",
							null != driver.findElement(getIdentifier(arrayStep[1])));
				} else if (arrayStep[3].trim().equals("not displayed")) {
					if ((driver.findElement(getIdentifier(arrayStep[1]))) != null) {
						Assert.assertTrue(arrayStep[1] + " is present. It is different with your expected!",
								!driver.findElement(getIdentifier(arrayStep[1])).isDisplayed());
					}
				} 
			} catch (AssertionError t) {
				errorMessage = t.toString();
				messageAlert = t.toString();
			}
			break;
		case "Horizontal scroll to see the element":
			 WebElement Element = driver.findElement(getIdentifier(arrayStep[1]));
		     //This will scroll the page Horizontally till the element is found		
			 je.executeScript("arguments[0].scrollIntoView();", Element);
			Thread.sleep(1000);
			break;
		case "Scroll down the web page by":
			je.executeScript("window.scrollBy(0,"+arrayStep[1]+")");
			Thread.sleep(1000);
			break;
		case "Scroll down the web page by the visibility of the element":
			 WebElement visibilityElement = driver.findElement(getIdentifier(arrayStep[1]));
		     //This will scroll the page Horizontally till the element is found		
			 je.executeScript("arguments[0].scrollIntoView();", visibilityElement);
			Thread.sleep(1000);
			break;
		case "Scroll down the web page at the bottom of the page":
			//This will scroll the web page till end.		
			je.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(1000);
			break;
		case "Drag and drop":
			Actions actions = new Actions(driver);
			WebElement srcElement = driver.findElement(getIdentifier(arrayStep[1]));
			WebElement targetElement = driver.findElement(getIdentifier(arrayStep[3]));
			Action dragAndDrop = actions.clickAndHold(srcElement).moveToElement(targetElement).release(targetElement)
					.build();
			dragAndDrop.perform();
			Thread.sleep(1000);
			break;
		case "Clear":
			WebElement clearElement = driver.findElement(getIdentifier(arrayStep[1]));
			clearElement.clear();
			break;
		case "Clean pathfield":
			WebElement eleClean = driver.findElement(getIdentifier(arrayStep[1]));
			eleClean.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			eleClean.sendKeys(Keys.BACK_SPACE);
			break;
		default:
			messageAlert = step + "\r\n => This step is not defined!";
		}
		System.out.println("Message:" + messageAlert);
	}

	public void openPageJira(String url) throws InterruptedException, MalformedURLException, IOException {
		/*
		 * String link = ReadProperties.getElementsAndUrls(url);
		 * Assert.assertTrue("The response code is :'" + getResponse(link) +
		 * "' on URL:'" + link + "'", getResponse(link) == 200); if (null !=
		 * ReadProperties.getElementsAndUrls("auuser")) { String part[] =
		 * link.split("://"); Assert.assertTrue(link + " wrong", part.length >
		 * 1); link = part[0] + "://" +
		 * ReadProperties.getElementsAndUrls("auuser") + ":" +
		 * ReadProperties.getElementsAndUrls("aupass") + "@" + part[1];
		 * driver.navigate().to(link); Thread.sleep(3000); link =
		 * ReadProperties.getElementsAndUrls(url); driver.navigate().to(url);
		 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 * Assert.assertFalse("data:,".equals(driver.getTitle())); }
		 * waitForWebState();
		 */
		String link = ReadProperties.getElementsAndUrls(url);
		System.out.println(link);
		driver.get(link);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	// get JIRA ID on cucumber
	public void getUserCaseAndRunAuto(String jiraID) throws Exception {
		// Get body ticket from JIRA ID
		String bodyTicket = ReadAPI.getAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID);
		messageAlert = "";
		errorMessage = "";
		JSONObject object = new JSONObject(bodyTicket);
		String description = "";
		if (object.getJSONObject("fields") != null && object.getJSONObject("fields").getString("description") != null) {
			description = object.getJSONObject("fields").getString("description");
		}
		SimpleDateFormat df = new SimpleDateFormat("HH:mm dd_MMM_YYYY");
		Date da = new Date();
		Assert.assertFalse("Warning!!! Jira ticket:'" + jiraID
				+ "' => Can't run automation test because the description is empty!", description.equals(""));
		boolean foundStep = false;
		// get the description for each ticket
		String[] u = description.split("\r\n");
		for (int j = 0; j < u.length; j++) {
			if (u[j].startsWith(" #") || u[j].startsWith("#")) {
				foundStep = true;
				String[] step = u[j].split("#");
				// for each of step on JIRA ticket description, we can run it
				runScenario(step[1].trim(), false);
				Assert.assertFalse("Warning!!! Jira ticket:'" + jiraID + "' =>" + messageAlert,
						!messageAlert.equals(""));
				String timeToCheck = df.format(da);
				if (!errorMessage.equals("")) {
					String comment = "Hi,\\r\\n\\n" + "This usercase is NOT Ok at " + timeToCheck + "\\r\\n"
							+ "Actual result: " + errorMessage + "\\r\\n" + "Please see attachment("
							+ (jiraID + " " + timeToCheck).replaceAll(":", "").replaceAll(" ", "_") + ".png"
							+ ") for more details\\r\\n" + "It is checked by Automation team.\\r\\n\\n" + "Thanks";
					ReadAPI.postAPI(
							ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID + "/comment",
							paramsCommentJira(comment));
					ReadAPI.putAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID,
							paramsRemoveLabelJira(Constant.LABEL_PASS));
					ReadAPI.putAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID,
							paramsAddLabelJira(Constant.LABEL_FAIL));
					takeScreenShort((jiraID + " " + timeToCheck).replaceAll(":", ""), myScenario);
					uploadAttachment("", "", jiraID, data.getOutputScreenshot(
							(jiraID + " " + timeToCheck).replaceAll(":", "").replaceAll(" ", "_") + ".png"));
					Assert.assertFalse("Fail!!! Jira ticket:'" + jiraID + "' =>" + errorMessage,
							!errorMessage.equals(""));
				}
			}
			if (errorMessage.equals("") && j == u.length - 1) {
				String comment = "Hi,\\r\\n\\n" + "This usercase is Ok at " + df.format(da) + "\\r\\n"
						+ "It is checked by Automation team\\r\\n\\n" + "Thanks";
				ReadAPI.postAPI(
						ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID + "/comment",
						paramsCommentJira(comment));
				ReadAPI.putAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID,
						paramsRemoveLabelJira(Constant.LABEL_FAIL));
				ReadAPI.putAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID,
						paramsAddLabelJira(Constant.LABEL_PASS));
			}
			Assert.assertFalse(
					"Warning!!! Jira ticket:'" + jiraID + "' => Can't find any step of description in this usercase!",
					!foundStep && j == u.length - 1);
		}
	}

	public void distance2Element(String object1, String object2) throws Exception {
		waitForAjax();
		System.out.println(getElement(object1).getLocation().getX());
		System.out.println(getElement(object2).getLocation().getX());
	}

	public void checkBrokenImages(String sheetName, String excelName)
			throws InterruptedException, MalformedURLException, IOException {
		waitForAjax();
		FileInputStream file = new FileInputStream(data.getResponseCode(excelName));
		workbook = new XSSFWorkbook(file);
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			if (workbook.getSheetName(i).equals(sheetName)) {
				workbook.removeSheetAt(i);
				break;
			}
		}
		workbook.createSheet(sheetName);
		sheet = workbook.getSheet(sheetName);
		setValueInExcel(0, 0, "URL", "");
		setValueInExcel(0, 1, driver.getCurrentUrl(), "");
		setValueInExcel(1, 0, "Alt NAME", "ORANGE");
		setValueInExcel(1, 1, "URL of Image", "ORANGE");
		setValueInExcel(1, 2, "STATUS", "ORANGE");
		List<WebElement> imagesList = driver.findElements(By.tagName("img"));
		System.out.println(imagesList.size());
		boolean statusScenario = true;
		for (int i = 0; i < imagesList.size(); i++) {
			String altName = imagesList.get(i).getAttribute("alt").trim();
			String imageURL = imagesList.get(i).getAttribute("src").trim();
			if (altName.equals("")) {
				altName = "No name";
				setValueInExcel(i + 2, 0, altName, "YELLOW");
			} else {
				setValueInExcel(i + 2, 0, altName, "");
			}
			setValueInExcel(i + 2, 1, imageURL, "");
			System.out.println(imageURL);
			int status = getResponse(imagesList.get(i).getAttribute("src").trim());
			if (status == 200) {
				setValueInExcel(i + 2, 2, Integer.toString(status), "PASSED");
			} else {
				setValueInExcel(i + 2, 2, Integer.toString(status), "FAIL");
				statusScenario = false;
			}
		}
		FileOutputStream streamOut = new FileOutputStream(data.getResponseCode(excelName));
		workbook.write(streamOut);
		streamOut.close();
		Assert.assertFalse("Status of some image urls are wrong! Please check the file '" + excelName + "'",
				!statusScenario);

	}

	// read testcase on excel
	public void getUserCasesListOnExcelAndRunAuto(String excelFile) throws IOException {
		// get excel file
		String strExcelTestCasePath = System.getProperty("user.dir") + "\\src\\test\\resources\\excel_data-input\\"
				+ excelFile + ".xlsx";

		fis = new FileInputStream(new File(strExcelTestCasePath));
		workbook = new XSSFWorkbook(fis);
		for (Sheet sheet : workbook) {
			XSSFSheet sheets = (XSSFSheet) sheet;
			Map<Integer, List<String>> data1 = new HashMap<>();
			data1 = getData1(data1, sheet);

			for (Map.Entry<Integer, List<String>> entry : data1.entrySet()) {
				System.out.println(entry);
				runEachJira(entry, "", sheets, strExcelTestCasePath);
			}
		}
		workbook.close();
	}

	public void getUserCaseOnExcelAndRunAuto(String jiraID, int excelSheet, String excelName) throws IOException {
		// TODO Auto-generated method stub
		// data with excel
		String strExcelTestCasePath = System.getProperty("user.dir") + "\\src\\test\\resources\\excel_data-input\\"
				+ excelName + ".xlsx";

		fis = new FileInputStream(new File(strExcelTestCasePath));
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(0);
		Map<Integer, List<String>> data1 = new HashMap<>();
		data1 = getData1(data1, sheet);

		for (Map.Entry<Integer, List<String>> entry : data1.entrySet()) {
			runEachJira(entry, jiraID, sheet, strExcelTestCasePath);
		}
	}

	public void runEachJira(Map.Entry<Integer, List<String>> entry, String jiraID, XSSFSheet sheetCurrent,
			String strExcelTestCasePath) throws IOException {
		Integer key = entry.getKey();

		if (key > 0) {
			List<String> output = entry.getValue();
			for (int j = 0; j < output.size(); j++) {
				if (jiraID.equals("") && j == 0) {
					jiraID = output.get(j).trim();
				} else {
					if (j == 4) {
						runStep(output.get(j), key, jiraID, strExcelTestCasePath, sheetCurrent);
					}
				}
			}
		}
	}

	public Map<Integer, List<String>> getData1(Map<Integer, List<String>> data1, Sheet sheet) {
		int i = 0;
		for (Row row : sheet) {
			data1.put(i, new ArrayList<String>());
			for (Cell cell : row) {
				switch (cell.getCellTypeEnum()) {
				case STRING:
					data1.get(new Integer(i)).add(cell.getRichStringCellValue().getString());
					break;
				case NUMERIC:
					if (DateUtil.isCellDateFormatted(cell)) {
						data1.get(i).add(cell.getDateCellValue() + "");
					} else {
						data1.get(i).add(cell.getNumericCellValue() + "");
					}
					break;
				case BOOLEAN:
					data1.get(i).add(cell.getBooleanCellValue() + "");
					break;
				case FORMULA:
					data1.get(i).add(cell.getCellFormula() + "");
					break;
				default:
					data1.get(new Integer(i)).add(" ");
				}
			}
			i++;
		}
		return data1;
	}

	public void runStep(String strSteps, int key, String jiraID, String strExcelTestCasePath, XSSFSheet sheetCurrent)
			throws IOException {
		String comment = null;

		SimpleDateFormat df = new SimpleDateFormat("HH:mm dd_MMM_YYYY");
		Date da = new Date();
		String timeToCheck = df.format(da);

		if (strSteps != null && !strSteps.equals("")) {
			String[] lStep = strSteps.split("\n");
			for (int k = 0; k < lStep.length; k++) {
				String step = lStep[k].toString().trim();
				step = clearNumberAndDot(step);
				try {
					System.out.println("Step: " + step);
					runScenario(step, true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// update excel
			row = sheetCurrent.getRow(key);
			Cell cellActualResult = row.getCell(9);
			Cell cellStatus = row.getCell(10);
			Cell cellExecuteBy = row.getCell(11);
			Cell cellExecuteDate = row.getCell(12);
			if (!messageAlert.equals("")) {
				comment = "Hi,\\r\\n\\n" + "This usercase is NOT Ok at " + timeToCheck + "\\r\\n" + "Actual result: "
						+ messageAlert + "\\r\\n" + "Please see attachment("
						+ (jiraID + " " + timeToCheck).replaceAll(":", "").replaceAll(" ", "_") + ".png"
						+ ") for more details\\r\\n" + "It is checked by Automation team.\\r\\n\\n" + "Thanks";
				// update excel
				if (cellActualResult != null) {
					cellActualResult.removeCellComment();
				} else {
					cellActualResult = row.createCell(9);
				}
				cellActualResult.setCellValue(messageAlert);

				if (cellStatus != null) {
					cellStatus.removeCellComment();
				} else {
					cellStatus = row.createCell(10);
				}
				cellStatus.setCellValue("Fail");

				if (cellExecuteBy != null) {
					cellExecuteBy.removeCellComment();
				} else {
					cellExecuteBy = row.createCell(11);
				}
				cellExecuteBy.setCellValue(ReadProperties.getConfigSelenium("jiraUserName"));

				if (cellExecuteDate != null) {
					cellExecuteDate.removeCellComment();
				} else {
					cellExecuteDate = row.createCell(12);
				}
				cellExecuteDate.setCellValue(timeToCheck);

				if (strSteps == null || strSteps.equals("")) {
					strSteps = "Not have Step";
				}
				System.out.println("strSteps:" + strSteps);

				// update jira
				try {
					ReadAPI.postAPI(
							ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID + "/comment",
							paramsCommentJira(comment));
					ReadAPI.putAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID,
							paramsAddDescriptionJira(changeTestStep(strSteps)));

					ReadAPI.putAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID,
							paramsRemoveLabelJira(Constant.LABEL_PASS));
					ReadAPI.putAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID,
							paramsAddLabelJira(Constant.LABEL_FAIL));
					// chup man hinh
					takeScreenShort((jiraID + " " + timeToCheck).replaceAll(":", ""), myScenario);
					uploadAttachment("", "", jiraID, data.getOutputScreenshot(
							(jiraID + " " + timeToCheck).replaceAll(":", "").replaceAll(" ", "_") + ".png"));
				} catch (KeyManagementException | NoSuchAlgorithmException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (messageAlert.equals("")) {
				comment = "Hi,\\r\\n\\n" + "This usercase is Ok at " + df.format(da) + "\\r\\n"
						+ "It is checked by Automation team\\r\\n\\n" + "Thanks";
				if (cellActualResult != null) {
					cellActualResult.removeCellComment();
				} else {
					cellActualResult = row.createCell(9);
				}
				cellActualResult.setCellValue(messageAlert);

				if (cellStatus != null) {
					cellStatus.removeCellComment();
				} else {
					cellStatus = row.createCell(10);
				}
				cellStatus.setCellValue("Pass");

				if (cellExecuteBy != null) {
					cellExecuteBy.removeCellComment();
				} else {
					cellExecuteBy = row.createCell(11);
				}
				cellExecuteBy.setCellValue(ReadProperties.getConfigSelenium("jiraUserName"));

				if (cellExecuteDate != null) {
					cellExecuteDate.removeCellComment();
				} else {
					cellExecuteDate = row.createCell(12);
				}
				cellExecuteDate.setCellValue(timeToCheck);

				if (strSteps == null || strSteps.equals("")) {
					strSteps = "Not have Step";
				}
				System.out.println("strSteps:" + strSteps);

				// update jira
				try {
					ReadAPI.postAPI(
							ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID + "/comment",
							paramsCommentJira(comment));
					ReadAPI.putAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID,
							paramsAddDescriptionJira(changeTestStep(strSteps)));
					ReadAPI.putAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID,
							paramsRemoveLabelJira(Constant.LABEL_FAIL));
					ReadAPI.putAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID,
							paramsAddLabelJira(Constant.LABEL_PASS));
				} catch (KeyManagementException | NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			fis.close();

			// Update excel
			FileOutputStream streamOut = new FileOutputStream(new File(strExcelTestCasePath));
			workbook.write(streamOut);
			streamOut.close();
		}
	}

	public String changeTestStep(String strStep) {
		// strStep = strStep.replaceAll("", replacement)
		String newStrStep = "";
		for (String step : strStep.split("\n")) {
			newStrStep += "# " + clearNumberAndDot(step) + " ";
		}
		return newStrStep;
	}

	public String clearNumberAndDot(String step) {
		step = step.trim();
		String newStep = step;
		for (char c : step.toCharArray()) {
			if (Character.isDigit(c)) {
				newStep = newStep.substring(1);
			} else {
				break;
			}
		}
		return newStep.replace(". ", "");
	}

	public void openPageExcel(String url) {
		String link = ReadProperties.getElementsAndUrls(url);
		System.out.println(link);
		driver.get(link);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public void createUsercases(String sheetName, String excelName)
			throws IOException, KeyManagementException, NoSuchAlgorithmException {
		String strExcelTestCasePath = System.getProperty("user.dir") + "\\src\\test\\resources\\excel_data-input\\"
				+ excelName + ".xlsx";

		fis = new FileInputStream(new File(strExcelTestCasePath));
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		System.out.println("[Sheet Name:" + sheetName + "]");
		int rowCount = sheet.getLastRowNum() + 1;
		System.out.println("[rowCount:" + rowCount + "]");
		for (int i = 2; i < rowCount; i++) {
			String userCaseName = getValueFromExcel(i, 1);
			String assignee = ReadProperties.getConfigSelenium("assignee");
			System.out.println("[userCaseName:" + userCaseName + "]");
			System.out.println("[assignee:" + assignee + "]");
			PostTicketOnJira jira = new PostTicketOnJira(); // jira.CreateEpicOnJira(userCaseName,
															// assignee);
			ParamsPostMethod params = new ParamsPostMethod();
			String description = "abc";
			System.out.println(description);
			System.err.println(params.paramsCreateUserCases(getValueFromExcel(i, 1), description));
			String status = ReadAPI.postAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue",
					params.paramsCreateUserCases(getValueFromExcel(i, 1), description));
			System.out.println("[status:" + status + "]");
		}

	}

	public String getValueFromExcel(int row, int column) {
		String value = "";
		XSSFCell cell = sheet.getRow(row).getCell(column);
		DataFormatter objDefaultFormat = new DataFormatter();
		FormulaEvaluator objFormulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);
		value = objDefaultFormat.formatCellValue(cell, objFormulaEvaluator);
		return value;
	}

	/*
	 * Follow by new template Create ticket on Jira by excel
	 */
	public void createUsercase(String sheetName, String excelName)
			throws KeyManagementException, NoSuchAlgorithmException, IOException, InterruptedException {
		/*
		 * // check if usercase not have on jira which these informaion
		 * (summary, // lable =Happy Case or Unhappy case) /// then if will
		 * create new // else if will update // after create or update, will
		 * write on List<int,object[]> to update // again the excel file as:
		 * Key, updated, start date
		 * 
		 * String strExcelTestCasePath = System.getProperty("user.dir") +
		 * "\\src\\test\\resources\\excel_data-input\\" + excelName + ".xlsx";
		 * Map<Integer, Map<String, String>> listUsercases = new HashMap<>();
		 * fis = new FileInputStream(new File(strExcelTestCasePath)); workbook =
		 * new XSSFWorkbook(fis); sheet = workbook.getSheet(sheetName);
		 * System.out.println("[Sheet Name:" + sheetName + "]"); int rowCount =
		 * sheet.getLastRowNum() + 1; System.out.println("[rowCount:" + rowCount
		 * + "]"); for (int i = 1; i < rowCount; i++) { // Map usercase = new
		 * HashMap(); String userCaseName = getValueFromExcel(i, 1); String
		 * assignee = ReadProperties.getConfigSelenium("assignee");
		 * System.out.println("[userCaseName:" + userCaseName + "]");
		 * System.out.println("[assignee:" + assignee + "]"); PostTicketOnJira
		 * jira = new PostTicketOnJira(); ParamsPostMethod params = new
		 * ParamsPostMethod(); String description =
		 * changeTestStep(getValueFromExcel(i, 4));
		 * System.out.println(description);
		 * System.err.println(params.paramsCreateUserCase(getValueFromExcel(i,
		 * 1), description)); String keyResonse =
		 * ReadAPI.postAPI(ReadProperties.getConfigSelenium("jiraURL") +
		 * "/rest/api/2/issue", params.paramsCreateUserCase(getValueFromExcel(i,
		 * 1), description)); // System.out.println("[status:"+keyResonse+"]");
		 * System.out.println("\n\n\n\n"); // ra obj Map usercase =
		 * JSON.decode(keyResonse, Map.class); System.out.println(usercase); //
		 * ghi vo listUsercases.put(i,usercase);
		 * 
		 * }
		 * 
		 * // in List System.out.println(listUsercases.get(1).get("key"));
		 * 
		 * // update excel for(Map.Entry<Integer, Map<String, String>> entry :
		 * listUsercases.entrySet()) { String jiraId =
		 * entry.getValue().get("key"); System.out.println(entry.getKey() + "=>"
		 * + jiraId); row = sheet.getRow(entry.getKey()); Cell cellActualResult
		 * = row.getCell(0); if(cellActualResult == null){ cellActualResult =
		 * row.createCell(0); } cellActualResult.setCellValue(jiraId); }
		 * fis.close();
		 * 
		 * // Update excel FileOutputStream streamOut = new FileOutputStream(new
		 * File(strExcelTestCasePath)); workbook.write(streamOut);
		 * streamOut.close();
		 */

		// NGAN COMMITTE
		String strExcelTestCasePath = System.getProperty("user.dir") + "\\src\\test\\resources\\excel_data-input\\"
				+ excelName + ".xlsx";

		fis = new FileInputStream(new File(strExcelTestCasePath));
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum() + 1;
		for (int i = 1; i < rowCount; i++) {
			String jiraticket = "";
			if (sheet.getRow(i).getCell(1) != null && !sheet.getRow(i).getCell(1).getStringCellValue().equals("")) {
				jiraticket = sheet.getRow(i).getCell(1).getStringCellValue();
			}

			String assignee = getValueFromExcel(i, 18);
			String expect = getValueFromExcel(i, 8);
			String designInfo = getValueFromExcel(i, 10);
			String priority = getValueFromExcel(i, 6);
			String label = getValueFromExcel(i, 5);
			String summary = sheet.getRow(i).getCell(7).getStringCellValue();
			String run = sheet.getRow(i).getCell(13).getStringCellValue();
			String team = "";
			String teamId = "";

			if (summary != "") {
				if (run.trim().toLowerCase().equals("yes")) {
					team = "Automation";
					teamId = "10200";
				} else if (run.trim().toLowerCase().equals("no")) {
					team = "Default";
					teamId = "10111";
				}

				PostTicketOnJira jira = new PostTicketOnJira();
				ParamsPostMethod params = new ParamsPostMethod();
				String description = StringEscapeUtils
						.escapeJson("Steps:" + changeTestStep(getValueFromExcel(i, 9)).replaceAll("#", "\r\n #")
								+ "\r\n\r\n" + expect + "\n" + designInfo);

				if (!jiraticket.equals("")) {
					boolean update = true;
					runStepForTestcase(run, i, sheet, jiraticket, update, strExcelTestCasePath, expect, designInfo);
				} else {
					boolean update = false;
					String keyResonse = ReadAPI.postAPI(
							ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue",
							params.paramsCreateUserCase(summary, description, assignee, priority, label, team, teamId));
					System.out.println("keyResonse: " + keyResonse);

					// Get ticket jira
					for (String item : keyResonse.substring(8, keyResonse.length() - 1).split(",")) {
						if (item.contains("key")) {
							jiraticket = item.split(":")[1].replaceAll("\"", "");
						}
					}

					// run step
					runStepForTestcase(run, i, sheet, jiraticket, update, strExcelTestCasePath, expect, designInfo);
				}
			}
		}
	}

	// Run check all usercase on excel
	public void runCheckUsercaseOnExcel(String excelName) throws Exception {
		String strExcelTestCasePath = System.getProperty("user.dir") + "\\src\\test\\resources\\excel_data-input\\"
				+ excelName + ".xlsx";

		fis = new FileInputStream(new File(strExcelTestCasePath));
		workbook = new XSSFWorkbook(fis);

		for (int i = 0; i <= workbook.getActiveSheetIndex(); i++) {
			sheet = workbook.getSheetAt(i);
			for (int j = 1; j <= sheet.getLastRowNum(); j++) {
				String jiraticket = "";
				if (sheet.getRow(j).getCell(1) != null && !sheet.getRow(j).getCell(1).getStringCellValue().equals("")) {
					jiraticket = sheet.getRow(j).getCell(1).getStringCellValue();
				}

				runExcel(j, jiraticket, strExcelTestCasePath);
			}
		}
	}

	// Run check some usercase on excel
	public void runCheckSomeUsercaseOnExcel(String jiraID, String sheetName, String excelName) throws Exception {
		String strExcelTestCasePath = System.getProperty("user.dir") + "\\src\\test\\resources\\excel_data-input\\"
				+ excelName + ".xlsx";

		fis = new FileInputStream(new File(strExcelTestCasePath));
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);

		for (int j = 1; j <= sheet.getLastRowNum(); j++) {
			if (sheet.getRow(j).getCell(1) != null && !sheet.getRow(j).getCell(1).getStringCellValue().equals("")) {
				if (sheet.getRow(j).getCell(1).getStringCellValue().trim().toLowerCase()
						.equals(jiraID.trim().toLowerCase())) {
					runExcel(j, jiraID, strExcelTestCasePath);
				}
			}
		}
	}

	public void runExcel(int noRow, String jiraID, String strExcelTestCasePath) throws Exception {
		String expect = getValueFromExcel(noRow, 8);
		String summary = sheet.getRow(noRow).getCell(7).getStringCellValue();
		String designInfo = getValueFromExcel(noRow, 10);
		String run = sheet.getRow(noRow).getCell(13).getStringCellValue();
		if (summary != "") {
			if (!jiraID.equals("")) {
				boolean update = true;
				runStepForTestcase(run, noRow, sheet, jiraID, update, strExcelTestCasePath, expect, designInfo);
			}
		}
	}

	public void runStepForTestcase(String run, int i, XSSFSheet sheet, String jiraticket, boolean update,
			String strExcelTestCasePath, String expect, String designInfo)
			throws KeyManagementException, NoSuchAlgorithmException, IOException, InterruptedException {
		row = sheet.getRow(i);
		Cell cellTicketID = row.getCell(1);
		Cell cellCreateDay = row.getCell(14);
		Cell cellStartDay = row.getCell(15);
		Cell cellStatus = row.getCell(16);
		Cell cellReporter = row.getCell(17);

		if (run.toLowerCase().trim().equals("no") && update) {
			System.out.println("changeTestStep(getValueFromExcel(i, 9): " + changeTestStep(getValueFromExcel(i, 9)));
			System.out.println("getValueFromExcel(i, 9): " + getValueFromExcel(i, 9));

			ReadAPI.postAPI(
					ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraticket + "/comment",
					paramsCommentJira("update description"));
			ReadAPI.putAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraticket,
					paramsAddDescriptionJira("Steps:" + changeTestStep(getValueFromExcel(i, 9)) + "\r\n\r\n" + expect
							+ "\n" + designInfo));
		}

		if (run.toLowerCase().trim().equals("yes")) {
			messageAlert = "";
			String[] lStep = getValueFromExcel(i, 9).split("\n");
			for (int j = 0; j < lStep.length; j++) {
				String step = lStep[j].toString().trim();
				step = clearNumberAndDot(step);
				try {
					runScenario(step, true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// update excel
			Cell cellEvidence = row.getCell(11);
			Cell cellComment = row.getCell(20);
			String comment = "";

			if (cellEvidence != null) {
				cellEvidence.removeCellComment();
			} else {
				cellEvidence = row.createCell(11);
			}

			if (cellCreateDay != null) {
				cellCreateDay.removeCellComment();
			} else {
				cellCreateDay = row.createCell(14);
			}

			if (cellStartDay != null) {
				cellStartDay.removeCellComment();
			} else {
				cellStartDay = row.createCell(15);
			}

			if (cellStatus != null) {
				cellStatus.removeCellComment();
			} else {
				cellStatus = row.createCell(16);
			}

			if (cellReporter != null) {
				cellReporter.removeCellComment();
			} else {
				cellReporter = row.createCell(17);
			}

			if (cellComment != null) {
				cellComment.removeCellComment();
			} else {
				cellComment = row.createCell(20);
			}

			if (!messageAlert.equals("")) {
				cellEvidence.setCellValue(
						(jiraticket + " " + timeToCheck).replaceAll(":", "").replaceAll(" ", "_") + ".png");
				cellComment.setCellValue("Not OK");

				// update jira
				comment = "Hi,\\r\\n\\n" + "This usercase is NOT Ok at " + timeToCheck + "\\r\\n" + "Actual result: "
						+ messageAlert + "\\r\\n" + "Please see attachment("
						+ (jiraticket + " " + timeToCheck).replaceAll(":", "").replaceAll(" ", "_") + ".png"
						+ ") for more details\\r\\n" + "It is checked by Automation team.\\r\\n\\n" + "Thanks";
				ReadAPI.postAPI(
						ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraticket + "/comment",
						paramsCommentJira(comment));
				ReadAPI.putAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraticket,
						paramsAddDescriptionJira("Steps:" + changeTestStep(getValueFromExcel(i, 9)) + "\r\n\r\n"
								+ expect + "\n" + designInfo));
				ReadAPI.putAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraticket,
						paramsAddLabelJira(Constant.LABEL_FAIL));
				ReadAPI.putAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraticket,
						paramsRemoveLabelJira(Constant.LABEL_PASS));

				// chup man hinh
				takeScreenShort((jiraticket + " " + timeToCheck).replaceAll(":", ""), myScenario);
				uploadAttachment(userJira, pwJira, jiraticket, data.getOutputScreenshot(
						(jiraticket + " " + timeToCheck).replaceAll(":", "").replaceAll(" ", "_") + ".png"));
			}

			if (messageAlert.equals("")) {
				// update excel
				cellComment.setCellValue("OK");
				cellEvidence.setCellValue("");
				// update jira
				comment = "Hi,\\r\\n\\n" + "This usercase is Ok at " + df.format(da) + "\\r\\n"
						+ "It is checked by Automation team\\r\\n\\n" + "Thanks";
				ReadAPI.postAPI(
						ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraticket + "/comment",
						paramsCommentJira(comment));
				ReadAPI.putAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraticket,
						paramsAddDescriptionJira("Steps:" + changeTestStep(getValueFromExcel(i, 9)) + "\r\n\r\n"
								+ expect + "\n" + designInfo));
				ReadAPI.putAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraticket,
						paramsAddLabelJira(Constant.LABEL_PASS));
				ReadAPI.putAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraticket,
						paramsRemoveLabelJira(Constant.LABEL_FAIL));
			}
		}

		if (!update) {
			if (cellTicketID != null) {
				cellTicketID.removeCellComment();
			} else {
				cellTicketID = row.createCell(1);
			}
			cellTicketID.setCellValue(jiraticket);
			cellCreateDay.setCellValue(timeToCheck);
			cellStartDay.setCellValue(timeToCheck);
		}

		cellStatus.setCellValue("To Do");
		cellReporter.setCellValue(userJira);

		fis.close();

		// Update excel
		FileOutputStream streamOut = new FileOutputStream(new File(strExcelTestCasePath));
		workbook.write(streamOut);
		streamOut.close();
	}

	public String captureExtentReports(String status) {
		String dest = "";
		String dateFormat = new SimpleDateFormat("yyyyMMdd_hhmmss").format(Calendar.getInstance().getTime());
		String fileName = "TS_" + status + "_" + dateFormat + ".jpg";
		try {
			File source = ((TakesScreenshot) ParentStepsSupport.driver).getScreenshotAs(OutputType.FILE);
			if (status == "FAILED") {
				dest = System.getProperty("user.dir") + "/reporting/fail/" + fileName;
				System.out.println("path:" + dest);
				File destination = new File(dest);
				FileUtils.copyFile(source, destination);
				dest = "./fail/" + fileName;
			} else {
				dest = System.getProperty("user.dir") + "/reporting/pass/" + fileName;
				System.out.println("path:" + dest);
				File destination = new File(dest);
				FileUtils.copyFile(source, destination);
				dest = "./pass/" + fileName;
			}
		} catch (IOException e) {
			System.out.println("Error captureExtentReports : " + e.getMessage());
		}
		return dest;
	}

	// Run check some usercase on Jira
	public void runCheckSomeCaseOnJira(String jiraID, String sheetName, String excelName) throws Exception {
		String strExcelTestCasePath = System.getProperty("user.dir") + "\\src\\test\\resources\\excel_data-input\\"
				+ excelName + ".xlsx";

		fis = new FileInputStream(new File(strExcelTestCasePath));
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);

		runAndUpdateExcel(sheet, jiraID, strExcelTestCasePath);
	}

	// Run check all usercase on Jira
	public void runCheckAllCaseOnJira(String excelName) throws Exception {
		String strExcelTestCasePath = System.getProperty("user.dir") + "\\src\\test\\resources\\excel_data-input\\"
				+ excelName + ".xlsx";

		fis = new FileInputStream(new File(strExcelTestCasePath));
		workbook = new XSSFWorkbook(fis);

		for (int i = 0; i <= workbook.getActiveSheetIndex(); i++) {
			sheet = workbook.getSheetAt(i);
			for (int j = 1; j <= sheet.getLastRowNum(); j++) {
				String jiraID = sheet.getRow(j).getCell(1).getStringCellValue();
				if (!jiraID.equals("") && jiraID != null) {
					messageAlert = "";
					errorMessage = "";
					runAndUpdateExcel(sheet, jiraID, strExcelTestCasePath);
				}
			}
		}
	}

	public void runAndUpdateExcel(XSSFSheet sheet, String jiraID, String strExcelTestCasePath) throws Exception {
		String apiOfJira = ReadAPI.getAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID);
		JSONObject object = new JSONObject(apiOfJira);
		String team = object.getJSONObject("fields").get("customfield_10102").toString().replace("[", "").replace("]",
				"");
		JSONObject jsonTeam = new JSONObject(team);
		String value = jsonTeam.getString("value");
		if (value.trim().toLowerCase().equals("automation")) {
			int noRow = 0;
			boolean update = false;
			boolean valueReturn = false;

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				if (sheet.getRow(i).getCell(1).getStringCellValue().equals(jiraID)) {
					update = true;
					noRow = i;
				}
			}

			// Get body ticket from JIRA ID
			String bodyTicket = ReadAPI
					.getAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID);
			messageAlert = "";
			errorMessage = "";
			String comment = "";
			String description = "";
			if (object.getJSONObject("fields") != null
					&& object.getJSONObject("fields").getString("description") != null) {
				description = object.getJSONObject("fields").getString("description");
			}
			SimpleDateFormat df = new SimpleDateFormat("HH:mm dd_MMM_YYYY");
			Date da = new Date();

			boolean foundStep = false;
			// get the description for each ticket
			String[] u = description.split("\r\n");
			for (int j = 0; j < u.length; j++) {
				if (u[j].startsWith(" #") || u[j].startsWith("#")) {
					foundStep = true;
					String[] step = u[j].split("#");
					// for each of step on JIRA ticket description, we can run
					// it
					runScenario(step[1].trim(), false);
					String timeToCheck = df.format(da);
					if (!errorMessage.equals("")) {
						comment = "Hi,\\r\\n\\n" + "This usercase is NOT Ok at " + timeToCheck + "\\r\\n"
								+ "Actual result: " + errorMessage + "\\r\\n" + "Please see attachment("
								+ (jiraID + " " + timeToCheck).replaceAll(":", "").replaceAll(" ", "_") + ".png"
								+ ") for more details\\r\\n" + "It is checked by Automation team.\\r\\n\\n" + "Thanks";
						ReadAPI.postAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID
								+ "/comment", paramsCommentJira(comment));
						ReadAPI.putAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID,
								paramsRemoveLabelJira(Constant.LABEL_PASS));
						ReadAPI.putAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID,
								paramsAddLabelJira(Constant.LABEL_FAIL));
						takeScreenShort((jiraID + " " + timeToCheck).replaceAll(":", ""), myScenario);
						uploadAttachment(userJira, pwJira, jiraID, data.getOutputScreenshot(
								(jiraID + " " + timeToCheck).replaceAll(":", "").replaceAll(" ", "_") + ".png"));
					}
				}
				if (errorMessage.equals("") && j == u.length - 1) {
					valueReturn = true;
					comment = "Hi,\\r\\n\\n" + "This usercase is Ok at " + df.format(da) + "\\r\\n"
							+ "It is checked by Automation team\\r\\n\\n" + "Thanks";
					ReadAPI.postAPI(
							ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID + "/comment",
							paramsCommentJira(comment));
					ReadAPI.putAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID,
							paramsRemoveLabelJira(Constant.LABEL_FAIL));
					ReadAPI.putAPI(ReadProperties.getConfigSelenium("jiraURL") + "/rest/api/2/issue/" + jiraID,
							paramsAddLabelJira(Constant.LABEL_PASS));
				}
			}

			if (noRow != 0) {
				XSSFRow row = sheet.getRow(noRow);

				Cell cellComment = row.getCell(20);
				if (cellComment != null) {
					cellComment.removeCellComment();
				} else {
					cellComment = row.createCell(20);
				}

				if (valueReturn) {
					cellComment.setCellValue("Ok");
				} else {
					cellComment.setCellValue("Not Ok");
				}

				fis.close();

				// Update excel
				FileOutputStream streamOut = new FileOutputStream(new File(strExcelTestCasePath));
				workbook.write(streamOut);
				streamOut.close();
			}
		}
	}

	/*
	 * Author: nhan.nguyen@sutrixsolution.com Description: read links on excel
	 * and run it then report to excel
	 */
	public void runCheckLinksOnExcel(String filename, String sheetname) throws MalformedURLException, IOException {
		String strFileName = System.getProperty("user.dir") + "//src//test//resources//excel_data-input//" + filename;
		String strFileNameOut = System.getProperty("user.dir") + File.separator + "reporting" + File.separator + "Excel"
				+ File.separator + filename;
		String strSheetName = sheetname;
		File oFile = new File(strFileName);
		fis = new FileInputStream(oFile);
		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet = wb.getSheet(strSheetName);

		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			row = sheet.getRow(rowIndex);
			if (row != null) {
				Cell cell = row.getCell(0);
				if (cell != null) {
					System.out.println(cell.getStringCellValue());
					String strCell = cell.getStringCellValue();
					int i = getResponse(strCell);
					if (i == 200) {
						writeXLSXFile(strFileNameOut, strSheetName, Integer.toString(i), "PASSED", rowIndex, 1);
					} else {
						writeXLSXFile(strFileNameOut, strSheetName, Integer.toString(i), "FAIL", rowIndex, 1);
					}
				}
			}
		}

	}

	public static void writeXLSXFile(String FileName, String SheetName, String text, String color, int row, int col)
			throws IOException {
		XSSFSheet sheet = null;
		try {
			fis = new FileInputStream(FileName);

			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFRow sheetrow = null;

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
			// set font
			CellStyle style = workbook.createCellStyle();
			Font font = workbook.createFont();
			style.setBorderBottom(CellStyle.BORDER_THIN);
			style.setBottomBorderColor(HSSFColor.BLACK.index);
			style.setBorderLeft(CellStyle.BORDER_THIN);
			style.setLeftBorderColor(HSSFColor.BLACK.index);
			style.setBorderRight(CellStyle.BORDER_THIN);
			style.setRightBorderColor(HSSFColor.BLACK.index);
			style.setBorderTop(CellStyle.BORDER_THIN);
			style.setTopBorderColor(HSSFColor.BLACK.index);
			style.setAlignment(HorizontalAlignment.LEFT);
			style.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
			// Set color
			switch (color) {
			case "PASSED":
				font.setColor(HSSFColor.WHITE.index);
				style.setFillForegroundColor(HSSFColor.GREEN.index);
				style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				style.setFont(font);
				cell.setCellStyle(style);
				break;
			case "FAIL":
				font.setColor(HSSFColor.WHITE.index);
				style.setFillForegroundColor(HSSFColor.RED.index);
				style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				style.setFont(font);
				cell.setCellStyle(style);
				break;
			case "YELLOW":
				font.setColor(HSSFColor.BLACK.index);
				style.setFillForegroundColor(HSSFColor.YELLOW.index);
				style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				style.setFont(font);
				cell.setCellStyle(style);
				break;
			case "ORANGE":
				font.setColor(HSSFColor.BLACK.index);
				style.setFillForegroundColor(HSSFColor.ORANGE.index);
				style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				style.setFont(font);
				cell.setCellStyle(style);
				break;
			default:
				font.setColor(HSSFColor.BLACK.index);
				style.setFont(font);
				cell.setCellStyle(style);
				break;
			}
			// end set font
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

	/********************************************
	 * Author: Nhan Nguyen Function: @Then: Capture image Paramter:String
	 * dataRow, DataTable table (from column to column) Return: None
	 * 
	 * @throws InterruptedException
	 * 
	 * @throws Exception
	 *
	 *******************************************/
	public String captureFullPageToCompare(String folder, String objectName) throws IOException {
		// TODO Auto-generated method stub
		String dest = "";
		String fileName = "";

		System.out.println("folder name:" + folder);
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);
		List<String> validFolder = Arrays.asList("Master", "Actual", "MigrationMaster");
		if (validFolder.contains(folder)) {
			String rootPath = System.getProperty("user.dir") + File.separator + "reporting" + File.separator + folder
					+ File.separator;
			File rootFolder = new File(rootPath);
			if (!rootFolder.exists()) {
				rootFolder.mkdirs();
			}
			fileName = objectName + "_" + folder + ".jpg";

			dest = rootPath + fileName;
			File destination = new File(dest);
			ImageIO.write(screenshot.getImage(), "JPG", destination);
			dest = "./" + folder + "/" + fileName;
		}

		return dest;
	}

	public String showMasterImages(String objectName) {
		String dest = "";

		System.out.println("Element name:" + objectName);
		String pathImage = objectName + "_Master.jpg";
		dest = "./Master/" + pathImage;

		return dest;
	}

	public String compareImagesInTwoFoders(String objectName, String folderMaster, String folderActual)
			throws Exception {
		// TODO Auto-generated method stub
		String dest = "";
		String fileNameMaster = "";
		String fileNameActual = "";
		String fileNameCompared = "";
		boolean found = true;
		String folder = "Compared";

		String rootPathMaster = System.getProperty("user.dir") + File.separator + "reporting" + File.separator
				+ folderMaster + File.separator + objectName + "_" + folderMaster + ".jpg";
		String rootPathActual = System.getProperty("user.dir") + File.separator + "reporting" + File.separator
				+ folderActual + File.separator + objectName + "_" + folderActual + ".jpg";
		String rootPathCompared = System.getProperty("user.dir") + File.separator + "reporting" + File.separator
				+ folder + File.separator;
		;
		fileNameMaster = File.separator;
		fileNameActual = "";

		List<String> validFolder = Arrays.asList("Compared");
		if (validFolder.contains(folder)) {
			String PathCompared = System.getProperty("user.dir") + File.separator + "reporting" + File.separator
					+ folder + File.separator;
			File rootFolder = new File(rootPathCompared);
			if (!rootFolder.exists()) {
				rootFolder.mkdirs();
			}
			fileNameCompared = objectName + "_" + folder + ".jpg";
			rootPathCompared = PathCompared + fileNameCompared;
		}
		System.out.println("rootPathMaster: " + rootPathMaster);
		System.out.println("rootPathActual: " + rootPathActual);
		System.out.println("rootPathCompared: " + rootPathCompared);

		try {
			ImageIO.read(new File(rootPathMaster));
		} catch (Exception e) {
			found = false;

		}
		try {
			ImageIO.read(new File(rootPathActual));
		} catch (Exception e) {
			found = false;

		}

		if (found == false) {
			System.out.println("Compared is FAIL!");

		} else {
			compareTwoImages(rootPathMaster, rootPathActual, rootPathCompared);
			Thread.sleep(1000);
		}

		dest = "./" + folder + "/" + fileNameCompared;
		return dest;
	}

	public static void compareTwoImages(String imageExpect, String imageActual, String imageDiff) throws Exception {
		ImageComparison imageComparison = new ImageComparison(10, 10, 0.05);
		if (imageComparison.fuzzyEqual(imageExpect, imageActual, imageDiff)) {
			sameImages = true;
		} else {
			sameImages = false;

		}
	}

	public static void verifyAllURL(String sheetname, String filename) throws IOException {
		// TODO Auto-generated method stub
		fileNameMetaTag = filename;
		String strFileName = System.getProperty("user.dir") + "//src//test//resources//excel_data-input//" + filename;
		fis = new FileInputStream(strFileName);
		workbook = new XSSFWorkbook(fis);
		System.out.println("Sheet Name:" + sheetname);
		sheet = workbook.getSheet(sheetname);

		Iterator<Row> iterator = sheet.iterator();

		int tmpheaderKeys = 2;
		while (iterator.hasNext()) {

			Row currentRow = iterator.next();
			Iterator<Cell> cellIterator = currentRow.iterator();

			while (cellIterator.hasNext()) {

				Cell currentCell = cellIterator.next();

				if (currentCell.getCellTypeEnum() == CellType.STRING) {

					if (currentCell.getColumnIndex() == 1) {
						if (!currentCell.getStringCellValue().contains("Page")) {
							String url = currentCell.getStringCellValue();

							System.out.println("Open URL:" + url);
							driver.navigate().to(url);
							// Check Meta
							List<WebElement> allMetas = driver.findElements(By.tagName("meta"));
							System.out.println("Metas count is: " + allMetas.size());
							tmpProperty = allMetas.size();
							// System.out.println("get tmpProperty from Meta: "
							// + tmpProperty);
							int indx = 0;

							Map<String, Object> data = new LinkedHashMap<>();
							data.put("rowindex", currentCell.getRowIndex());

							String[][] metaArray = new String[500][2];

							for (int i = 0; i < allMetas.size(); i++) {
								System.out.println("tempIndex:" + indx);
								// get Meta Name
								try {
									if ((!allMetas.get(i).getAttribute("name").isEmpty())
											&& (allMetas.get(i).getAttribute("name") != null)
											&& (allMetas.get(i).getAttribute("name").toString().trim().length() > 0)
											&& allMetas.get(i).getAttribute("name").toString().trim() != null
											&& allMetas.get(i).getAttribute("name").toString().trim() != "") {
										System.out.println("Property [" + i + "] "
												+ allMetas.get(i).getAttribute("name").toString());
										metaArray[indx][0] = allMetas.get(i).getAttribute("name").toString();
										metaArray[indx][1] = allMetas.get(i).getAttribute("content").toString();
										System.out.println("metaArray[" + indx + "][0]"
												+ allMetas.get(i).getAttribute("name").toString());
										System.out.println("metaArray[" + indx + "][1]"
												+ allMetas.get(i).getAttribute("content").toString());
										// put headerKeys

										if (!headerKeys.containsKey(metaArray[indx][0])) {
											headerKeys.put(metaArray[indx][0], tmpheaderKeys);
											tmpheaderKeys++;
										}
										indx++;
									}
								} catch (Exception e) {

								}
								// get Property Name
								try {
									if ((!allMetas.get(i).getAttribute("property").isEmpty())
											&& (allMetas.get(i).getAttribute("property") != null)
											&& (allMetas.get(i).getAttribute("property").toString().trim().length() > 0)
											&& allMetas.get(i).getAttribute("property").toString().trim() != null
											&& allMetas.get(i).getAttribute("property").toString().trim() != "") {
										System.out.println("Property [" + i + "] "
												+ allMetas.get(i).getAttribute("property").toString());
										metaArray[indx][0] = allMetas.get(i).getAttribute("property").toString();
										metaArray[indx][1] = allMetas.get(i).getAttribute("content").toString();
										System.out.println("metaArray[" + indx + "][0]"
												+ allMetas.get(i).getAttribute("property").toString());
										System.out.println("metaArray[" + indx + "][1]"
												+ allMetas.get(i).getAttribute("content").toString());
										// put headerKeys
										if (!headerKeys.containsKey(metaArray[indx][0])) {
											headerKeys.put(metaArray[indx][0], tmpheaderKeys);
											tmpheaderKeys++;
										}
										indx++;
									}
								} catch (Exception e) {

								}
								// get http-equiv name
								try {
									if ((!allMetas.get(i).getAttribute("http-equiv").isEmpty())
											&& (allMetas.get(i).getAttribute("http-equiv") != null)
											&& (allMetas.get(i).getAttribute("http-equiv").toString().trim()
													.length() > 0)
											&& allMetas.get(i).getAttribute("http-equiv").toString().trim() != null
											&& allMetas.get(i).getAttribute("http-equiv").toString().trim() != "") {
										System.out.println("Property [" + i + "] "
												+ allMetas.get(i).getAttribute("http-equiv").toString());
										metaArray[indx][0] = allMetas.get(i).getAttribute("http-equiv").toString();
										metaArray[indx][1] = allMetas.get(i).getAttribute("content").toString();
										System.out.println("metaArray[" + indx + "][0]"
												+ allMetas.get(i).getAttribute("http-equiv").toString());
										System.out.println("metaArray[" + indx + "][1]"
												+ allMetas.get(i).getAttribute("content").toString());
										// put headerKeys
										if (!headerKeys.containsKey(metaArray[indx][0])) {
											headerKeys.put(metaArray[indx][0], tmpheaderKeys);
											tmpheaderKeys++;
										}
										indx++;
									}
								} catch (Exception e) {

								}

							}
							data.put("data", metaArray);
							rowsData.put(url, data);
						}
					}
				}

			}

			System.out.println();

		}
	}

	public void checkAndInsertTagByPage() throws Exception {
		// TODO Auto-generated method stub
		int rowCount = 0;

		Row HeaderRow = sheet.getRow(rowCount);
		System.out.println("Row Index:" + HeaderRow.getRowNum());
		Cell CellDataName = HeaderRow.getCell(2);
		int intDataRow = 0;
		System.out.println("Datas Row Index:" + intDataRow);
		System.out.println(fileNameMetaTag);
		System.out.println("Cell data:" + CellDataName);
		System.out.println("headerKeys: " + headerKeys.size());
		int t = 2;
		// temp = metaArray.clone();
		// Integer col = headerKeys.get(metaArray[i][0]);

		for (Entry<String, Integer> entry : headerKeys.entrySet()) {
			setCellData(rowCount, entry.getValue(), entry.getKey());
		}

		String strFileName = System.getProperty("user.dir") + File.separator + "reporting" + File.separator + "Excel"
				+ File.separator + fileNameMetaTag;
		fos = new FileOutputStream(strFileName);

		System.out.println(fos);
		workbook.write(fos);
		fos.flush();
		fos.close();
	}

	public void setCellData(int RowNum, int ColNum, String Value) throws Exception {
		DataProvider data = new DataProvider();
		XSSFCell cell;
		XSSFRow row;
		try {
			row = sheet.getRow(RowNum);
			if (row == null) {
				row = sheet.createRow(RowNum);
			}
			cell = row.getCell(ColNum, org.apache.poi.ss.usermodel.Row.RETURN_BLANK_AS_NULL);
			if (cell == null) {
				cell = row.createCell(ColNum);
				cell.setCellValue(Value);

			} else {

				cell.setCellValue(Value);

			}
		} catch (Exception e) {
			throw (e);
		}
	}

	public void checkAndInsertTagByValue() throws Exception {
		// TODO Auto-generated method stub
		int rowCount = 0;

		Row HeaderRow = sheet.getRow(rowCount);
		System.out.println("Row Index:" + HeaderRow.getRowNum());
		Cell CellDataName = HeaderRow.getCell(2);
		int intDataRow = 1;
		System.out.println("Datas Row Index:" + intDataRow);
		System.out.println(fileNameMetaTag);
		System.out.println("Cell data:" + CellDataName);
		/*
		 * System.out.println("\n\n====================================");
		 * System.out.println(JSON.serialize(metaArray));
		 * System.out.println("====================================\n\n");
		 */
		/* Display content using Iterator */
		boolean isNewLine = true;
		int indx = 1;
		for (Entry<String, Object> rowItem : rowsData.entrySet()) {
			Map<String, Object> objs = (Map<String, Object>) rowItem.getValue();
			String[][] rows = (String[][]) objs.get("data");
			Integer rowIndex = (Integer) objs.get("rowindex");
			if (rowIndex != null && rows != null) {
				for (int i = 0; i < rows.length; i++) {
					if (StringUtils.isNotBlank(rows[i][1])) {
						Integer col = headerKeys.get(rows[i][0]);
						if (col != null) {
							setCellValue(rowIndex, col, rows[i][1]);
						}
					}
				}
			}
		}
		String strFileName = System.getProperty("user.dir") + File.separator + "reporting" + File.separator + "Excel"
				+ File.separator + fileNameMetaTag;
		fos = new FileOutputStream(strFileName);

		System.out.println(fos);
		workbook.write(fos);
		fos.flush();
		fos.close();
	}

	public void setCellValue(int RowNum, int ColNum, String Value) throws Exception {
		DataProvider data = new DataProvider();
		XSSFCell cell;
		XSSFRow row;
		try {
			row = sheet.getRow(RowNum);
			if (row == null) {
				row = sheet.createRow(RowNum);
			}
			cell = row.getCell(ColNum, org.apache.poi.ss.usermodel.Row.RETURN_BLANK_AS_NULL);
			if (cell == null) {
				cell = row.createCell(ColNum);
				cell.setCellValue(Value);

			} else {

				cell.setCellValue(Value);

			}

		} catch (Exception e) {
			throw (e);
		}
	}

	public void verifyMetaTagOnExcel() throws Exception {
		// TODO Auto-generated method stub
		int rowCount = 0;

		Row HeaderRow = sheet.getRow(rowCount);
		System.out.println("Row Index:" + HeaderRow.getRowNum());
		Cell CellDataName = HeaderRow.getCell(2);
		int intDataRow = 1;
		System.out.println("Datas Row Index:" + intDataRow);
		System.out.println(fileNameMetaTag);
		System.out.println("Cell data:" + CellDataName);
		// get row and colum
		// get metaArray
		// System.out.println("metaArray: "+ metaArray.length);

		// temp = metaArray.clone();
		/*
		 * System.out.println("\n\n====================================");
		 * System.out.println(JSON.serialize(metaArray));
		 * System.out.println("====================================\n\n");
		 */
		/* Display content using Iterator */
		boolean isNewLine = true;
		int indx = 1;
		for (Entry<String, Object> rowItem : rowsData.entrySet()) {
			Map<String, Object> objs = (Map<String, Object>) rowItem.getValue();
			String[][] rows = (String[][]) objs.get("data");
			Integer rowIndex = (Integer) objs.get("rowindex");
			if (rowIndex != null && rows != null) {
				for (int i = 0; i < rows.length; i++) {
					if (StringUtils.isNotBlank(rows[i][1])) {
						Integer col = headerKeys.get(rows[i][0]);
						if (col != null) {
							// set color when see the Metatag or not
							// setCellValue(rowIndex, col, rows[i][1]);
							System.out.println("String Value:" + rows[i][1]);
							setColorMetaValue(rowIndex, col, rows[i][1]);
						}
					}
				}
			}
		}
		String strFileName = System.getProperty("user.dir") + File.separator + "reporting" + File.separator + "Excel"
				+ File.separator + fileNameMetaTag;
		fos = new FileOutputStream(strFileName);

		System.out.println(fos);
		workbook.write(fos);
		fos.flush();
		fos.close();
	}

	public void setColorMetaValue(int RowNum, int ColNum, String Value) {
		DataProvider data = new DataProvider();
		XSSFCell cell;
		XSSFRow row;
		try {
			row = sheet.getRow(RowNum);
			cell = row.getCell(ColNum);
			/*
			 * if (row == null) { row = sheet.createRow(RowNum); }
			 */

			String strTmp = cell.getStringCellValue().toString();
			System.out.println("value: " + Value);
			System.out.println("cell: " + strTmp);
			if (strTmp.trim().equalsIgnoreCase(Value.trim())) {

				cell.setCellStyle(getGreenCellStyle());
			} else {
				cell.setCellStyle(getRedCellStyle());
			}

		} catch (Exception e) {
			throw (e);
		}
	}

	public CellStyle getGreenCellStyle() {
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setColor(HSSFColor.WHITE.index);
		style.setFillForegroundColor(HSSFColor.GREEN.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFont(font);
		// cell.setCellStyle(style);
		return style;
	}

	public CellStyle getRedCellStyle() {
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setColor(HSSFColor.WHITE.index);
		style.setFillForegroundColor(HSSFColor.RED.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFont(font);
		// cell.setCellStyle(style);
		return style;
	}

	public void testCopy(Scenario myScenario) throws Exception {
		// TODO Auto-generated method stub
		String strFileName = System.getProperty("user.dir") + "//src//test//resources//excel_data-input//TestCopy.xlsx";
		fis = new FileInputStream(strFileName);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet("TestCopy");
		int rowNum = 2;
		int elemenCol = 0;
		int actualContentCol = 2;
		int rowCount = sheet.getLastRowNum() + 1;
		System.out.println("Row Count:" + rowCount);
		for (int i = rowNum; i < rowCount; i++) {
			// XSSFCell actual = (sheet.getRow(i).getCell(actualContentCol));
			String elementValue = getValueFromExcel(i, elemenCol);
			String expectedContent = getValueFromExcel(i, elemenCol + 1);
			// System.out.println(elementValue);
			// System.out.println(expectedContent);
			String actualContent = "";
			try {
				actualContent = driver.findElement(By.xpath(elementValue)).getText();
				// System.out.println("actualContent: " +actualContent);
				// System.out.println("expectedContent: " +expectedContent);
				isCheckDisplay = true;
			} catch (Exception e2) {
				isCheckDisplay = false;
			}
			if (isCheckDisplay == false) {
				setCellDataTestCopy(i, actualContentCol, "*This element is not exist*", "Pending", "YELLOW");
			} else {
				if (actualContent.equals(expectedContent)) {
					setCellDataTestCopy(i, actualContentCol, actualContent, "Pass", "PASSED");
				} else {
					setCellDataTestCopy(i, actualContentCol, actualContent, "Fail", "FAIL");
				}
			}
		}
		System.out.println("TestCopy.xlsx");

	}

	public void setCellDataTestCopy(int RowNum, int ColNum, String content, String result, String color)
			throws Exception {
		DataProvider data = new DataProvider();
		XSSFCell cell;
		XSSFRow row;
		try {
			row = sheet.getRow(RowNum);
			if (row == null) {
				row = sheet.createRow(RowNum);
			}

			cell = row.getCell(ColNum, org.apache.poi.ss.usermodel.Row.RETURN_BLANK_AS_NULL);
			if (cell == null) {
				cell = row.createCell(ColNum);
				cell.setCellValue(content);
				cell = row.createCell(ColNum + 1);
				cell.setCellValue(result);
			} else {
				cell.setCellValue(content);
				cell = row.createCell(ColNum + 1);
				cell.setCellValue(result);
			}

			CellStyle style = workbook.createCellStyle();
			Font font = workbook.createFont();
			style.setBorderBottom(CellStyle.BORDER_THIN);
			style.setBottomBorderColor(HSSFColor.BLACK.index);
			style.setBorderLeft(CellStyle.BORDER_THIN);
			style.setLeftBorderColor(HSSFColor.BLACK.index);
			style.setBorderRight(CellStyle.BORDER_THIN);
			style.setRightBorderColor(HSSFColor.BLACK.index);
			style.setBorderTop(CellStyle.BORDER_THIN);
			style.setTopBorderColor(HSSFColor.BLACK.index);
			style.setAlignment(HorizontalAlignment.CENTER);
			style.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
			switch (color) {
			case "PASSED":
				font.setColor(HSSFColor.WHITE.index);
				style.setFillForegroundColor(HSSFColor.GREEN.index);
				style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				style.setFont(font);
				cell.setCellStyle(style);
				break;
			case "FAIL":
				font.setColor(HSSFColor.WHITE.index);
				style.setFillForegroundColor(HSSFColor.RED.index);
				style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				style.setFont(font);
				cell.setCellStyle(style);
				break;
			case "YELLOW":
				font.setColor(HSSFColor.BLACK.index);
				style.setFillForegroundColor(HSSFColor.YELLOW.index);
				style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				style.setFont(font);
				cell.setCellStyle(style);
				break;
			default:
				font.setColor(HSSFColor.BLACK.index);
				style.setFont(font);
				cell.setCellStyle(style);
				break;
			}
			// Constant variables Test Data path and Test Data file name
			String strFileName = System.getProperty("user.dir") + File.separator + "reporting" + File.separator
					+ "Excel" + File.separator + "TestCopy.xlsx";
			fos = new FileOutputStream(strFileName);

			System.out.println(fos);
			workbook.write(fos);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			throw (e);
		}
	}

	public void testImagesBroken(String fileName, String sheetName) throws IOException {
		// TODO Auto-generated method stub
		int j = 0;
		int status = 0;
		String strFileName = System.getProperty("user.dir") + File.separator + "reporting" + File.separator + "Excel"
				+ File.separator + fileName;
		fis = new FileInputStream(strFileName);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			if (workbook.getSheetName(i).equals(sheetName)) {
				workbook.removeSheetAt(i);
				break;
			}
		}

		try {
			invalidImageCount = 0;
			List<WebElement> imagesList = driver.findElements(By.tagName("img"));
			System.out.println("Total no. of images are: " + imagesList.size());
			workbook.createSheet(sheetName);
			sheet = workbook.getSheet(sheetName);
			setValueInExcel(0, 0, "URL", "");
			setValueInExcel(0, 1, driver.getCurrentUrl(), "");
			setValueInExcel(1, 0, "Total no. of images are ", "");
			setValueInExcel(1, 1, imagesList.size() + "", "");
			setValueInExcel(3, 0, "Alt NAME", "ORANGE");
			setValueInExcel(3, 1, "URL of Image", "ORANGE");
			setValueInExcel(3, 2, "STATUS", "ORANGE");
			for (WebElement imgElement : imagesList) {
				String altName = imgElement.getAttribute("alt").trim();
				String imageURL = imgElement.getAttribute("src").trim();
				if (altName.equals("")) {
					altName = "No name";
					setValueInExcel(j + 4, 0, altName, "YELLOW");
				} else {
					setValueInExcel(j + 4, 0, altName, "");
				}
				setValueInExcel(j + 4, 1, imageURL, "");
				if (imgElement != null) {
					status = verifyimageActive(imgElement);
				}
				if (status == 200) {
					setValueInExcel(j + 4, 2, Integer.toString(status), "PASSED");
				} else {
					setValueInExcel(j + 4, 2, Integer.toString(status), "FAIL");

				}
				j++;
			}
			System.out.println("Total no. of invalid images are " + invalidImageCount);
			setValueInExcel(2, 0, "Total no. of invalid images are: ", "");
			setValueInExcel(2, 1, invalidImageCount + "", "");
			String strFileNameOut = System.getProperty("user.dir") + File.separator + "reporting" + File.separator
					+ "Excel" + File.separator + fileName;
			fos = new FileOutputStream(strFileName);

			System.out.println(fos);
			workbook.write(fos);
			fos.flush();
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public int verifyimageActive(WebElement imgElement) {
		int code = 0;
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(imgElement.getAttribute("src"));
			HttpResponse response = client.execute(request);
			// verifying response code he HttpStatus should be 200 if not,
			// increment as invalid images count
			if (response.getStatusLine().getStatusCode() != 200)
				invalidImageCount++;
			code = response.getStatusLine().getStatusCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return code;

	}

	public void verifyMigrationURLs(String filename, String sheetname) throws IOException {
		// TODO Auto-generated method stub
		String pageTitle;
		String btnAgreement;
		// String user = "info-pharm@chugai-pharm.co.jp";
		// String pass = "7dDHM1B4";

		String strFileName = System.getProperty("user.dir") + "//src//test//resources//excel_data-input//" + filename;
		String strFileNameOut = System.getProperty("user.dir") + File.separator + "reporting" + File.separator + "Excel"
				+ File.separator + filename;
		String strSheetName = sheetname;
		File oFile = new File(strFileName);
		fis = new FileInputStream(oFile);
		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet = wb.getSheet(strSheetName);

		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			row = sheet.getRow(rowIndex);
			if (row != null) {
				Cell cell = row.getCell(0);
				if (cell != null) {
					System.out.println(cell.getStringCellValue());
					String strCell = cell.getStringCellValue();
					if (StringUtils.isBlank(strCell))
						continue;

					// Current root folder
					String rootFolder = System.getProperty("user.dir") + "//reporting//sourceHTML//";
					File rootFile = new File(rootFolder);
					if (!rootFile.exists()) {
						rootFile.mkdirs();
					}

					String htmlFileName = FilenameUtils.getName(strCell);
					String htmlFilePath = getFilePathFromURL(strCell);

					if (StringUtils.isBlank(htmlFilePath)) {
						System.out.println("Can't download html file : " + htmlFileName);
						continue;
					}

					// get the url and run it
					driver.get(strCell);

					// get page title
					pageTitle = driver.getTitle() + rowIndex + ".html";
					writeXLSXFile(strFileNameOut, strSheetName, pageTitle, "PASSED", rowIndex, 2);
					// check this page have button agreement other

					if (existsElement(ReadProperties.getElementsAndUrls("migration-btn-xp"))) {
						btnAgreement = "Yes";
						driver.findElement(By.xpath(ReadProperties.getElementsAndUrls("migration-btn-xp"))).click();
						writeXLSXFile(strFileNameOut, strSheetName, btnAgreement, "PASSED", rowIndex, 3);
					} else {
						btnAgreement = "No";
						writeXLSXFile(strFileNameOut, strSheetName, btnAgreement, "FAIL", rowIndex, 3);
					}
					pageTitle = driver.getTitle();
					// capture full page
					Cell cellPageName = row.getCell(1);
					String img = captureFullPageToCompare("MigrationMaster", htmlFileName);
					// get html file

					String pageSource = driver.getPageSource();
					// replace absolute to relative
					// pageSource = pageSource.replaceAll("=\"/", "=\"");

					// Save file html

					File htmlRootFolder = new File(rootFile, htmlFilePath);
					if (!htmlRootFolder.exists()) {
						htmlRootFolder.mkdirs();
					}
					File fileHtml = new File(htmlRootFolder, htmlFileName);
					if (!fileHtml.exists()) {
						fileHtml.createNewFile();
					}
					try (BufferedWriter BW = new BufferedWriter(
							new OutputStreamWriter(new FileOutputStream(fileHtml), StandardCharsets.UTF_8));) {
						BW.write(pageSource);
						BW.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// get images
					List<WebElement> listImages = driver.findElements(By.tagName("img"));

					downloadResourceByWebElements(listImages, "src", rootFolder);
					// get script
					List<WebElement> listJs = driver.findElements(By.tagName("script"));
					downloadResourceByWebElements(listJs, "src", rootFolder);
					// get css
					List<WebElement> listCss = driver.findElements(By.tagName("link"));
					downloadResourceByWebElements(listCss, "href", rootFolder);
				}

			}
		}

	}

	public String getFilePathFromURL(String url) {
		String filePath = "";
		// System.out.println("url : " + url);
		String fileName = FilenameUtils.getName(url);
		if (url.startsWith(baseHost) && url.length() > baseHost.length()) {
			filePath = url.substring(baseHost.length(), url.length());
		}
		// System.out.println("filepath : " + filePath);
		if (filePath.endsWith(fileName))
			filePath = filePath.substring(0, filePath.indexOf(fileName));
		return filePath;
	}

	public void downloadResourceByWebElements(List<WebElement> elements, String attr, String rootFol) {
		if (null == elements || elements.size() == 0)
			return;
		File rootFile = new File(rootFol);
		if (!rootFile.exists()) {
			rootFile.mkdirs();
		}
		for (WebElement item : elements) {
			if (item != null && !StringUtils.isBlank(item.getAttribute(attr))) {
				String fileUrl = item.getAttribute(attr);
				processFile(fileUrl, rootFile);

			}
		}
	}

	public void processFile(String fileUrl, File rootFile) {
		System.out.println("Process :" + fileUrl);

		if (StringUtils.isBlank(fileUrl)) {
			return;
		}
		String fileName = FilenameUtils.getName(fileUrl);
		String filePath = getFilePathFromURL(fileUrl);
		// System.out.println("fileUrl: "+fileUrl);
		// System.out.println("fileName: "+fileName);
		// System.out.println("filePath: "+filePath);
		if (StringUtils.isBlank(filePath))
			return;

		File subPathFile = new File(rootFile, filePath);
		if (!subPathFile.exists()) {
			subPathFile.mkdirs();
		}

		// Image image = null;
		byte[] fileContent = urlToBytes(fileUrl);
		if (fileContent != null) {
			try (FileOutputStream fos = new FileOutputStream(new File(subPathFile, fileName));) {
				fos.write(fileContent);
				fos.close();
			} catch (Exception ex) {
				System.out.println("Can't save file : " + ex.getMessage());
			}
		}

		// Read file css / js
		if (fileName.toLowerCase().endsWith(".css")) {
			System.out.println("Process for : " + fileName);
			String cssFileContent = new String(fileContent);
			// String strPattern =
			// "background(-image)?:url\\([\"']?(.*)[\"']?\\)";
			// strPattern = "url\\([\"']?(.*)[\"']?\\)";
			String strPattern = "[a-z\\-_0-9\\/\\:\\.]*\\.(jpg|jpeg|png|gif|css)";
			Pattern pattern = Pattern.compile(strPattern);
			Matcher matcher = pattern.matcher(cssFileContent);
			List<String> imgLinks = new ArrayList<>();
			while (matcher.find()) {
				for (int i = 0; i < matcher.groupCount(); i++) {
					String matchFile = matcher.group(i);
					if (StringUtils.isBlank(matchFile))
						continue;
					// System.out.println("find : " + matchFile);
					String matchFilePath = "";
					if (matchFile.toLowerCase().endsWith("css")) {
						if (matchFile.startsWith("/")) {
							matchFile = baseHost + matchFile;
						} else if (matchFile.startsWith("../")) {
							matchFilePath = matchFile.substring("../".length(), matchFile.length());
							matchFile = baseHost + filePath + matchFilePath;
						}
						System.out.println("Found " + matchFile);
						processFile(matchFile, rootFile);
					} else if (matchFile.toLowerCase().endsWith("jpg") || matchFile.toLowerCase().endsWith("png")
							|| matchFile.toLowerCase().endsWith("jpeg") || matchFile.toLowerCase().endsWith("gif")) {
						// matchFile = matchFile.substring("url(".length(),
						// matchFile.length());
						// matchFile = matchFile.substring(0,
						// matchFile.indexOf(")"));
						// System.out.println("pass : " + matchFile);
						if (!imgLinks.contains(matchFile)) {
							imgLinks.add(matchFile);
						}
					}
				}
			}
			System.out.println("imagLinks: " + JSON.encode(imgLinks));
			// Download files
			String imgFileUrl = "";
			String matchFilePath = "";
			for (String imgLink : imgLinks) {
				if (imgLink.toLowerCase().startsWith("/")) {
					System.out.println("imgLink with /: " + baseHost + imgLink);
					imgFileUrl = baseHost + imgLink;
				} else if (imgLink.toLowerCase().startsWith("../")) {
					matchFilePath = imgLink.substring("../".length(), imgLink.length());
					System.out.println("imgLink with ../: " + filePath + matchFilePath);
					imgFileUrl = baseHost + filePath + matchFilePath;
				}
				String imgFileName = FilenameUtils.getName(imgFileUrl);
				String imgFilePath = getFilePathFromURL(imgFileUrl);
				// System.out.println("fileUrl: " + imgFileUrl);
				// System.out.println("fileName: " + imgFileName);
				// System.out.println("filePath: " + imgFilePath);
				if (StringUtils.isBlank(filePath))
					continue;

				File subPathImgFile = new File(rootFile, imgFilePath);
				if (!subPathImgFile.exists()) {
					subPathImgFile.mkdirs();
				}

				// Image image = null;
				byte[] imgFileContent = urlToBytes(imgFileUrl);
				if (imgFileContent != null) {
					try (FileOutputStream fos = new FileOutputStream(new File(subPathImgFile, imgFileName));) {
						fos.write(imgFileContent);
						fos.close();
					} catch (Exception ex) {
						System.out.println("Can't save file : " + ex.getMessage());
					}
				}
			}
		} else if (fileName.toLowerCase().endsWith(".js")) {
			String imgFileUrl = "";
			String matchFilePath = "";
			System.out.println("Process for : " + fileName);
			String jsFileContent = new String(fileContent);
			String strPattern = "[a-z\\-_0-9\\/\\:\\.]*\\.(jpg|jpeg|png|gif|pdf)";
			Pattern pattern = Pattern.compile(strPattern);
			Matcher matcher = pattern.matcher(jsFileContent);
			List<String> imgLinks = new ArrayList<>();
			while (matcher.find()) {
				for (int i = 0; i < matcher.groupCount(); i++) {
					String matchFile = matcher.group(i);
					if (StringUtils.isBlank(matchFile))
						continue;
					System.out.println("find : " + matchFile);
					imgLinks.add(matchFile);

				}
			}
			System.out.println("imagLinks: " + JSON.encode(imgLinks));
			for (String imgLink : imgLinks) {
				if (imgLink.toLowerCase().startsWith("/")) {
					System.out.println("imgLink with /: " + baseHost + imgLink);
					imgFileUrl = baseHost + imgLink;
				} else if (imgLink.toLowerCase().startsWith("../")) {
					matchFilePath = imgLink.substring("../".length(), imgLink.length());
					System.out.println("imgLink with ../: " + filePath + matchFilePath);
					imgFileUrl = baseHost + filePath + matchFilePath;
				}
				String imgFileName = FilenameUtils.getName(imgFileUrl);
				String imgFilePath = getFilePathFromURL(imgFileUrl);
				// System.out.println("fileUrl: " + imgFileUrl);
				// System.out.println("fileName: " + imgFileName);
				// System.out.println("filePath: " + imgFilePath);
				if (StringUtils.isBlank(filePath))
					continue;

				File subPathImgFile = new File(rootFile, imgFilePath);
				if (!subPathImgFile.exists()) {
					subPathImgFile.mkdirs();
				}
				// Image image = null;
				byte[] imgFileContent = urlToBytes(imgFileUrl);
				if (imgFileContent != null) {
					try (FileOutputStream fos = new FileOutputStream(new File(subPathImgFile, imgFileName));) {
						fos.write(imgFileContent);
						fos.close();
					} catch (Exception ex) {
						System.out.println("Can't save file : " + ex.getMessage());
					}
				}
			}
		}
	}

	public String urlToString(String url) {
		byte[] strContent = urlToBytes(url);
		if (strContent == null)
			return StringUtils.EMPTY;
		return new String(strContent);
	}

	public byte[] urlToBytes(String url) {
		byte[] result = null;
		try {
			URL uri = new URL(url);
			InputStream in = new BufferedInputStream(uri.openStream());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int n = 0;
			while (-1 != (n = in.read(buf))) {
				out.write(buf, 0, n);
			}
			out.close();
			in.close();
			result = out.toByteArray();
		} catch (Exception ex) {
			System.out.println("eee : " + ex.getMessage());
		}
		return result;
	}

	private boolean existsElement(String xpath) {
		try {
			driver.findElement(By.xpath(xpath));
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}

	public void testBrokenURL(String fileName, String sheetName) throws IOException {
		// TODO Auto-generated method stub
		int j = 0;
		int status = 0;
		String strFileName = System.getProperty("user.dir") + File.separator + "reporting" + File.separator + "Excel"
				+ File.separator + fileName;
		fis = new FileInputStream(strFileName);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			if (workbook.getSheetName(i).equals(sheetName)) {
				workbook.removeSheetAt(i);
				break;
			}
		}

		try {
			invalidImageCount = 0;
			// List<WebElement> aLists = driver.findElements(By.tagName("a"));
			// System.out.println("Total no. of URLs are: " + aLists.size());
			workbook.createSheet(sheetName);
			sheet = workbook.getSheet(sheetName);

			List<String> hrefList = new ArrayList<>();
			List<String> urlNameList = new ArrayList<>();
			List<WebElement> urlList = driver.findElements(By.tagName("a"));
			setValueInExcel(0, 0, "URL", "");
			setValueInExcel(0, 1, driver.getCurrentUrl(), "");
			setValueInExcel(1, 0, "Total no. of URLs are ", "");
			setValueInExcel(1, 1, urlList.size() + "", "");
			setValueInExcel(3, 0, "Alt NAME", "ORANGE");
			setValueInExcel(3, 1, "URL of Image", "ORANGE");
			setValueInExcel(3, 2, "STATUS", "ORANGE");

			for (int i = 0; i < urlList.size(); i++) {
				try {
					if (!urlList.get(i).getAttribute("href").trim().equals("javascript:void(0)")
							&& !urlList.get(i).getAttribute("href").trim().equals("")) {
						String urlName = urlList.get(i).getAttribute("innerHTML").replaceAll("<[^>]*>", "").trim();
						String url = urlList.get(i).getAttribute("href").trim();
						urlNameList.add(urlName);
						hrefList.add(url);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			boolean statusScenario = true;
			for (int i = 0; i < hrefList.size(); i++) {
				String urlName = urlNameList.get(i);
				if (urlName.equals("")) {
					urlName = "No name";
					setValueInExcel(i + 2, 0, urlName, "YELLOW");
				} else {
					setValueInExcel(i + 2, 0, urlName, "");
				}

				setValueInExcel(i + 2, 1, hrefList.get(i), "");
				System.out.println(hrefList.get(i));
				status = getResponse(hrefList.get(i));
				if (status == 200) {
					setValueInExcel(i + 2, 2, Integer.toString(status), "PASSED");
				} else {
					setValueInExcel(i + 2, 2, Integer.toString(status), "FAIL");
					invalidImageCount++;
				}
			}
			System.out.println("Total no. of invalid URLs are " + invalidImageCount);
			setValueInExcel(2, 0, "Total no. of invalid URL are: ", "");
			setValueInExcel(2, 1, invalidImageCount + "", "");
			String strFileNameOut = System.getProperty("user.dir") + File.separator + "reporting" + File.separator
					+ "Excel" + File.separator + fileName;
			fos = new FileOutputStream(strFileName);

			System.out.println(fos);
			workbook.write(fos);
			fos.flush();
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void openAuthPage(String testURL, DataTable table, Scenario myScenario2) throws InterruptedException {
		// TODO Auto-generated method stub
		String link = ReadProperties.getElementsAndUrls(testURL);

		if (null != ReadProperties.getElementsAndUrls("auuser")) {
			String part[] = link.split("://");
			Assert.assertTrue(link + " wrong", part.length > 1);
			link = part[0] + "://" + ReadProperties.getElementsAndUrls("auuser") + ":"
					+ ReadProperties.getElementsAndUrls("aupass") + "@" + part[1];
			driver.navigate().to(link);
			Thread.sleep(3000);
			link = ReadProperties.getElementsAndUrls(testURL);
			driver.navigate().to(link);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			// Assert.assertFalse("data:,".equals(driver.getTitle()));
		} else {
			Assert.assertFalse("Can't find url: '" + testURL + "' ", link == null);
			driver.get(link);
		}
		// Login to this site
		List<List<String>> data = table.raw();
		System.out.println("email:" + data.get(0).get(0));
		System.out.println("email:" + data.get(0).get(1));
		// click on link
		WebElement lnkLogin = driver.findElement(By.xpath("//*[@id='app']/div/div[1]/div[2]/div/div[3]/a[1]"));
		lnkLogin.click();
		// email
		WebElement inputEmail = driver.findElement(By.id("email"));
		inputEmail.clear();
		inputEmail.sendKeys(data.get(0).get(0));
		// password
		WebElement inputPassword = driver.findElement(By.id("password"));
		inputPassword.clear();
		inputPassword.sendKeys(data.get(0).get(1));
		//submit
		WebElement tbnSubmit = driver.findElement(By.xpath("//*[@id='app']/div/div[1]/div/main/div/div[2]/div[2]/div[2]/div/form/button"));
		tbnSubmit.click();
		waitForWebState();
	}
	
	public static List<String> getCssAttrFromFile(String filePath){
		try {
			return FileUtils.readLines(new File(filePath), Charset.forName("UTF-8"));
		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
		}
		return new ArrayList<>();
	}
	
	public void verifyCSS(String attrib, String Object, String value) throws Exception{
		WebElement eleCss = driver.findElement(getIdentifier(Object));
		String actualResult = eleCss.getCssValue(attrib);
		//define CSS with color
		//define CSS with font-weight
		//define other
		if (attrib.contains("color")){
			actualResult = Color.fromString(actualResult).asHex();
			Assert.assertEquals(attrib+"=>",value.toLowerCase(), actualResult.toLowerCase());
		}else if(attrib.contains("font-weight")){
			switch (actualResult) {
			case "900":
				Assert.assertEquals(attrib+"=>",value.toLowerCase(), "ultra bold");
				break;
			case "800":
				Assert.assertEquals(attrib+"=>",value.toLowerCase(), "extra bold");
				break;
			case "700":
				Assert.assertEquals(attrib+"=>",value.toLowerCase(), "bold");
				break;
			case "600":
				Assert.assertEquals(attrib+"=>",value.toLowerCase(), "semi bold");
				break;
			case "500":
				Assert.assertEquals(attrib+"=>",value.toLowerCase(), "medium");
				break;
			case "400":
				Assert.assertEquals(attrib+"=>",value.toLowerCase(), "normal");
				break;
			case "300":
				Assert.assertEquals(attrib+"=>",value.toLowerCase(), "light");
				break;
			case "200":
				Assert.assertEquals(attrib+"=>",value.toLowerCase(), "extra light");
				break;
			case "100":
				Assert.assertEquals(attrib+"=>",value.toLowerCase(), "thin");
				break;
			default:
				Assert.assertEquals(attrib+"=>",value.toLowerCase(), actualResult.toLowerCase());
			}
		}else {
			Assert.assertEquals(attrib+"=>",value.toLowerCase(), actualResult.toLowerCase());
		}
	}
	/*public static void main(String[] args) {
		System.out.println(JSON.encode(getCssAttrFromFile("C:\\Users\\nhan.nguyen\\workspace\\auto_fw_jira\\src\\test\\resources\\properties\\attrib.txt")));
		String strTemp = getCssAttrFromFile("C:\\Users\\nhan.nguyen\\workspace\\auto_fw_jira\\src\\test\\resources\\properties\\attrib.txt").get(0).toString();
		System.out.println(strTemp);
	}*/

}
