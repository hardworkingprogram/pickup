package org.example.service;

import org.example.pojo.User;

import java.util.List;

public interface UserService {
    List findAllUsers();
    User login(String phone_number, String password);
    User getUserByPhoneNumber(String phone_number);
    boolean updateUserInfo(User user);
}
