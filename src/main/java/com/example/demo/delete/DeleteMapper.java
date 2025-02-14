package com.example.demo.delete;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

//DBへのSQLクエリを定義する
@Mapper
public interface DeleteMapper {
    @Select("SELECT * FROM employee WHERE id = #{id}")
    DeleteEmployee findById(int id);

    @Delete("DELETE FROM employee WHERE id = #{id}")
    void deleteById(int id);
}
