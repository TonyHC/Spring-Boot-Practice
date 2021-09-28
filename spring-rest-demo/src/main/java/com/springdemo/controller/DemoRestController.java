package com.springdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class DemoRestController {
	// Add "/hello" endpoint
	// Access the REST endpoint at /test/hello
	@GetMapping("/hello")
	public String hello() {
		// Returns content to client
		return "Hello World";
	}
}