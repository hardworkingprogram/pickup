package org.example.service.ordinaryUser;

import org.example.pojo.Notification;
import java.util.List;
import java.util.Map;

public interface NotificationService {
    void sendNotification(Notification notification);

    List<Notification> getNotificationsByUserId(int userId);

    List<Notification> getNotificationsByPickupUserId(int pickupUserId);

    // 添加普通用户通知分页查询方法
    Map<String, Object> getNotificationsByPage(int userId, int pageNum, int pageSize);

    // 添加兼职代取用户通知分页查询方法
    Map<String, Object> getNotificationsForPickupByPage(int pickupUserId, int pageNum, int pageSize);
    boolean readNotification(int notificationId);
}