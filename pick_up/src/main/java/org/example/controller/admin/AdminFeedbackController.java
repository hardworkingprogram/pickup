
package org.example.controller.admin;

import org.example.pojo.Evaluation;
import org.example.pojo.Notification;
import org.example.service.admin.FeedbackService;
import org.example.service.ordinaryUser.NotificationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin/feedback")
public class AdminFeedbackController {
    @Resource
    private FeedbackService evaluationService;

    @Resource
    private NotificationService notificationService;

    // 1. 获取投诉列表
    @GetMapping("/list")
    public List<Evaluation> getComplaintList() {
        return evaluationService.getComplaints();
    }

    // 2. 处理投诉（含通知发送）
    @PostMapping("/handle")
    public String handleComplaint(@RequestBody Evaluation handlingEval) {
        try {
            // 1. 更新投诉状态
            boolean success = evaluationService.handleComplaint(
                    handlingEval.getEvaluation_id()
            );

            // 2. 发送通知（调用通知服务）
            if (success) {
                Notification notification = new Notification(
                        handlingEval.getUser_id(),
                        handlingEval.getPackage_id(),
                        "投诉处理通知",
                        "您的投诉（包裹ID：" + handlingEval.getPackage_id() +
                                "）已处理：" + handlingEval.getFeedback(),
                        new Date()
                );
                notificationService.sendNotification(notification);
                return "处理成功";
            }
            return "处理失败";
        } catch (Exception e) {
            return "系统异常：" + e.getMessage();
        }
    }

}