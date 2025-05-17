package org.example.service.partTimeUser.impl;


import org.example.mapper.ordinaryUser.NotificationMapper;
import org.example.mapper.partTimeUser.PartTimePickupUserMapper;
import org.example.pojo.GaodeGeocodingUtil;
import org.example.pojo.Notification;
import org.example.pojo.PartTimePickupUser;
import org.example.pojo.PickupApplication;
import org.example.service.partTimeUser.PartTimePickupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PartTimePickupUserServiceImpl implements PartTimePickupUserService {

    @Autowired
    private PartTimePickupUserMapper partTimePickupUserMapper;
    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public PartTimePickupUser getPartTimeUserById(int pickup_user_id) {
        return partTimePickupUserMapper.getPartTimeUserById(pickup_user_id);
    }

    @Override
    public PartTimePickupUser login(String phone_number, String password) {
        return partTimePickupUserMapper.selectUserByPhoneAndPassword(phone_number, password);
    }

    @Override
    public List<PickupApplication> getAllApplications() {
        // 获取所有未处理的订单
        List<PickupApplication> applications = partTimePickupUserMapper.selectUnassignedTasks();
        // 添加经纬度
        for (PickupApplication app : applications) {
            String location = app.getPickup_location();
            double[] lngLat = GaodeGeocodingUtil.addressToLngLat(location);

            if (lngLat != null) {
                app.setPickup_lng(lngLat[0]); // 新增经度字段（需在PickupApplication实体中添加）
                app.setPickup_lat(lngLat[1]); // 新增纬度字段
            } else {
                app.setPickup_lng(0);
                app.setPickup_lat(0);
            }
            System.out.println(app.getPickup_lng());
            System.out.println(app.getPickup_lat());
            System.out.println(app.getPickup_location());
            System.out.println("\n");
        }

        return applications;
    }

    @Override
    public boolean acceptOrder(int applicationId, int pickupUserId) {

        // 1. 校验订单是否存在且状态为"待处理"
        PickupApplication application = partTimePickupUserMapper.getApplicationById(applicationId);
        if (application == null || !"待处理".equals(application.getStatus())) {
            return false;
        }

        // 2. 更新订单状态为"已处理"

        int packageId = application.getPackage_id();
        int userId = application.getUser_id();

        //更新包裹状态
        int updatePackageRows = partTimePickupUserMapper.updatePackageTimeAndId(packageId, pickupUserId);
        //更新申请状态
        int updateApplicationRows = partTimePickupUserMapper.updateApplicationStatus(applicationId);
        //发送通知给用户
        String content = "您的包裹（包裹ID：" + packageId + "）正在配送中，请耐心等候。";
        Notification notification = new Notification(userId, packageId, "快递接单通知", content, new Date());
        notificationMapper.insertNotification(notification);

        return updatePackageRows > 0 && updateApplicationRows > 0;
    }

    @Override
    public boolean updateUserInfo(PartTimePickupUser partTimePickupUser) {
        return partTimePickupUserMapper.updateUserInfo(partTimePickupUser) > 0;
    }

    @Override
    public PartTimePickupUser getByPhone(String phoneNumber) {
        return partTimePickupUserMapper.selectByPhone(phoneNumber);
    }

    @Override
    public boolean register(PartTimePickupUser user) {
        user.setScore(0.0f);
        int result = partTimePickupUserMapper.insertUser(user);
        return result > 0;
    }

}