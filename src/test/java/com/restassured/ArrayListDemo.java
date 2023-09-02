package com.restassured;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//classname objname = new classname() 
		ArrayList<String> a1 =new ArrayList<String>();		
		System.out.println("size of array a1:"+ a1.size());
		a1.add("Hyderabad");
		a1.add("Delhi");
		a1.add("Chennai");
		a1.add("Mumbai");
		System.out.println("size of array a1:"+ a1.size());
		a1.remove("Delhi");
		System.out.println("size of array a1:"+ a1.size());
		System.out.println("Contents of this array list :"+ a1);
		/*
		System.out.println("Contents of this array list :"+ a1.get(0));
		System.out.println("Contents of this array list :"+ a1.get(1));
		System.out.println("Contents of this array list :"+ a1.get(2));
		*/
		/*
		a1.iterator() // return the start point of the collection 
		hasNext()// will return  true  if  collection has next element
		next() // return next element in the iterator 
		*/
		System.out.println("*****iterator******");
		Iterator itr=a1.iterator();
		while(itr.hasNext())
		{
			System.out.println(itr.next());
		}
		System.out.println("*****for loop******");
		for (String cities :a1)
		{
			System.out.println(cities);
		}
		
		System.out.println(a1.get(0));

	}

}
