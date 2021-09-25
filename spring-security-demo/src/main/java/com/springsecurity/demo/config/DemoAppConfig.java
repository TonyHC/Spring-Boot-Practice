package com.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@PropertySource("classpath:persistence-mysql.properties")
@ComponentScan(basePackages = "com.springsecurity.demo")
public class DemoAppConfig implements WebMvcConfigurer  {
	// Variable to hold Properties File Data
	@Autowired
	private Environment env;
	
	// Setup a logger for diagonistics 
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// Define a Bean for ViewResolver
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	// Define a Bean for Data Source
	@Bean
	public DataSource myDataSource() {
		// Create Connection Source
		ComboPooledDataSource myDataSource = new ComboPooledDataSource();
		
		try {
			myDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException exception) {
			throw new RuntimeException(exception);
		}
		
		// For sanity, log url and user just to make sure we are reading the data
		logger.info("jdbc.url = " + env.getProperty("jdbc.url"));
		logger.info("jdbc.user = " + env.getProperty("jdbc.user"));
		
		// Set Database Connection Properties
		myDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		myDataSource.setUser(env.getProperty("jdbc.user"));
		myDataSource.setPassword(env.getProperty("jdbc.password"));
		
		// Set Connection Pool Properties
		myDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		myDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		myDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		myDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
	
		return myDataSource;
	}

	// Helper Method: Read Environment Property and convert to int
	private int getIntProperty(String propertyName) {
		String propertyValue = env.getProperty(propertyName);
		int intPropertyValue = Integer.parseInt(propertyValue);
		return intPropertyValue;
	}
	
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/resources/**")
          .addResourceLocations("/resources/"); 
    }
}