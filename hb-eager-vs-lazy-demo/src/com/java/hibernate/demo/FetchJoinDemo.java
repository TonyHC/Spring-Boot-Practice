package com.java.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.java.hibernate.demo.entity.Course;
import com.java.hibernate.demo.entity.Instructor;
import com.java.hibernate.demo.entity.InstructorDetail;


public class FetchJoinDemo {
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
			
			// Option 2: Hibernate Query with HQL
			
			// Get the Instructor from DB using Primary Key
			int instructorID = 1;
			
			// When executed, will load Instructor and Courses at once
			Query<Instructor> query = 
					session.createQuery("SELECT i FROM Instructor i "
									+ "JOIN FETCH i.courses "
									// Parameter we set later (specify the Instructor.id)
									+ "WHERE i.id =: theInstructorID", Instructor.class);
			
			// Set the Query Parameter for Instructor.id
			// query.setParameter(parameter_name, value)
			query.setParameter("theInstructorID", instructorID);
			
			// Execute Query and Get Instructor
			// Load the Instructor and Courses at once
			Instructor instructor = query.getSingleResult();
			
			System.out.println("Instructor: " + instructor);
			
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