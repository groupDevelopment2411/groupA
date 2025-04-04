package com.example.demo.update;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;



@Mapper
public interface UpdateMapper {
	
//	@Select("SELECT id, name, age, password, start_date, end_date FROM employee WHERE id = #{id}")
//    List<Update> findUserById(int id);

	
//	@Select("SELECT id, name, age, password, start_date, end_date FROM employee WHERE id = #{id}")
//    List<Update> findUserById(int id);
	
	@Select("SELECT id, name, age, password, start_date, end_date FROM employee WHERE id = #{id}")
//	DBとUPDATEのカラム名を合わせる
	@Results({
		@Result(property = "id", column = "id"),
	    @Result(property = "name", column = "name"),
	    @Result(property = "age", column = "age"),
	    @Result(property = "password1", column = "password"),
	    @Result(property = "startDate", column = "start_date"),
	    @Result(property = "endDate", column = "end_date")
	})
	List<Update> findUserById(int id);
	
	
	@Insert("UPDATE employee SET name=#{name}, age=#{age}, password=#{password1}, start_date=#{startDate}, end_date=#{endDate} WHERE id=#{id}")
    void updateinsert(Update update);
	
	
	
}
