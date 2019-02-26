package support.steps;

import java.io.IOException;
import java.io.UncheckedIOException;

import org.junit.Assert;
import org.junit.BeforeClass;

//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.PrintStream;
//import java.io.UnsupportedEncodingException;
//import java.nio.charset.StandardCharsets;

//import junit.framework.Assert;
//import junit.framework.TestCase;

//import org.apache.log4j.LogManager;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import support.utils.OperationHelper;
//import support.utils.listener.ExtentProperties;
//import support.utils.listener.Reporter;
import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import support.utils.listener.ExtentProperties;
import support.utils.listener.Reporter;
//import cucumber.runtime.StepDefinitionMatch;

public class StepsBase extends ParentStepsSupport {
	AssertionError error;
	private OperationHelper support;
	private Scenario myScenario;
	public ParentStepsSupport par = new ParentStepsSupport();
	JavascriptExecutor je = (JavascriptExecutor) driver;
	boolean isPrint = false;
	String imagePath;
	// private final ByteArrayOutputStream outContent = new
	// ByteArrayOutputStream();
	// private final ByteArrayOutputStream errContent = new
	// ByteArrayOutputStream();
	// private final PrintStream originalOut = System.out;
	// private final PrintStream originalErr = System.err;

	public StepsBase() {
		support = new OperationHelper();
	}

	@When("^I get object$")
	public void i_get_object() throws Throwable {
		isPrint = true;
	}

	@Before
	public void embedScreenshotStep(Scenario scenario) throws InterruptedException {
		// driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		this.myScenario = scenario;
	}
	
	// @After
	// public void restoreStreams() throws UnsupportedEncodingException {
	// System.setOut(originalOut);
	// System.setErr(originalErr);
	// System.out.println(originalOut);
	// System.out.println(LogManager.getRootLogger());
	// String output = new String(outContent.toByteArray());
	// String output1 = new String(errContent.toByteArray());
	// System.out.println("AAAAAA:"+myScenario.toString());
	// }

	// Click object
	@When("^I click on \"([^\"]*)\"(?: to \"([^\"]*)\")?(?: #\\(\"([^\"]*)\"\\))?$")
	public void clickObject(String object, String description, String number) throws Throwable {
		support.clickObject(object, number);
	}

	// Check object display or not
	@Then("^I should see \"([^\"]*)\"(?:,\"([^\"]*)\")?(?:,\"([^\"]*)\")?(?:,\"([^\"]*)\")?(?:,\"([^\"]*)\")?(?:,\"([^\"]*)\")?(?:,\"([^\"]*)\")?(?:,\"([^\"]*)\")?(?:,\"([^\"]*)\")?(?:,\"([^\"]*)\")? \"([^\"]*)\"(?: #\\(\"([^\"]*)\"\\))?$")
	public void i_should_see(String object, String object1, String object2, String object3, String object4,
			String object5, String object6, String object7, String object8, String object9, String isDisplay,
			String number) throws Throwable {
		support.verifyDisplay(object, isDisplay, number);
		support.verifyDisplay(object1, isDisplay, number);
		support.verifyDisplay(object2, isDisplay, number);
		support.verifyDisplay(object3, isDisplay, number);
		support.verifyDisplay(object4, isDisplay, number);
		support.verifyDisplay(object5, isDisplay, number);
		support.verifyDisplay(object6, isDisplay, number);
		support.verifyDisplay(object7, isDisplay, number);
		support.verifyDisplay(object8, isDisplay, number);
		support.verifyDisplay(object9, isDisplay, number);
	}

	@When("^I should see \"([^\"]*)\" is \"([^\"]*)\" #\\(\"([^\"]*)\"\\)$")
	public void i_should_see_is(String object, String value, String number) throws Throwable {
		support.verifyText(object, value, number);
	}

	// Write something not implement code
	@When("^\"([^\"]*)\"$")
	public void something(String strArg1) throws Throwable {
	}

