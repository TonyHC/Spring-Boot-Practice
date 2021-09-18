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


public class CreateCourseandStudentDemo {
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
			
			// Create a Course
			Course course = new Course("Physics");
		
			// Creates some Students
			Student student1 = new Student("Thomas", "Wayne", "ThoWayne@gmail.com", DateUtils.parseDate("12/31/2001"));
			Student student2 = new Student("Mark", "Boom", "BooM@gmail.com", DateUtils.parseDate("04/21/1999"));
			
			// Add Students to the Course
			course.addStudent(student1);
			course.addStudent(student2);
			
			// Save the Course and Students into DB 
			session.save(course);
			session.save(student1);
			session.save(student2);
			
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