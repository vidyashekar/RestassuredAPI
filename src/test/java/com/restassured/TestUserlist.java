package com.restassured;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response; 


public class TestUserlist {
	/*
	given -- open url
	when  -- enter user name and password 
	then  --  validate user is succesfull
	
	Rest API -- endpoint , http methos : get , post ,put , delete 
	
*/
	public static void main (String[] args)
	{
	
	
	Response response = given()			
            .contentType(ContentType.JSON)
            .when()
            .get("https://reqres.in/api/users?page=2");
            
	System.out.println("status code :"+response.getStatusLine());
	System.out.println("-----");
	System.out.println(" data is :"+ response.asString());
	System.out.println(" data is :"+ response.getHeader("Content-Type"));
	System.out.println(" data is :"+ response.getHeader("Date"));
	
	
	
	
	}
	
}
