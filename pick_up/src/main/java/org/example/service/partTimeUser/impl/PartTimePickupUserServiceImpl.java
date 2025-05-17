package org.example.service.partTimeUser.impl;

import org.example.mapper.ordinaryUser.NotificationMapper;
import org.example.mapper.partTimeUser.PartTimePickupUserMapper;
import org.example.pojo.*;
import org.example.service.partTimeUser.PartTimePickupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
        // 这个方法将不再使用，或者可以保留用于其他不需要排序的场景
        // 新的排序逻辑在 getAllApplicationsWithLocationAndSort 中实现
        throw new UnsupportedOperationException(
                "This method is deprecated. Use getAllApplicationsWithLocationAndSort instead.");
        // // 获取所有未处理的订单
        // List<PickupApplication> applications =
        // partTimePickupUserMapper.selectUnassignedTasks();
        // // 添加经纬度
        // for (PickupApplication app : applications) {
        // String location = app.getPickup_location();
        // double[] lngLat = GaodeGeocodingUtil.addressToLngLat(location);
        //
        // if (lngLat != null) {
        // app.setPickup_lng(lngLat[0]); // 新增经度字段（需在PickupApplication实体中添加）
        // app.setPickup_lat(lngLat[1]); // 新增纬度字段
        // } else {
        // app.setPickup_lng(null);
        // app.setPickup_lat(null);
        // }
        // System.out.println(app.getPickup_lng());
        // System.out.println(app.getPickup_lat());
        // System.out.println(app.getPickup_location());
        // System.out.println("\n");
        // }
        //
        // return applications;
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

        // 更新包裹状态
        int updatePackageRows = partTimePickupUserMapper.updatePackageTimeAndId(packageId, pickupUserId);
        // 更新申请状态
        int updateApplicationRows = partTimePickupUserMapper.updateApplicationStatus(applicationId);
        // 发送通知给用户
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

    @Override
    public List<PickupApplication> getAllApplicationsWithLocationAndSort(double userLng, double userLat) {
        // 1. 获取所有待处理的订单，同时获取快递点经纬度
        List<PickupApplication> applications = partTimePickupUserMapper.selectUnassignedTasks();

        // 2. 对每个申请进行地理编码（送达位置），计算总距离
        for (PickupApplication app : applications) {
            // 获取送达位置经纬度 (通过地理编码获取)
            String pickupLocation = app.getPickup_location();
            // 您可以根据需要提供城市参数来提高地理编码精度，例如从用户或系统配置中获取
            double[] pickupLngLat = GaodeGeocodingUtil.addressToLngLat(pickupLocation); // 调用地理编码工具

            if (pickupLngLat != null && pickupLngLat.length == 2) {
                app.setPickup_lng(pickupLngLat[0]);
                app.setPickup_lat(pickupLngLat[1]);

                // 获取快递点经纬度 (已通过 Mapper 查询获取)
                Double expressLng = app.getExpress_lng();
                Double expressLat = app.getExpress_lat();

                // 确保快递点经纬度和送达位置经纬度都已获取
                if (expressLng != null && expressLat != null && app.getPickup_lng() != null
                        && app.getPickup_lat() != null) {
                    // 计算用户到快递点的距离
                    double distUserToExpress = GeoUtil.calculateDistance(userLat, userLng, expressLat, expressLng);

                    // 计算快递点到送达位置的距离
                    double distExpressToPickup = GeoUtil.calculateDistance(expressLat, expressLng, app.getPickup_lat(),
                            app.getPickup_lng()); // 修正这里经纬度参数顺序

                    // 计算总距离
                    double totalDistance = distUserToExpress + distExpressToPickup;
                    app.setTotalDistance(totalDistance);

                } else {
                    // 如果获取不到必要的经纬度，将总距离设为一个很大的值，使其排在后面
                    app.setTotalDistance(Double.MAX_VALUE);
                }

            } else {
                // 如果送达位置地理编码失败，将总距离设为一个很大的值
                app.setPickup_lng(null); // 将经纬度设为 null
                app.setPickup_lat(null);
                app.setTotalDistance(Double.MAX_VALUE);
            }
        }

        // 3. 按总距离排序
        applications.sort(Comparator.comparingDouble(PickupApplication::getTotalDistance));

        return applications;
    }

    @Override
    public List<ExpressPoint> getAllExpressPoints() {
        return partTimePickupUserMapper.getAllExpressPoints();
    }

}