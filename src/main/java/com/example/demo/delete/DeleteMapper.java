package com.example.demo.delete;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

//DBへのSQLクエリを定義する
@Mapper
public interface DeleteMapper {
	//社員情報を取得	
	@Select({
	    "<script>",
	    "SELECT * FROM employee WHERE id IN ",
	    "<foreach item='id' collection='list' open='(' separator=',' close=')'>",
	    "#{id}",
	    "</foreach>",
	    "</script>"
	})
	List<DeleteEmployee> findByIds(@Param("list") List<Integer> ids);

//	idで社員情報を取得するメソッド
	@Select("SELECT * FROM employee WHERE id = #{id}")
	DeleteEmployee findById(Integer id);
	
	@Select("SELECT * FROM employee WHERE name = #{username}")
	DeleteEmployee findByUsername(String username);


    //社員情報を削除
	@Delete({
	    "<script>",
	    "DELETE FROM employee WHERE id IN ",
	    "<foreach item='id' collection='list' open='(' separator=',' close=')'>",
	    "#{id}",
	    "</foreach>",
	    "</script>"
	})
	void deleteByIds(@Param("list") List<Integer> ids);
}
