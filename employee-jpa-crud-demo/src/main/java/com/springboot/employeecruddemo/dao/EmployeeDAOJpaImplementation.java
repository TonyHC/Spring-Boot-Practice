package com.springboot.employeecruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.employeecruddemo.entity.Employee;

@Repository
public class EmployeeDAOJpaImplementation implements EmployeeDAO {
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Employee> findAllEmployees() {
		// Create a Query to get List of Employee
		Query query = entityManager.createQuery("FROM Employee");
		
		// Execute Query and get result list
		List<Employee> employees = query.getResultList();
		
		// Return the results
		return employees;
	}

	@Override
	public Employee findEmployeeByID(int employeeID) {
		// Get Employee by its ID (Primary Key)
		Employee employee = entityManager.find(Employee.class, employeeID);
		
		// Return the employee
		return employee;
	}

	@Override
	public void saveEmployee(Employee employee) {
		// Save or update the Employee
		Employee dbEmployee = entityManager.merge(employee);
		
		// Update with ID from DB, so we can get generated id from Save/Update
		employee.setId(dbEmployee.getId());
	}

	@Override
	public void deleteEmployeeByID(int employeeID) {
		/*
		// Delete Employee with Primary Key
		Query query = entityManager.createQuery("DELETE FROM Employee WHERE id =: employeeId");
		
		// Set the parameter
		query.setParameter("employeeId", employeeID);
		
		query.executeUpdate();
		*/
		
		// Same as above
		Employee tmpEmployee = entityManager.find(Employee.class, employeeID);
		
		entityManager.remove(tmpEmployee);
	}
}