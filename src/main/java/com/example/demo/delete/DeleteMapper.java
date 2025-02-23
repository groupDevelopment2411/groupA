package com.example.demo.delete;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DeleteMapper {
		
	List<DeleteEmployee> findByIds(@Param("list") List<Integer> ids);
	
	void deleteByIds(@Param("list") List<Integer> ids);
	
	@Select("SELECT * FROM employee WHERE id = #{id}")
	DeleteEmployee findById(Integer id); /*idで社員情報を取得するメソッド*/
    
}
