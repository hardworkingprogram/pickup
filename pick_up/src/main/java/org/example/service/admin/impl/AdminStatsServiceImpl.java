package org.example.service.admin.impl;

import org.example.mapper.admin.AdminStatsMapper;
import org.example.pojo.PartTimePickupUser;
import org.example.service.admin.AdminStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminStatsServiceImpl implements AdminStatsService {

    @Autowired
    private AdminStatsMapper adminStatsMapper;

    @Override
    public Map<String, Long> getPartTimeUserRatingDistribution() {
        // 1. 获取所有兼职用户
        List<PartTimePickupUser> allUsers = adminStatsMapper.getAllPartTimeUsers();

        // 2. 按评分区间进行分组统计
        Map<String, Long> distribution = allUsers.stream()
                .collect(Collectors.groupingBy(user -> {
                    float score = user.getScore();
                    if (score >= 0 && score < 1)
                        return "0-1";
                    else if (score >= 1 && score < 2)
                        return "1-2";
                    else if (score >= 2 && score < 3)
                        return "2-3";
                    else if (score >= 3 && score < 4)
                        return "3-4";
                    else if (score >= 4 && score <= 5)
                        return "4-5";
                    else
                        return "其他"; // 处理意外的评分范围
                }, Collectors.counting()));

        // 确保所有区间都存在，没有用户的区间数量为0
        Map<String, Long> finalDistribution = new HashMap<>();
        String[] ranges = { "0-1", "1-2", "2-3", "3-4", "4-5", "其他" };
        for (String range : ranges) {
            finalDistribution.put(range, distribution.getOrDefault(range, 0L));
        }

        return finalDistribution;
    }

    @Override
    public List<Map<String, Object>> getDailyNewApplicationsCount() {
        // 调用 Mapper 方法获取数据
        return adminStatsMapper.getDailyNewApplicationsCount();
    }

    // TODO: 添加其他统计方法实现
}