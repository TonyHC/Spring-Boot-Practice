package com.springboot.thymeleafcruddemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.thymeleafcruddemo.entity.Employee;
import com.springboot.thymeleafcruddemo.exception.EmployeeNotFoundException;
import com.springboot.thymeleafcruddemo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> findAllEmployees() {
		// Delegate Call to EmployeeRepository 
		List<Employee> employees = employeeRepository.findAllByOrderByLastNameAsc();
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

	@Override
	public List<Employee> searchEmployeeByName(String employeeName) {
		List<Employee> employees = null;
		
		// If form input data is not null, empty or length of String is less than or equal to zero
		if (employeeName != null && employeeName.trim().length() > 0) {
			// Then, perform a Query to search for Employees whose first or last name matches form input data
			// and sort by last name in ascending order
			employees = employeeRepository.searchEmployeeByFirstOrLastName(employeeName);
		} else {
			// Else, perform a Query to get all Employees and sort by last name ascending order 
			employees = employeeRepository.findAllByOrderByLastNameAsc();
		}
		
		// Return the results
		return employees;
	}

	@Override
	public Page<Employee> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {
		// Create a Sort based on if Sort Direction in URL is same as method call for ascending order
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		// Create a Pageable object to perform PageRequest with sorted parameters applied
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		
		// Return all Employees from Pageable object
		return this.employeeRepository.findAll(pageable);
	}
}