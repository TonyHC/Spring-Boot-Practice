package com.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	// Add reference to security data source
	@Autowired
	private DataSource securityDataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Use JDBC Authentication for data source
		auth.jdbcAuthentication().dataSource(securityDataSource);
			// / How to find users: ‘?’ Parameter Value will be the user name from login form
			//.usersByUsernameQuery("SELECT user_id, password, active FROM users WHERE user_id = ?")
			// / How to find roles: ‘?’ Parameter Value will be the user name from login form
			//.authoritiesByUsernameQuery("SELECT user_id, role FROM roles WHERE user_id = ?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").permitAll() // Allow public access to landing page
			.antMatchers("/resources/**").permitAll()
			.antMatchers("/employees").hasRole("EMPLOYEE")
			.antMatchers("/leaders/**").hasRole("MANAGER")
			.antMatchers("/systems/**").hasRole("ADMIN")
			.and()
			.formLogin()
				.loginPage("/login/showLoginPage")
				.loginProcessingUrl("/authenticateUser")
				.permitAll()
			.and()
			.logout().logoutSuccessUrl("/").permitAll() // After logout redirect to landing page
			.and()
			.exceptionHandling().accessDeniedPage("/login/accessDenied");
	}
	
	@Bean
	public UserDetailsManager userDetailsManager() {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(securityDataSource);
		return jdbcUserDetailsManager;
	}
}
