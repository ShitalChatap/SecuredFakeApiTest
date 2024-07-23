package com.maveric.project.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.equalToObject;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.http.HttpStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.fakeapi.auth.ClientAuthConstant;
import com.maveric.fakeapi.pojos.AirlinePojo;
import com.maveric.fakeapi.pojos.PassengerPojo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class SecuredFakeApiTest
{
	@BeforeClass
	public static void setAuthEnv()
	{
		ClientAuthConstant.BEARER_TOKEN = RestAssured.given()
				.contentType("application/x-www-form-urlencoded; charset=utf-8")
				.formParam("grant_type", ClientAuthConstant.GRANT_TYPE)
				.formParam("scope", ClientAuthConstant.SCOPE)
				.formParam("username", ClientAuthConstant.USER_NAME)
				.formParam("password", ClientAuthConstant.PASSWORD)
				.formParam("client_id", ClientAuthConstant.CLIENT_ID)
				.when()
				.post("https://dev-457931.okta.com/oauth2/aushd4c95QtFHsfWt4x6/v1/token").then().extract()
				.path("access_token");
	}
	@BeforeClass
	public static void setUpAPIEnv() 
	{
		RestAssured.baseURI = "https://api.instantwebtools.net/v2";
	}
	
	@Test(testName = "Get all airlines details")
	public void test1() {
		String airlinesDetails= RestAssured.given().headers(
	              "Authorization",
	              "Bearer " + ClientAuthConstant.BEARER_TOKEN,
	              "Content-Type",
	              ContentType.JSON,
	              "Accept",
	              ContentType.JSON)
		.when()
		.get("/airlines")
		.then()
		.log().body().toString();
		System.out.println("======= " +airlinesDetails );
	}
	@Test(testName = "Get sepcific airline details")
	public void test2() {
		RestAssured.given().headers(
	              "Authorization",
	              "Bearer " + ClientAuthConstant.BEARER_TOKEN,
	              "Content-Type",
	              ContentType.JSON,
	              "Accept",
	              ContentType.JSON)
		.when()
		.get("/airlines/73dd5420-3bf9-48f3-a0b6-17cf7aa61b19")
		.then()
		.assertThat()
		.body("name", equalToObject("American Airlines"));
		
		
	}
	@AfterClass
	public static void tearDownEnv() 
	{
		RestAssured.reset();
	}
	
	
	
	@Test(testName = "Get all passenger details")
	public void test3() {
		String passengerDetails= RestAssured.given().headers(
	              "Authorization",
	              "Bearer " + ClientAuthConstant.BEARER_TOKEN,
	              "Content-Type",
	              ContentType.JSON,
	              "Accept",
	              ContentType.JSON)
		.when()
		.get("/passenger?page=0&size=10")
		.then()
		.log().body().toString();
		System.out.println("======= " +passengerDetails );
	}
	
	@Test(testName ="Get passenger  by passengerid")
	public  void test4() {
		RestAssured.given().headers(
	              "Authorization",
	              "Bearer " + ClientAuthConstant.BEARER_TOKEN,
	              "Content-Type",
	              ContentType.JSON,
	              "Accept",
	              ContentType.JSON)
		.when()
		.get("/passenger/66781445d6a086b675bb224e")
		.then()
		.assertThat()
		.body("name", equalToObject("John Doe"));
		
	}
	
	
	@Test(testName="Add new airline details using AirlineData.json file")
	public  void test5() throws IOException,DatabindException
	{
		ObjectMapper mapper=new ObjectMapper();
		FileInputStream stream=new FileInputStream("./TestData/AirlineData.json");
		AirlinePojo  payload=mapper.readValue(stream, AirlinePojo.class);
		RestAssured.given().headers(
	              "Authorization",
	              "Bearer " + ClientAuthConstant.BEARER_TOKEN,
	              "Content-Type",
	              ContentType.JSON,
	              "Accept",
	              ContentType.JSON)
			.when()
			.body(payload)
			.post("/airlines")
			.then()
			.statusCode(HttpStatus.SC_OK)
			.and()
			.body("name", equalTo("Sri Lankan Airways"))
			.log()
			.all();
	}
	
	
	@Test(testName="Add new passenger details using PassengerData.json file")
	public  void test6() throws IOException,DatabindException 
	{
		
		ObjectMapper mapper=new ObjectMapper();
		FileInputStream stream=new FileInputStream("./TestData/PassengerData.json");
		PassengerPojo  payload=mapper.readValue(stream, PassengerPojo.class);
		
		RestAssured.given().headers(
	              "Authorization",
	              "Bearer " + ClientAuthConstant.BEARER_TOKEN,
	              "Content-Type",
	              ContentType.JSON,
	              "Accept",
	              ContentType.JSON)
			.when()
			.body(payload)
			.post("/passenger")
			.then()
			.statusCode(HttpStatus.SC_OK)
			.and()
			.body("name", equalTo("John Doe"))
			.log()
			.all();
	}
	
	
	@Test(testName = "Update every detail of a passenger.")
	public void test7()
	{
				
		String passengerID = "6679447b7ad8fb3f5e433c33";
		String newname = "Haulmer Airlines";
	    int trips = 500;
	    int airline = 10;
	
	     String requestBody = "{\n" +
	             "    \"name\": \"" + newname + "\",\n" +
	             "    \"salary\": " + trips + ",\n" +
	             "    \"age\": " + airline + "\n" +
	             "}";
	
	     
	     RestAssured.given().headers(
	              "Authorization",
	              "Bearer " + ClientAuthConstant.BEARER_TOKEN,
	              "Content-Type",
	              ContentType.JSON,
	              "Accept",
	              ContentType.JSON)
				 .when()	             
				 .pathParam("id",passengerID)
	             .body(requestBody)
	             .put("/passenger/{id}");
	             System.out.println(requestBody);
		
	}
	
	@Test(testName = "patch name  of a passenger.")
	public void test8() 
	{
				
		String passengerID = "667aafa07ad8fbcd834342a6";
		String newname = "tinu sakhare";
	    
	     String requestBody = "{\n" +
	             "    \"name\": \"" + newname + "\",\n" +
	            
	             "}";
 
	     RestAssured.given().headers(
	              "Authorization",
	              "Bearer " + ClientAuthConstant.BEARER_TOKEN,
	              "Content-Type",
	              ContentType.JSON,
	              "Accept",
	              ContentType.JSON)
				 .when()
	             .pathParam("id",passengerID)
	             .body(requestBody)
	             .put("/passenger/{id}");
	             System.out.println(requestBody);
		
	}
	
	@Test(testName="delete passenger details based on passengerid")
	public  void test9()
	{
	
		RestAssured.given().headers(
	              "Authorization",
	              "Bearer " + ClientAuthConstant.BEARER_TOKEN,
	              "Content-Type",
	              ContentType.JSON,
	              "Accept",
	              ContentType.JSON)
			  	.when()
                .delete("/passenger/667aafa27ad8fb55654342b0")
                .then()
                .assertThat()
                .statusCode(200)
                .body("message",equalToObject("Passenger data deleted successfully."));
		}
	
	
}
