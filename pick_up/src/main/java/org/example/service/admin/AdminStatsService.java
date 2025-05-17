package org.example.service.admin;

import java.util.List;
import java.util.Map;

public interface AdminStatsService {
    /**
     * 获取兼职用户评分分布统计数据
     * 
     * @return 评分区间到用户数量的Map
     */
    Map<String, Long> getPartTimeUserRatingDistribution();

    /**
     * 获取每日新增代取申请数量
     *
     * @return 包含日期和数量的列表
     */
    List<Map<String, Object>> getMonthlyNewApplicationsCount();

    /**
     * 获取每日包裹到达数量
     *
     * @return 包含日期和数量的列表
     */
    List<Map<String, Object>> getMonthlyPackageArrivalCount();

    // TODO: 添加其他统计方法
}