package com.java.hibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.hibernate.demo.entity.Student;
import com.java.hibernate.utils.DateUtils;

public class CreateStudentDemo {
	public static void main(String[] args) {
		// Create SessionFactory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// Create Session
		Session session = factory.getCurrentSession();
		
		try {
			String dateOfBirth = "12/31/2004";
			Date date = DateUtils.parseDate(dateOfBirth);
			
			// Create Student Object
			Student tempStudent = new Student("Mark", "Bone", "MarkBone@gmail.com", date);
			
			// Begin Transaction
			session.beginTransaction();
			
			// Save Student Object
			session.save(tempStudent);
			
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