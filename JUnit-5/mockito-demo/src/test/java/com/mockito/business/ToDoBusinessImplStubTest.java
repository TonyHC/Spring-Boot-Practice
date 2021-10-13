package com.mockito.business;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mockito.service.ToDoService;
import com.mockito.service.ToDoServiceStub;

@ExtendWith(MockitoExtension.class)
public class ToDoBusinessImplStubTest {
	private ToDoService toDoServiceStub;
	private ToDoBusinessImpl toDoBusinessImpl;
	
	@Nested
	class retrieveToDosRelatedToSpringTest {
		@BeforeEach
		void setup() {
			toDoServiceStub = new ToDoServiceStub();
			toDoBusinessImpl = new ToDoBusinessImpl(toDoServiceStub);
		}

		@Test
		void Should_ReturnToDos_When_UsingAStub() {
			// Given
			int filterListSize = 2;
			String firstToDo = "Spring 5";

			// When
			List<String> filterToDos = toDoBusinessImpl.retrieveToDosRelatedToSpring("dummy");

			// Then
			assertAll(
					() -> assertEquals(filterListSize, filterToDos.size()),
					() -> assertEquals(firstToDo, filterToDos.get(0))
			);
		}	
	}
}