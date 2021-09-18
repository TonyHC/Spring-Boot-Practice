package com.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestJdbc {
	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&serverTimezone=UTC";
		String userName = "hbstudent";
		String password = "hbstudent";
		
		try {
			System.out.println("Connecting to MySQL: " + jdbcUrl);
			
			Connection myConnection = DriverManager.getConnection(jdbcUrl, userName, password);
			
			System.out.println("Connection Successful");
			
			
			Statement statement = myConnection.createStatement();
			
	        ResultSet resultSet = statement.executeQuery("select * from student");

	        while (resultSet.next()) {
	            System.out.println(resultSet.getString("first_name") + " " + resultSet.getString("last_name")
	                    + " " + resultSet.getString("email"));
	        }
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}