package com.springboot.thymeleafdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.thymeleafdemo.model.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	private List<Employee> employees;
	
	@PostConstruct
	private void loadEmployeeData() {
		employees = new ArrayList<>();
		
		employees.add(new Employee("Tom", "Banks", "TB@mail.com"));
		employees.add(new Employee("Will", "Banks", "WB@gmail.com"));
		employees.add(new Employee("Arte", "Banks", "AB@hotmail.com"));
	}
	
	@GetMapping("/list")
	public String listEmployees(Model model) {
		// Add List of Employees to Spring MVC Model
		model.addAttribute("employees", employees);
		
		return "list-employees";
	}
}