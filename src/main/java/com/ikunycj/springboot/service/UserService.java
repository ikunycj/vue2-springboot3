package com.ikunycj.springboot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ikunycj.springboot.entity.User;
import com.ikunycj.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl <UserMapper, User> {

    public boolean saveUser(User user) {
        return saveOrUpdate(user);
    }
    /*@Autowired
    private UserMapper userMapper;

    public int save(User user) {
        if (user.getId() == null) {
            return userMapper.insert(user);
        } else {
            return userMapper.update(user);
        }
    }*/
}
