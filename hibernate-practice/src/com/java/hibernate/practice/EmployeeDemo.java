package com.java.hibernate.practice;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.hibernate.demo.entity.Employee;
import com.java.hibernate.utils.DateTimeUtils;

public class EmployeeDemo {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {
			String dateTime = "11/02/2006 14:46:48";
			Date employmentDate = DateTimeUtils.parseDateTime(dateTime);
			
			System.out.println("DateTime: " + employmentDate);
			System.out.println(DateTimeUtils.formatDateTime(employmentDate));
			
			// Create Employee Object
			Employee employee = new Employee("Neil", "Taylor", "WorkHouse", employmentDate);
			
			// Begin Transaction
			session.beginTransaction();
			
			// Save Employee Object to DB
			session.save(employee);
			
			// Commit Transaction
			session.getTransaction().commit();
			
			/*
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// Get a Employee Object based on Primary Key
			Employee e = session.get(Employee.class, 3);
			System.out.println(e);
			
			session.getTransaction().commit();
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// Search for Employee Object whose id = 1
			session.createQuery("update Employee set company = 'Microsoft' where id = 1").executeUpdate();
			
			session.getTransaction().commit();
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// Delete Employees who work at the company containing Studios in its name
			session.createQuery("delete from Employee where company LIKE '% Studios'").executeUpdate();
			
			session.getTransaction().commit();
			*/
			
			System.out.println("Successfully Add an Employee");
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			factory.close();
		}
	}
}