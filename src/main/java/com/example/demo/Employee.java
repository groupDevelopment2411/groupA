package com.example.demo;

public class Employee {

    private int id;
    private String name;
    private int age;
    private String password;
    private String startDate;
    private String endDate;
    private String department;

    public Employee() {}

    public Employee(int id, String name, int age, String password, String startDate, String endDate, String department) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.password = password;
        this.startDate = startDate;
        this.endDate = endDate;
        this.department = department;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}
