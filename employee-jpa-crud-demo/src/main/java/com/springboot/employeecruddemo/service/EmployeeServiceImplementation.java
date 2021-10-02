package com.springboot.employeecruddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.employeecruddemo.dao.EmployeeDAO;
import com.springboot.employeecruddemo.entity.Employee;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
	@Autowired
	@Qualifier("employeeDAOJpaImplementation")
	private EmployeeDAO employeeDAO;
	
	@Override
	@Transactional
	public List<Employee> findAllEmployees() {
		// Delegate Call to EmployeeDAO to handle
		return employeeDAO.findAllEmployees();
	}

	@Override
	@Transactional
	public Employee findEmployeeByID(int employeeID) {
		// Delegate Call to EmployeeDAO to handle
		return employeeDAO.findEmployeeByID(employeeID);
	}

	@Override
	@Transactional
	public void saveEmployee(Employee employee) {
		// Delegate Call to EmployeeDAO to handle
		employeeDAO.saveEmployee(employee);
	}

	@Override
	@Transactional
	public void deleteEmployeeByID(int employeeID) {
		// Delegate Call to EmployeeDAO to handle
		employeeDAO.deleteEmployeeByID(employeeID);
	}
}