package com.springboot.employeecruddemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.employeecruddemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	@Query("SELECT e FROM Employee e WHERE e.lastName LIKE ?1%")
	List<Employee> findEmployeeByLastName(String lastName);
}