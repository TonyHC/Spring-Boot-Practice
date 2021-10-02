package com.springboot.employeecruddemo.service;

import java.util.List;

import com.springboot.employeecruddemo.entity.Employee;

public interface EmployeeService {
	List<Employee> findAllEmployees();
	Employee findEmployeeByID(int employeeID);
	void saveEmployee(Employee employee);
	void deleteEmployeeByID(int employeeID);
}