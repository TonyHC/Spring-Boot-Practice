package com.springsecurity.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springsecurity.demo.user.RegisterUser;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	// An extension of the UserDetailsService which provides the ability to create new users and update existing ones
	@Autowired
	private UserDetailsManager userDetailsManager;
	
	// Service interface for encoding passwords. The preferred implementation is BCryptPasswordEncoder
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	private Map<String, String> roles;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		// Every time a String is encountered, the String's leading and trailing white spaces are trimmed
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@PostConstruct
	protected void loadRoles() {
		roles = new HashMap<String, String>();
		
		// key = user role, value = display to user
		roles.put("ROLE_EMPLOYEE", "Employee");
		roles.put("ROLE_MANAGER", "Manager");
		roles.put("ROLE_ADMIN", "Admin");
	}
	
	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model theModel) {
		theModel.addAttribute("registerUser", new RegisterUser());
		theModel.addAttribute("roles", roles);
		
		return "registration-form";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(@Valid @ModelAttribute("registerUser") RegisterUser aRegisterUser, 
				BindingResult theBindingResult, Model theModel) {
						
		String userName = aRegisterUser.getUserName();
		
		logger.info("Processing registration form for: " + userName);
		
		// Registration Form Validation to check if any Constraint Validators were violated
		if (theBindingResult.hasErrors()) {
			theModel.addAttribute("registerUser", new RegisterUser());
			// Add Roles to the Model for Form to display
			theModel.addAttribute("roles", roles);
			
			theModel.addAttribute("registrationError", "User name/password can not be empty.");
			logger.warning("User name/password can not be empty.");
			
			return "registration-form";	
		}
		
		// Check if Username already exists in DB
		if (doesUserExist(userName)) {
			theModel.addAttribute("registerUser", new RegisterUser());
			theModel.addAttribute("roles", roles);
			
			theModel.addAttribute("registrationError", "User name already exists.");
			logger.warning("User name already exists.");
			
			return "registration-form";			
		}
		
		// Encrypt the password
        String encodedPassword = passwordEncoder.encode(aRegisterUser.getPassword());

        // Prepend the encoding algorithm id [bcrypt]
        encodedPassword = "{bcrypt}" + encodedPassword;
                 
		// Give newly created user's with the default role of "employee"
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList();
        // Stores a String representation of an authority granted to the Authentication object.
        authorities.add(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
        
        // If the User selects the role other than EMPLOYEE,
        // then also add that role (multiple roles for one user)
        String formRole = aRegisterUser.getFormRole();
        if (!formRole.equals("ROLE_EMPLOYEE")) {
        	authorities.add(new SimpleGrantedAuthority(formRole));
        }

        // Create user object (from Spring Security framework) to store User Info
        User tempUser = new User(userName, encodedPassword, authorities);

        // Create the User and save to DB
        userDetailsManager.createUser(tempUser);		
		
        logger.info("Successfully created user: " + userName);
        
        return "registration-confirmation";		
	}
	
	private boolean doesUserExist(String userName) {
		logger.info("Checking if user exists: " + userName);
		
		// Check the database if the user already exists
		boolean exists = userDetailsManager.userExists(userName);
		
		logger.info("User: " + userName + ", exists: " + exists);
		
		return exists;
	}
}
