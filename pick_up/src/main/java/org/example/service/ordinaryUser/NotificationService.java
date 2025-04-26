package org.example.service.ordinaryUser;

import org.example.pojo.Notification;
import java.util.List;

public interface NotificationService {
    void sendNotification(Notification notification);
    List<Notification> getNotificationsByUserId(int userId);
    List<Notification> getNotificationsByPickupUserId(int pickupUserId);
}