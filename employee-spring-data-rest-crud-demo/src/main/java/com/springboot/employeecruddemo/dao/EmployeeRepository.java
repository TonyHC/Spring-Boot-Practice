package com.springboot.employeecruddemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.springboot.employeecruddemo.entity.Employee;

// http://localhost:8080/api/members instead of http://localhost:8080/api/employees
// @RepositoryRestResource(path="members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	// Custom HTTP Endpoint: http://localhost:/api/employees/search/byLastName?lastName
	// Without @RestResource: http://localhost:8080/api/employees/search/findByLastName?lastName
	// @RestResource(path = "byLastName", rel = "customFindMethod")
	// @Param uses the entity class (Employee) properties and calls appropriate getter/setter methods
	List<Employee> findByLastName(@Param("lastName") String lastName);
}