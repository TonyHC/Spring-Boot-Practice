package com.spring.demo.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello")
public class HelloWorldController {
	// Need a Controller Method to show initial Form
	@RequestMapping("/showForm")
	public String showForm() {
		return "helloworld-form";
	}
	
	// Need a Controller Method to process Form
	@RequestMapping("/processForm")
	public String processForm() {
		return "helloworld";
	}
	
	// Add a Controller Method to read form data and add data to model using HttpServletRequest
	@RequestMapping("processFormVersionTwo")
	public String welcomeString(HttpServletRequest request, Model model) {
		// Read the request parameter from HTML Form
		String studentName = request.getParameter("studentName");
		
		// Convert data to uppercase
		studentName.toUpperCase();
		
		// Create the message
		String welcomeMessage = "Hello Student " + studentName;
		
		// Add message to model
		model.addAttribute("message", welcomeMessage);
		
		// Return View Name
		return "helloworld";
	}
	
	// Add a Controller Method to read form data and add data to model using @RequestParam
	@RequestMapping("processFormVersionThree")
	public String welcomeStudent(@RequestParam("studentName") String studentName, Model model) {	
		// Convert data to uppercase
		studentName.toUpperCase();
		
		// Create the message
		String welcomeMessage = "Welcome Student " + studentName;
		
		// Add message to model
		model.addAttribute("message", welcomeMessage);
		
		// Return View Name
		return "helloworld";
	}
}