package com.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DateController {
	@GetMapping("/date")
	public String currentDate(Model model) {
		model.addAttribute("currentDate", new java.util.Date());
		
		return "date";
	}
}