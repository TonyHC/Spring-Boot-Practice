package com.java.hibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.hibernate.demo.entity.Instructor;
import com.java.hibernate.demo.entity.InstructorDetail;
import com.java.hibernate.demo.entity.Student;
import com.java.hibernate.utils.DateUtils;

public class CreateInstructorDemo {
	public static void main(String[] args) {
		// Create SessionFactory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		// Create Session
		Session session = factory.getCurrentSession();
		
		try {
			// Create Instructor Object
			Instructor tmpInstructor = new Instructor("Wayne", "Iris", "WayneIris@gmail.com");
			
			// Create InstructorDetail Object
			InstructorDetail instructorDetail = new InstructorDetail("http://youtube.com/WIris", "Football");
					
			// Associate the Object (One-to-One Mapping)
			tmpInstructor.setInstructorDetail(instructorDetail);
			
			// Begin Transaction
			session.beginTransaction();
			
			// Save the Instructor Object to DB
			// This will also save the InstructorDetail object
			// because of CascadeType.ALL
			session.save(tmpInstructor);
			
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