package com.springboot.employeecruddemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.employeecruddemo.dao.EmployeeRepository;
import com.springboot.employeecruddemo.entity.Employee;
import com.springboot.employeecruddemo.rest.EmployeeNotFoundException;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> findAllEmployees() {
		// Delegate Call to EmployeeRepository 
		List<Employee> employees = employeeRepository.findEmployeeByLastName("H");
		return employees;
	}

	@Override
	public Employee findEmployeeByID(int employeeID) {
		// Delegate Call to EmployeeRepository 
		// Optional: Different pattern instead of having to check nulls
		Optional<Employee> result = employeeRepository.findById(employeeID);
		
		Employee employee = null;
		
		// If value is present, then return the value
		if (result.isPresent()) {
			employee = result.get();
		} else {
			// Otherwise, throw custom Employee exception
			throw new EmployeeNotFoundException("Employee ID not found: " + employeeID);
		}
	
		return employee;
	}

	@Override
	public void saveEmployee(Employee employee) {
		// Delegate Call to EmployeeRepository 
		employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployeeByID(int employeeID) {
		// Delegate Call to EmployeeRepository 
		employeeRepository.deleteById(employeeID);
	}
}