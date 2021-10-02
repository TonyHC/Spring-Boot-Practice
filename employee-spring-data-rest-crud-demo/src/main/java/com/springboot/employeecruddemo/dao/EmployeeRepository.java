package com.springboot.employeecruddemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.springboot.employeecruddemo.entity.Employee;

// http://localhost:8080/api/members instead of http://localhost:8080/api/employees
// @RepositoryRestResource(path="members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	@Query("SELECT e FROM Employee e WHERE e.lastName LIKE ?1%")
	List<Employee> findEmployeeByLastName(String lastName);
}