	// Open page
	@When("^I (?:got success to)?(?:am on)?(?:open)?(?:run)? \"([^\"]*)\"(?: to \"([^\"]*)\")?(?: #\\(\"([^\"]*)\"\\))?$")
	public void openPage(String url, String description, String author) throws Throwable {
		support.openPage(url, myScenario);
	}

	// Back to previous page
	@When("^I back to \"([^\"]*)\"$")
	public void backToPreviousPage(String desCriptionStep) throws Throwable {
		support.backToPreviousPage();
	}

	// Check placeholder
	@Then("^I should see the placeholder of \"([^\"]*)\" is \"([^\"]*)\"$")
	public void i_should_see_the_placeholder_of_something_is_something(String object, String value) throws Throwable {
		support.checkPlaceholder(object, value);
	}

	// Roll down
	@When("^I roll down \"([^\"]*)\"$")
	public void i_roll_down_something(String strArg1) throws Throwable {
		support.scrollTo(0, 250);
	}

	@After
	public void tearDown(Scenario scenario) throws Exception {
		String imagePath = "";
		System.out.println("Scenario Name:" + myScenario.getName());
		if (myScenario.isFailed() && !scenario.getName().contains("compare")) {
			imagePath = support.captureExtentReports("FAILED");
			try {
				Reporter.addScreenCaptureFromPath(imagePath);
			} catch (UncheckedIOException e) {
			}
			myScenario.getSourceTagNames();

		} else if (!scenario.isFailed() && !scenario.getName().contains("compare")) {

			imagePath = support.captureExtentReports("PASSED");

			try {
				Reporter.addScreenCaptureFromPath(imagePath);
			} catch (UncheckedIOException e) {
			}
		}

		support.closePage(scenario);
		if (driver != null) {
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
		}
	}

	@When("^I input into \"([^\"]*)\": \"([^\"]*)\"(?:,\"([^\"]*)\": \"([^\"]*)\")?(?:,\"([^\"]*)\": \"([^\"]*)\")?(?:,\"([^\"]*)\": \"([^\"]*)\")?(?:,\"([^\"]*)\": \"([^\"]*)\")?(?:,\"([^\"]*)\": \"([^\"]*)\")?(?:,\"([^\"]*)\": \"([^\"]*)\")?(?:,\"([^\"]*)\": \"([^\"]*)\")?(?:,\"([^\"]*)\": \"([^\"]*)\")?(?:,\"([^\"]*)\": \"([^\"]*)\")?(?: to \"([^\"]*)\")?(?: #\\(\"([^\"]*)\"\\))?$")
	public void i_input_into_with_to(String object, String value, String object1, String value1, String object2,
			String value2, String object3, String value3, String object4, String value4, String object5, String value5,
			String object6, String value6, String object7, String value7, String object8, String value8, String object9,
			String value9, String desCriptionStep, String number) throws Throwable {
		support.inputData(object, value, number);
		support.inputData(object1, value1, number);
		support.inputData(object2, value2, number);
		support.inputData(object3, value3, number);
		support.inputData(object4, value4, number);
		support.inputData(object5, value5, number);
		support.inputData(object6, value6, number);
		support.inputData(object7, value7, number);
		support.inputData(object8, value8, number);
		support.inputData(object9, value9, number);
	}

