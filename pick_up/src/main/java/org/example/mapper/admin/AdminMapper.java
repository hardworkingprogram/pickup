package org.example.mapper.admin;


import org.example.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

@Mapper
public interface AdminMapper {
    Admin selectAdminByUsername(@Param("username") String username);

    // 新增：查询所有普通用户
    List<Map<String, Object>> selectAllOrdinaryUsers();

    // 新增：查询所有兼职代取用户
    List<Map<String, Object>> selectAllPartTimeUsers();

    // 新增：修改用户信息
    int updateUserInfo(@Param("tableName") String tableName, @Param("userId") int userId, @Param("field") String field, @Param("value") String value);

    // 新增：冻结用户账号
    int freezeUser(@Param("tableName") String tableName, @Param("userId") int userId);

    // 新增：解冻用户账号
    int unfreezeUser(@Param("tableName") String tableName, @Param("userId") int userId);
}