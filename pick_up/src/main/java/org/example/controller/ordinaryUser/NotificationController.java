package org.example.controller.ordinaryUser;

import org.example.pojo.Notification;
import org.example.service.ordinaryUser.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{userId}")
    public List<Notification> getNotificationsByUserId(@PathVariable int userId) {
        return notificationService.getNotificationsByUserId(userId);
    }

    // 模拟快递到达通知
    @PostMapping("/expressArrival")
    public void sendExpressArrivalNotification(@RequestParam int userId, @RequestParam int packageId) {
        String content = "您的包裹（包裹ID：" + packageId + "）已到达快递点，请及时处理。";
        Notification notification = new Notification(userId, packageId, "快递到达通知", content, new Date());
        notificationService.sendNotification(notification);
    }

    // 模拟代取成功通知
    @PostMapping("/pickupSuccess")
    public void sendPickupSuccessNotification(@RequestParam int userId, @RequestParam int packageId) {
        String content = "您的包裹（包裹ID：" + packageId + "）已被成功代取。";
        Notification notification = new Notification(userId, packageId, "代取成功通知", content, new Date());
        notificationService.sendNotification(notification);
    }
}