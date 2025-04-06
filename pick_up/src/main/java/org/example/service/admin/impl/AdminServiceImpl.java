package org.example.service.admin.impl;


import org.example.mapper.admin.AdminMapper;
import org.example.pojo.Admin;
import org.example.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(String username, String password) {
        Admin admin = adminMapper.selectAdminByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }

    // 新增：查看所有普通用户信息
    @Override
    public List<Map<String, Object>> getAllOrdinaryUsers() {
        return adminMapper.selectAllOrdinaryUsers();
    }

    // 新增：查看所有兼职代取用户信息
    @Override
    public List<Map<String, Object>> getAllPartTimeUsers() {
        return adminMapper.selectAllPartTimeUsers();
    }

    // 新增：修改用户信息
    @Override
    public boolean updateUserInfo(String tableName, int userId, String field, String value) {
        int rows = adminMapper.updateUserInfo(tableName, userId, field, value);
        return rows > 0;
    }

    // 新增：冻结用户账号
    @Override
    public boolean freezeUser(String tableName, int userId) {
        int rows = adminMapper.freezeUser(tableName, userId);
        return rows > 0;
    }

    // 新增：解冻用户账号
    @Override
    public boolean unfreezeUser(String tableName, int userId) {
        int rows = adminMapper.unfreezeUser(tableName, userId);
        return rows > 0;
    }
}