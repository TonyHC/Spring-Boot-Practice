package com.java.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.hibernate.demo.entity.Student;

public class DeleteStudentDemo {
	public static void main(String[] args) {
		// Create SessionFactory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// Create Session
		Session session = factory.getCurrentSession();
		
		try {
			// Begin Transaction
			session.beginTransaction();
			
			/*
			int studentID = 1;
			Student myStudent = session.get(Student.class, studentID);
			
			// Delete a Student 
			session.delete(myStudent);
			
			// Commit Transaction
			session.getTransaction().commit();
			
			// Now get new session and begin transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// Update Student email with id = 1
			session.createQuery("DELETE FROM Student WHERE email LIKE '%@gmail.com'")
					.executeUpdate();
			*/
			
			session.createQuery("DELETE FROM Student WHERE birthDate < '2003/01/23'")
			.executeUpdate();
			
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}
}