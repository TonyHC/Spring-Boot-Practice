package com.java.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.hibernate.demo.entity.Course;
import com.java.hibernate.demo.entity.Instructor;
import com.java.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDemo {
	public static void main(String[] args) {
		// Create SessionFactory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();

		// Create Session
		Session session = factory.getCurrentSession();
		
		try {			
			// Begin Transaction
			session.beginTransaction();
			
			int instructorID = 2;
			Instructor tmpInstructor = session.get(Instructor.class, instructorID);
			
			
			// Delete a Instructor by id will also delete associated InstructorDetail object because CascadeType.ALL
			// All courses associated with Instructor to be deleted will have Column [instructor_id] be NULL
			if (tmpInstructor != null) {
				tmpInstructor.deleteCourses();
				
				session.delete(tmpInstructor);
			}
			
			// Commit Transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}
}