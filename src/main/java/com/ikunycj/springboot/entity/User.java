package com.ikunycj.springboot.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName(value = "sys_user")                // 数据库表名注解
public class User {


    @TableId(type = IdType.AUTO)              // 主键注解
    private Integer id;

    private String username;

    @JsonIgnore                              // 忽略该属性
    private String password;

    @TableField(value = "nickname")          // 数据库字段名注解
    private String nickname;

    private String email;

    private String phone;

    private String address;

}
