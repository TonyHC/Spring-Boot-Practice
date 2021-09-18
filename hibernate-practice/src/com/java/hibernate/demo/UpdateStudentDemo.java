package com.java.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.hibernate.demo.entity.Student;

public class UpdateStudentDemo {
	public static void main(String[] args) {
		// Create SessionFactory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// Create Session
		Session session = factory.getCurrentSession();
		
		try {
			/*
			// Begin Transaction
			session.beginTransaction();
			
			int studentID = 1;
			Student myStudent = session.get(Student.class, studentID);
			
			// Update Student first name
			myStudent.setFirstName("Bobby");
			
			// Commit Transaction
			session.getTransaction().commit();
			
			// Now get new session and begin transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// Update Student email with id = 1
			session.createQuery("update Student s set email = 'test@gmail.com' WHERE s.id = '1'")
					.executeUpdate();
			
			session.getTransaction().commit();
			*/
			
			// Now get new session and begin transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// Update Student email with id = 1
			session.createQuery("update Student s set birthDate = '2008/04/14 12:41:12'" + " WHERE s.id = '2'")
					.executeUpdate();
			
			session.getTransaction().commit();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			factory.close();
		}
	}
}