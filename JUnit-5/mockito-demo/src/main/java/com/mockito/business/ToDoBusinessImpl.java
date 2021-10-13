package com.mockito.business;

import java.util.ArrayList;
import java.util.List;

import com.mockito.service.ToDoService;

// ToDoBusinessImpl: SUT (System Under Test)
// ToDoService: Dependency
public class ToDoBusinessImpl {
	private ToDoService toDoService;
	
	public ToDoBusinessImpl(ToDoService toDoService) {
		this.toDoService = toDoService;
	}
	
	public List<String> retrieveToDosRelatedToSpring(String user) {
		List<String> filterToDos = new ArrayList<String>();
		List<String> toDos = toDoService.retrieveToDos(user);
		
		for (String toDo : toDos) {
			if (toDo.contains("Spring")) {
				filterToDos.add(toDo);
			}
		}
		
		return filterToDos;
	}
	
	public void deleteToDosRelatedToSpring(String user) {
		List<String> toDos = toDoService.retrieveToDos(user);
		
		for (String toDo : toDos) {
			if (!toDo.contains("Spring")) {
				toDoService.deleteToDo(toDo);
			}
		}
	}
}