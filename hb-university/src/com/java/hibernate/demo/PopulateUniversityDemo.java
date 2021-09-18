package com.java.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.hibernate.demo.entity.Course;
import com.java.hibernate.demo.entity.Instructor;
import com.java.hibernate.demo.entity.InstructorDetail;
import com.java.hibernate.demo.entity.Review;
import com.java.hibernate.demo.entity.Student;
import com.java.hibernate.utils.DateUtils;

public class PopulateUniversityDemo {
	public static void main(String[] args) {
		// Create SessionFactory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// Create Session
		Session session = factory.getCurrentSession();
		
		try {
			// Begin Transaction
			session.beginTransaction();
			
			// Create Instructors
			Instructor instructor1 = new Instructor("Neil", "Lance", "NeilLance@gmail.com");
			Instructor instructor2 = new Instructor("Tommy", "Hicks", "THicks@hotmail.com");
			
			// Set the each Instructor's InstructorDetail
			instructor1.setInstructorDetail(new InstructorDetail("http://youtube/LanceNeil", "Bouldering"));
			instructor2.setInstructorDetail(new InstructorDetail("http://youtube/HicksGames", "Gaming"));
			
			// Create Courses
			Course course1 = new Course("Video Editing");
			Course course2 = new Course("Swimming");
			Course course3 = new Course("P.E.");
			Course course4 = new Course("Health");
			Course course5 = new Course("World History");
			
			// Create Reviews
			Review review1 = new Review("Teaching was great");
			Review review2 = new Review("Teaching was horrible");
			Review review3 = new Review("Really good instructor");
			Review review4 = new Review("Very boring");
			Review review5 = new Review("Overall terrible");
			
			// Create Students
			Student s1 = new Student("TJ", "Cloud", "TJC@mail.com", DateUtils.parseDate("08/21/1999"));
			Student s2 = new Student("Ray", "Band", "RB@gmail.com", DateUtils.parseDate("11/06/1994"));
			Student s3 = new Student("Moose", "Vicks", "VicksMoo@yahoo.com", DateUtils.parseDate("05/14/2005"));
			
			// Add Review to Courses
			course1.addReview(review1);
			course1.addReview(review2);
			course3.addReview(review3);
			course4.addReview(review4);
			course5.addReview(review5);
			
			// Add Courses for instructor
			instructor1.addCourse(course3);
			instructor1.addCourse(course1);
			instructor2.addCourse(course5);
			instructor2.addCourse(course4);
			instructor2.addCourse(course2);
			
			// Add Courses for each Student
			s1.addCourse(course4);
			s1.addCourse(course2);
			s2.addCourse(course5);
			s2.addCourse(course3);
			s2.addCourse(course2);
			s2.addCourse(course1);
			s3.addCourse(course3);
			s3.addCourse(course5);
			s3.addCourse(course2);
			
			// Save the Instructors, which also saves the InstructorDetails
			session.save(instructor1);
			session.save(instructor2);
			
			// Save the Course, also saving the Reviews 
			session.save(course1);
			session.save(course2);
			session.save(course3);
			session.save(course4);
			session.save(course5);
			
			// Save the Students
			session.save(s1);
			session.save(s2);
			session.save(s3);
			
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