	// dropdow
	@When("^I select \"([^\"]*)\" in \"([^\"]*)\"(?:,\"([^\"]*)\" in \"([^\"]*)\")?(?:,\"([^\"]*)\" in \"([^\"]*)\")?(?:,\"([^\"]*)\" in \"([^\"]*)\")?(?:,\"([^\"]*)\" in \"([^\"]*)\")?(?:,\"([^\"]*)\" in \"([^\"]*)\")?(?:,\"([^\"]*)\" in \"([^\"]*)\")?(?:,\"([^\"]*)\" in \"([^\"]*)\")?(?:,\"([^\"]*)\" in \"([^\"]*)\")?(?:,\"([^\"]*)\" in \"([^\"]*)\")?(?: to \"([^\"]*)\")?(?: #\\(\"([^\"]*)\"\\))?$")
	public void i_select_in_to(String value, String object, String value1, String object1, String value2,
			String object2, String value3, String object3, String value4, String object4, String value5, String object5,
			String value6, String object6, String value7, String object7, String value8, String object8, String value9,
			String object9, String description, String number) throws Throwable {
		if (isPrint == false) {
			support.selectDropdowList(object, value, number);
			support.selectDropdowList(object1, value1, number);
			support.selectDropdowList(object2, value2, number);
			support.selectDropdowList(object3, value3, number);
			support.selectDropdowList(object4, value4, number);
			support.selectDropdowList(object5, value5, number);
			support.selectDropdowList(object6, value6, number);
			support.selectDropdowList(object7, value7, number);
			support.selectDropdowList(object8, value8, number);
			support.selectDropdowList(object9, value9, number);
		} else {
			support.getKey(object);
			support.getKey(object1);
			support.getKey(object2);
			support.getKey(object3);
			support.getKey(object4);
			support.getKey(object5);
			support.getKey(object6);
			support.getKey(object7);
			support.getKey(object8);
			support.getKey(object9);
		}
	}

	@When("^I resize windown with height \"([^\"]*)\" and width \"([^\"]*)\"$")
	public void resize(String height, String width) throws Throwable {
		support.resize(height, width);
	}

	@When("^I click on \"([^\"]*)\" checkbox and select \"([^\"]*)\" in \"([^\"]*)\" list$")
	public void i_click_on_checkbox_and_select_in_list(String elementToClick, String selectText, String dropdownList)
			throws Throwable {
		support.selectCheckboxByText(elementToClick, selectText, dropdownList);
	}

	@When("^I verify element on \"([^\"]*)\"(?: #\\(\"([^\"]*)\"\\))?$")
	public void i_verify_element_on_something(String object, String number) throws Throwable {
		support.switchTo(object, number);
	}

	@Then("^I should see title of page is \"([^\"]*)\"$")
	public void i_should_see_title_of_page_is(String expectTitleName) throws Throwable {
		support.getTitleOfPage(expectTitleName);
	}

	// Export all links to excel file
	@Then("^I export \"([^\"]*)\" to \"([^\"]*)\"$")
	public void i_export_to(String arg1, String arg2) throws Throwable {
		// support.exportLinkToExcel();
	}

	@Then("^I highlight element \"([^\"]*)\"$")
	public void i_highlight_element(String arg1) throws Throwable {
		support.highlightElement();
	}

	// Device
	@When("^I click on \"([^\"]*)\" and \"([^\"]*)\" to choose \"([^\"]*)\" on device$")
	public void i_click_on_and_to_choose_on_device(String xOffset, String yOffset, String newLocation)
			throws Throwable {
		support.chooseNewLocationOnDevice(xOffset, yOffset, newLocation);
	}

	// ExportFromExcelToCucumber
	@Then("^I read data in \"([^\"]*)\" in \"([^\"]*)\"$")
	public void i_read_data_in_in(String arg1, String arg2) throws Throwable {
		// support.readDataExcelInCucumber();
	}

	@When("^I wait in \"([^\"]*)\" milisecond(?: to \"([^\"]+)\")?(?: \"([^\"]+)\")?$")
	public void wait(String value, String desCriptionStep, String doThisStep) throws Throwable {
		support.wait(value, doThisStep);
	}

	@Then("^I should go to \"([^\"]*)\"$")
	public void i_should_go_to(String page) throws Throwable {
		support.checkGoToPage(page);
	}

	@Then("^I take screenshot full page \"([^\"]*)\"$")
	public void i_take_screenshot_full_page(String screenshotName) throws Throwable {
		support.captureFullScreen(screenshotName);
	}

