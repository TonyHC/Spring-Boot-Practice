package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

class BMICalculatorTest {
	private String environment = "dev";
	
	// Can have any method name but must be a static method
	// Used for operations that should be run exactly once before all Unit Test are run
	// Usually applied to operations that are too expensive to be run before each Unit Tests
	// such as setting up DB connections or starting up servers
	@BeforeAll
	static void beforeAll() {
		System.out.println("Before all unit tests");
	}
	
	// Opposite of @BeforeAll
	// Typically used to closed DB connections or close servers
	@AfterAll
	static void afterAll() {
		System.out.println("After all unit tests");
	}
	
	// Inner Class to organize related Unit Tests in a structured way
	@Nested
	// Change the name of a Nested Class
	@DisplayName("DietRecommendedTests")
	class isDietRecommendedTests {
		// @ParameterizedTest is used to signal that the annotated method is a parameterized test method
		@ParameterizedTest
		// @ValueSource is one of the simplest possible sources
		// It lets you specify a single array of literal values and can only be used for 
		// providing a single argument per parameterized test invocation
		@ValueSource(doubles = {85.0, 89.0, 95.0, 110.0})
		// Change the name of a Unit Test
		@DisplayName("returnTrueForIsDietRecommended")
		// Test is skipped
		// @Disabled
		// Test is skipped based on Operating System
		@DisabledOnOs(OS.LINUX)
		// Should-When Naming Convention
		void should_ReturnTrue_when_DietRecommend(Double coderWeight) {
			// Given: initial conditions
			double weight = coderWeight;
			double height = 1.72;
			
			// When: Invoke the method on a test
			boolean recommended = BMICalculator.isDietRecommended(weight, height);
			
			// Then: Provide the assertion
			// assertTrue(...): Pass the test if result is true, 
			// Otherwise fails the test if result is false
			assertTrue(recommended);
		}
		
		// The display name to be used for individual invocations of the parameterized test; 
		// never blank or consisting solely of whitespace. 
		@ParameterizedTest(name = "weight = {0}, height = {1}")
		// Comma-Separated List that allows for multiple values to be inserted into Test Case
		// @CsvSource(value = {"62.3, 1.92", "58.4, 1.78", "72.0, 1.98"})
		// Load values from CSB File to be inserted into Test Case
		// Specify the location of the file and which lines to skip (ignore)
		@CsvFileSource(resources = "/diet-recommended-input-data.csv", numLinesToSkip = 1)
		void should_ReturnFalse_when_DietRecommend(Double coderWeight, Double coderHeight) {
			// Given: initial conditions
			double weight = coderWeight;
			double height = coderHeight;
			
			// When: Invoke the method on a test
			boolean recommended = BMICalculator.isDietRecommended(weight, height);
			
			// Then: Provide the assertion
			// assertFalse(...): Pass the test if result is false, 
			// Otherwise fails the test if result is true
			assertFalse(recommended);
		}
		
		@Test
		void should_ThrowArithmeticException_when_HeightIsZero() {
			// Given: initial conditions
			double weight = 50.0;
			double height = 0.0;
			
			// When: Invoke the method on a test
			// Executable is a functional interface that can be used to implement 
			// any generic block of code that potentially throws a Throwable
			// Executable required a lambda expression
			Executable executable = () -> BMICalculator.isDietRecommended(weight, height);
			
			// Then: Provide the assertion
			// assertThrows(Exception class, Executable): Pass the test if result throws an exception, 
			// Otherwise fails the test if result doesn't throw an exception
			assertThrows(ArithmeticException.class, executable);
		}
	}
	
	@Nested
	class FindCoderWithWorstBMITests {
		@Test
		void show_ReturnCoderWithWorstBMI_when_CoderListIsNotEmpty() {
			// Given
			List<Coder> coders = new ArrayList<>();
			coders.add(new Coder(1.80, 60.0));
			coders.add(new Coder(1.82, 98.0));
			coders.add(new Coder(1.82, 84.0));
			
			// When
			Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
			
			// Then
			// assertAll(...): Test with multiple assertions
			// Used for assertions that make sense as a whole
			// Requires an lambda expression
			assertAll(
				// assertEquals(...): Pass the test if results are equal,
				// Otherwise fails the test if results are not equal;
				() -> assertEquals(1.82, coderWorstBMI.getHeight()),
				() -> assertEquals(98.0, coderWorstBMI.getWeight())
			);
		}
		
		@Test 
		void show_ReturnNullWorstBMICoder_when_CoderListIsEmpty() {
			// Given
			List<Coder> coders = new ArrayList<>();
			
			// When
			Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
			
			// Then
			// assertNull(...): Test for null values
			// Passes the test if result is null, otherwise fails the test if result is not null
			assertNull(coderWorstBMI);
		}
	}
	
	@Nested
	class GetBMIScoresTests {
		@Test
		void should_ReturnCoderWithWorstBMIIn10Ms_When_CoderListHas100000Elements() {
			// assumeTrue(boolean assumption, String message): 
			// assumption: Validate the given assumption
			// message: The message to be included in the TestAbortedException if the assumption is invalid
			// BMICalculatorTest.this.environment: Refers to inner class variable 'environment'
			assumeTrue(BMICalculatorTest.this.environment.equals("dev"), "Invalid assumption");
			
			// Given
			List<Coder> coders = new ArrayList<>();
			// Create 100,000 Coder objects
			for (int i = 0; i < 100000; i++) {
				coders.add(new Coder(1.0 + i, 10.0 + i));
			}
			
			// When
			Executable executable = () -> BMICalculator.getBMIScores(coders);
			
			// Then
			// assertTimeout(Time Duration, Executable object)
			assertTimeout(Duration.ofMillis(10), executable);
		}
		
		@Test 
		void show_ReturnCorrectBMIScoreArray_when_CoderListIsNotEmpty() {
			// Given
			List<Coder> coders = new ArrayList<>();
			coders.add(new Coder(1.80, 60.0));
			coders.add(new Coder(1.82, 98.0));
			coders.add(new Coder(1.82, 64.7));
			
			double[] expectedResults = {18.52, 29.59, 19.53};
			
			// When
			double[] bmiScores = BMICalculator.getBMIScores(coders);
			
			// Then
			// Using assertEquals() here fails because the two Arrays have the same value 
			// but have different references (points to different values in memory)
			// assertArrayEquals(Array, Array): Compares two Arrays for equality (same values)
			// Passes the test if both arrays are equal, otherwise fails the test if both arrays are not equal
			assertArrayEquals(expectedResults, bmiScores);
		}
	}
}