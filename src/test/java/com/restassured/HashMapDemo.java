package com.restassured;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HashMap<Integer,String> map =new HashMap<Integer,String>();
		map.put(1,"Hyderabad");
		map.put(2,"Delhi");
		map.put(3,"Chennai");
		map.put(4,"Mumbai");
		map.put(1,"Pune");	
		// entryset
		/*
		 * 
		 * for (String cities :a1)
		{
			System.out.println(cities);
		}
		 */
		for (Map.Entry<Integer,String> cities :map.entrySet())
		{
		System.out.println(cities.getKey()+" ->"+cities.getValue());
		}
		System.out.println(map.get(1));
		//System.out.println("userId :"+ data.get(0).get("userId"));
	}

}
