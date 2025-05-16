package org.example.service.ordinaryUser.impl;

import org.example.mapper.ordinaryUser.NotificationMapper;
import org.example.pojo.Notification;
import org.example.service.ordinaryUser.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Notification> getNotificationsByPickupUserId(int pickupUserId) {
        return notificationMapper.getNotificationsByPickupUserId(pickupUserId);
    }

    @Override
    public Map<String, Object> getNotificationsByPage(int userId, int pageNum, int pageSize) {
        // 计算偏移量
        int offset = (pageNum - 1) * pageSize;

        // 获取总记录数
        int total = notificationMapper.getTotalCountByUserId(userId);

        // 获取分页数据
        List<Notification> notifications = notificationMapper.getNotificationsByPage(userId, offset, pageSize);

        // 封装返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("list", notifications);
        result.put("pageNum", pageNum);
        result.put("pageSize", pageSize);

        return result;
    }

    @Override
    public Map<String, Object> getNotificationsForPickupByPage(int pickupUserId, int pageNum, int pageSize) {
        // 计算偏移量
        int offset = (pageNum - 1) * pageSize;

        // 获取总记录数
        int total = notificationMapper.getTotalCountForPickupByUserId(pickupUserId);

        // 获取分页数据
        List<Notification> notifications = notificationMapper.getNotificationsForPickupByPage(pickupUserId, offset,
                pageSize);

        // 封装返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("list", notifications);
        result.put("pageNum", pageNum);
        result.put("pageSize", pageSize);

        return result;
    }
}