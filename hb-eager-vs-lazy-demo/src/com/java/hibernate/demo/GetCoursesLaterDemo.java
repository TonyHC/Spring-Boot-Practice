package com.java.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.java.hibernate.demo.entity.Course;
import com.java.hibernate.demo.entity.Instructor;
import com.java.hibernate.demo.entity.InstructorDetail;


public class GetCoursesLaterDemo {
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
            // Start a Transaction
            session.beginTransaction();
                        
            // Get the Instructor from DB using Primary Key
            int instructorID = 1;
            Instructor tempInstructor = session.get(Instructor.class, instructorID);                    
            
            System.out.println("Instructor: " + tempInstructor);    
            
            // Commit Transaction
            session.getTransaction().commit();
            
            // Close the Session
            session.close();
            
            System.out.println("The session is now closed!\n");
            
            System.out.println("\n\nOpening a NEW session \n");
           
            // Open a new Session and Begin a Transaction
            session = factory.getCurrentSession();
            session.beginTransaction();
            
            // Get Courses for a Given Instructor
            Query<Course> query = session.createQuery("SELECT c FROM Course c "
                                                    + "WHERE c.instructor.id =: theInstructorId",    
                                                    Course.class);
            
            // Set the Parameter for Course.instructor_id with value of instructorID
            query.setParameter("theInstructorId", instructorID);
            
            // Execute the Query and Get (Load) the Specific Courses 
            List<Course> tempCourses = query.getResultList();
            
            System.out.println("tempCourses: " + tempCourses);
            
            // Now assign the Courses we loaded to Instructor Object in Memory
            tempInstructor.setCourses(tempCourses);
            
            System.out.println("Courses: " + tempInstructor.getCourses());
            
            // Commit Transaction
            session.getTransaction().commit();
            
            System.out.println("Done");
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}
}