package com.java.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.hibernate.demo.entity.Course;
import com.java.hibernate.demo.entity.Instructor;
import com.java.hibernate.demo.entity.InstructorDetail;


public class CreateCoursesDemo {
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
			
			// Get the Instructor from DB using Primary Key
			int instructorID = 1;
			Instructor instructor = session.get(Instructor.class, instructorID);
			
			// Create some Courses
			Course course1 = new Course("Business");
			Course course2 = new Course("Mining");
			
			// Add Courses to Instructor
			instructor.addCourse(course1);
			instructor.addCourse(course2);
			
			// Save Courses to DB
			session.save(course1);
			session.save(course2);
			
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