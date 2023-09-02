package com.restassured;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.HashMap;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class AddResturant1 {

	/*
	 * Create a new class AddRestaurantTest and write test cases. We will write the
	 * below assertions. 1. It should add a Restaurant with Success 2. response must
	 * be valid and have a body 3. response must be available in less than 150 ms 4.
	 * response should have code 101 5. response should contain message and check
	 * for restaurant email
	 * 
	 */

	@Test(enabled=false)
	public void tesAddDishToRestaurant() {
		RestAssured.baseURI = "http://localhost:8080/restaurants";

		Response response = given().queryParam("name", "Cafe Coffee Day")
				.queryParam("description", "A chain of Coffee Restaurants").queryParam("address", "ABC Street")
				.queryParam("contact", "9876598765").queryParam("email", "info@ccd.cafe").queryParam("images", "null")
				.queryParam("thumbnailImage", "0").queryParam("rating", "5").queryParam("status", "1")
				.queryParam("addedOn", "2022/2/2").contentType(ContentType.JSON).when().post("/add").then().extract()
				.response();

		System.out.println(response.asPrettyString());
		JsonPath json = response.jsonPath();
		int statusCode = response.getStatusCode();
		long responseTime = response.getTime();

		int code = json.getInt("code");
		String message = json.getString("message");
		List<HashMap<String, Object>> data = json.getList("data");

		System.out.println(code);
		System.out.println(json.get("code"));

		// Check if response is OK
		Assert.assertEquals(statusCode, 200);
		
		/*
		 * 1. It should add a Restaurant with Success 
		 * 2. response must be valid and have a body 
		 * 3. response must be available in less than 150 ms 
		 * 4. response should have code 101 
		 * 5. response should contain message and check for restaurant
		 * email
		 */

		// Check Success Message from JSON Response
		Assert.assertTrue(message.contains("Success"));

		// Check if Response has a valid body
		Assert.assertNotNull(response.getBody());

		// Check response must be available in less than 150 ms
		Assert.assertTrue(responseTime < 550);

		// Check JSON Response to have code 101
		Assert.assertEquals(code, 101);
		
        // Check Success Message from JSON Response
        String email = (String)data.get(0).get("email");
        Assert.assertEquals(email, "info@ccd.cafe");

	

		
	}
	
	@Test(enabled=false)
	public void validateAddDishToCartWithRestAssured() {
		
		RestAssured.baseURI = "http://localhost:8080/restaurants"; 
				
		given()
				.queryParam("name", "Cafe Coffee Day")
		        .queryParam("description", "A chain of Coffee Restaurants")
		        .queryParam("address", "ABC Street")
		        .queryParam("contact", "9876598765")
		        .queryParam("email", "info@ccd.cafe")
		        .queryParam("images", "null")
		        .queryParam("thumbnailImage", "0")
		        .queryParam("rating", "5")
		        .queryParam("status", "1")
		        .queryParam("addedOn", "2022/2/2")
                .contentType(ContentType.JSON)
                .when()
                .post("/add")
                .then()
                
                .statusCode(200) // Verify that the response status code is 200 OK
                .body(notNullValue()) // Validate that the response body is not null
                .time(lessThan(500L)) // Validate that the response time is less than 500 milliseconds
                .body(
                		"code", equalTo(101),  
                		"message", containsString("Success"),
                		"data[0].email", equalTo("info@ccd.cafe")
                );
		
		/*
		 * 1. It should add a Restaurant with Success  --done 
		 * 2. response must be valid and have a body 
		 * 3. response must be available in less than 150 ms 
		 * 4. response should have code 101  --done 
		 * 5. response should contain message and check for restaurant --done 
		 * email
		 */
        
        
	}
	@Test
	public void deserializeAndValidateResponse() {
		
		RestAssured.baseURI = "http://localhost:8080/restaurants"; 
				
		Response response = given()
				.queryParam("name", "Cafe Coffee Day")
		        .queryParam("description", "A chain of Coffee Restaurants")
		        .queryParam("address", "ABC Street")
		        .queryParam("contact", "9876598765")
		        .queryParam("email", "info@ccd.cafe")
		        .queryParam("images", "null")
		        .queryParam("thumbnailImage", "0")
		        .queryParam("rating", "5")
		        .queryParam("status", "1")
		        .queryParam("addedOn", "2022/2/2")
                .contentType(ContentType.JSON)
                .when()
                .post("/add")
                .then()
                .extract().response();
		 
		FoodDeliveryResponse adminResponse = response.getBody().as(FoodDeliveryResponse.class); 
		
		System.out.println(adminResponse);
		
		// Check JSON Response to have code 101
       Assert.assertEquals(adminResponse.code,101);
        
        // Check Success Message from JSON Response
        Assert.assertTrue(adminResponse.message.contains("Success"));
        System.out.println("-----------");
        System.out.println(adminResponse.data.get(0));
        System.out.println(adminResponse.data.get(0).get("email"));
     // Check if Response has a valid body
     		Assert.assertNotNull(response.getBody());
     		
     	// Check response must be available in less than 150 ms
    		Assert.assertTrue(response.getTime() < 550);
        
        /*
		 * 1. It should add a Restaurant with Success  --done 
		 * 2. response must be valid and have a body  --done 
		 * 3. response must be available in less than 150 ms  --done 
		 * 4. response should have code 101  --done 
		 * 5. response should contain message and check for restaurant --done 
		 * email
		 */
        
	}



}
