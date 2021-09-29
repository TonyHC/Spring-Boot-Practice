package com.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	// Inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<Customer> getCustomers() {
		// Get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// Create a Query for Customer and sort by last name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		// Execute query and get result list
		List<Customer> customers = theQuery.getResultList();
				
		// Return the results		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		// Get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Save or Update Customer depends if Customer has a primary key or not
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		// Get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Now retrieve/read from database using the primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		// Get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Delete customer using primary key (customer ID)
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();		
	}
}