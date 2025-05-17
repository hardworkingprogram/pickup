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
    public List<Map<String, Object>> getMonthlyNewApplicationsCount() {
        // 1. 调用 Mapper 方法获取数据库中存在新增申请的月份数据
        List<Map<String, Object>> newApplications = adminStatsMapper.getMonthlyNewApplicationsCount();

        // 2. 将数据库返回的数据转换成 Map，方便按月份查找
        Map<String, Long> applicationCountMap = newApplications.stream()
                .collect(Collectors.toMap(
                        item -> (String) item.get("month"), // 使用月份字符串作为 key
                        item -> (Long) item.get("count"),
                        (oldValue, newValue) -> newValue // 处理月份重复的情况
                ));

        // 3. 确定月份范围（从最早的新增申请月份到当前月份）
        List<String> months = newApplications.stream()
                .map(item -> (String) item.get("month"))
                .sorted() // 按月份字符串排序
                .collect(Collectors.toList());

        if (months.isEmpty()) {
            return new java.util.ArrayList<>(); // 如果没有数据，返回空列表
        }

        // 解析最早的年月
        java.time.YearMonth startMonth = java.time.YearMonth.parse(months.get(0));
        java.time.YearMonth endMonth = java.time.YearMonth.now();

        // 4. 生成完整月份列表并填充数据
        List<Map<String, Object>> monthlyStats = new java.util.ArrayList<>();
        java.time.YearMonth currentMonth = startMonth;
        while (!currentMonth.isAfter(endMonth)) {
            String monthStr = currentMonth.toString(); // 格式为 YYYY-MM
            Long count = applicationCountMap.getOrDefault(monthStr, 0L);

            Map<String, Object> monthlyStat = new HashMap<>();
            monthlyStat.put("month", monthStr);
            monthlyStat.put("count", count);
            monthlyStats.add(monthlyStat);

            currentMonth = currentMonth.plusMonths(1);
        }

        return monthlyStats;
    }

    @Override
    public List<Map<String, Object>> getMonthlyPackageArrivalCount() {
        // 1. 调用 Mapper 方法获取数据库中存在到达量的月份数据
        List<Map<String, Object>> arrivedPackages = adminStatsMapper.getMonthlyPackageArrivalCount();

        // 2. 将数据库返回的数据转换成 Map，方便按月份查找
        Map<String, Long> arrivalCountMap = arrivedPackages.stream()
                .collect(Collectors.toMap(
                        item -> (String) item.get("month"), // 直接使用月份字符串作为 key
                        item -> (Long) item.get("count"),
                        (oldValue, newValue) -> newValue // 处理月份重复的情况
                ));

        // 3. 确定月份范围（从最早的到达月份到当前月份）
        List<String> months = arrivedPackages.stream()
                .map(item -> (String) item.get("month"))
                .sorted() // 按月份字符串排序
                .collect(Collectors.toList());

        if (months.isEmpty()) {
            return new java.util.ArrayList<>(); // 如果没有数据，返回空列表
        }

        // 解析最早的年月
        java.time.YearMonth startMonth = java.time.YearMonth.parse(months.get(0));
        java.time.YearMonth endMonth = java.time.YearMonth.now();

        // 4. 生成完整月份列表并填充数据
        List<Map<String, Object>> monthlyStats = new java.util.ArrayList<>();
        java.time.YearMonth currentMonth = startMonth;
        while (!currentMonth.isAfter(endMonth)) {
            String monthStr = currentMonth.toString(); // 格式为 YYYY-MM
            Long count = arrivalCountMap.getOrDefault(monthStr, 0L);

            Map<String, Object> monthlyStat = new HashMap<>();
            monthlyStat.put("month", monthStr);
            monthlyStat.put("count", count);
            monthlyStats.add(monthlyStat);

            currentMonth = currentMonth.plusMonths(1);
        }

        return monthlyStats;
    }

    // TODO: 添加其他统计方法实现
}