	// Check response code
	@Then("^I check respose code all links on page and output it on \"([^\"]*)\" name of \"([^\"]*)\" file$")
	public void i_check_respose_code_all_links_on_page_and_output_it_on_name_of_file(String sheetName, String excelName)
			throws Throwable {
		support.checkResponseCode(sheetName, excelName);
	}

	// Check alt of image
	@Then("^I check alt tag for all images on page and output it on \"([^\"]*)\" name of \"([^\"]*)\" file$")
	public void i_check_alt_tag_for_all_images_on_page_and_output_it_on_name_of_file(String sheetName, String excelName)
			throws Throwable {
		support.checkAltOfImages(sheetName, excelName);
	}

	// Check alt and output cases fail
	@Then("^I check alt tag all images on page and output cases fail on \"([^\"]*)\" name of \"([^\"]*)\" file$")
	public void i_check_alt_tag_all_images_on_page_and_output_cases_fail_on_name_of_file(String sheetName,
			String excelName) throws Throwable {
		support.checkAlt_CaseFail(sheetName, excelName);
	}

	@Then("^I check meta keyword should be \"([^\"]*)\" and meta description should be \"([^\"]*)\"$")
	public void i_check_meta_keyword_should_be_and_meta_description_should_be(String metaKeyword,
			String metaDescription) throws Throwable {
		support.checkMetaTag(metaKeyword, metaDescription);
	}

	@Then("^I input \"([^\"]*)\" character into \"([^\"]*)\" field, click on \"([^\"]*)\" button and should see \"([^\"]*)\" is \"([^\"]*)\"$")
	public void i_input_character_into_field_click_on_button_and_should_see_is(int characterNum, String field,
			String button, String errorElement, String errorMessage) throws Throwable {
		support.verifyLengthText(characterNum, field, button, errorElement, errorMessage);
	}

	@When("^I open \"([^\"]*)\" page$")
	public void i_open_page(String url) throws Throwable {
		support.openPageToCheckPageSpeed(url, myScenario);
	}

	@Then("^I open page and check time to load page with \"([^\"]*)\" sheet in excel file \"([^\"]*)\"$")
	public void i_open_page_and_check_time_to_load_page_with_sheet_in_excel_file(String sheetName, String excelName)
			throws Throwable {
		support.checkTimePageLoad(sheetName, excelName);
	}

	@Then("^I check all content on page, if it contains UTF(\\d+) content I will output into \"([^\"]*)\" name of \"([^\"]*)\" file$")
	public void i_check_all_content_on_page_if_it_contains_UTF_content_I_will_output_into_name_of_file(int arg1,
			String sheetName, String excelName) throws Throwable {
		support.checkUnicodeContent(sheetName, excelName);
	}

	@Then("^I input invalid value into \"([^\"]*)\" field, click on \"([^\"]*)\" button and should see \"([^\"]*)\" is \"([^\"]*)\"$")
	public void i_input_invalid_value_into_field_click_on_button_and_should_see_is(String field, String button,
			String errorElement, String errorMessage) throws Throwable {
		support.verifyEmailAddress(field, button, errorElement, errorMessage);
	}

	@Then("^I input \"([^\"]*)\" into \"([^\"]*)\" field, click on \"([^\"]*)\" button and check \"([^\"]*)\" each page of \"([^\"]*)\"$")
	public void i_input_into_field_click_on_button_and_check_each_page_of(String value, String eSearchBox,
			String eSubmit, String resultList, String paging) throws Throwable {
		support.checkSearch(value, eSearchBox, eSubmit, resultList, paging);
	}

	@Then("^I input data from \"([^\"]*)\" sheet in excel file \"([^\"]*)\"$")
	public void i_input_data_from_sheet_in_excel_file(String sheetName, String excelName, DataTable table)
			throws Throwable {
		support.inputContent(sheetName, excelName, table);
	}

