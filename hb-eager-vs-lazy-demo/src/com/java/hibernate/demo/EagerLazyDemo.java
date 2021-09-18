package com.java.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.hibernate.demo.entity.Course;
import com.java.hibernate.demo.entity.Instructor;
import com.java.hibernate.demo.entity.InstructorDetail;


public class EagerLazyDemo {
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
			
			// Option 1: Use session.get() and Call appropriate Getter Method(s) while Session is still open
			 
			// Get the Instructor from DB using Primary Key
			int instructorID = 1;
			Instructor instructor = session.get(Instructor.class, instructorID);
			
			// Print out the Instructor name
			System.out.println("Instructor: " + instructor);
			
			// Print out the Courses for Instructor
			System.out.println("Courses: " + instructor.getCourses());
			
			// Commit Transaction
			session.getTransaction().commit();
			
			// Close the Session
			session.close();
			
			System.out.println("\nThe Session is now closed\n");
			
			// Executed After We Closed the Session
			// Also works because we Load the data before we closed the Session
			System.out.println("Courses: " + instructor.getCourses());
			
			System.out.println("Done!");
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			factory.close();
		}
	}
}