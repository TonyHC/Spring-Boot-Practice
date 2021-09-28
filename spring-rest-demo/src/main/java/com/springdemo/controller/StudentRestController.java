package com.springdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springdemo.entity.Student;
import com.springdemo.rest.StudentNotFoundException;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	private List<Student> students;
	
	// @PostConstruct to load Student Data after Bean created once
	@PostConstruct
	public void loadData() {
		students = new ArrayList<>();

		students.add(new Student("Thomas", "Noir"));
		students.add(new Student("Kyle", "Boris"));
		students.add(new Student("Tucker", "Lapis"));
	}
	
	// Define REST endpoint for "/api/students"
	@GetMapping("/students")
	public List<Student> getStudents() {
		return students;
	}
	
	// Define REST endpoint for "/students/{studentID}"
	@GetMapping("/students/{studentID}")
	public Student getStudent(@PathVariable int studentID) {
		// If Student is not found by index, then throw StudentNotFoundException
		if (studentID >= students.size() || studentID < 0) {
			throw new StudentNotFoundException("Student ID not found: " + studentID);
		}
		
		// Return Student at index
		return students.get(studentID);
	}
}