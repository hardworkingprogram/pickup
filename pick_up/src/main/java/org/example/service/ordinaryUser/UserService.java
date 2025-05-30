package org.example.service.ordinaryUser;

import org.example.pojo.User;

import java.util.List;

public interface UserService {
    List findAllUsers();
    User login(String phone_number, String password);
    User getUserById(int user_id);
    boolean updateUserInfo(User user);
    User loginByRole(String phone_number, String password, String role);
    User getByPhone(String phoneNumber);
    boolean register(User user);
}
