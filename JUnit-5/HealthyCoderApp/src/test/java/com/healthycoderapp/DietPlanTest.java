package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

class DietPlanTest {
	private DietPlanner dietPlanner;
	
	// This function will be invoke before each Unit Test
	@BeforeEach
	void setup() {
		dietPlanner = new DietPlanner(20, 30, 50);
	}
	
	// This function will be invoke after each Unit Test
	// @AfterEach used much more infrequently compared to @BeforeEach,
	// used for more complicated unit tests
	@AfterEach
	void afterEach() {
		System.out.println("A unit test was finished");
	}
	
	// Repeats this Test X times and displays the Test name
	@RepeatedTest(value = 5, name = RepeatedTest.LONG_DISPLAY_NAME)
	void should_ReturnCorrectDietPlan_when_CorrectCoder() {
		// Given
		Coder coder = new Coder(1.82, 75.0, 26, Gender.MALE);
		DietPlan expected = new DietPlan(2202, 110, 73, 275);
		
		// When
		DietPlan actual = dietPlanner.calculateDiet(coder);
		
		// Then
		assertAll(
				() -> assertEquals(expected.getCalories(), actual.getCalories()),
				() -> assertEquals(expected.getCarbohydrate(), actual.getCarbohydrate()),
				() -> assertEquals(expected.getFat(), actual.getFat()),
				() -> assertEquals(expected.getProtein(), actual.getProtein())
		);
	}
}