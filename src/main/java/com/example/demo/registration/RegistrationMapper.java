package com.example.demo.registration;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface RegistrationMapper {
	
	
	@Insert("INSERT INTO employee (name, age, password, start_date, end_date) VALUES(#{name},#{age},#{password1},#{startdate},#{enddate})") 
	void insert(Registration registration);

}
