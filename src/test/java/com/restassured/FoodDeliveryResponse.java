package com.restassured;

import java.util.HashMap;
import java.util.List;

public class FoodDeliveryResponse {

	public int code;
	public String message;
	public List<HashMap<String, Object>> data;
	
	@Override
	public String toString() {
		return "RegisterUserResponse:\ncode=" + code + "\nmessage=" + message + "\ndata=" + data + "]";
	}
}
