package com.example.demo.update;

import java.sql.Date;

public class Update {
		private int id;
		private String name;
		private int age;
		private String password1;
//		private String password2;
//		private Date startdate;
		private String startDate;
//		private Date enddate;
		private String endDate; 	
		
//	    private java.sql.Date startDate;
//	    private java.sql.Date endDate;
	    
		
		
		public Update() {};
		public Update(int id,String name,int age,String password1,String startDate,String endDate) {
			
	        // デバッグ用出力
	        System.out.println("UPDATEクラスを使用 - ID: " + id);
	        System.out.println("UPDATEクラスを使用 - Name: " + name);
	        System.out.println("UPDATEクラスを使用 - Start Date: " + startDate);
	        System.out.println("UPDATEクラスを使用 - End Date: " + endDate);
			
			this.id = id;
			this.name = name;
			this.age = age;
			this.password1 = password1;
//			this.password2 = password2;
//			this.startdate = startdate;
			this.startDate = startDate;
//			this.enddate = enddate;
			this.endDate = endDate;
			
			
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
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
//		public String getPassword2() {
//			return password2;
//		}
//		public void setPassword2(String password2) {
//			this.password2 = password2;
//		}
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		public String getEndDate() {
			return endDate;
		}
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
	
}