	@Then("^I run and check all usercases on Jira and output into \"([^\"]*)\" file$")
	public void i_run_and_check_all_usercases_on_Jira_and_output_into_file(String excelFile) throws Throwable {
		support.getUserCasesListAndRunAuto(excelFile);
	}

	@Then("^I run and check some usercases: \"([^\"]*)\" on Jira$")
	public void i_run_and_check_some_usercases_on_Jira(String jiraID) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		support.getUserCaseAndRunAuto(jiraID);
	}

	@Then("^I check images on page are broken or not and output it on \"([^\"]*)\" name of \"([^\"]*)\" file$")
	public void i_check_images_on_page_are_broken_or_not_and_output_it_on_name_of_file(String sheet, String excelFile)
			throws Throwable {
		support.checkBrokenImages(sheet, excelFile);
	}

	// read testcase on excel
	@Then("^I run and check all usercases on Excel and output into \"([^\"]*)\" file$")
	public void i_run_and_check_all_usercases_on_Excel_and_output_into_file(String excelFile) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		support.getUserCasesListOnExcelAndRunAuto(excelFile);
	}

	@Then("^I run and check some usercases: \"([^\"]*)\" on sheet \"([^\"]*)\" on \"([^\"]*)\" Excel$")
	public void i_run_and_check_some_usercases_on_Excel(String jiraID, int excelSheet, String excelName)
			throws Throwable {
		// Write code here that turns the phrase above into concrete action
		support.getUserCaseOnExcelAndRunAuto(jiraID, excelSheet, excelName);
	}

	@When("^I read contents on sheet name: \"([^\"]*)\" of \"([^\"]*)\" file then create UserCases on Jira$")
	public void i_read_contents_on_sheet_name_of_file_then_create_UserCases_on_Jira(String sheetName, String excelName)
			throws Throwable {
		support.createUsercase(sheetName, excelName);
	}

	// run testcase on jira
	@Then("^I run and check some usercases: \"([^\"]*)\" and \"([^\"]*)\" then output into: \"([^\"]*)\" on Jira$")
	public void i_run_and_check_some_usercases_and_then_output_into_on_Jira(String jiraID, String sheetName,
			String excelName) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		support.runCheckSomeCaseOnJira(jiraID, sheetName, excelName);
	}

	@Then("^I run and check all usercase tickets on Jira then ouput into \"([^\"]*)\"$")
	public void i_run_and_check_all_usercase_tickets_on_Jira_then_ouput_into(String excelName) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		support.runCheckAllCaseOnJira(excelName);
	}

	@Then("^I run and check all usercases on Excel and update into \"([^\"]*)\" file and Jira$")
	public void i_run_and_check_all_usercases_on_Excel_and_update_into_file_and_Jira(String excelName)
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		support.runCheckUsercaseOnExcel(excelName);
	}

	@Then("^I run and check usercases: \"([^\"]*)\" on sheet \"([^\"]*)\" on \"([^\"]*)\" Excel$")
	public void i_run_and_check_usercases_on_sheet_on_Excel(String jiraID, String sheetName, String excelName)
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		support.runCheckSomeUsercaseOnExcel(jiraID, sheetName, excelName);
	}
	
	//CheckURLs
	@Then("^I get the file \"([^\"]*)\" and I verify links on sheet \"([^\"]*)\" and report to excel$")
	public void i_get_the_file_and_I_verify_links_on_sheet_and_report_to_excel(String filename, String sheetname)
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		support.runCheckLinksOnExcel(filename, sheetname);
	}

	@Given("^I open url \"([^\"]*)\"$")
	public void i_open_url(String url) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		support.openPage(url, myScenario);
	}

	@And("^I click on page \"([^\"]*)\"$")
	public void i_click_on_page(String objectName) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		support.clickObject(objectName, null);
	}

	@Then("^I capture page \"([^\"]*)\" with \"([^\"]*)\"$")
	public void i_capture_page(String folder, String objectName) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// captureFullPageToCompare

		imagePath = support.captureFullPageToCompare(folder, objectName);
		System.out.println("Image name:" + imagePath);
		try {
			Reporter.addScreenCaptureFromPath(imagePath);
		} catch (UncheckedIOException e) {
		}
	}

	@And("^I show Master page \"([^\"]*)\"$")
	public void i_show_Master_page(String objectName) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		imagePath = support.showMasterImages(objectName);
		System.out.println("Image name:" + imagePath);
		try {

			Reporter.addScreenCaptureFromPath(imagePath);
		} catch (UncheckedIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("^I click on Actual page \"([^\"]*)\"$")
	public void i_click_on_Actual_page(String objectName) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		support.clickObject(objectName, null);

		imagePath = support.captureFullPageToCompare("Actual", objectName);
		System.out.println("Image name:" + imagePath);
		try {
			Reporter.addScreenCaptureFromPath(imagePath);
		} catch (UncheckedIOException e) {
		}
	}

	@Then("^compare \"([^\"]*)\" on \"([^\"]*)\" with \"([^\"]*)\"$")
	public void compare_on_with(String objectName, String folderMaster, String folderActual) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		imagePath = support.compareImagesInTwoFoders(objectName, folderMaster, folderActual);
		System.out.println("Image name:" + imagePath);
		try {
			Reporter.addScreenCaptureFromPath(imagePath);
		} catch (UncheckedIOException e) {
		}
		if (OperationHelper.sameImages) {
			Assert.assertTrue(true);
		} else {
			Assert.assertFalse(true);
		}
	}

	// Meta
	@Given("^I verify all URL on sheet name: \"([^\"]*)\" of \"([^\"]*)\" file$")
	public void i_verify_all_URL_on_sheet_name_of_file(String sheetName, String fileName) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		support.verifyAllURL(sheetName, fileName);
	}

	@Then("^Check and Insert Meta in to excel$")
	public void check_and_Insert_Meta_in_to_excel() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		support.checkAndInsertTagByPage();
		support.checkAndInsertTagByValue();
	}

	@Then("^Verify each of metaTag suitable on excel or not$")
	public void verify_each_of_metaTag_suitable_on_excel_or_not() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		support.verifyMetaTagOnExcel();
	}

	// Check content
	@Then("^I check content of element is correct with expected result or not$")
	public void i_check_content_of_element_is_correct_with_expected_result_or_not() throws Throwable {
		support.testCopy(myScenario);
	}

	//images
	@Then("^I get the file \"([^\"]*)\" and I verify image on sheet \"([^\"]*)\" and report to excel$")
	public void i_get_the_file_and_I_verify_image_on_sheet_and_report_to_excel(String fileName, String sheetName) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   support.testImagesBroken(fileName,sheetName);
	}
	
	//migration HTML
	@Then("^I get the file \"([^\"]*)\" and I verify migration links on sheet \"([^\"]*)\" and report to excel$")
	public void i_get_the_file_and_I_verify_migration_links_on_sheet_and_report_to_excel(String filename, String sheetname) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   support.verifyMigrationURLs(filename,sheetname);
	}
	@Then("^I get the file \"([^\"]*)\" and I verify tag a on sheet \"([^\"]*)\" and report to excel$")
	public void i_get_the_file_and_I_verify_tag_a_on_sheet_and_report_to_excel(String fileName, String sheetName) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		support.testBrokenURL(fileName,sheetName);
	}
		
		//CheckUsercaseOnExcel
	@Then("^I get the file \"([^\"]*)\" and I check usercase on sheet \"([^\"]*)\" and report to excel on row:$")
	public void i_get_the_file_and_I_check_usercase_on_sheet_and_report_to_excel_on_row(String fileName, String sheetName, DataTable data) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    support.CheckUsercaseOnExcel(fileName, sheetName, data);
	}

}

