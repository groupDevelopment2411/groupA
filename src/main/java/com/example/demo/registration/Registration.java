package com.example.demo.registration;

import java.sql.Date;

public class Registration {
	
//	private int id;
	private String name;
	private int age;
	private String password1;
//	private String password2;
	private Date startdate;
	private Date enddate;
	
	
	public Registration() {};
	public Registration(String name,int age,String password1,Date startdate,Date enddate) {
//		this.id = id;
		this.name = name;
		this.age = age;
		this.password1 = password1;
//		this.password2 = password2;
		this.startdate = startdate;
		this.enddate = enddate;
	}
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
//	public String getPassword2() {
//		return password2;
//	}
//	public void setPassword2(String password2) {
//		this.password2 = password2;
//	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	
	
}
