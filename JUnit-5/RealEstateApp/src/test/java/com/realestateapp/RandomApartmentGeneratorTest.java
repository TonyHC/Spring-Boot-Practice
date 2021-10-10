package com.realestateapp;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;

class RandomApartmentGeneratorTest {
	private static final double MAX_MULTIPLIER = 4.0;
	
	@Nested
	class GeneratorDefaultParameteresTest {
		private RandomApartmentGenerator randomApartmentGenerator;
		
		@BeforeEach
		void setup() {
			randomApartmentGenerator = new RandomApartmentGenerator();
		}
		
		@RepeatedTest(value = 5, name = RepeatedTest.LONG_DISPLAY_NAME)
		void should_GenerateCorrectApartment_When_DefaultMinAreaMinPrice() {
			// Given
			double minArea = 30;
			double maxArea = minArea * RandomApartmentGeneratorTest.MAX_MULTIPLIER;
			BigDecimal minPricePerSquareMeter = new BigDecimal(3000.0);
			BigDecimal maxPricePerSquareMeter = minPricePerSquareMeter.multiply(new BigDecimal(RandomApartmentGeneratorTest.MAX_MULTIPLIER));
			
			// When
			Apartment apartment = randomApartmentGenerator.generate();
			
			// Then
			BigDecimal minApartmentPrice = new BigDecimal(apartment.getArea()).multiply(minPricePerSquareMeter);
			BigDecimal maxApartmentPrice = new BigDecimal(apartment.getArea()).multiply(maxPricePerSquareMeter);
			
			assertAll(
					() -> assertTrue(apartment.getArea() >= minArea),
					() -> assertTrue(apartment.getArea() <= maxArea),
					() -> assertTrue(apartment.getPrice().compareTo(minApartmentPrice) >= 0),
					() -> assertTrue(apartment.getPrice().compareTo(maxApartmentPrice) <= 0)
			);
		}
	}
	
	@Nested
	class GeneratorCustomParametersTests {
		private RandomApartmentGenerator randomApartmentGenerator;
		private double minArea = 15.0;
		private BigDecimal minPricePerSquareMeter = new BigDecimal(5000.0);
		
		@BeforeEach
		void setup() {
			randomApartmentGenerator = new RandomApartmentGenerator(minArea, minPricePerSquareMeter);
		}
		
		@RepeatedTest(value = 5, name = RepeatedTest.LONG_DISPLAY_NAME)
		void should_GenerateCorrectApartment_When_DefaultMinAreaMinPrice() {
			// Given
			double minArea = this.minArea;
			double maxArea = minArea * RandomApartmentGeneratorTest.MAX_MULTIPLIER;
			BigDecimal minPricePerSquareMeter = this.minPricePerSquareMeter;
			BigDecimal maxPricePerSquareMeter = minPricePerSquareMeter.multiply(new BigDecimal(RandomApartmentGeneratorTest.MAX_MULTIPLIER));
			
			// When
			Apartment apartment = randomApartmentGenerator.generate();
			
			// Then
			BigDecimal minApartmentPrice = new BigDecimal(apartment.getArea()).multiply(minPricePerSquareMeter);
			BigDecimal maxApartmentPrice = new BigDecimal(apartment.getArea()).multiply(maxPricePerSquareMeter);
			
			assertAll(
					() -> assertTrue(apartment.getArea() >= minArea),
					() -> assertTrue(apartment.getArea() <= maxArea),
					() -> assertTrue(apartment.getPrice().compareTo(minApartmentPrice) >= 0),
					() -> assertTrue(apartment.getPrice().compareTo(maxApartmentPrice) <= 0)
			);
		}
	}
}