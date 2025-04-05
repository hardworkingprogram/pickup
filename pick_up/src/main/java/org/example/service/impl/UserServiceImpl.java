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
    public User getUserById(int user_id) {
        return userMapper.selectUserById(user_id);
    }

    @Override
    public boolean updateUserInfo(User user) {
        int rows = userMapper.updateUserInfo(user);
        return rows > 0;
    }

    @Override
    public User loginByRole(String phone_number, String password, String role) {
        if ("ordinary_user".equals(role)) {
            return userMapper.selectUserByPhoneAndPassword(phone_number, password);
        } else if ("part_time_user".equals(role)) {
            // 这里需要实现兼职用户的登录逻辑，假设存在 PartTimeUserMapper
            // return partTimeUserMapper.selectUserByPhoneAndPassword(phone_number, password);
        } else if ("admin".equals(role)) {
            // 这里需要实现管理员的登录逻辑，假设存在 AdminMapper
            // return adminMapper.selectUserByPhoneAndPassword(phone_number, password);
        }
        return null;
    }
}
