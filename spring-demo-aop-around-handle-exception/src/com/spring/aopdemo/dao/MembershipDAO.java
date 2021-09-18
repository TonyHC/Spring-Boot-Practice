package com.spring.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {
	public String addAccount(String name) {
		System.out.println(getClass() + ": addAccount - " + name);
		return "";
	}
}