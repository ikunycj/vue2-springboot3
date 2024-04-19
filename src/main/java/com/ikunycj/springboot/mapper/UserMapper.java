package com.ikunycj.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ikunycj.springboot.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Mapper                                     // 标注为mybatis的mapper接口
public interface UserMapper extends BaseMapper<User> {               // 继承mybatis的Mapper接口
    @Select("SELECT * FROM sys_user")       // 定义查询用户信息的sql语句
    List<User> findAll();

    // 定义插入用户信息的sql语句
    @Insert("INSERT into sys_user(username, password, nickname, email, phone, address) "+
                   "VALUES(#{username}, #{password}, #{nickname}, #{email}, #{phone}, #{address})")
    int insert(User user);


    int update(User user);

    // 定义根据id删除用户信息的sql语句
    @Delete("DELETE FROM sys_user WHERE id = #{id}")
    Integer deleteById(@Param("id") Integer id);


    // 定义分页查询用户信息的sql语句
    @Select("SELECT * FROM sys_user WHERE username LIKE #{username} LIMIT #{pageNum},#{pageSize}")
    List<User> slectPage(Integer pageNum, Integer pageSize,String username);

    // 定义查询总记录数的sql语句
    @Select("SELECT COUNT(*) FROM sys_user")
    Integer selectTotal(String username);
}
