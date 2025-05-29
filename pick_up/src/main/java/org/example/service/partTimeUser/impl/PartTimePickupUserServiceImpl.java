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
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class PartTimePickupUserServiceImpl implements PartTimePickupUserService {

    @Autowired
    private PartTimePickupUserMapper partTimePickupUserMapper;
    @Autowired
    private NotificationMapper notificationMapper;

    // 创建一个固定大小的线程池，线程数量可以根据实际情况调整
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

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

        if (applications.isEmpty()) {
            return applications;
        }

        // 2. 创建 Callable 任务列表
        List<Callable<PickupApplication>> tasks = applications.stream()
                .map(app -> (Callable<PickupApplication>) () -> {
                    // 获取送达位置经纬度 (通过地理编码获取)
                    String pickupLocation = app.getPickup_location();
                    double[] pickupLngLat = GaodeGeocodingUtil.addressToLngLat(pickupLocation); // 调用地理编码工具
                    if (pickupLngLat != null && pickupLngLat.length == 2) {
                        app.setPickup_lng(pickupLngLat[0]);
                        app.setPickup_lat(pickupLngLat[1]);
                        // 获取快递点经纬度 (已通过 Mapper 查询获取)
                        Double expressLng = app.getExpress_lng();
                        Double expressLat = app.getExpress_lat();
                        if (expressLng != null && expressLat != null && app.getPickup_lng() != null
                                && app.getPickup_lat() != null) {
                            // 计算用户到快递点的距离
                            double distUserToExpress = GeoUtil.calculateDistance(userLat, userLng, expressLat,
                                    expressLng);
                            // 计算快递点到送达位置的距离
                            double distExpressToPickup = GeoUtil.calculateDistance(expressLat, expressLng,
                                    app.getPickup_lat(),
                                    app.getPickup_lng());
                            double totalDistance = distUserToExpress + distExpressToPickup;
                            app.setTotalDistance(totalDistance);
                        } else {
                            app.setTotalDistance(Double.MAX_VALUE);
                        }
                    } else {
                        app.setPickup_lng(null); // 将经纬度设为 null
                        app.setPickup_lat(null);
                        app.setTotalDistance(Double.MAX_VALUE);
                    }
                    return app; // 返回处理后的 PickupApplication 对象
                })
                .collect(Collectors.toList());

        // 3. 提交任务给线程池并收集 Future
        List<Future<PickupApplication>> futures = null;
        try {
            futures = executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            // 处理中断异常
            Thread.currentThread().interrupt();
            e.printStackTrace(); // 或者记录日志
            return applications; // 返回原始列表或空列表，取决于期望的行为
        }

        // 4. 获取每个任务的结果
        List<PickupApplication> processedApplications = new java.util.ArrayList<>();
        for (Future<PickupApplication> future : futures) {
            try {
                processedApplications.add(future.get()); // 获取处理后的对象
            } catch (InterruptedException | ExecutionException e) {
                // 处理获取结果时的异常
                e.printStackTrace(); // 或者记录日志
                // 可以选择跳过当前申请或进行其他错误处理
            }
        }

        // 5. 按总距离排序
        processedApplications.sort(Comparator.comparingDouble(PickupApplication::getTotalDistance));

        return processedApplications;
    }

    @Override
    public List<ExpressPoint> getAllExpressPoints() {
        return partTimePickupUserMapper.getAllExpressPoints();
    }

    // 在服务关闭时关闭线程池，避免资源泄露
    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("Executor service did not terminate."); // 或者记录日志
                }
            }
        } catch (InterruptedException ie) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    // 为了确保 shutdown 方法被调用，可以考虑实现 DisposableBean 接口或使用 @PreDestroy 注解
    // 例如:
    // @PreDestroy
    // public void onDestroy() {
    // shutdown();
    // }

}