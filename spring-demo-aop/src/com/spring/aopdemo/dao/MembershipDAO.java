package com.spring.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {
	public String addAccount() {
		System.out.println(getClass() + ": addAccount");
		return "";
	}
}