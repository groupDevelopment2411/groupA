package com.example.demo.login;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
	@Select("SELECT * FROM employee")
	List<LoginEmployee> selectAll();

	@Select("SELECT * FROM employee WHERE id = #{id} AND password = #{password}")
    LoginEmployee findByIdAndPassword(@Param("id") int id, @Param("password") String password);
}