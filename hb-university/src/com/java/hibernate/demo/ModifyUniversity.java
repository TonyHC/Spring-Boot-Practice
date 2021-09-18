package com.java.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.hibernate.demo.entity.Course;
import com.java.hibernate.demo.entity.Instructor;
import com.java.hibernate.demo.entity.InstructorDetail;
import com.java.hibernate.demo.entity.Review;
import com.java.hibernate.demo.entity.Student;

public class ModifyUniversity {
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
			
			// Get the Instructor from DB using Primary Key
			int instructorID = 1;
			Instructor instructor = session.get(Instructor.class, instructorID);
			
			// Print the Instructor's Details
			System.out.println("Instructor Detail: " + instructor.getInstructorDetail());
			
			// Print out all the Courses that the Instructor is teaching
			System.out.println("Instructor Courses: " + instructor.getCourses());
			
			// Print out the Students and Reviews of each Course the Instructor is teaching
			for (Course course : instructor.getCourses()) {
				System.out.println(course.getCourseName() + " " + course.getStudents());
				System.out.println("\n" + course.getCourseName() + " " + course.getReviews());
			}
			
			
			// Get a Course and Updates its Instructor
			Course updateCourse = session.get(Course.class, 2);
			updateCourse.setInstructor(instructor);
			
			// Delete a Course along with its Reviews 
			// Does not affect the Students (Many-To-Many Relationship with Student and Join Table)
			Course tmpCourse = session.get(Course.class, 2);
			session.delete(tmpCourse);
			
			// Delete a Student, it does not affect the Courses (did not allow for CascadeType.REMOVE)
			Student student = session.get(Student.class, 2);
			session.delete(student);

			// Get Instructor to be deleted
			Instructor tmpInstructor = session.get(Instructor.class, 2);
			
			// Set all the Courses the Instructor taught to NULL
			// This allows us to delete the Instructor (Bi-Directional Relationship with Course)
			for (Course course : tmpInstructor.getCourses())
				course.setInstructor(null);
			
			// Delete a Instructor, which also deletes its Instructor Details
			session.delete(tmpInstructor);			
			
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