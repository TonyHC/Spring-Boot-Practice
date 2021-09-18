package com.java.hibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.hibernate.demo.entity.Student;
import com.java.hibernate.utils.DateUtils;

public class ReadStudentDemo {
	public static void main(String[] args) {
		// Create SessionFactory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// Create Session
		Session session = factory.getCurrentSession();
		
		try {
			String birthDateString = "09/23/2004";
			Date birthDate = DateUtils.parseDate(birthDateString);
			
			// Create Student Object
			Student tempStudent = new Student("Neil", "Thomas", "NeilThomas@mail.com", birthDate);
			
			// Begin Transaction
			session.beginTransaction();
			
			// Save Student Object
			session.save(tempStudent);
			
			// Commit Transaction
			session.getTransaction().commit();
			
			// Now get new session and begin transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// Retrieve the Student Object by using a Primary Key
			Student myStudent = session.get(Student.class, tempStudent.getId());
			
			System.out.println("Get completed: " + myStudent);
			
			// Commit Transaction
			session.getTransaction().commit();
			
			System.out.println("Successfully Save Student Object");
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			factory.close();
		}
	}
}