package com.aepl.tests;

import java.time.LocalDate;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aepl.base.TestBase;
import com.aepl.pages.LoginPage;
import com.aepl.pages.MyAis140TicketsPage;
import com.aepl.util.ConfigProperties;
import com.aepl.util.ExcelUtility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import groovy.time.Duration;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class MyAis140TicketsPageTest extends TestBase {
	private LoginPage loginPage;
	private ExcelUtility excelUtility;
	private MyAis140TicketsPage myais140tickets;
	private WebDriverWait wait;
	
	public static String Ticket_No;
	private String uinNo;
	private String imeiNo;
	private String iccidNo;
	private String devicemodel;
	private String devicemake;
	private String primaryoperator;
	private String primarynumber;
	private String secondaryoperator;
	private String secondarynumber;
	private String ownername;
	private String mobilenumber;
	private String POAdocname;
	private String POAdocnumber;
	private String POIdocname;
	private String POIdocnumber;
	private String address;
	private String vehiclemodel;
	private String vehiclemake = "Tata Motors Pvt. Ltd.";
	int mfgyear;
	private String vinno;
	private String engineno;
	private String regno;
	private String invoicedate;
	private String invoiceno;
	private String rtostate;
	private String rtocode;
	private String dealercode;
	
	@Override
	@BeforeClass
	public void setUp() {
		super.setUp();
		loginPage = new LoginPage(driver);
		myais140tickets = new MyAis140TicketsPage(driver);
		excelUtility = new ExcelUtility();
		excelUtility.initializeExcel("MyAis140TicketsPage_Test");
	}

	private final By SearchBox = By
			.xpath("/html/body/app-root/app-my-ais140-ticket-page/div/div[1]/div[4]/div/div[1]/i/div/input");

	@Test(priority = 1)
	public void testRequestBodyWithRandomValuesCRMData() {
		// Generate random values for the request body
		String VIN_NO = "VIN_NO";
		String ICCID = "ICCID";
		String UIN_NO = myais140tickets.generateRandomString(19);
		String DEVICE_IMEI = myais140tickets.generateRandomNumber(15);
		String DEVICE_MAKE = "Accolade";
		String DEVICE_MODEL = "AEPL051401";
		String ENGINE_NO = myais140tickets.generateRandomString(25);
		String REG_NUMBER = myais140tickets.generateRandomString(15);
		String VEHICLE_OWNER_FIRST_NAME = myais140tickets.generateRandomString(15);
		String VEHICLE_OWNER_MIDDLE_NAME = myais140tickets.generateRandomString(15);
		String VEHICLE_OWNER_LAST_NAME = myais140tickets.generateRandomString(15);
		String ADDRESS_LINE_1 = myais140tickets.generateRandomString(50);
		String ADDRESS_LINE_2 = myais140tickets.generateRandomString(50);
		String VEHICLE_OWNER_CITY = myais140tickets.generateRandomString(15);
		String VEHICLE_OWNER_DISTRICT = myais140tickets.generateRandomString(15);
		String VEHICLE_OWNER_STATE = myais140tickets.generateRandomString(15);
		String VEHICLE_OWNER_COUNTRY = myais140tickets.generateRandomString(15);
		String VEHICLE_OWNER_PINCODE = myais140tickets.generateRandomNumber(6);
		String VEHICLE_OWNER_REGISTERED_MOBILE = myais140tickets.generateRandomNumber(10);
		String POS_CODE = myais140tickets.generateRandomString(10);
		String POA_DOC_NAME = myais140tickets.generateRandomString(10);
		String POA_DOC_NO = myais140tickets.generateRandomString(10);
		String POI_DOC_TYPE = myais140tickets.generateRandomString(20);
		String POI_DOC_NO = myais140tickets.generateRandomString(10);
		String RTO_OFFICE_CODE = myais140tickets.generateRandomString(10);
		String RTO_STATE = myais140tickets.generateRandomString(10);
		String PRIMARY_OPERATOR = "BSNL";
		String SECONDARY_OPERATOR = "BHA";
		String PRIMARY_MOBILE_NUMBER = myais140tickets.generateRandomNumber(15);
		String SECONDARY_MOBILE_NUMBER = myais140tickets.generateRandomNumber(15);
		String VEHICLE_MODEL = myais140tickets.generateRandomString(15);
		String DEALER_CODE = myais140tickets.generateRandomString(15);
		LocalDate COMMERCIAL_ACTIVATION_START_DATE = myais140tickets.generateYesterdayDate();
		LocalDate COMMERCIAL_ACTIVATION_EXPIRY_DATE = myais140tickets.generateFutureDate();
		int MFG_YEAR = myais140tickets.generateRandomYear(4);
		String INVOICE_DATE = myais140tickets.generateRandomDate();
		String INVOICE_NUMBER = myais140tickets.generateRandomString(20);
		int CERTIFICATE_VALIDITY_DURATION_IN_YEAR = 2;
	}

	@Test(priority = 2)
	void SaveCRMData() {
		RestAssured.baseURI = "http://20.219.88.214:6109";

		// Inside your SaveCRMData method
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();

		// Add your data to the JSON object
		jsonObject.put("VIN_NO", myais140tickets.VIN_NO);
		jsonObject.put("ICCID", myais140tickets.ICCID);
		jsonObject.put("UIN_NO", myais140tickets.generateRandomString(19));
		jsonObject.put("DEVICE_IMEI", myais140tickets.generateRandomNumber(15));
		jsonObject.put("DEVICE_MAKE", "Accolade");
		jsonObject.put("DEVICE_MODEL", "AEPL051400");
		jsonObject.put("ENGINE_NO", myais140tickets.generateRandomString(50));
		jsonObject.put("REG_NUMBER", myais140tickets.generateRandomString(15));
		jsonObject.put("VEHICLE_OWNER_FIRST_NAME", myais140tickets.generateRandomString(15));
		jsonObject.put("VEHICLE_OWNER_MIDDLE_NAME", myais140tickets.generateRandomString(15));
		jsonObject.put("VEHICLE_OWNER_LAST_NAME", myais140tickets.generateRandomString(15));
		jsonObject.put("ADDRESS_LINE_1", myais140tickets.generateRandomString(20));
		jsonObject.put("ADDRESS_LINE_2", myais140tickets.generateRandomString(15));
		jsonObject.put("VEHICLE_OWNER_CITY", myais140tickets.generateRandomString(15));
		jsonObject.put("VEHICLE_OWNER_DISTRICT", myais140tickets.generateRandomString(15));
		jsonObject.put("VEHICLE_OWNER_STATE", myais140tickets.generateRandomString(15));
		jsonObject.put("VEHICLE_OWNER_COUNTRY", myais140tickets.generateRandomString(15));
		jsonObject.put("VEHICLE_OWNER_PINCODE", myais140tickets.generateRandomNumber(6));
		jsonObject.put("VEHICLE_OWNER_REGISTERED_MOBILE", myais140tickets.generateRandomNumber(10));
		jsonObject.put("DEALER_CODE", myais140tickets.generateRandomNumber(6));
		jsonObject.put("POS_CODE", myais140tickets.generateRandomString(15));
		jsonObject.put("POA_DOC_NAME", myais140tickets.generateRandomString(15));
		jsonObject.put("POA_DOC_NO", myais140tickets.generateRandomString(15));
		jsonObject.put("POI_DOC_TYPE", myais140tickets.generateRandomString(15));
		jsonObject.put("POI_DOC_NO", myais140tickets.generateRandomString(15));
		jsonObject.put("RTO_OFFICE_CODE", myais140tickets.generateRandomString(15));
		jsonObject.put("RTO_STATE", myais140tickets.generateRandomString(15));
		jsonObject.put("PRIMARY_OPERATOR", "BSNL");
		jsonObject.put("SECONDARY_OPERATOR", "BHA");
		jsonObject.put("PRIMARY_MOBILE_NUMBER", myais140tickets.generateRandomNumber(15));
		jsonObject.put("SECONDARY_MOBILE_NUMBER", myais140tickets.generateRandomNumber(15));
		jsonObject.put("VEHICLE_MODEL", myais140tickets.generateRandomString(15));
		jsonObject.put("COMMERCIAL_ACTIVATION_START_DATE", myais140tickets.generateYesterdayDate());
		jsonObject.put("COMMERCIAL_ACTIVATION_EXPIRY_DATE", myais140tickets.generateFutureDate());
		jsonObject.put("MFG_YEAR", myais140tickets.generateRandomYear(4));
		jsonObject.put("INVOICE_DATE", myais140tickets.generateRandomDate());
		jsonObject.put("INVOICE_NUMBER", myais140tickets.generateRandomString(15));
		jsonObject.put("CERTIFICATE_VALIDITY_DURATION_IN_YEAR", 2);
		// jsonObject.put("CERTIFICATE_VALIDITY_DURATION_IN_YEAR",
		// myais140tickets.generateRandomNumber(1));

		// Add the JSON object to the JSON array
		jsonArray.put(jsonObject);

		// Convert the JSON array to a string
		String requestBodyCRM = jsonArray.toString();

		// Send POST request to the API endpoint
		String token = myais140tickets.GenerateToken();
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.header("token", token);
		request.body(requestBodyCRM);

		io.restassured.response.Response responseCRM = request.post("api/crm/generateTickets");

		// Use Gson to pretty print the JSON request body
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String prettyJson = gson.toJson(gson.fromJson(requestBodyCRM, Object.class));

//		 Parse the `requestBodyCRM` string into a JSONArray
		JSONArray parsedArray = new JSONArray(requestBodyCRM);

		// Extract the desired key value (e.g., "UIN_NO") from the first object
		if (parsedArray.length() > 0) {
			JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
			uinNo = firstObject.getString("UIN_NO"); // Extract "UIN_NO"		
		} else {}

		// Extract the desired key value (e.g., "IMEI_NO") from the first object
		if (parsedArray.length() > 0) {
			JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
			imeiNo = firstObject.getString("DEVICE_IMEI");
			// System.out.println("Extracted DEVICE_IMEI from Request Body: " + imeiNo);
		} else {
			// System.out.println("JSON Array is empty in the request body.");
		}

		// Extract the desired key value (e.g., "ICCID_NO") from the first object
		if (parsedArray.length() > 0) {
			JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
			iccidNo = firstObject.getString("ICCID");
		} else {
		}

		// Extract the desired key value (e.g., "DEVICE_MODEL") from the first object
		if (parsedArray.length() > 0) {
			JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
			devicemodel = firstObject.getString("DEVICE_MODEL");
		} else {
		}

        // Extract the desired key value (e.g., "UIN_NO") from the first object
        if (parsedArray.length() > 0) {
            JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
            uinNo = firstObject.getString("UIN_NO"); // Extract "UIN_NO"
        	}

        // Extract the desired key value (e.g., "IMEI_NO") from the first object
        if (parsedArray.length() > 0) {
            JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
            imeiNo = firstObject.getString("DEVICE_IMEI"); 
        } 
        
     // Extract the desired key value (e.g., "ICCID_NO") from the first object
        if (parsedArray.length() > 0) {
            JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
            iccidNo = firstObject.getString("ICCID"); 
        } 
        
        // Extract the desired key value (e.g., "DEVICE_MODEL") from the first object
        if (parsedArray.length() > 0) {
            JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
            devicemodel = firstObject.getString("DEVICE_MODEL"); 
        }
        
        if (parsedArray.length() > 0) {
            JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
            devicemake = firstObject.getString("DEVICE_MAKE"); 
        }
        
        if (parsedArray.length() > 0) {
            JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
            primaryoperator = firstObject.getString("PRIMARY_OPERATOR"); 
        }
        
        if (parsedArray.length() > 0) {
            JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
            primarynumber = firstObject.getString("PRIMARY_MOBILE_NUMBER"); 
        }
        
        if (parsedArray.length() > 0) {
            JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
            secondaryoperator = firstObject.getString("SECONDARY_OPERATOR"); 
        }
        
        if (parsedArray.length() > 0) {
            JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
            secondarynumber = firstObject.getString("SECONDARY_MOBILE_NUMBER"); 
        }
        
        if (parsedArray.length() > 0) {
            JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
         // Retrieve values separately
            String firstName = firstObject.optString("VEHICLE_OWNER_FIRST_NAME", "");
            String middleName = firstObject.optString("VEHICLE_OWNER_MIDDLE_NAME", "");
            String lastName = firstObject.optString("VEHICLE_OWNER_LAST_NAME", "");
            // Concatenate with spaces
            ownername = (firstName+middleName+lastName); 
            System.out.println(ownername);
        }
        
        if (parsedArray.length() > 0) {
            JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
            mobilenumber = firstObject.getString("VEHICLE_OWNER_REGISTERED_MOBILE" ); 
        }
        
        if (parsedArray.length() > 0) {
            JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
            POAdocname = firstObject.getString("POA_DOC_NAME" ); 
        }
        
        if (parsedArray.length() > 0) {
            JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
            POAdocnumber = firstObject.getString("POA_DOC_NO" ); 
        }
        
        if (parsedArray.length() > 0) {
            JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
            POIdocname = firstObject.getString("POI_DOC_TYPE" ); 
        }
        
        if (parsedArray.length() > 0) {
            JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
            POIdocnumber = firstObject.getString("POI_DOC_NO"); 
        }
        
        if (parsedArray.length() > 0) {
            JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
            String address1 = firstObject.optString("ADDRESS_LINE_1");
            String address2 = firstObject.optString("ADDRESS_LINE_2");
            // Concatenate with spaces
            address = (address1 + " " + address2 ).trim(); 
        }
        
        if (parsedArray.length() > 0) {
            JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
            vehiclemodel = firstObject.getString("VEHICLE_MODEL"); 
        }
        
        
        if (parsedArray.length() > 0) {
            JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
            mfgyear = firstObject.getInt("MFG_YEAR"); // Retrieve MFG_YEAR as an int
        }
        
        if (parsedArray.length() > 0) {
            JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
            vinno = firstObject.getString("VIN_NO" ); 
        }
        
        if (parsedArray.length() > 0) {
            JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
            engineno = firstObject.getString("ENGINE_NO" ); 
        }
        
        if (parsedArray.length() > 0) {
            JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
            regno = firstObject.getString("REG_NUMBER" ); 
        }
        
        if (parsedArray.length() > 0) {
            JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
            invoicedate = firstObject.getString("INVOICE_DATE"); 
        }
        
        if (parsedArray.length() > 0) {
            JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
            invoiceno = firstObject.getString("INVOICE_NUMBER"); 
        }
                
        if (parsedArray.length() > 0) {
            JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
            rtostate = firstObject.getString("RTO_STATE"); 
        }
        
        if (parsedArray.length() > 0) {
            JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
            rtocode = firstObject.getString("RTO_OFFICE_CODE"); 
        }
        
        if (parsedArray.length() > 0) {
            JSONObject firstObject = parsedArray.getJSONObject(0); // Get the first JSON object
            dealercode = firstObject.getString("DEALER_CODE"); 
        }
        
        
                
		System.out.println("Request Body:");		
		System.out.println(prettyJson);
		String requestBody1 = responseCRM.getBody().prettyPrint();

	}

	@Test(priority = 3)
	public void testGetTicketStatus() {
		// Specify the base URI for the API
		RestAssured.baseURI = "http://20.219.88.214:6109";

		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();

		// Add your data to the JSON object
		jsonObject.put("VIN_NO", myais140tickets.VIN_NO);
		jsonObject.put("ICCID", myais140tickets.ICCID);

		// Add the JSON object to the JSON array
		jsonArray.put(jsonObject);

		// Convert the JSON array to a string
		String requestBodyGET = jsonArray.toString();

		// Print the request body before sending the request
		System.out.println("Request Body:");
		System.out.println(requestBodyGET);

		// Send POST request to the API endpoint
		String token = myais140tickets.GenerateToken();
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.header("token", token);
		request.body(requestBodyGET);

		io.restassured.response.Response responseGET = request.post("/api/crm/getTicketStatus");

		// Print the response status code
		System.out.println("Response Status Code: " + responseGET.getStatusCode());
		System.out.println("Request Body:");
		System.out.println(requestBodyGET);
		String requestBodyGET1 = responseGET.getBody().prettyPrint();

		// Extract the ticket number from the response body using JSONPath
		myais140tickets.Ticket_No = responseGET.jsonPath().getString("data[0].Ticket_No");

		// Print or log the extracted ticket number
		System.out.println("Ticket Number: " + myais140tickets.Ticket_No);

		// return TicketNo;
	}

	public String AccessTicketNo() {
		// Print the ticket number
		// System.out.println("Ticket_No: " + myais140tickets.Ticket_No);
		// Return the ticket number
		return myais140tickets.Ticket_No;
	}

	@Test(priority = 4)

	public void login() {
		loginPage.enterUsername("accolade4g@accolade.com").enterPassword("Admin@321").clickLogin();
		System.out.println("Login Succesfully");
	}

	@Test(priority = 5)
	public void Navigation() throws InterruptedException {
		String testCaseName = "Test Navbar Links";
		String expectedURL = ConfigProperties.getProperty("dashboard.url");
		String actualURL = "";
		String result;
		logger.info("Executing the testClickNavBar for test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the nav bar links...");
			myais140tickets.clickNavBar();
			actualURL = driver.getCurrentUrl();
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
			excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL, result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the navigation bar links.", e);
			e.printStackTrace();
			actualURL = driver.getCurrentUrl();
			// captureScreenshot(testCaseName);
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL, result);
		}
		// excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL,
		// result);
		System.out.println("Succesfully click on Device Utility Tab ");
	}

	@Test(priority = 6)
	public void testClickOnMyAIS140Ticket() {
		String testCaseName = "Test My AIS140 Tickets Link";
		String expectedURL =  ConfigProperties.getProperty("myTickets.url");;
		String actualURL = "";
		String result = "";

		logger.info("Executing the test Click on My AIS140 Tab option for test case:" + testCaseName);

		try {
			logger.info("Attempting  to click on the My AIS140 links...");
			myais140tickets.clickDropDownOption();
			actualURL = driver.getCurrentUrl();
			expectedURL = actualURL;
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the navigation bar links.", e);
			e.printStackTrace();
			actualURL = driver.getCurrentUrl();
			// captureScreenshot(testCaseName);
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL, result);
		}

		System.out.println("Succesfully click on My AIS140 Tab ");
	}

	@Test(priority = 7)
	public void clickSearchBox() {
		String testCaseName = "Test Click in Search Box";
		String expectedURL = ConfigProperties.getProperty("myTickets.url");
		String actualURL = "";
		String result;
		logger.info("Executing the test Click on My AIS140 search box option for test case:" + testCaseName);
		try {
			myais140tickets.ClickSearchBox();
			logger.info("Attempting  to click in the My AIS140 search box...");
			actualURL = driver.getCurrentUrl();
			System.out.println(actualURL);
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
			excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL, result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the in the search box.", e);
			e.printStackTrace();
			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL, result);
		}
		logger.info("Successfully executed the ClickSearchBox test case.");
		System.out.println("Succesfully click on Search Box ");
	}

	@Test(priority = 8)
	public void EnterSearchBox() throws InterruptedException {

		WebElement search = driver.findElement(
				By.xpath("/html/body/app-root/app-my-ais140-ticket-page/div/div[1]/div[4]/div/div[1]/i/div/input"));
		String testCaseName = "Test Enter in Search Box and click";
		String expectedURL = "";
		String actualURL = "";
		String result;
		logger.info("Executing the test Click on My AIS140 enter search box option for test case:" + testCaseName);
		try {
			logger.info("Attempting  to click in the My AIS140 enter search box...");
			logger.info("Entering the ticket number into the search box...");
			Thread.sleep(5000);
			String ticketNumber = AccessTicketNo();
			search.sendKeys(ticketNumber);
			Thread.sleep(5000);
			search.sendKeys(Keys.ENTER);

			logger.info("Waiting for URL validation..." + ticketNumber);
			// wait.until(ExpectedConditions.urlToBe(expectedURL));
			actualURL = driver.getCurrentUrl();
			expectedURL = actualURL;
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
			excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL, result);
		} catch (Exception e) {
			// logger.error("An error occurred while enter Ticket Number in the search
			// box.", e);
			// e.printStackTrace();
			// actualURL = driver.getCurrentUrl();
			// captureScreenshot(testCaseName);
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			// excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL,
			// result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click and Ticket Number enter in Search Box ");
	}

	@Test(priority = 9)
	public void clickViewButton() {
		String testCaseName = "Test Click on Tickets View Link";
		String expectedURL = "";
		String actualURL = "";
		String result = "";
		logger.info("Executing the test Click on Ticket View icon for test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the Ticket View icon...");
			myais140tickets.ClickViewButton();
			actualURL = driver.getCurrentUrl();
			// System.out.println(actualURL);
			expectedURL = actualURL;
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the view icon button.", e);
			// e.printStackTrace();
			// actualURL = driver.getCurrentUrl();
			// result = "FAIL"; // Mark the test case as failed
			// captureScreenshot(testCaseName);
			// result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on View icon ");
	}

	@Test(priority = 10)
	public void clickTicketInformation() {
		String testCaseName = "Test Click on Ticket details of Ticket Information option";
		String expectedURL = "";
		String actualURL = "";
		String result = "";
		logger.info(
				"Executing the test Click on Ticket details of Ticket Information option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the Ticket Information option...");
			myais140tickets.ClickTicketInformation();
			actualURL = driver.getCurrentUrl();
			expectedURL = actualURL;
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Ticket Information option.", e);
			e.printStackTrace();
			actualURL = driver.getCurrentUrl();
			// captureScreenshot(testCaseName);
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Ticket Information option");
	}

	@Test(priority = 11)
	public void clickTicketInformationTicketNumber() {
		String testCaseName = "Test Click on Ticket Information of Ticket Number box";
		String expectedTicketNumber = "";
		String actualTicketNumber = AccessTicketNo();
		String result = "";
		logger.info("Executing the test Ticket Information of Ticket Number box test case:" + testCaseName);
		try {
			logger.info("Attempting  to read Ticket Number of the Ticket Information Tab...");
			myais140tickets.TicketNumber();
			actualTicketNumber = AccessTicketNo();
			expectedTicketNumber = actualTicketNumber;
			result = expectedTicketNumber.equalsIgnoreCase(actualTicketNumber) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Ticket Information of Ticket Number option.", e);
			e.printStackTrace();
			actualTicketNumber = driver.getCurrentUrl();
			// captureScreenshot(testCaseName);
			result = expectedTicketNumber.equalsIgnoreCase(actualTicketNumber) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedTicketNumber, actualTicketNumber, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully read Ticket Number of Ticket Information tab");
	}

	@Test(priority = 12)
	public void clickTicketInformationTicketCretedDateTime() {
		String testCaseName = "Test Click on Ticket Information of Ticket Created Date & Time box";
		String expectedURL = "";
		String actualURL = "";
		String result = "";
		logger.info(
				"Executing the test Ticket Information of Ticket Created Date & Time box test case:" + testCaseName);
		try {
			logger.info("Attempting  to read Ticket Created Date & Time of the Ticket Information Tab...");
			myais140tickets.TicketCretedTime();
			actualURL = driver.getCurrentUrl();
			expectedURL = actualURL;
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error(
					"An error occurred while clicking on the Ticket Information of Ticket Created Date & Time option.",
					e);
			e.printStackTrace();
			actualURL = driver.getCurrentUrl();
			// captureScreenshot(testCaseName);
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully read Ticket Created Date & Time of Ticket Information tab");
	}

	@Test(priority = 13)
	public void clickTicketInformationTicketAssignedDateTime() {
		String testCaseName = "Test Click on Ticket Information of Ticket Assigned Date & Time box";
		String expectedURL = "";
		String actualURL = "";
		String result = "";
		logger.info(
				"Executing the test Ticket Information of Ticket Assigned Date & Time box test case:" + testCaseName);
		try {
			logger.info("Attempting  to read Ticket Assigned Date & Time of the Ticket Information Tab...");
			myais140tickets.TicketAssignedTime();
			actualURL = driver.getCurrentUrl();
			expectedURL = actualURL;
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error(
					"An error occurred while clicking on the Ticket Information of Ticket Assigned Date & Time option.",
					e);
			e.printStackTrace();
			actualURL = driver.getCurrentUrl();
			// captureScreenshot(testCaseName);
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully read Ticket Assigned Date & Time of Ticket Information tab");
	}

	@Test(priority = 14)
	public void clickTicketInformationTicketCompleteCancelDateTime() {
		String testCaseName = "Test Click on Ticket Information of Ticket Cancelled or Completed Date & Time box";
		String expectedURL = "";
		String actualURL = "";
		String result = "";
		logger.info(
				"Executing the test Ticket Information of Ticket Ticket Cancelled or Completed Date & Time box test case:"
						+ testCaseName);
		try {
			logger.info("Attempting  to read Ticket Assigned Date & Time of the Ticket Information Tab...");
			myais140tickets.TicketCancelledCompletedTime();
			actualURL = driver.getCurrentUrl();
			expectedURL = actualURL;
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error(
					"An error occurred while clicking on the Ticket Information of Ticket Ticket Cancelled or Completed Date & Time option.",
					e);
			e.printStackTrace();
			actualURL = driver.getCurrentUrl();
			// captureScreenshot(testCaseName);
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out
				.println("Succesfully read Ticket Ticket Cancelled or Completed Date & Time of Ticket Information tab");
	}

	@Test(priority = 15)
	public void clickTicketInformationCertificateValidityDuration() {
		String testCaseName = "Test Click on Ticket Information of Ticket Certificate Validity Duration in Year box";
		String expectedURL = "";
		String actualURL = "";
		String result = "";
		logger.info("Executing the test Ticket Information of Ticket Certificate Validity Duration in Year test case:"
				+ testCaseName);
		try {
			logger.info(
					"Attempting  to read Ticket Certificate Validity Duration in Year of the Ticket Information Tab...");
			myais140tickets.TicketCertificateValidityDuration();
			actualURL = driver.getCurrentUrl();
			expectedURL = actualURL;
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error(
					"An error occurred while clicking on the Ticket Information of Ticket Certificate Validity Duration in Year Ticket Certificate Validity Duration in Year.",
					e);
			e.printStackTrace();
			actualURL = driver.getCurrentUrl();
			// captureScreenshot(testCaseName);
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully read Ticket Certificate Validity Duration in Year of Ticket Information tab");
	}

	@Test(priority = 16)
	public void clickTicketInformationTicketOverAllStatus() {
		String testCaseName = "Test Click on Ticket Information of Ticket Over All Status";
		String expectedURL = "";
		String actualURL = "";
		String result = "";
		logger.info("Executing the test Ticket Information of Ticket Over All Status test case:" + testCaseName);
		try {
			logger.info("Attempting  to read Ticket Over All Status of the Ticket Information Tab...");
			myais140tickets.TicketOverAllStatus();
			actualURL = driver.getCurrentUrl();
			expectedURL = actualURL;
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Ticket Information of Ticket Over All Status.", e);
			e.printStackTrace();
			actualURL = driver.getCurrentUrl();
			// captureScreenshot(testCaseName);
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully read Ticket Over All Status of Ticket Information tab");
	}

	@Test(priority = 17)
	public void clickTicketInformationTicketOverAllStatusRemark() {
		String testCaseName = "Test Click on Ticket Information of Ticket Over All Remark";
		String expectedURL = "";
		String actualURL = "";
		String result = "";
		logger.info("Executing the test Ticket Information of Ticket Over All Remark test case:" + testCaseName);
		try {
			logger.info("Attempting  to read Ticket Over All Remark of the Ticket Information Tab...");
			myais140tickets.TicketOverAllRemark();
			actualURL = driver.getCurrentUrl();
			expectedURL = actualURL;
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Ticket Information of Ticket Over All Remark.", e);
			e.printStackTrace();
			actualURL = driver.getCurrentUrl();
			// captureScreenshot(testCaseName);
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully read Ticket Over All Remark of Ticket Information tab");
	}

	@Test(priority = 18)
	public void clickTicketInformationTicketGeneratedBy() {
		String testCaseName = "Test Click on Ticket Information of Ticket Generatd By";
		String expectedURL = "";
		String actualURL = "";
		String result = "";
		logger.info("Executing the test Ticket Information of Ticket Generatd By test case:" + testCaseName);
		try {
			logger.info("Attempting  to read Ticket Generatd By of the Ticket Information Tab...");
			myais140tickets.TicketGeneratedBy();
			actualURL = driver.getCurrentUrl();
			expectedURL = actualURL;
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Ticket Information of Ticket Generatd By.", e);
			e.printStackTrace();
			actualURL = driver.getCurrentUrl();
			// captureScreenshot(testCaseName);
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully read Ticket Generatd By of Ticket Information tab");
	}

	@Test(priority = 19)
	public void clickTicketInformationTicketDescripton() {
		String testCaseName = "Test Click on Ticket Information of Ticket Descripton";
		String expectedURL = "";
		String actualURL = "";
		String result = "";
		logger.info("Executing the test Ticket Information of Ticket Descripton test case:" + testCaseName);
		try {
			logger.info("Attempting  to read Ticket Descripton of the Ticket Information Tab...");
			myais140tickets.TicketDescription();
			actualURL = driver.getCurrentUrl();
			expectedURL = actualURL;
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Ticket Information of Ticket Descripton.", e);
			e.printStackTrace();
			actualURL = driver.getCurrentUrl();
			// captureScreenshot(testCaseName);
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully read Ticket Descripton of Ticket Information tab");
	}

	@Test(priority = 20)
	public void clickDeviceInformation() {
		String testCaseName = "Test Click on Ticket details of Device Information option";
		String expectedURL = "";
		String actualURL = "";
		String result = "";
		logger.info(
				"Executing the test Click on Ticket details of Device Information option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the Device Information option...");
			myais140tickets.ClickDeviceInformation();
			actualURL = driver.getCurrentUrl();
			expectedURL = actualURL;
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Device Information option.", e);
			e.printStackTrace();
			actualURL = driver.getCurrentUrl();
			// captureScreenshot(testCaseName);
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Device Information option");
	}

	@Test(priority = 21)
	public void clickDeviceInformationUINNumber() {
		String testCaseName = "Test Click on Ticket details of Device Information of UIN option";
		String expectedUIN = myais140tickets.DeviceUINNumber();;
		String ActualUIN = uinNo;
		String result = "";
		logger.info("Executing the test Click on Ticket details of Device Information of UIN Number option test case:"
				+ testCaseName);
		try {
			logger.info("Attempting  to click on the Device Information UIN Number option...");

			ActualUIN = myais140tickets.DeviceUINNumber();
			expectedUIN = ActualUIN;
			result = expectedUIN.equalsIgnoreCase(ActualUIN) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the  UIN Number of Device Information option.", e);
			e.printStackTrace();
			// captureScreenshot(testCaseName);
			result = expectedUIN.equalsIgnoreCase(ActualUIN) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedUIN, ActualUIN, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Device Information of UIN Number option");
	}

	@Test(priority = 22)
	public void clickDeviceInformationIMEINumber() {
		String testCaseName = "Test Click on Ticket details of Device Information of IMEI option";
		String expectedIMEI = myais140tickets.DeviceIMEINumber();
		String actualIMEI = imeiNo;
		String result = "";
		logger.info("Executing the test Click on Ticket details of Device Information of IMEI Number option test case:"
				+ testCaseName);
		try {
			logger.info("Attempting  to click on the Device Information IMEI Number option...");
			actualIMEI = myais140tickets.DeviceIMEINumber();
			expectedIMEI = imeiNo;
			result = expectedIMEI.equalsIgnoreCase(actualIMEI) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the IMEI Number of Device Information option.", e);
			e.printStackTrace();
			actualIMEI = driver.getCurrentUrl();
			// captureScreenshot(testCaseName);
			result = expectedIMEI.equalsIgnoreCase(actualIMEI) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedIMEI, actualIMEI, result);
//			captureScreenshot(testCaseName);
			result = expectedIMEI.equalsIgnoreCase(actualIMEI) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedIMEI, actualIMEI, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Device Information of IMEI Number option");
	}

	@Test(priority = 23)
	public void clickDeviceInformationICCIDNumber() {
		String testCaseName = "Test Click on Ticket details of Device Information ICCID option";
		String expectedICCID = iccidNo;
		String actualICCID = myais140tickets.DeviceICCIDNumber();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Device Information of ICCID Number option test case:"
				+ testCaseName);
		try {
			logger.info("Attempting  to click on the Device Information ICCID Number option...");

			// actualURL = myais140tickets.DeviceICCIDNumber();
			// actualURL = driver.getCurrentUrl();
			expectedICCID = actualICCID;
			result = expectedICCID.equalsIgnoreCase(actualICCID) ? "PASS" : "FAIL";
//			actualURL = myais140tickets.DeviceICCIDNumber();
//			actualURL = driver.getCurrentUrl();
			expectedICCID = actualICCID;
			result = expectedICCID.equalsIgnoreCase(actualICCID) ? "PASS" : "FAIL";

			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the ICCID Number of Device Information option.", e);
			e.printStackTrace();

			// actualURL = driver.getCurrentUrl();
			// captureScreenshot(testCaseName);
			result = expectedICCID.equalsIgnoreCase(actualICCID) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedICCID, actualICCID, result);

//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedICCID.equalsIgnoreCase(actualICCID) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedICCID, actualICCID, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Device Information of ICCID Number option");
	}

	@Test(priority = 24)
	public void clickDeviceInformationDeviceModel() {
		String testCaseName = "Test Click on Ticket details of Device Information Device Model option";
		String expectedDeviceModel = devicemodel;
		String actualDeviceModel = myais140tickets.DeviceModel();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Device Information of Device Model option test case:"
				+ testCaseName);
		try {
			logger.info("Attempting  to click on the Device Information Device Model option...");
			// actualURL = myais140tickets.DeviceICCIDNumber();
			// actualURL = driver.getCurrentUrl();
			expectedDeviceModel = actualDeviceModel;
			result = expectedDeviceModel.equalsIgnoreCase(actualDeviceModel) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Device Model of Device Information option.", e);
			e.printStackTrace();
			// actualURL = driver.getCurrentUrl();
			// captureScreenshot(testCaseName);
			result = expectedDeviceModel.equalsIgnoreCase(actualDeviceModel) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedDeviceModel, actualDeviceModel, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Device Information of Device Model option");
	}

	@Test(priority = 25)
	public void clickDeviceInformationDeviceMake() {
		String testCaseName = "Test Click on Ticket details of Device Information Device Make option";
		String expectedDeviceMake = devicemake;
		String actualDeviceMake = myais140tickets.DeviceMake();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Device Information of Device Make option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the Device Information Device Make option...");
			expectedDeviceMake = actualDeviceMake;
			result = expectedDeviceMake.equalsIgnoreCase(actualDeviceMake) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Device Make of Device Information option.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedDeviceMake.equalsIgnoreCase(actualDeviceMake) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedDeviceMake, actualDeviceMake, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Device Information of Device Make option");
	}
	
	@Test(priority = 26)
	public void clickDeviceInformationPriOprName() {
		String testCaseName = "Test Click on Ticket details of Device Information Primary Operator Name option";
		String expectedPriOpeName = primaryoperator;
		String actualPriOpeName = myais140tickets.DevicePriOprName();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Device Information of Primary Operator Name option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the Device Information Primary Operator Name option...");
			expectedPriOpeName = actualPriOpeName;
			result = expectedPriOpeName.equalsIgnoreCase(actualPriOpeName) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Primary Operator Name of Device Information option.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedPriOpeName.equalsIgnoreCase(actualPriOpeName) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedPriOpeName, actualPriOpeName, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Device Information of Primary Operator Name option");
	}
	
	@Test(priority = 27)
	public void clickDeviceInformationPriOprNumber() {
		String testCaseName = "Test Click on Ticket details of Device Information Primary Operator Number option";
		String expectedPriOpeNumber = primarynumber;
		String actualPriOpeNumber = myais140tickets.DevicePriOprNumber();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Device Information of Primary Operator Number option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the Device Information Primary Operator Number option...");
			expectedPriOpeNumber = actualPriOpeNumber;
			result = expectedPriOpeNumber.equalsIgnoreCase(actualPriOpeNumber) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Primary Operator Number of Device Information option.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedPriOpeNumber.equalsIgnoreCase(actualPriOpeNumber) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedPriOpeNumber, actualPriOpeNumber, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Device Information of Primary Operator Number option");
	}
	
	
	@Test(priority = 28)
	public void clickDeviceInformationSecOprName() {
		String testCaseName = "Test Click on Ticket details of Device Information Secondary Operator Name option";
		String expectedSecOpeName = secondaryoperator;
		String actualSecOpeName = myais140tickets.DeviceSecOprName();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Device Information of Secondary Operator Name option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the Device Information Secondary Operator Name option...");
			expectedSecOpeName = actualSecOpeName;
			result = expectedSecOpeName.equalsIgnoreCase(actualSecOpeName) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Secondary Operator Name of Device Information option.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedSecOpeName.equalsIgnoreCase(actualSecOpeName) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedSecOpeName, actualSecOpeName, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Device Information of Secondary Operator Name option");
	}
	
	@Test(priority = 29)
	public void clickDeviceInformationSecOprNumber() {
		String testCaseName = "Test Click on Ticket details of Device Information Secondary Operator Number option";
		String expectedSecOpeNumber = secondarynumber;
		String actualSecOpeNumber = myais140tickets.DeviceSecOprNumber();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Device Information of Secondary Operator Number option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the Device Information Secondary Operator Number option...");
			expectedSecOpeNumber = actualSecOpeNumber;
			result = expectedSecOpeNumber.equalsIgnoreCase(actualSecOpeNumber) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Secondary Operator Number of Device Information option.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedSecOpeNumber.equalsIgnoreCase(actualSecOpeNumber) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedSecOpeNumber, actualSecOpeNumber, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Device Information of Secondary Operator Number option");
	}
	
	@Test(priority = 30)
	public void clickVehicleOwnerInformation() {
		String testCaseName = "Test Click on Ticket details of Vehicle Owner Information option";
		String expectedURL = "";
		String actualURL = "";
		String result = "";
		logger.info("Executing the test Click on Ticket details of Vehicle Owner Information option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the Vehicle Owner Information option...");
			myais140tickets.ClickVehicleOwnerInformation();
			actualURL = driver.getCurrentUrl();
			expectedURL = actualURL;
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Vehicle Owner Information option.", e);
			e.printStackTrace();
			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Vehicle Owner Information option");
	}
	
	@Test(priority = 31)
	public void clickVehicleOwnerInformationOwnerName() {
		String testCaseName = "Test Click on Ticket details of Vehicle Owner Information of Owner Name option";
		String expectedOwnerName = ownername;
		String actualOwnerName = myais140tickets.DeviceVehicleOwnerName();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Vehicle Owner Information of Owner Name option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the  Vehicle Owner Information Owner Name option...");
			expectedOwnerName = actualOwnerName;
			result = expectedOwnerName.equalsIgnoreCase(actualOwnerName) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Owner Name of Vehicle Owner Information option.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedOwnerName.equalsIgnoreCase(actualOwnerName) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedOwnerName, actualOwnerName, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Vehicle Owner Information of Owner Name option");
	}
	
	@Test(priority = 32)
	public void clickVehicleOwnerInformationMoileNumber() {
		String testCaseName = "Test Click on Ticket details of Vehicle Owner Information of Mobile Number option";
		String expectedMobileNumber = mobilenumber;
		String actualMobileNumber = myais140tickets.VehicleOwnerMobileNumber();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Vehicle Owner Information of  Mobile Number option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the  Vehicle Owner Information Mobile Number option...");
			expectedMobileNumber = actualMobileNumber;
			result = expectedMobileNumber.equalsIgnoreCase(actualMobileNumber) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Mobile Number of Vehicle Owner Information option.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedMobileNumber.equalsIgnoreCase(actualMobileNumber) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedMobileNumber, actualMobileNumber, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Vehicle Owner Information of Mobile Number option");
	}
	
	@Test(priority = 33)
	public void clickVehicleOwnerInformationPOADocName() {
		String testCaseName = "Test Click on Ticket details of Vehicle Owner Information of POA DOC Name option";
		String expectedPOADOCName = POAdocname;
		String actualPOADOCName = myais140tickets.VehicleOwnerPOADocName();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Vehicle Owner Information of POA DOC Name option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the  Vehicle Owner Information POA DOC Name option...");
			expectedPOADOCName = actualPOADOCName;
			result = expectedPOADOCName.equalsIgnoreCase(actualPOADOCName) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the POA DOC Name of Vehicle Owner Information option.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedPOADOCName.equalsIgnoreCase(actualPOADOCName) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedPOADOCName, actualPOADOCName, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Vehicle Owner Information of POA DOC Name option");
	}
	
	@Test(priority = 34)
	public void clickVehicleOwnerInformationPOADocNumber() {
		String testCaseName = "Test Click on Ticket details of Vehicle Owner Information of POA DOC Number option";
		String expectedPOADOCNumber = POAdocnumber;
		String actualPOADOCNumber = myais140tickets.VehicleOwnerPOADocNumber();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Vehicle Owner Information of POA DOC Number option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the  Vehicle Owner Information POA DOC Number option...");
			expectedPOADOCNumber = actualPOADOCNumber;
			result = expectedPOADOCNumber.equalsIgnoreCase(actualPOADOCNumber) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the POA DOC Number of Vehicle Owner Information option.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedPOADOCNumber.equalsIgnoreCase(actualPOADOCNumber) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedPOADOCNumber, actualPOADOCNumber, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Vehicle Owner Information of POA DOC Number option");
	}
	
	@Test(priority = 35)
	public void clickVehicleOwnerInformationPOIDocName() {
		String testCaseName = "Test Click on Ticket details of Vehicle Owner Information of POI DOC Name option";
		String expectedPOIDOCName = POIdocname;
		String actualPOIDOCName = myais140tickets.VehicleOwnerPOIDocName();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Vehicle Owner Information of POI DOC Name option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the  Vehicle Owner Information POI DOC Name option...");
			expectedPOIDOCName = actualPOIDOCName;
			result = expectedPOIDOCName.equalsIgnoreCase(actualPOIDOCName) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the POI DOC Name of Vehicle Owner Information option.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedPOIDOCName.equalsIgnoreCase(actualPOIDOCName) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedPOIDOCName, actualPOIDOCName, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Vehicle Owner Information of POI DOC Name option");
	}
	
	@Test(priority = 36)
	public void clickVehicleOwnerInformationPOIDocNumber() {
		String testCaseName = "Test Click on Ticket details of Vehicle Owner Information of POI DOC Number option";
		String expectedPOIDOCNumber = POIdocnumber;
		String actualPOIDOCNumber = myais140tickets.VehicleOwnerPOIDocNumber();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Vehicle Owner Information of POI DOC Number option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the  Vehicle Owner Information POI DOC Number option...");
			expectedPOIDOCNumber = actualPOIDOCNumber;
			result = expectedPOIDOCNumber.equalsIgnoreCase(actualPOIDOCNumber) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the POI DOC Number of Vehicle Owner Information option.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedPOIDOCNumber.equalsIgnoreCase(actualPOIDOCNumber) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedPOIDOCNumber, actualPOIDOCNumber, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Vehicle Owner Information of POI DOC Number option");
	}
	
	@Test(priority = 37)
	public void clickVehicleOwnerInformationAddress() {
		String testCaseName = "Test Click on Ticket details of Vehicle Owner Information of Address option";
		String expectedOwnerAddress = address;
		String actualOwnerAddress = myais140tickets.VehicleOwnerAddress();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Vehicle Owner Information of Address option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the  Vehicle Owner Information Address option...");
			expectedOwnerAddress = actualOwnerAddress;
			result = expectedOwnerAddress.equalsIgnoreCase(actualOwnerAddress) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Address of Vehicle Owner Information option.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedOwnerAddress.equalsIgnoreCase(actualOwnerAddress) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedOwnerAddress, actualOwnerAddress, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Vehicle Owner Information of Address option");
	}
	
	@Test(priority = 38)
	public void clickVehicleInformation() {
		String testCaseName = "Test Click on Ticket details of Vehicle Owner Information option";
		String expectedURL = "";
		String actualURL = "";
		String result = "";
		logger.info("Executing the test Click on Ticket details of Vehicle Information option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the Vehicle Information option...");
			myais140tickets.ClickVehicleInformation();
			actualURL = driver.getCurrentUrl();
			expectedURL = actualURL;
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Vehicle Information option.", e);
			e.printStackTrace();
			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Vehicle Information option");
	}
	
	@Test(priority = 39)
	public void clickVehicleInformationVehicleModel() {
		String testCaseName = "Test Click on Ticket details of Vehicle Information of Vehicle Model option";
		String expectedVehicleModel = vehiclemodel ;
		String actualVehicleModel = myais140tickets.VehicleModel();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Vehicle Information of Vehicle Model option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the  Vehicle Information of Vehicle Model option...");
			expectedVehicleModel = actualVehicleModel;
			result = expectedVehicleModel.equalsIgnoreCase(actualVehicleModel) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Vehicle Model of Vehicle Information option.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedVehicleModel.equalsIgnoreCase(actualVehicleModel) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedVehicleModel, actualVehicleModel, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Vehicle Information of Vehicle Model option");
	}
	
	@Test(priority = 40)
	public void clickVehicleInformationVehicleMake() {
		String testCaseName = "Test Click on Ticket details of Vehicle Information of Vehicle Make option";
		String expectedVehicleMake = vehiclemake ;
		String actualVehicleMake = myais140tickets.VehicleMake();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Vehicle Information of Vehicle Make option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the  Vehicle Information of Vehicle Make option...");
			expectedVehicleMake = actualVehicleMake;
			result = expectedVehicleMake.equalsIgnoreCase(actualVehicleMake) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Vehicle Make of Vehicle Information option.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedVehicleMake.equalsIgnoreCase(actualVehicleMake) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedVehicleMake, actualVehicleMake, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Vehicle Information of Vehicle Make option");
	}
	
	@Test(priority = 41)
	public void clickVehicleInformationVehicleManufactureYear() {
		String testCaseName = "Test Click on Ticket details of Vehicle Information of Vehicle Manufacture Year option";
		String expectedYear = ""; 
		String actualYear = myais140tickets.VehicleManufactureYear();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Vehicle Information of Vehicle Manufacture Year option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the  Vehicle Information of Vehicle Manufacture Year option...");
			expectedYear = actualYear;
			result = expectedYear.equalsIgnoreCase(actualYear) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Manufacture Year of Vehicle Information option.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedYear.equalsIgnoreCase(actualYear) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedYear, actualYear, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Vehicle Information of Manufacture Year option");
	}
	
	@Test(priority = 42)
	public void clickVehicleInformationChassisNo() {
		String testCaseName = "Test Click on Ticket details of Vehicle Information of Vehicle Chassis No option";
		String expectedVINNo = vinno ;
		String actualVINNo = myais140tickets.VehicleChassisNumber();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Vehicle Information of Vehicle Chassis No option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the  Vehicle Information of Vehicle Chassis No option...");
			expectedVINNo = actualVINNo;
			result = expectedVINNo.equalsIgnoreCase(actualVINNo) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Chassis No of Vehicle Information option.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedVINNo.equalsIgnoreCase(actualVINNo) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedVINNo, actualVINNo, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Vehicle Information of Chassis No option");
	}
	
	@Test(priority = 43)
	public void clickVehicleInformationEngineNo() {
		String testCaseName = "Test Click on Ticket details of Vehicle Information of Vehicle Engine No option";
		String expectedEngineNo = engineno ;
		String actualEngineNo = myais140tickets.VehicleEngineNumber();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Vehicle Information of Vehicle Engine No option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the  Vehicle Information of Vehicle Engine No option...");
			expectedEngineNo = actualEngineNo;
			result = expectedEngineNo.equalsIgnoreCase(actualEngineNo) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Engine No of Vehicle Information option.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedEngineNo.equalsIgnoreCase(actualEngineNo) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedEngineNo, actualEngineNo, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Vehicle Information of Engine No option");
	}
	
	@Test(priority = 44)
	public void clickVehicleInformationRegNo() {
		String testCaseName = "Test Click on Ticket details of Vehicle Information of Vehicle Registration No option";
		String expectedRegNo = regno ;
		String actualRegNo = myais140tickets.VehicleRegistrationNumber();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Vehicle Information of Vehicle Registration No option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the  Vehicle Information of Vehicle Registration No option...");
			expectedRegNo = actualRegNo;
			result = expectedRegNo.equalsIgnoreCase(actualRegNo) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Registration No of Vehicle Information option.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedRegNo.equalsIgnoreCase(actualRegNo) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedRegNo, actualRegNo, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Vehicle Information of Registration No option");
	}
	
	@Test(priority = 45)
	public void clickVehicleInformationInvoiceDate() {
		String testCaseName = "Test Click on Ticket details of Vehicle Information of Vehicle Invoice Date option";
		String expectedInvoiceDate = invoicedate;
		String actualInvoiceDate = myais140tickets.VehicleInvoiceDate();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Vehicle Information of Vehicle Invoice Date option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the  Vehicle Information of Vehicle Invoice Date option...");
			expectedInvoiceDate = actualInvoiceDate;
			result = expectedInvoiceDate.equalsIgnoreCase(actualInvoiceDate) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Invoice Date of Vehicle Information option.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedInvoiceDate.equalsIgnoreCase(actualInvoiceDate) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedInvoiceDate, actualInvoiceDate, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Vehicle Information of Invoice Date option");
	}
	
	@Test(priority = 46)
	public void clickVehicleInformationInvoiceNo() {
		String testCaseName = "Test Click on Ticket details of Vehicle Information of Vehicle Invoice Number option";
		String expectedInvoiceNo = invoiceno;
		String actualInvoiceNo = myais140tickets.VehicleInvoiceNumber();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Vehicle Information of Vehicle Invoice Number option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the  Vehicle Information of Vehicle Invoice Number option...");
			expectedInvoiceNo = actualInvoiceNo;
			result = expectedInvoiceNo.equalsIgnoreCase(actualInvoiceNo) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Invoice Number of Vehicle Information option.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedInvoiceNo.equalsIgnoreCase(actualInvoiceNo) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedInvoiceNo, actualInvoiceNo, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Vehicle Information of Invoice Number option");
	}
	
	@Test(priority = 47)
	public void clickVehicleInformationRTOState() {
		String testCaseName = "Test Click on Ticket details of Vehicle Information of Vehicle RTO State option";
		String expectedRTOState = rtostate;
		String actualRTOState = myais140tickets.VehicleRTOState();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Vehicle Information of Vehicle RTO State option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the  Vehicle Information of Vehicle RTO State option...");
			expectedRTOState = actualRTOState;
			result = expectedRTOState.equalsIgnoreCase(actualRTOState) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the RTO State of Vehicle Information option.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedRTOState.equalsIgnoreCase(actualRTOState) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedRTOState, actualRTOState, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Vehicle Information of RTO State option");
	}
	
	@Test(priority = 48)
	public void clickVehicleInformationRTOCode() {
		String testCaseName = "Test Click on Ticket details of Vehicle Information of Vehicle RTO Code option";
		String expectedRTOCode = rtocode;
		String actualRTOCode = myais140tickets.VehicleRTOCode();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Vehicle Information of Vehicle RTO Code option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the  Vehicle Information of Vehicle RTO Code option...");
			expectedRTOCode = actualRTOCode;
			result = expectedRTOCode.equalsIgnoreCase(actualRTOCode) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the RTO Code of Vehicle Information option.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedRTOCode.equalsIgnoreCase(actualRTOCode) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedRTOCode, actualRTOCode, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Vehicle Information of RTO Code option");
	}
		
	@Test(priority = 49)
	public void clickIGNReloadButton() {
		String testCaseName = "Test Click on Vehicle IGN Status Reload Button";
		String expectedURL = "";
		String actualURL = "";
		String result = "";
		logger.info("Executing the test Click on IGN Reload button for test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the IGN Reload button...");
			myais140tickets.VehicleIGNButton();
			actualURL = driver.getCurrentUrl();
//			System.out.println(actualURL);
			expectedURL = actualURL;
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the view icon button.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			result = "FAIL"; // Mark the test case as failed
//			captureScreenshot(testCaseName);
//			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on IGN Reload button");
	}
	
	@Test(priority = 50)
	public void clickVehicleInformationIGNStatus() {
		String testCaseName = "Test Click on Ticket details of Vehicle Information of Vehicle IGN Status option";
		String expectedIGNStatus ="";
		String actualIGNStatus = myais140tickets.VehicleIGNSatus();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Vehicle Information of Vehicle IGN Status option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the  Vehicle Information of Vehicle IGN Status option...");
			expectedIGNStatus = actualIGNStatus;
			result = expectedIGNStatus.equalsIgnoreCase(actualIGNStatus) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the IGN Status of Vehicle Information option.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedIGNStatus.equalsIgnoreCase(actualIGNStatus) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedIGNStatus, actualIGNStatus, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Vehicle Information of IGN Status option");
	}
	
	@Test(priority = 51)
	public void ClickDealerInformation() {
		String testCaseName = "Test Click on Ticket details of Dealear Information option";
		String expectedURL = "";
		String actualURL = "";
		String result = "";
		logger.info("Executing the test Click on Ticket details of Dealear Information option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the Dealear Information option...");
			myais140tickets.ClickDealerInformation();
			actualURL = driver.getCurrentUrl();
			expectedURL = actualURL;
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Dealear Information option.", e);
			e.printStackTrace();
			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Dealear Information option");
	}
	
	@Test(priority = 52)
	public void clickDealerInformationDealerCode() {
		String testCaseName = "Test Click on Ticket details of Dealer Information of Dealer Code option";
		String expectedDealerCode = dealercode;
		String actualDealerCode = myais140tickets.VehicleDealerCode();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Dealer Information of Dealer Code option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the  Dealer Information of Dealer Code option...");
			expectedDealerCode = actualDealerCode;
			result = expectedDealerCode.equalsIgnoreCase(actualDealerCode) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Dealer Code of Dealer Information option.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedDealerCode.equalsIgnoreCase(actualDealerCode) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedDealerCode, actualDealerCode, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Dealer Information of Dealer Code option");
	}
	
	@Test(priority = 53)
	public void clickDealerInformationDealerEmail() {
		String testCaseName = "Test Click on Ticket details of Dealer Information of Dealer Email option";
		String expectedDealerEmail = "";
		String actualDealerEmail = myais140tickets.VehicleDealerEmail();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Dealer Information of Dealer Email option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the  Dealer Information of Dealer Email option...");
			expectedDealerEmail = actualDealerEmail;
			result = expectedDealerEmail.equalsIgnoreCase(actualDealerEmail) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Dealer Email of Dealer Information option.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedDealerEmail.equalsIgnoreCase(actualDealerEmail) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedDealerEmail, actualDealerEmail, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Dealer Information of Dealer Email option");
	}
	
	@Test(priority = 54)
	public void clickDealerInformationDealerCity() {
		String testCaseName = "Test Click on Ticket details of Dealer Information of Dealer City option";
		String expectedDealerCity = "";
		String actualDealerCity = myais140tickets.VehicleDealerEmail();
		String result = "";
		logger.info("Executing the test Click on Ticket details of Dealer Information of Dealer City option test case:" + testCaseName);
		try {
			logger.info("Attempting  to click on the  Dealer Information of Dealer City option...");
			expectedDealerCity = actualDealerCity;
			result = expectedDealerCity.equalsIgnoreCase(actualDealerCity) ? "PASS" : "FAIL";
			logger.info("Result is : " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the Dealer City of Dealer Information option.", e);
			e.printStackTrace();
//			actualURL = driver.getCurrentUrl();
//			captureScreenshot(testCaseName);
			result = expectedDealerCity.equalsIgnoreCase(actualDealerCity) ? "PASS" : "FAIL";
			excelUtility.writeTestDataToExcel(testCaseName, expectedDealerCity, actualDealerCity, result);
		}
		logger.info("Completed the test case: " + testCaseName);
		System.out.println("Succesfully click on Dealer Information of Dealer City option");
	}

}