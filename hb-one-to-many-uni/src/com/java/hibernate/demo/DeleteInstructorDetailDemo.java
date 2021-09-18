package com.java.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.hibernate.demo.entity.Instructor;
import com.java.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {
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
			// Begin Transaction
			session.beginTransaction();
			
			// Get a InstructorDetail object by using its Primary Key
			int instructorDetailID = 3;
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, instructorDetailID);
			
			// Print the InstructorDetails
			System.out.println("instructorDetail: " + instructorDetail);
			
			// Print the Associated Instructor
			System.out.println("The Associated Instructor: " + instructorDetail.getInstructor());
			
			// Delete InstructorDetail and associate Instructor because of 
			// @OneToOne(mappedBy = "instructorDetail", cascade = CascadeType.ALL)
			if (instructorDetail != null) {
				System.out.println("Deleting instructorDetail" + instructorDetail);
				
				// Remove the associated object reference
				// Break the Bi-Directional Link
				instructorDetail.getInstructor().setInstructorDetail(null);
				
				session.delete(instructorDetail);
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