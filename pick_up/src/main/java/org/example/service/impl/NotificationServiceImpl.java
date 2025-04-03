package org.example.service.impl;

import org.example.mapper.NotificationMapper;
import org.example.pojo.Notification;
import org.example.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public void sendNotification(Notification notification) {
        notificationMapper.insertNotification(notification);
    }

    @Override
    public List<Notification> getNotificationsByUserId(int userId) {
        return notificationMapper.getNotificationsByUserId(userId);
    }
}