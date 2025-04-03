package org.example.service;

import org.example.pojo.Notification;
import java.util.List;

public interface NotificationService {
    void sendNotification(Notification notification);
    List<Notification> getNotificationsByUserId(int userId);
}