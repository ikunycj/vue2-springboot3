package com.ikunycj.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ikunycj.springboot.entity.User;
import com.ikunycj.springboot.mapper.UserMapper;
import com.ikunycj.springboot.service.UserService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")             // 修改访问路径为http://localhost:8080/user
public class UserController {

    @Autowired                        // 增加Autowired注解, 自动注入UserMapper
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    // 添加和修改
    @PostMapping                   // 增加PostMapping注解, 用于添加用户
    public boolean save(@RequestBody User user){    // 增加@RequestBody注解, 用于接收前端传入的json数据
        return userService.saveUser(user);                // 调用UserService的save方法, 保存用户信息
    }

    // 查询所有数据
    @GetMapping             // 增加GetMapping注解, 修改访问路径为http://localhost:8080/user/
    public List<User> findAll() {
        return userService.list();
    }

    // 根据ids删除
    @DeleteMapping("/{id}")     // 增加DeleteMapping注解, 修改访问路径为http://localhost:8080/user/{id}
    public boolean delete(@PathVariable Integer id) {
        return userService.removeById(id);
    }

    // 批量删除
    @DeleteMapping("/del/batch")

    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return userService.removeByIds(ids);
    }

    // 分页查询
    // 增加GetMapping注解, 修改访问路径为http://localhost:8080/user/page?pageNum=1&pageSize=10
    // 增加RequestParam注解, 用于接收前端传入的pageNum和pageSize参数
    /*@GetMapping ("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String username) {
        pageNum = (pageNum -1) * pageSize;
        username = "%" + username + "%";
        List<User> data = userMapper.slectPage(pageNum, pageSize, username);
        Integer total = userMapper.selectTotal(username);
        Map<String, Object> res =  new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }*/

    // 分页查询mybatis-plus
    @GetMapping ("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String username,
                                @RequestParam(defaultValue = "") String email,
                                @RequestParam(defaultValue = "") String address) {
        IPage<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(!"".equals(username)){
            queryWrapper.like("username", username);
        }
        if(!"".equals(email)){
            queryWrapper.like("email", email);
        }
        if(!"".equals(address)){
            queryWrapper.like("address", address);
        }
        queryWrapper.orderByDesc("id");
        return userService.page(page, queryWrapper);
    }
}



