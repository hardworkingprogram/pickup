package org.example.mapper.ordinaryUser;

import org.apache.ibatis.annotations.Param;
import org.example.pojo.Notification;
import java.util.List;

public interface NotificationMapper {
    void insertNotification(Notification notification);
    List<Notification> getNotificationsByUserId(int userId);
    List<Notification> getNotificationsByPickupUserId(int pickupUserId);
    void insertNotificationForPickup(Notification notificationPickup);
    // 添加分页查询方法
    List<Notification> getNotificationsByPage(
            @Param("userId") int userId,
            @Param("offset") int offset,
            @Param("pageSize") int pageSize
    );
    // 添加获取总数方法
    int getTotalCountByUserId(@Param("userId") int userId);
}