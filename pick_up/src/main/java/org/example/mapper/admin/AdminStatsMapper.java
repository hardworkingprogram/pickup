package org.example.mapper.admin;

import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.PartTimePickupUser;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminStatsMapper {

    /**
     * 获取所有兼职用户
     * 
     * @return 兼职用户列表
     */
    List<PartTimePickupUser> getAllPartTimeUsers();

    /**
     * 获取每日新增代取申请数量
     *
     * @return 包含日期和数量的列表 (例如: [{date: '2023-10-26', count: 10}])
     */
    List<Map<String, Object>> getMonthlyNewApplicationsCount();

    /**
     * 获取每日包裹到达数量
     *
     * @return 包含日期和数量的列表
     */
    List<Map<String, Object>> getMonthlyPackageArrivalCount();

    // TODO: 添加其他管理员统计相关的Mapper方法
}