package support.testAPI;

//import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.net.iharder.Base64;
import support.utils.ReadProperties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.json.Json;
import javax.json.JsonObject;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.bind.DatatypeConverter;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.junit.Assert;

public class ReadAPI {
	public static String streamStrGetAPI;
	public static String streamStrPostAPI;
	public static String params;
	public static String encoding="";
	
	public static String getAPI(String urlAPI)
			throws IOException, NoSuchAlgorithmException {
		String user = ReadProperties.getConfigSelenium("jiraUserName");
		String password = ReadProperties.getConfigSelenium("jiraPassword");
		urlAPI = urlAPI.replaceAll(" ", "%20");
		URL url = new URL(urlAPI);
		//String dataHmac[] = urlAPI.split("\\?");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		//System.out.println("[before to get authentication]");
		//System.out.println("[user: "+user+"]");
		//System.out.println("[password: "+password+"]");
		if (!user.equals("") || !password.equals("")) {
			String author = user + ":" + password;
			String encoding = Base64.encodeBytes(author.getBytes());
			//System.out.println("[encoding:"+encoding+"]");
			conn.setRequestProperty("Authorization", "Basic " + encoding);
			//System.out.println("[Get authentication]");
		}
		//System.out.println("[after to get authentication]");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Content-Type",
				"application/json;charset=UTF-8");
		Assert.assertTrue(" HTTP error code : " + conn.getResponseCode()
				+ "=> (" + url + ")", conn.getResponseCode() == 200);
		InputStream inputStream = conn.getInputStream();
		streamStrGetAPI = getStringFromInputStream(inputStream);
		return streamStrGetAPI;
	}
	
	public static void putAPI(String urlAPI, String params) throws IOException, NoSuchAlgorithmException, KeyManagementException {
		String user = ReadProperties.getConfigSelenium("jiraUserName");
		String password = ReadProperties.getConfigSelenium("jiraPassword");
		URL url = new URL(urlAPI);
		HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
		      public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
		        return true;
		      }
		});
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
			@Override
			public void checkClientTrusted(
					java.security.cert.X509Certificate[] arg0, String arg1)
					throws CertificateException {
				// TODO Auto-generated method stub
			}
			@Override
			public void checkServerTrusted(
					java.security.cert.X509Certificate[] arg0, String arg1)
					throws CertificateException {
			}
        } };
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("PUT");
		if (!user.equals("") && !password.equals("")) {
			String author = user + ":" + password;
			 encoding = Base64.encodeBytes(author.getBytes());
			conn.setRequestProperty("Authorization", "Basic " + encoding);
		}
		
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Content-Type",
				"application/json;charset=UTF-8");
		conn.getOutputStream().write(params.trim().getBytes());
		conn.setConnectTimeout(60000);
		conn.setReadTimeout(30000);
		conn.connect();
		Assert.assertTrue(" HTTP error code : " + conn.getResponseCode()
				+ "=> (" + url + ")", conn.getResponseCode() == 200 || conn.getResponseCode() == 201
				|| conn.getResponseCode() == 204);


	}
	
	public static String postAPI(String urlAPI, String params) throws IOException, NoSuchAlgorithmException, KeyManagementException {
		String user = ReadProperties.getConfigSelenium("jiraUserName");
		String password = ReadProperties.getConfigSelenium("jiraPassword");
		URL url = new URL(urlAPI);
		HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
		      public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
		        return true;
		      }
		});
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
			@Override
			public void checkClientTrusted(
					java.security.cert.X509Certificate[] arg0, String arg1)
					throws CertificateException {
				// TODO Auto-generated method stub
			}
			@Override
			public void checkServerTrusted(
					java.security.cert.X509Certificate[] arg0, String arg1)
					throws CertificateException {
			}
        } };
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		if (!user.equals("") && !password.equals("")) {
			String author = user + ":" + password;
			 encoding = Base64.encodeBytes(author.getBytes());
			conn.setRequestProperty("Authorization", "Basic " + encoding);
		}
		
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Content-Type",
				"application/json;charset=UTF-8");
		conn.getOutputStream().write(params.trim().getBytes());
		conn.setConnectTimeout(60000);
		conn.setReadTimeout(60000);
		conn.connect();
		Assert.assertTrue(" HTTP error code : " + conn.getResponseCode()
				+ "=> (" + url + ")", conn.getResponseCode() == 200 || conn.getResponseCode() == 201);

		InputStream inputStream = conn.getInputStream();
		streamStrPostAPI = getStringFromInputStream(inputStream);

		return streamStrPostAPI;

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

	public static void CreateUserCaseOnJira(String sumary,String assign) throws NoSuchAlgorithmException, KeyManagementException, IOException{
		
			String user = ReadProperties.getConfigSelenium("jiraUserName");
			String password = ReadProperties.getConfigSelenium("jiraPassword");
			URL jiraREST_URL = new URL(ReadProperties.getConfigSelenium("jiraURL")
					+ "/rest/api/2/issue");
			
			HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
			      public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
			        return true;
			      }
			});
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
	            public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
				@Override
				public void checkClientTrusted(
						java.security.cert.X509Certificate[] arg0, String arg1)
						throws CertificateException {
					// TODO Auto-generated method stub
				}
				@Override
				public void checkServerTrusted(
						java.security.cert.X509Certificate[] arg0, String arg1)
						throws CertificateException {
				}
	        } };
	        SSLContext sc = SSLContext.getInstance("SSL");
	        sc.init(null, trustAllCerts, new java.security.SecureRandom());
	        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HttpsURLConnection conn = (HttpsURLConnection) jiraREST_URL.openConnection();
			conn.setDoOutput(true);
			//conn.setRequestMethod("POST");
			if (!user.equals("") && !password.equals("")) {
				String author = user + ":" + password;
				 encoding = Base64.encodeBytes(author.getBytes());
				conn.setRequestProperty("Authorization", "Basic " + encoding);
			}
			System.out.println("jiraREST_URL: "+jiraREST_URL);
			System.out.println(getJSON_Epic(sumary, assign));
			conn.setRequestMethod("POST");
			
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
		
	}
	
	private static String getJSON_Epic(String sumaryAndUserCaseName,String assign) {
		JsonObject createIssue = Json
				.createObjectBuilder()
				.add("fields",
						Json.createObjectBuilder()
								.add("project",
										Json.createObjectBuilder().add(
												"key",
												ReadProperties
														.getConfigSelenium("projectKey")))
								.add("summary", sumaryAndUserCaseName)
								.add("issuetype",
										Json.createObjectBuilder()
												.add("name","User case"))
								.add("assignee",
										Json.createObjectBuilder().add("name",
												assign)))
				.build();

		return createIssue.toString();

	}
	
	public static String Hmac(String data) throws NoSuchAlgorithmException {
		final Charset charSet = Charset.forName("US-ASCII");
		final Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		String hash = "";
		final SecretKeySpec secret_key = new SecretKeySpec(charSet.encode(
				"zB/f1OL7upiFWSmiIaJSP/HtdQwIdc1B").array(), "HmacSHA256");
		try {
			sha256_HMAC.init(secret_key);
			byte[] bytes = sha256_HMAC.doFinal(data.trim().getBytes(
					StandardCharsets.US_ASCII));
			hash = Base64.encodeBytes(bytes);
			System.out.println(hash);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hash;
	}
}
