package org.example.mapper.admin;

import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.PartTimePickupUser;

import java.util.List;

@Mapper
public interface AdminStatsMapper {

    /**
     * 获取所有兼职用户
     * 
     * @return 兼职用户列表
     */
    List<PartTimePickupUser> getAllPartTimeUsers();

    // TODO: 添加其他管理员统计相关的Mapper方法
}