package com.springboot.employeecruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.employeecruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImplementation implements EmployeeDAO {
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Employee> findAllEmployees() {
		// Get the hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// Create a Query to Get all Employees
		Query<Employee> query = currentSession.createQuery("FROM Employee", Employee.class);
		
		// Execute the query and get result list
		List<Employee> employees = query.getResultList();
		
		// Return the results
		return employees;
	}

	@Override
	public Employee findEmployeeByID(int employeeID) {
		// Get the hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// Create a Query to get Employee by ID (Primary Key)
		Query<Employee> query = currentSession.createQuery("FROM Employee WHERE id =: employeeId", Employee.class);
		query.setParameter("employeeId", employeeID);
		
		// Execute the query and get Employee
		Employee employee = query.getSingleResult();
		
		// Return Employee
		return employee;
	}

	@Override
	public void saveEmployee(Employee employee) {
		// Get the hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// Save or Update Employee: If ID or Primary Key = 0, then SAVE/INSERT Employee, else UPDATE Employee
		currentSession.saveOrUpdate(employee);
	}

	@Override
	public void deleteEmployeeByID(int employeeID) {
		// Get the hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// Delete Employee by its ID (Primary Key)
		Query query = currentSession.createQuery("DELETE FROM Employee WHERE id =: employeeId");
		query.setParameter("employeeId", employeeID);
		
		query.executeUpdate();
	}
}