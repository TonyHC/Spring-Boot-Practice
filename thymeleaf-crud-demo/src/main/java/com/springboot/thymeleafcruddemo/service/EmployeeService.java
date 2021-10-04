package com.springboot.thymeleafcruddemo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.springboot.thymeleafcruddemo.entity.Employee;

public interface EmployeeService {
	List<Employee> findAllEmployees();
	Employee findEmployeeByID(int employeeID);
	void saveEmployee(Employee employee);
	void deleteEmployeeByID(int employeeID);
	List<Employee> searchEmployeeByName(String employeeName);
	Page<Employee> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
}