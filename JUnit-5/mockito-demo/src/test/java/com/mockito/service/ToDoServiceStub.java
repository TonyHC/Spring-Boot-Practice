package com.mockito.service;

import java.util.Arrays;
import java.util.List;

public class ToDoServiceStub implements ToDoService {
	public List<String> retrieveToDos(String user) {
		return Arrays.asList("Spring 5", "Unit Testing", "Spring MVC");
	}

	@Override
	public void deleteToDo(String toDo) {

	}
}