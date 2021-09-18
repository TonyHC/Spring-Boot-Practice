package com.java.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.hibernate.demo.entity.Student;

public class QueryStudentDemo {
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
			
			// Query All Students
			List<Student> students = session.createQuery("from Student ORDER BY firstName")
											.getResultList();
			displayStudents(students);
			
			// Query Students with last name of Young
			students = session.createQuery("from Student s WHERE s.lastName = 'Young'")
											.getResultList();
			
			displayStudents(students);
			
			students = session.createQuery("from Student s WHERE s.firstName = 'Roy' OR s.firstName = 'Tom'")
											.getResultList();
			
			displayStudents(students);
			
			students = session.createQuery("from Student s WHERE s.email LIKE '%Thomas@mail.com'")
											.getResultList();
			
			displayStudents(students);
			
			// Commit Transaction
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> students) {
		// Display Students
		for (Student student : students) 
			System.out.println(student);
	}
}