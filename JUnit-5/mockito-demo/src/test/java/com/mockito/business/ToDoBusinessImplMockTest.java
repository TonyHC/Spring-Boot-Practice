package com.mockito.business;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mockito.service.ToDoService;

@ExtendWith(MockitoExtension.class)
public class ToDoBusinessImplMockTest {
	private ToDoService toDoServiceMock;
	private ToDoBusinessImpl toDoBusinessImpl;

	@Nested
	class retrieveToDosRelatedToSpringTest {
		@BeforeEach
		void setup() {
			// Mocking is creating objects that stimulate the behavior of real objects
			// Unlike Stubs, Mocks can be dynamically created from code at runtime
			// Creates Mock Object of given class or interface
			toDoServiceMock = mock(ToDoService.class);

			toDoBusinessImpl = new ToDoBusinessImpl(toDoServiceMock);
		}

		@Test
		void Should_ReturnToDos_When_ListIsNotEmpty() {
			// Given: Setup
			List<String> toDos = Arrays.asList("Spring 5", "Unit Testing", "Spring MVC");
			// When a particular method is called, then returns a particular value
			when(toDoServiceMock.retrieveToDos("Dummy")).thenReturn(toDos);

			int filterListSize = 2;
			String firstToDo = "Spring 5";

			// When: Actual method call
			List<String> filterToDos = toDoBusinessImpl.retrieveToDosRelatedToSpring("Dummy");

			// Then: assertions
			assertAll(() -> assertEquals(filterListSize, filterToDos.size()),
					() -> assertEquals(firstToDo, filterToDos.get(0)));
		}

		@Test
		void Should_ReturnToDos_When_ListIsEmpty() {
			// Given
			List<String> toDos = Arrays.asList();
			given(toDoServiceMock.retrieveToDos("Dummy")).willReturn(toDos);
			
			int filterListSize = 0;

			// When
			List<String> filterToDos = toDoBusinessImpl.retrieveToDosRelatedToSpring("Dummy");

			// Then
			assertEquals(filterListSize, filterToDos.size());
		}
		
		@Test
		void Should_VerifyDeleteToDo_When_StringIsNotSpring() {
			// Given
			List<String> toDos = Arrays.asList("Spring 5", "Unit Testing", "Spring MVC");
			given(toDoServiceMock.retrieveToDos("Dummy")).willReturn(toDos);;

			// When
			toDoBusinessImpl.deleteToDosRelatedToSpring("Dummy");

			// Then
			// Verifies this method was called once
			// verify(toDoServiceMock, times(1)).deleteToDo("Unit Testing");
			// BDD syntax for verify(...)
			then(toDoServiceMock).should(times(1)).deleteToDo("Unit Testing");
			
			// Verifies this method was never called
			// verify(toDoServiceMock, never()).deleteToDo("Spring 5");
			// verify(toDoServiceMock, never()).deleteToDo("Spring MVC");
			// BDD syntax for verify(...)
			then(toDoServiceMock).should(never()).deleteToDo("Spring 5");
			then(toDoServiceMock).should(never()).deleteToDo("Spring MVC");
		}
		
		@Test
		void Should_CaptureDeleteToDoArgument_When_StringIsNotSpring() {
			// Declare Argument Captor
			ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
			
			// Given
			List<String> toDos = Arrays.asList("Spring 5", "Unit Testing", "Spring MVC");
			given(toDoServiceMock.retrieveToDos("Dummy")).willReturn(toDos);;
			
			// When
			toDoBusinessImpl.deleteToDosRelatedToSpring("Dummy");

			// Then
			// BDD syntax for verify(...)
			// Capture the argument from deleteToDo once
			then(toDoServiceMock).should(times(1)).deleteToDo(stringArgumentCaptor.capture());
			assertEquals(stringArgumentCaptor.getValue(), "Unit Testing");
		}
		
		@Test
		void Should_CaptureDeleteToDoArgumentMultipleTimes_When_StringIsNotSpring() {
			// Declare Argument Captor
			ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
			
			// Given
			List<String> toDos = Arrays.asList("Spring 5", "Unit Testing", "Mockito");
			given(toDoServiceMock.retrieveToDos("Dummy")).willReturn(toDos);;
			
			List<String> capturedToDos = Arrays.asList("Unit Testing", "Mockito");
			
			// When
			toDoBusinessImpl.deleteToDosRelatedToSpring("Dummy");

			// Then
			// BDD syntax for verify(...)
			// Capture the argument from deleteToDo multiple times (twice)
			then(toDoServiceMock).should(times(2)).deleteToDo(stringArgumentCaptor.capture());
			assertEquals(stringArgumentCaptor.getAllValues(), capturedToDos);
		}
	}
}