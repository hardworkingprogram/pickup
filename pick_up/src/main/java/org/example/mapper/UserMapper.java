package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.pojo.User;

import java.util.List;

public interface UserMapper {
    List selectAllUsers();
    User selectUserByPhoneAndPassword(@Param("phone_number") String phone_number, @Param("password") String password);
    User selectUserById(int user_id);
    int updateUserInfo(User user);
}
