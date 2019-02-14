package support.testAPI;

import gherkin.deps.net.iharder.Base64;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.xml.bind.DatatypeConverter;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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

import support.datas.DataProvider;
import support.utils.ReadProperties;

public class CreateJiraIssue {
	static DataProvider data = new DataProvider();
	public static void main(String[] args) throws KeyManagementException,
			NoSuchAlgorithmException, IOException {
//		System.out.println(paramsCreateJira("", ""));
//		ReadAPI.postAPI(ReadProperties.getConfigSelenium("jiraURL")
//				+ "/rest/api/2/issue", paramsCreateJira("", ""), "phuong.dang",
//				"B4ophuon9@123");
		
		createTicketOnJira("phuong.dang", "B4ophuon9@123", "123", "1111111", data.getOutputScreenshot("Test.png"));
	}
	public static void createTicketOnJira(String userJira,String passJira,
			String summary,String description,String pathAttachment
			) throws KeyManagementException, NoSuchAlgorithmException, IOException{
		System.out.println( paramsCreateJira(summary, description));
		String body = ReadAPI.postAPI(ReadProperties.getConfigSelenium("jiraURL")
				+ "/rest/api/2/issue", paramsCreateJira(summary, description));
		
		JSONObject json = new JSONObject(body);
		String jiraID = json.getString("key");
		uploadAttachment(userJira,passJira,jiraID,pathAttachment);
		ReadAPI.postAPI(ReadProperties.getConfigSelenium("jiraURL")
				+ "/rest/api/2/issue/"+jiraID+"/comment", paramsCommentJira("ABCDEF"));
	}
	private static String paramsCreateJira(String sumary, String description) {
		return "{\"fields\":{\"project\":{\"key\":\""
				+ ReadProperties.getConfigSelenium("projectKey") + "\"}"
				+ ",\"summary\":\"" + sumary + "\",\"description\":\""
				+ description + "\"" + ",\"issuetype\":{\"name\":\""
				+ ReadProperties.getConfigSelenium("issueType") + "\"}"
				+ ",\"priority\":{\"name\":\""
				+ ReadProperties.getConfigSelenium("priority") + "\"}"
				+ ",\"assignee\":{\"name\":\""
				+ ReadProperties.getConfigSelenium("assignee") + "\"}"
				+ ",\"labels\":[\""
				+ ReadProperties.getConfigSelenium("label_1") + "\",\""
				+ ReadProperties.getConfigSelenium("label_2") + "\"" + ",\""
				+ ReadProperties.getConfigSelenium("label_3") + "\"]}}";
	}
	private static String paramsCommentJira(String comment) {
		return "{\"body\":\""+comment+"\"}";
	}

	public static void uploadAttachment(String username,String password,String jiraID,String file) throws ClientProtocolException, IOException {
		File f = new File(file);//data.getOutputScreenshot("Test.png")
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(ReadProperties.getConfigSelenium("jiraURL")
				+ "/rest/api/2/issue/"+jiraID+"/attachments");
		String author = username+":"+password;
		String encoding = Base64.encodeBytes(author.getBytes());
		post.setHeader("Authorization", "Basic " + encoding);
		post.setHeader("X-Atlassian-Token", "nocheck");
		HttpEntity reqEntity = MultipartEntityBuilder
				.create()
				.setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
				.addBinaryBody("file", new FileInputStream(f),
						ContentType.APPLICATION_OCTET_STREAM, f.getName())
				.build();
		post.setEntity(reqEntity);
		post.setHeader(reqEntity.getContentType());
		CloseableHttpResponse response = httpClient.execute(post);
	}

	private static String getJSON_Epic(String sumaryAndEpicName, String assign) {
		JsonObject createIssue = Json
				.createObjectBuilder()
				.add("fields",
						Json.createObjectBuilder()
								.add("project",
										Json.createObjectBuilder()
												.add("key",
														ReadProperties
																.getConfigSelenium("projectKey")))
								.add("summary", sumaryAndEpicName)
								.add("customfield_10002", sumaryAndEpicName)
								.add("customfield_10007", sumaryAndEpicName)
								.add("issuetype",
										Json.createObjectBuilder().add("name",
												"Epic"))
								.add("assignee",
										Json.createObjectBuilder().add("name",
												assign))).build();
		return createIssue.toString();
	}
}
