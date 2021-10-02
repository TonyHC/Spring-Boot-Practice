package com.springboot.employeecruddemo.dao;

import com.springboot.employeecruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
	List<Employee> findAllEmployees();
	Employee findEmployeeByID(int employeeID);
	void saveEmployee(Employee employee);
	void deleteEmployeeByID(int employeeID);
}