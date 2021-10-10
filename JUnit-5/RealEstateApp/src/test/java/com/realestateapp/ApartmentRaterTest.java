package com.realestateapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ApartmentRaterTest {
	@Nested
	class rateApartmentTests {
		@ParameterizedTest
		@CsvSource(value = { "72.0, 250000.0, 0", "48.0, 350000.0, 1", "30.0, 600000.0, 2" })
		void should_ReturnCorrectRating_When_CorrectApartment(double area, BigDecimal price, int rating) {
			// Given
			Apartment apartment = new Apartment(area, price);
			int expectedRating = rating;

			// When
			int actualRating = ApartmentRater.rateApartment(apartment);

			// Then
			assertEquals(expectedRating, actualRating);
		}

		@Test
		void should_ReturnErrorValue_When_IncorrectApartment() {
			// Given
			Apartment apartment = new Apartment(0.0, new BigDecimal(35000.0));
			int expectedRating = -1;

			// When
			int actualRating = ApartmentRater.rateApartment(apartment);

			// Then
			assertEquals(expectedRating, actualRating);
		}
	}

	@Nested
	class calculateAverageRatingTests {
		@Test 
		void should_CalculateAverageRating_When_CorrectApartmentList() {
			// Given
			List<Apartment> apartments = new ArrayList<>();
			apartments.add(new Apartment(72.0, new BigDecimal(250000.0)));
			apartments.add(new Apartment(48.0, new BigDecimal(350000.0)));
			apartments.add(new Apartment(30.0, new BigDecimal(600000.0)));
			
			double expectedRating = 1.0;
			
			// When
			double actualRating = ApartmentRater.calculateAverageRating(apartments);
			
			// Then
			assertEquals(expectedRating, actualRating);
		}
		
		@Test
		void should_ThrowExceptionInCalculateAverageRating_When_EmptyApartmentList() {
			// Given 
			List<Apartment> apartments = new ArrayList<>();

			// When
			Executable executable = () -> ApartmentRater.calculateAverageRating(apartments);
			
			// Then
			assertThrows(RuntimeException.class, executable);
		}
	}
}