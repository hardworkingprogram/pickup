package org.example.mapper.ordinaryUser;

import org.example.pojo.Notification;
import java.util.List;

public interface NotificationMapper {
    void insertNotification(Notification notification);
    List<Notification> getNotificationsByUserId(int userId);

    void insertNotificationForPickup(Notification notificationPickup);
}