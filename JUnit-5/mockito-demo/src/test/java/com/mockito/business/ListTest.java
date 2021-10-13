package com.mockito.business;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ListTest {
	private List listMock;

	@Nested
	class UtilListTests {
		@BeforeEach
		void setup() {
			listMock = mock(List.class);
		}

		@Test
		void Should_ReturnListSize_When_ListIsNotEmpty() {
			// Given
			int listSize = 2;
			
			// When
			when(listMock.size()).thenReturn(2);
			
			// Then
			assertEquals(listSize, listMock.size());
		}
		
		@Test
		void Should_ReturnMultipleValues_When_ListIsNotEmpty() {
			// Given
			int listSizeFirstCall = 2;
			int listSizeSecondCall = 3;
			
			// When
			// First call of size() returns '2', then '3'
			when(listMock.size()).thenReturn(2).thenReturn(3);
			
			// Then
			assertAll(
				() -> assertEquals(listSizeFirstCall, listMock.size()),
				() -> assertEquals(listSizeSecondCall, listMock.size())
			);
		}
		
		@Test
		void Should_ReturnFirstAndSecondIndex_When_ListIsNotEmpty() {
			// Given
			String indexValue = "Spring";
			
			// When
			// Argument Matcher: Any Integer or non-null Integer
			when(listMock.get(0)).thenReturn("Spring");
			
			// Then
			assertEquals(indexValue, listMock.get(0));
			// When you don't tell a Mockito Mock what to do, they will return default value:
			// In this case, return null when accessing any index besides first index
			assertEquals(null, listMock.get(1));
		}
		
		@Test
		void Should_ReturnAnyIndex_When_ListIsNotEmpty() {
			// Given
			String indexValue = "Spring";
			
			// When
			// Argument Matcher: Any Integer or non-null Integer
			when(listMock.get(anyInt())).thenReturn("Spring");
			
			// Then
			assertEquals(indexValue, listMock.get(0));
			// Regardless of index, will return "Spring"
			assertEquals(indexValue, listMock.get(1));
		}
		
		@Test
		void Should_ThrowRuntimeException_When_ListIsEmpty() {
			// Given
			int index = 1;
			
			// When
			// Argument Matcher: Any Integer or non-null Integer
			// Throw an exception
			when(listMock.get(anyInt())).thenThrow(new RuntimeException("Test Exception"));
			
			// Then
			// Retrieving any index from List will throw a RuntimException
			assertThrows(RuntimeException.class, () -> listMock.get(index));
		}
	}
}