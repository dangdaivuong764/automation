package support.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.json.Json;
import javax.json.JsonObject;
import javax.xml.bind.DatatypeConverter;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.json.JSONArray;
import org.json.JSONObject;

public class PostTicketOnJira {

	/*
	 * Description this function: Nhan.Nguyen
	 * Automation QC: Phuong.Dang
	 * Descrition:
	 * 1. check on file JiraTicket.xcls to verify duplicated is use (Summary and Description)
	 * 2. when it have on excel, tool not create Jira
	 * 3. when it not have on excel,tool will
	 *    a. insert new bug to JiraTicket.xcls
	 *    b. create jira by Epic + summary and description ( will have image in need)
	 */
	public void CreateJira(String sumary,String description,String linkedIssue){
		try {
			File dir = new File(System.getProperty("user.dir"));
			String pathJiraTickets = dir.getPath() + File.separator + "src" + File.separator + "test" + File.separator
					+ "resources" + File.separator + "properties" + File.separator +"JiraTickets.xlsx";
			FileInputStream file = new FileInputStream(pathJiraTickets);
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet("JiraTickets");
			int countRow = sheet.getLastRowNum()+1;
			boolean found = true;
			for (int i = 1; i < countRow ; i++) {				
				int summaryColumn = 1;
				int descriptionColumn = 2;
				DataFormatter objDefaultFormat = new DataFormatter();
				FormulaEvaluator objFormulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);
				String summaryRow = objDefaultFormat.formatCellValue(sheet.getRow(i).getCell(summaryColumn), objFormulaEvaluator).trim();
				String descriptionRow = objDefaultFormat.formatCellValue(sheet.getRow(i).getCell(descriptionColumn), objFormulaEvaluator).trim();
				if(sumary.trim().equals(summaryRow) && description.trim().equals(descriptionRow)){
					found = false;
					break;
				}
			}
			if(found){
			URL jiraREST_URL = new URL(ReadProperties.get("jiraURL")
					+ "/rest/api/2/issue");
			URLConnection urlConnection = jiraREST_URL.openConnection();
			urlConnection.setDoInput(true);
			String author = ReadProperties.get("jiraUserName") + ":"
					+ ReadProperties.get("jiraPassword");
			String encoding = DatatypeConverter.printBase64Binary(author
					.getBytes("UTF-8"));
			HttpURLConnection conn = (HttpURLConnection) jiraREST_URL
					.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// String encodedData = URLEncoder.encode(getJSON(), "UTF-8");
			System.out.println(getJSON(sumary, description,linkedIssue));
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Basic " + encoding);
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type",
					"application/json;charset=UTF-8");
			// conn.setRequestProperty("Content-Length",
			// String.valueOf(encodedData.length()));
			conn.getOutputStream().write(getJSON(sumary, description,linkedIssue).getBytes());
			conn.setConnectTimeout(60000);
			conn.setReadTimeout(30000);
			conn.connect();

			// Send POST output.
			// DataOutputStream printout = new
			// DataOutputStream(conn.getOutputStream ());
			// printout.writeBytes(getJSON_Body());
			// printout.flush();
			// printout.close();
			if (conn.getResponseCode() == 201) {
				try {
					InputStream inputStream = conn.getInputStream();
					String streamStr = getStringFromInputStream(inputStream);
					JSONObject dataJira = new JSONObject(streamStr);
					String keyJira = dataJira.getString("key");
					conn.disconnect();
					System.out.println("Post ticket successfully!");					
					countRow = sheet.getLastRowNum() + 1;
						for (int i = 1; i <= countRow ; i++) {
							int keyColumn = 0;
							int summaryColumn = 1;
							int descriptionColumn = 2;
							DataFormatter objDefaultFormat = new DataFormatter();
							FormulaEvaluator objFormulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);
							String summaryRow = objDefaultFormat.formatCellValue(sheet.getRow(i).getCell(summaryColumn), objFormulaEvaluator).trim();
							String descriptionRow = objDefaultFormat.formatCellValue(sheet.getRow(i).getCell(descriptionColumn), objFormulaEvaluator).trim();
						if(summaryRow.equals("") && descriptionRow.equals("")){
							XSSFCell keyCell;
							XSSFCell summaryCell;
							XSSFCell descriptionCell;
							XSSFRow row;
							
							try {
								row = sheet.getRow(i);
								if (row == null) {
									row = sheet.createRow(i);
								}
								keyCell = row.getCell(keyColumn,Row.RETURN_BLANK_AS_NULL);
								summaryCell = row.getCell(summaryColumn, org.apache.poi.ss.usermodel.Row.RETURN_BLANK_AS_NULL);
								descriptionCell = row.getCell(descriptionColumn, org.apache.poi.ss.usermodel.Row.RETURN_BLANK_AS_NULL);
								if (keyCell == null || summaryCell == null || descriptionCell == null) {
									keyCell = row.createCell(keyColumn);
									keyCell.setCellValue(keyJira);
									summaryCell = row.createCell(summaryColumn);
									summaryCell.setCellValue(sumary);
									descriptionCell = row.createCell(descriptionColumn);
									descriptionCell.setCellValue(description);
								} else {
									keyCell.setCellValue(keyJira);
									summaryCell.setCellValue(sumary);
									descriptionCell.setCellValue(description);
								}
								// Constant variables Test Data path and Test Data file name
								FileOutputStream fileOut = new FileOutputStream(pathJiraTickets);
								workbook.write(fileOut);
								fileOut.flush();
								fileOut.close();
								break;
							} catch (Exception e) {
								throw (e);
							}
						}
					}
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			} else {
				System.out.println(conn.getResponseCode());
				Assert.assertTrue("Post jira fail. Please recheck data! ",false);
				conn.disconnect();
			}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static String getJSON(String sumary,String description,String linkedIssue) {
		JsonObject createIssue = null;
		if(ReadProperties.get("fixVersion").equals("") && linkedIssue.equals("")){
			createIssue= Json
					.createObjectBuilder()
					.add("fields",
							Json.createObjectBuilder()
									.add("project",
											Json.createObjectBuilder().add(
													"key",
													ReadProperties
															.get("projectKey")))
									.add("summary", sumary)
									.add("description", description)
									.add("issuetype",
											Json.createObjectBuilder()
													.add("name",
															ReadProperties
																	.get("issueType")))
									.add("priority",
											Json.createObjectBuilder().add("name",
													ReadProperties.get("priority")))
									.add("assignee",
											Json.createObjectBuilder().add("name",
													ReadProperties.get("assignee")))
									.add("labels",
											Json.createArrayBuilder()
													.add(ReadProperties
															.get("label_1"))
													.add(ReadProperties
															.get("label_2"))
													.add(ReadProperties
															.get("label_3"))))
					.build();
		}
		else if(ReadProperties.get("fixVersion").equals("")){
			createIssue= Json
			.createObjectBuilder()
			.add("fields",
					Json.createObjectBuilder()
							.add("project",
									Json.createObjectBuilder().add(
											"key",
											ReadProperties
													.get("projectKey")))
							.add("summary", sumary)
							.add("description", description)
							.add("customfield_10005", linkedIssue)
							.add("issuetype",
									Json.createObjectBuilder()
											.add("name",
													ReadProperties
															.get("issueType")))
							.add("priority",
									Json.createObjectBuilder().add("name",
											ReadProperties.get("priority")))
							.add("assignee",
									Json.createObjectBuilder().add("name",
											ReadProperties.get("assignee")))
							.add("labels",
									Json.createArrayBuilder()
											.add(ReadProperties
													.get("label_1"))
											.add(ReadProperties
													.get("label_2"))
											.add(ReadProperties
													.get("label_3"))))
			.build();
		}
		else if(linkedIssue.equals("")){
			createIssue= Json
					.createObjectBuilder()
					.add("fields",
							Json.createObjectBuilder()
									.add("project",
											Json.createObjectBuilder().add(
													"key",
													ReadProperties
															.get("projectKey")))
									.add("summary", sumary)
									.add("description", description)
									.add("fixVersions",Json.createArrayBuilder()
											.add(Json.createObjectBuilder().add("name",ReadProperties.get("fixVersion")))
											)
									.add("issuetype",
											Json.createObjectBuilder()
													.add("name",
															ReadProperties
																	.get("issueType")))
									.add("priority",
											Json.createObjectBuilder().add("name",
													ReadProperties.get("priority")))
									.add("assignee",
											Json.createObjectBuilder().add("name",
													ReadProperties.get("assignee")))
									.add("labels",
											Json.createArrayBuilder()
													.add(ReadProperties
															.get("label_1"))
													.add(ReadProperties
															.get("label_2"))
													.add(ReadProperties
															.get("label_3"))))
					.build();				
		}
		else{
			createIssue= Json
					.createObjectBuilder()
					.add("fields",
							Json.createObjectBuilder()
									.add("project",
											Json.createObjectBuilder().add(
													"key",
													ReadProperties
															.get("projectKey")))
									.add("summary", sumary)
									.add("description", description)	
									.add("fixVersions",Json.createArrayBuilder()
											.add(Json.createObjectBuilder().add("name",ReadProperties.get("fixVersion")))
											)
									.add("customfield_10005", linkedIssue)
									.add("issuetype",
											Json.createObjectBuilder()
													.add("name",
															ReadProperties
																	.get("issueType")))
									.add("priority",
											Json.createObjectBuilder().add("name",
													ReadProperties.get("priority")))
									.add("assignee",
											Json.createObjectBuilder().add("name",
													ReadProperties.get("assignee")))
									.add("labels",
											Json.createArrayBuilder()
													.add(ReadProperties
															.get("label_1"))
													.add(ReadProperties
															.get("label_2"))
													.add(ReadProperties
															.get("label_3"))))
					.build();
		}

		return createIssue.toString();

	}
	public void CreateEpicOnJira(String sumary,String assign){
		try {
			URL jiraREST_URL = new URL(ReadProperties.get("jiraURL")
					+ "/rest/api/2/issue");
			URLConnection urlConnection = jiraREST_URL.openConnection();
			urlConnection.setDoInput(true);
			String author = ReadProperties.get("jiraUserName") + ":"
					+ ReadProperties.get("jiraPassword");
			String encoding = DatatypeConverter.printBase64Binary(author
					.getBytes("UTF-8"));
			HttpURLConnection conn = (HttpURLConnection) jiraREST_URL
					.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			System.out.println(getJSON_Epic(sumary, assign));
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Basic " + encoding);
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type",
					"application/json;charset=UTF-8");
			conn.getOutputStream().write(getJSON_Epic(sumary, assign).getBytes());
			conn.setConnectTimeout(60000);
			conn.setReadTimeout(30000);
			conn.connect();
			if (conn.getResponseCode() == 201) {
				try {
					InputStream inputStream = conn.getInputStream();
					conn.disconnect();
					System.out.println("Post ticket successfully!");
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			} else {
				System.out.println(conn.getResponseCode());
				Assert.assertTrue("Post jira fail. Please recheck data! ",false);
				conn.disconnect();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static String getJSON_Epic(String sumaryAndEpicName,String assign) {
		JsonObject createIssue = Json
				.createObjectBuilder()
				.add("fields",
						Json.createObjectBuilder()
								.add("project",
										Json.createObjectBuilder().add(
												"key",
												ReadProperties
														.get("projectKey")))
								.add("summary", sumaryAndEpicName)
								.add("customfield_10002", sumaryAndEpicName)
								.add("customfield_10007", sumaryAndEpicName)
								.add("issuetype",
										Json.createObjectBuilder()
												.add("name","Epic"))
								.add("assignee",
										Json.createObjectBuilder().add("name",
												assign)))
				.build();

		return createIssue.toString();

	}

	public static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
}
