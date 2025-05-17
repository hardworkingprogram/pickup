package org.example.controller.admin;

import org.example.service.admin.AdminStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/stats")
public class AdminStatsController {

    @Autowired
    private AdminStatsService adminStatsService;

    /**
     * 获取兼职用户评分分布统计数据
     *
     * @return 评分区间到用户数量的Map
     */
    @GetMapping("/partTimeUserRatingDistribution")
    @ResponseBody
    public Map<String, Long> getPartTimeUserRatingDistribution() {
        return adminStatsService.getPartTimeUserRatingDistribution();
    }

    /**
     * 获取每日新增代取申请数量
     * 
     * @return 包含日期和数量的列表
     */
    @GetMapping("/dailyNewApplicationsCount")
    @ResponseBody
    public List<Map<String, Object>> getDailyNewApplicationsCount() {
        return adminStatsService.getDailyNewApplicationsCount();
    }

    // TODO: 添加其他统计接口
}