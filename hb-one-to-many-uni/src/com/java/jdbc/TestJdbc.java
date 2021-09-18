package com.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {
	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false&serverTimezone=UTC";
		String userName = "hbstudent";
		String password = "hbstudent";
		
		try {
			System.out.println("Connecting to MySQL: " + jdbcUrl);
			
			Connection myConnection = DriverManager.getConnection(jdbcUrl, userName, password);
			
			System.out.println("Connection Successful");
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}