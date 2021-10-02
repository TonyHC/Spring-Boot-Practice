package com.springboot.employeecruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.employeecruddemo.entity.Employee;
import com.springboot.employeecruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	@Autowired
	private EmployeeService employeeService;
	
	// Mapping for GET /employees: Get all Employees
	@GetMapping("/employees")
	public List<Employee> findAllEmployees() {
		return employeeService.findAllEmployees();
	}
	
	// Maping for GET /employees/{employeeID}: Get a specific Employee
	@GetMapping("/employees/{employeeID}")
	public Employee getEmployee(@PathVariable int employeeID) {
		Employee employee = employeeService.findEmployeeByID(employeeID);
		
		if (employee == null) {
			throw new EmployeeNotFoundException("Employee ID not found " + employeeID);
		}
		
		return employee;
	}
	
	// Mapping for POST /employees: Add new Employee
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		employee.setId(0);
		employeeService.saveEmployee(employee);
		return employee;
	}
	
	// Mapping for PUT /employees: Update existing Employee
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee) {
		employeeService.saveEmployee(employee);
		return employee;
	}
	
	// Mapping for DELETE /employees/{employeeID}
	@DeleteMapping("/employees/{employeeID}")
	public String deleteEmployee(@PathVariable int employeeID) {
		Employee tmpEmployee = employeeService.findEmployeeByID(employeeID);
		
		if (tmpEmployee == null) {
			throw new EmployeeNotFoundException("Employee ID not found: " + employeeID);
		}
		
		employeeService.deleteEmployeeByID(employeeID);
		
		return "Deleted Employee ID: " + employeeID;
	}
}