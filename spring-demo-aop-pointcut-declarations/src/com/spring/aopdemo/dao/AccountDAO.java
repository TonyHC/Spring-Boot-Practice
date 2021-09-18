package com.spring.aopdemo.dao;

import org.springframework.stereotype.Component;

import com.spring.aopdemo.Account;

@Component
public class AccountDAO {
	public void addAccount(Account account, boolean vipMember) {
		// Prints out the Class Name
		System.out.println(getClass() + ": addAccount");
	}
	
	public boolean isVipMember() {
		System.out.println(getClass() + ": isVipMember");
		return false;
	}
}