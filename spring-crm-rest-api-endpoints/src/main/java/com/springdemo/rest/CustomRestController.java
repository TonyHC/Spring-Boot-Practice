package com.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springdemo.entity.Customer;
import com.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomRestController {
	// Reference the CustomerService
	@Autowired
	private CustomerService customerService;
	
	// Mapping for GET /customers: Get all Customers
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}
	
	// Mapping for GET /customers/{customerID}: Get a Customer by ID (primary key)
	@GetMapping("/customers/{customerID}")
	public Customer getCustomer(@PathVariable int customerID) {
		// Get the Customer by its ID
		Customer customer = customerService.getCustomer(customerID);
		
		// If we can't find the Customer by its ID, then we throw
		// an Custom Exception with Custom Error Response 		
		if (customer == null) {
			throw new CustomerNotFoundException("Customer ID not found: " + customerID);
		}
		
		return customer;
	}
	
	// Mapping for POST /customers: Add new Customer
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer customer) {
		// Set customer ID to 0, so it Inserts new Customer
		customer.setId(0);
		
		customerService.saveCustomer(customer);
		
		return customer;
	}
	
	// Mapping for PUT /customers: Update an existing Customer
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);
		return customer;
	}
	
	// Mapping for DELETE /customers/{customerID}: Delete an existing Customer if exists
	@DeleteMapping("/customers/{customerID}")
	public String deleteCustomer(@PathVariable int customerID) {
		// Get the Customer based on its ID
		Customer tmpCustomer = customerService.getCustomer(customerID);
		
		// If Customer does not exist, we throw the CustomerNotFoundException and 
		// CustomerRestExceptionHandler handles this exception's error response 
		if (tmpCustomer == null) {
			throw new CustomerNotFoundException("Customer ID not found: " + customerID);
		}
		
		// If Customer does exist, we delete the Customer from DB
		customerService.deleteCustomer(customerID);
		
		return "Deleted Customer ID: " + customerID;
	}
}