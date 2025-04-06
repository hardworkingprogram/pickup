package org.example.service.admin;


import org.example.pojo.Admin;
import java.util.List;
import java.util.Map;

public interface AdminService {
    Admin login(String username, String password);

    // 新增：查看所有普通用户信息
    List<Map<String, Object>> getAllOrdinaryUsers();

    // 新增：查看所有兼职代取用户信息
    List<Map<String, Object>> getAllPartTimeUsers();

    // 新增：修改用户信息
    boolean updateUserInfo(String tableName, int userId, String field, String value);

    // 新增：冻结用户账号
    boolean freezeUser(String tableName, int userId);

    // 新增：解冻用户账号
    boolean unfreezeUser(String tableName, int userId);
}