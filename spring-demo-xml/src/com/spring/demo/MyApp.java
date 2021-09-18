package com.spring.demo;

public class MyApp {

	public static void main(String[] args) {
		// Create the object (still hard coded)
		Coach theCoach = new TrackCoach();
		
		// Use the object
		System.out.println(theCoach.getDailyWorkout());
	}
}
