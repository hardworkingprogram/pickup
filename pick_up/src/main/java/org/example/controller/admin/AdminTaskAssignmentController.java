package org.example.controller.admin;

import org.example.pojo.PickupApplication;
import org.example.service.admin.AdminTaskAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminTaskAssignmentController {

    @Autowired
    private AdminTaskAssignmentService adminTaskAssignmentService;

    @GetMapping("/unassignedTasks")
    public List<PickupApplication> getUnassignedTasks() {
        return adminTaskAssignmentService.getUnassignedTasks();
    }

    /* 分配任务
    1.修改数据库pickup_applications, packages, notifications表
    2.再创建一个专属于代取用户的通知表notifications_pickup表，并插入对应记录通知该代取用户
    */
    @PostMapping("/assignTask/{applicationId}/{pickupUserId}")
    public String assignTask(@PathVariable int applicationId, @PathVariable int pickupUserId) {
        boolean success = adminTaskAssignmentService.assignTask(applicationId, pickupUserId);
        if (success) {
            return "任务分配成功";
        }
        return "任务分配失败";
    }
}