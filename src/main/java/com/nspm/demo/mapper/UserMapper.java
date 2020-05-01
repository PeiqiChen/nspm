package com.nspm.demo.mapper;

import com.nspm.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {

//    @Select("SELECT * FROM USERS WHERE NAME = #{name}")
//    User findByName(@Param("name") String name); // 自动类型转化？
//
//    @Insert("INSERT INTO USERS(ID, NAME, AGE) VALUES(#{id},#{name}, #{age})")
//    int insert(@Param("id") String id,@Param("name") String name, @Param("age") Integer age);

    public User findByName(@Param("name") String name);

    public int insert(@Param("id") String id,@Param("name") String name, @Param("age") Integer age);


}