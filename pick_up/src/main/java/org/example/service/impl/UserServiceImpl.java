package org.example.service.impl;

import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List findAllUsers() {
        return userMapper.selectAllUsers();
    }

    @Override
    public User login(String phone_number, String password) {
        return userMapper.selectUserByPhoneAndPassword(phone_number, password);
    }

    @Override
    public User getUserByPhoneNumber(String phone_number) {
        return userMapper.selectUserByPhoneNumber(phone_number);
    }

    @Override
    public boolean updateUserInfo(User user) {
        int rows = userMapper.updateUserInfo(user);
        return rows > 0;
    }
}
