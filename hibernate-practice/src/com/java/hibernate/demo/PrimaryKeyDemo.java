package com.java.hibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.hibernate.demo.entity.Student;
import com.java.hibernate.utils.DateUtils;

public class PrimaryKeyDemo {
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
			Date birthDate = DateUtils.parseDate(dateOfBirth);
			
			// Create Student Object
			Student tempStudent1 = new Student("Roy", "Thomas", "RoyThomas@mail.com", birthDate);
			Student tempStudent2 = new Student("Tom", "Thomas", "TomThomas@mail.com", birthDate);
			Student tempStudent3 = new Student("Linda", "Thomas", "LindaThomas@mail.com", birthDate);
			
			// Begin Transaction
			session.beginTransaction();
			
			// Save Student Object
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			
			// Commit Transaction
			session.getTransaction().commit();
			
			System.out.println("Successfully Save Student Objects");
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			factory.close();
		}
	}
}
