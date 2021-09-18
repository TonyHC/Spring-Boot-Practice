package com.spring.demo.mvc;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Value("#{countryOptions}")
	private Map<String, String> countryOptions;
	
	@RequestMapping("/showForm")
	public String showForm(Model model) {
		// Create new Student Object
		Student student = new Student();
		
		// Add Student Object to the Model
		model.addAttribute("student", student);
		
		// Add countryOptions Map to the Model
		model.addAttribute("countryOptions", countryOptions);
		
		return "student-form";
	}
	
	@RequestMapping("/processForm")
	public String processForm(@ModelAttribute("student") Student student) {
		// Log the Input Data
		System.out.println("Student: " + student.getFirstName() + " " + student.getLastName());
		
		return "student-confirmation";
	}
}