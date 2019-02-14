package support.testAPI;

import net.arnx.jsonic.JSON;
import support.utils.ReadProperties;

public class ParamsPostMethod {
	// 5.20 Login
	public static String ParamsLogin(String user, String password) {
		return "{" + "\"UserId\":\"" + user + "\"," + "\"Password\":\"" + password + "\"" + "}".toString();
	}

	// 7.1 Services list
	public static String ParamsServicesList(String owner, String propertyType) {
		return "{\"UserType\":\"" + owner + "\",\"PropertyType\":\"" + propertyType
				+ "\",\"Language\":\"en\"}".toString();
	}

	public static String Params() {
		return "{ \"username\" : \"api\", \"password\" : \"$724.15tqD$\",\"domain\" : \"sitecore\" }".toString();
	}

	/*
	 * create usercase on JIRA params: Project Key, Summary, Priority, assignee,
	 * labels, team, Status, Reporter, Description: Steps. Expected, Design
	 * Information
	 * 
	 */
	
	 /*public static String paramsCreateUserCase(String sumary, String description) { 
		 return "{\"fields\":{\"project\":{\"key\":\"" + ReadProperties.getConfigSelenium("projectKey") 
		 + "\"}" + ",\"summary\":\"" + sumary + "\",\"description\":\"" + description 
		 + "\"" + ",\"issuetype\":{\"name\":\"" + ReadProperties.getConfigSelenium("issueType")
		 + "\"}" + ",\"priority\":{\"name\":\"" + ReadProperties.getConfigSelenium("priority") 
		 + "\"}" + ",\"assignee\":{\"name\":\"" + ReadProperties.getConfigSelenium("assignee") 
		 + "\"}" + ",\"labels\":[\"" 
		 + ReadProperties.getConfigSelenium("label_1") +"\",\"" 
		 + ReadProperties.getConfigSelenium("label_2") + "\",\"" 
		 + ReadProperties.getConfigSelenium("label_3") + "\"]}}"; 
	 }*/
	 

	// NGAN
	public static String paramsCreateUserCase(String sumary, String description, String assignee, String priority,
			String label, String team, String teamId) {
		return "{\"fields\":{" + "\"project\":{\"key\":\"" + ReadProperties.getConfigSelenium("projectKey") + "\"}"
				+ ",\"summary\":\"" + sumary + "\"" + ",\"description\":\"" + description + "\""
				+ ",\"issuetype\":{\"name\":\"" + ReadProperties.getConfigSelenium("issueType") + "\"}"
				+ ",\"priority\":{\"name\":\"" + priority + "\"}" + ",\"assignee\":{\"name\":\"" + assignee + "\"}"
				+ ",\"customfield_10102\":[{\"self\":\"https://jira.sutrix.com/rest/api/2/customFieldOption/" + teamId
				+ "\",\"value\":\"" + team + "\",\"id\":\"" + teamId + "\"}]" 
				+ ",\"labels\":[\"" + label + "\",\""
				+ ReadProperties.getConfigSelenium("label_2") + "\"" + ",\""
				+ ReadProperties.getConfigSelenium("label_3") + "\"]}}";
	}

	public static String paramsUpdatedCustomField(String customfield) {
		return "";
	}

	// create usercases
	public static String paramsCreateUserCases(String sumary, String description) {
		return "{\"fields\":{\"project\":{\"key\":\"" + ReadProperties.getConfigSelenium("projectKey") + "\"}"
				+ ",\"summary\":\"" + sumary + "\",\"description\":\"" + description + "\""
				+ ",\"issuetype\":{\"name\":\"" + ReadProperties.getConfigSelenium("issueType") + "\"}"
				+ ",\"priority\":{\"name\":\"" + ReadProperties.getConfigSelenium("priority") + "\"}"
				+ ",\"assignee\":{\"name\":\"" + ReadProperties.getConfigSelenium("assignee") + "\"}"
				+ ",\"labels\":[\"" + ReadProperties.getConfigSelenium("label_1") + "\",\""
				+ ReadProperties.getConfigSelenium("label_2") + "\"" + ",\""
				+ ReadProperties.getConfigSelenium("label_3") + "\"]}}";
	}
}
