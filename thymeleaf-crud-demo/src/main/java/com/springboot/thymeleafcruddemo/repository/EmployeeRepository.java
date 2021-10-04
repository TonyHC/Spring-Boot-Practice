package com.springboot.thymeleafcruddemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.thymeleafcruddemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	// Spring Data JPA will parse the method name
	// Looks for a specific format and pattern
	// Creates appropriate query behind the scenes: FROM Employee ORDER BY lastName asc
	// Sort all Employees by last name ascending order: order by last name
	List<Employee> findAllByOrderByLastNameAsc();
	
	// Manually define query using @Query
	@Query("SELECT e FROM Employee e WHERE LOWER(e.firstName) LIKE %?1% OR LOWER(e.lastName) LIKE %?1% ORDER BY e.lastName")
	List<Employee> searchEmployeeByFirstOrLastName(String employeeName);
}