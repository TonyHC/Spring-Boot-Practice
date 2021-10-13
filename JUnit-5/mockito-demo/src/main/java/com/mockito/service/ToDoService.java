package com.mockito.service;

import java.util.List;

public interface ToDoService {
	public List<String> retrieveToDos(String user);
	public void deleteToDo(String toDo);
}