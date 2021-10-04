package com.springboot.thymeleafcruddemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.thymeleafcruddemo.entity.Employee;
import com.springboot.thymeleafcruddemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/list")
	public String listEmployees(Model model) {
		/*
		// Get all Employees through Employee Service
		List<Employee> employees = employeeService.findAllEmployees();
		
		// Add List of Employees to Spring MVC Model
		model.addAttribute("employees", employees);
		
		return "employees/list-employees";
		*/
		return findPaginated(1, "firstName", "asc", model);
	}
	
	@GetMapping("/showFormForAddingEmployee")
	public String showFormForAddingEmployee(Model model) {
		// Add empty Employee object to Model Attribute
		Employee employee = new Employee();
		
		model.addAttribute("employee", employee);
		
		return "employees/employee-form";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		// Save the Employee
		employeeService.saveEmployee(employee);
	
		// Redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}
	
	@GetMapping("/showFormForUpdatingEmployee")
	public String showFormForUpdatingEmployee(@RequestParam("employeeId") int employeeId, Model model) {
		// Get the Employee from Employee Service
		Employee employee = employeeService.findEmployeeByID(employeeId);
		
		// Set Employee as a Model Attribute to Pre-Populate the Form
		model.addAttribute("employee", employee);
		
		// Send over to the form
		return "employees/employee-form";
	}
	
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam("employeeId") int employeeId) {
		// Delete the Employee
		employeeService.deleteEmployeeByID(employeeId);
		
		// Redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}
	
	@GetMapping("/searchEmployees")
	public String searchEmployees(@RequestParam("employeeName") String employeeName, Model model) {
		// Search Employees by Name Using Employee Service
		List<Employee> employees = employeeService.searchEmployeeByName(employeeName);
		
		// Set Employees as a Model Attribute to Populate the Form for the Search Employee Name
		model.addAttribute("employees", employees);
		
		return "employees/list-employees";
	}
	
	
	@GetMapping("/page/{pageNumber}")
	public String findPaginated(@PathVariable int pageNumber, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDirection") String sortDirection,
			Model model) {
		// Set Page Size for each Page
		int pageSize = 5;
		
		// Get all Employees from Page using EmployeeService
		Page<Employee> page = employeeService.findPaginated(pageNumber, pageSize, sortField, sortDirection);
		List<Employee> listEmployees = page.getContent();
		
		// Set Pagination Values to Model Attribute
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		// Set Sort Values to Model Attribute
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
		
		// Add List of Employees to Model Attribute
		model.addAttribute("employees", listEmployees);
		
		return "employees/list-employees";
	}
}