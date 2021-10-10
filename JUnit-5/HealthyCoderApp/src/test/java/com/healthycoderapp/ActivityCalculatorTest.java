package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ActivityCalculatorTest {
	@ParameterizedTest
	@CsvSource(value = {"40, 1"})
	void should_ReturnBad_When_AvgBelow20(int weeklyCardioMins, int weeklyWorkouts) {
		// Given
		int mins = weeklyCardioMins;
		int workouts = weeklyWorkouts;
		
		// When
		String rating = ActivityCalculator.rateActivityLevel(mins, workouts);
		
		// Then
		assertEquals("bad", rating);
	}
	
	@ParameterizedTest
	@CsvSource(value = {"40, 3"})
	void should_ReturnAverage_When_AvgBetween20And40(int weeklyCardioMins, int weeklyWorkouts) {
		// Given
		int mins = weeklyCardioMins;
		int workouts = weeklyWorkouts;
		
		// When
		String rating = ActivityCalculator.rateActivityLevel(mins, workouts);
		
		// Then
		assertEquals("average", rating);
	}
	
	@ParameterizedTest
	@CsvSource(value = {"40, 7"})
	void should_ReturnGood_When_AvgAbove40(int weeklyCardioMins, int weeklyWorkouts) {
		// Given
		int mins = weeklyCardioMins;
		int workouts = weeklyWorkouts;
		
		// When
		String rating = ActivityCalculator.rateActivityLevel(mins, workouts);
		
		// Then
		assertEquals("good", rating);
	}
	
	@ParameterizedTest
	@CsvSource(value = {"-40, 7"})
	void should_ThrowException_When_InputBelowZero(int weeklyCardioMins, int weeklyWorkouts) {
		// Given
		int mins = weeklyCardioMins;
		int workouts = weeklyWorkouts;
		
		// When
		Executable executable = () -> ActivityCalculator.rateActivityLevel(mins, workouts);
		
		// Then
		assertThrows(RuntimeException.class, executable);
	}
}