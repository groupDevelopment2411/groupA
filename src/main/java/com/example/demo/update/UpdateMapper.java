package com.example.demo.update;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;



@Mapper
public interface UpdateMapper {
	
	@Select("SELECT * FROM employee WHERE id = #{id}")
	List<Update> findUserById(int id);

	
}
