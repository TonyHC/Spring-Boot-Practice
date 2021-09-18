package com.java.hibernate.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.java.hibernate.utils.DateTimeUtils;

@Entity
@Table(name = "Employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "company")
	private String company;
	
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date employmentDate;
	
	public Employee() {
		
	}

	public Employee(String firstName, String lastName, String company, Date employmentDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.company = company;
		this.employmentDate = employmentDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Date getEmployedAt() {
		return employmentDate;
	}

	public void setEmployedAt(Date employmentDate) {
		this.employmentDate = employmentDate;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", company=" + company
				+ ", employmentDate=" + DateTimeUtils.formatDateTime(employmentDate) + "]";
	}
}