package org.example.service.admin.impl;

import org.example.mapper.admin.AdminPickupApplicationMapper;
import org.example.pojo.Notification;
import org.example.pojo.PickupApplication;
import org.example.service.admin.AdminTaskAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.mapper.partTimeUser.PartTimePickupUserMapper;
import org.example.mapper.ordinaryUser.NotificationMapper;

import java.util.Date;
import java.util.List;

@Service
public class AdminTaskAssignmentServiceImpl implements AdminTaskAssignmentService {
    @Autowired
    private AdminPickupApplicationMapper adminPickupApplicationMapper;
    @Autowired
    private PartTimePickupUserMapper partTimePickupUserMapper;
    @Autowired
    private NotificationMapper notificationMapper;

    public List<PickupApplication> getUnassignedTasks() {
        return adminPickupApplicationMapper.selectUnassignedTasksOrderByCreateTime();
    }

    public boolean assignTask(int applicationId, int pickupUserId) {
        // 检查任务是否存在且未分配
        PickupApplication application = adminPickupApplicationMapper.getApplicationById(applicationId);
        if (application == null || !"待处理".equals(application.getStatus())) {
            return false;
        }
        // 接任务、发送通知给用户
        int packageId = application.getPackage_id();
        int updatePackageRows = partTimePickupUserMapper.updatePackageTimeAndId(packageId, pickupUserId);
        int updateApplicationRows = partTimePickupUserMapper.updateApplicationStatus(applicationId);

        // 发送通知给用户
        String content = "您的包裹（包裹ID：" + packageId + "）已经由管理员分配，正在配送中，请耐心等候。";
        Notification notification = new Notification(pickupUserId, packageId, "快递接单通知", content, new Date());
        notificationMapper.insertNotification(notification);

        // 新增一条通知记录给强制接取的代取用户
        String content_pickup = "管理员已为你接取（包裹ID：" + packageId + "），请及时处理。";
        Notification notification_pickup = new Notification(pickupUserId, packageId, "强制分配订单通知", content_pickup, new Date());
        notificationMapper.insertNotificationForPickup(notification_pickup);

        return updatePackageRows > 0 && updateApplicationRows > 0;
    }
}
