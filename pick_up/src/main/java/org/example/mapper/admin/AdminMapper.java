package org.example.mapper.admin;

import org.example.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface AdminMapper {
    Admin selectAdminByUsername(@Param("username") String username);

    // 新增：查询所有普通用户
    // List<Map<String, Object>> selectAllOrdinaryUsers(); // 暂时注释掉，使用分页查询

    // 新增：查询所有兼职代取用户
    // List<Map<String, Object>> selectAllPartTimeUsers(); // 暂时注释掉，使用分页查询

    // 新增：修改用户信息
    int updateUserInfo(@Param("tableName") String tableName, @Param("userId") int userId, @Param("field") String field,
            @Param("value") String value);

    // 新增：冻结用户账号
    int freezeUser(@Param("tableName") String tableName, @Param("userId") int userId);

    // 新增：解冻用户账号
    int unfreezeUser(@Param("tableName") String tableName, @Param("userId") int userId);

    // 获取普通用户总数
    int getTotalOrdinaryUserCount();

    // 分页查询普通用户
    List<Map<String, Object>> getOrdinaryUsersByPage(
            @Param("offset") int offset,
            @Param("pageSize") int pageSize);

    // 获取兼职代取用户总数
    int getTotalPartTimeUserCount();

    // 分页查询兼职代取用户
    List<Map<String, Object>> getPartTimeUsersByPage(
            @Param("offset") int offset,
            @Param("pageSize") int pageSize);

    // 获取公告总数
    int getTotalAnnouncementCount();

    // 分页查询公告
    List<Map<String, Object>> getAnnouncementsByPage(
            @Param("offset") int offset,
            @Param("pageSize") int pageSize);

    // 获取包裹总数
    int getTotalPackageCount();

    // 分页查询包裹
    List<Map<String, Object>> getPackagesByPage(
            @Param("offset") int offset,
            @Param("pageSize") int pageSize);

    // 获取代取任务总数
    int getTotalPickupTaskCount();

    // 分页查询代取任务
    List<Map<String, Object>> getPickupTasksByPage(
            @Param("offset") int offset,
            @Param("pageSize") int pageSize);

    // 获取反馈总数
    int getTotalFeedbackCount();

    // 分页查询反馈
    List<Map<String, Object>> getFeedbackByPage(
            @Param("offset") int offset,
            @Param("pageSize") int pageSize);
}