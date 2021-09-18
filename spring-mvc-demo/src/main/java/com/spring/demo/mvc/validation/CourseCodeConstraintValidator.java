package com.spring.demo.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {
	private String[] coursePrefix;
	
	@Override
	public void initialize(CourseCode courseCode) {
		coursePrefix = courseCode.value();
	}
	
	@Override
	public boolean isValid(String code, ConstraintValidatorContext constraintValidatorContext) {
		boolean result = false;
		
		if (code != null) {
			// Loop through all the coursePrefix and 
			// check to see if any prefix matches any of the Course Prefixes defined
			for (String prefix : coursePrefix) {
				result = code.startsWith(prefix);
				// If we found a match, then break out of the loop (result = true)
				if (result)
					break;
			}
		} else {
			result = false;
		}
		
		return result;
	}
}