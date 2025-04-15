package org.example.controller.admin;

import org.example.pojo.Evaluation;
import org.example.service.ordinaryUser.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin/feedback")
public class AdminFeedbackController {
    @Autowired
    private EvaluationService evaluationService;

    // 获取所有投诉
    @GetMapping("/complaints")
    public List<Evaluation> getComplaints() {
        return evaluationService.getComplaintsByIsComplaint(true);
    }

    // 处理投诉
    @PostMapping("/handle")
    public String handleComplaint(@RequestBody ComplaintHandlingDTO dto) {
        try {
            // 更新投诉状态和处理内容
            evaluationService.updateComplaintHandling(
                    dto.getEvaluationId(),
                    dto.getHandlingContent(),
                    new Date()
            );

            // 发送通知（调用NotificationController）
            notificationController.sendComplaintHandlingNotification(
                    dto.getUserId(),
                    dto.getPackageId(),
                    dto.getHandlingContent()
            );

            return "投诉处理成功";
        } catch (Exception e) {
            return "处理失败：" + e.getMessage();
        }
    }
}

// 新增DTO
@Data
public class ComplaintHandlingDTO {
    private int evaluationId;
    private int userId;
    private int packageId;
    private String handlingContent;
}