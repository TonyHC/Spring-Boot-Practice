package com.java.jackson.json.demo;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Driver {
	public static void main(String[] args) {
		try {
			// Create Object Mapper
			ObjectMapper mapper = new ObjectMapper();
			
			// Read JSON File and map/convert to Java POJO
			Student student = 
					mapper.readValue(new File("data/sample-full.json"), Student.class);
			
			// Print First Name and Last Name
			System.out.println("First Name: " + student.getFirstName());
			System.out.println("Last Name: " + student.getLastName());
			
			// Print out Address: Street and City
			Address address = student.getAddress();
			
			System.out.println("Street: " + address.getStreet());
			System.out.println("City: " + address.getCity());
			
			// Print out Languages
			for (String language : student.getLanguages()) {
				System.out.println(language);
			}
		} catch (Exception exception) {
			exception.printStackTrace();;
		}

	}
}