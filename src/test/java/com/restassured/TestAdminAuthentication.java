package com.restassured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;



public class TestAdminAuthentication {
	
	
	
	@Test(enabled = false) 
	public void testAdminLogin()
	{
		RestAssured.baseURI="http://localhost:8080/adminauth";
		Response response = given()
				.queryParam("email", "admin@example.com")
				.queryParam("password", "admin123")
	            .contentType(ContentType.JSON)
	            .when()
	            .post("/login")
	            .then()
	            .extract().response();
		System.out.println(response.asPrettyString());
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		Assert.assertEquals(200, response.getStatusCode());
		Assert.assertTrue(response.getTime()>200);
		JsonPath json=response.jsonPath();
		System.out.println(json.getInt("code"));
		System.out.println(json.getString("message"));
		
		
	}
	
	@Test
	public void testUserLogin()
	{
		RestAssured.baseURI="http://localhost:8080/users";
		Response response = given()
				.queryParam("email", "shekar2@example.com")
				.queryParam("password", "jackson1234")
	            .contentType(ContentType.JSON)
	            .when()
	            .post("/login")
	            .then()
	            .extract().response();
		
		System.out.println(response.asPrettyString());
		/*
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		*/
		
		JsonPath json=response.jsonPath();
		System.out.println("code :" +json.getInt("code"));
		System.out.println("message: "+json.getString("message"));
		
		
		
		String msg =json.getString("message");
		System.out.println(msg.contains("Successfully"));
		List<HashMap<String,Object>> data=json.getList("data");
		System.out.println("userId :"+ data.get(0).get("userId"));
		System.out.println("email :"+ data.get(0).get("email"));
		System.out.println("password :"+ data.get(0).get("password"));
		System.out.println("fullName :"+ data.get(0).get("fullName"));
		System.out.println("contact :"+ data.get(0).get("contact"));
		Assert.assertEquals("shekar2@example.com", data.get(0).get("email"));
		Assert.assertEquals(101,json.getInt("code"));
		Assert.assertTrue(msg.contains("User Logged In Successfully"));
		
		
	}
	@Test
	public void testUserLoginwithRestAssured()
	{
		RestAssured.baseURI="http://localhost:8080/users";
		given()
				.queryParam("email", "shekar2@example.com")
				.queryParam("password", "jackson1234")
	            .contentType(ContentType.JSON)
	            .when()
	            .post("/login")
	            .then()
	            .body("code",equalTo(101),
	            	"data[0].userId",equalTo(33),
	            	"data[0].password",equalTo("jackson1234"),
	            	"message",containsString("User Logged In Successfully"));
	
	
}
	
	@Test
	public void testUserRegisterwithRestAssured()
	{
		RestAssured.baseURI="http://localhost:8080/users";
		given()
				.queryParam("email", "shekar3@example.com")
				.queryParam("password", "jackson1234")
				.queryParam("fullName", "Jackson Joe")
				.queryParam("image", "NA")
				.queryParam("contact", "9999912345")				
	            .contentType(ContentType.JSON)
	            .when()
	            .post("/add")
	            .then()
	            .body("code",equalTo(101),
	            	"data[0].userId",equalTo(42),
	            	"data[0].password",equalTo("jackson1234"),
	            	"message",containsString("User Saved Successfully"));
	
	
}
}
