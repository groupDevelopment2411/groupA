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
    @Select("SELECT * FROM employee WHERE id = #{id}")
    DeleteEmployee findById(int id);

	//社員情報を取得(複数)
	@Select({
	    "<script>",
	    "SELECT * FROM employee WHERE id IN ",
	    "<foreach item='id' collection='idList' open='(' separator=',' close=')'>",
	    "#{id}",
	    "</foreach>",
	    "</script>"
	})
	List<DeleteEmployee> findByIds(@Param("idList") List<Integer> ids);

    //社員情報を削除
    @Delete("DELETE FROM employee WHERE id = #{id}")
    void deleteById(int id);
	
	//社員情報を削除(複数)
	@Delete({
	    "<script>",
	    "DELETE FROM employee WHERE id IN ",
	    "<foreach item='id' collection='idList' open='(' separator=',' close=')'>",
	    "#{id}",
	    "</foreach>",
	    "</script>"
	})
	void deleteByIds(@Param("idList") List<Integer> ids);
}
