package com.example.demo.delete;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public class DeleteMapper {
	@Select("SELECT * FROM employee")
	List<DeleteEmployee> selectAll() {
		return null;
	}
	
	@Delete("DELETE FROM employee WHERE id = #{id}")
	void delete(int id) {
	}
	 
	
}
