package org.example.controller.partTimeUser;

import org.example.pojo.*;
import org.example.service.ordinaryUser.NotificationService;
import org.example.service.partTimeUser.PartTimePickupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/partTimeUser")
public class PartTimePickupUserController {

    @Autowired
    private PartTimePickupUserService partTimePickupUserService;
    @Autowired
    private NotificationService notificationService;

    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestParam String phone_number, @RequestParam String password, @RequestParam String role,
            HttpServletRequest request) {
        PartTimePickupUser partTimePickupUser = null;
        if ("part_time_user".equals(role)) {
            partTimePickupUser = partTimePickupUserService.login(phone_number, password);
        }
        if (partTimePickupUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("phone_number", phone_number);
            session.setAttribute("role", role); // 保存用户角色
            session.setAttribute("user_id", partTimePickupUser.getPickup_user_id());
            return "<script>sessionStorage.setItem('phone_number', '" + phone_number + "'); " +
                    "sessionStorage.setItem('role', '" + role + "'); " +
                    "sessionStorage.setItem('pickupUserId', '" + partTimePickupUser.getPickup_user_id() + "');" +
                    " window.location.href='/pickup_SpringBoot/partTimeUser/dashboard.html';</script>";
        }
        return "登录失败";
    }

    // 个人中心
    @GetMapping("/personalCenter")
    public String personalCenter() {
        return "/partTimeUser/personalCenter.html";
    }

    @GetMapping("/getPersonalInfo")
    @ResponseBody
    public Map<String, Object> getPersonalInfo(@RequestParam int pickup_user_id) {
        PartTimePickupUser user = partTimePickupUserService.getPartTimeUserById(pickup_user_id);
        Map<String, Object> result = new HashMap<>();
        if (user != null) {
            result.put("phone_number", user.getPhone_number());
            result.put("name", user.getName());
            result.put("nickname", user.getNickname());
            result.put("contact_info", user.getContact_info());
            result.put("score", user.getScore());
        }
        return result;
    }

    @PostMapping("/updatePersonalInfo")
    @ResponseBody
    public String updatePersonalInfo(@RequestBody PartTimePickupUser partTimePickupUser) {
        boolean success = partTimePickupUserService.updateUserInfo(partTimePickupUser);
        if (success) {
            return "个人信息修改成功";
        } else {
            return "个人信息修改失败";
        }
    }

    // 获取所有代取申请列表
    // 返回经纬度和其他需要展示的信息即可
    // todo:修改距离为根据快递点-代取用户当前位置-送达位置的总距离排序
    // @GetMapping("/getAllApplications")
    // @ResponseBody
    // public List<PickupApplication> getAllApplications() {
    // return partTimePickupUserService.getAllApplications();
    // }
    // 获取所有代取申请列表（修改为接收用户位置参数并调用新 Service 方法）
    @GetMapping("/getAllApplications")
    @ResponseBody
    public List<PickupApplication> getAllApplications(
            @RequestParam("userLng") double userLng, // 接收用户经度
            @RequestParam("userLat") double userLat) { // 接收用户纬度
        System.out.println("Received user location: Lng=" + userLng + ", Lat=" + userLat);
        // 调用 Service 层新方法获取排序好的列表
        List<PickupApplication> applications = partTimePickupUserService.getAllApplicationsWithLocationAndSort(userLng,
                userLat);
        return applications;
    }

    // 新增接单接口
    @PostMapping("/acceptOrder/{applicationId}")
    @ResponseBody
    public String acceptOrder(
            @RequestParam("userId") int userId,
            @PathVariable("applicationId") int applicationId,
            HttpServletRequest request) {
        try {
            // 从Session获取当前兼职用户ID（登录时保存的user_id）
            HttpSession session = request.getSession();
            int pickupUserId = userId;
            if (pickupUserId == 0) {
                return "请先登录";
            }

            // 调用Service处理接单逻辑
            boolean success = partTimePickupUserService.acceptOrder(applicationId, pickupUserId);
            return success ? "接单成功" : "接单失败（订单不存在或已被处理）";
        } catch (Exception e) {
            return "系统异常：" + e.getMessage();
        }
    }

    @GetMapping("/getNotifications") // 移除了路径变量
    @ResponseBody // 确保返回JSON格式
    public List<Notification> getNotificationsByUserId(
            @RequestParam("pickupUserId") int userId) {
        try {
            System.out.println("getNotificationsByUserId called with userId: " + userId);
            // 打印列表中的所有数据
            List<Notification> list = notificationService.getNotificationsByPickupUserId(userId);
            for (Notification notification : list) {
                System.out.println(notification.getNotification_id() + " " +
                        notification.getUser_id() + " " + notification.getPackage_id() + " " +
                        notification.getNotification_type() + " " + notification.getContent() + " " +
                        notification.getSend_time() + " " + notification.isIs_read());
            }
            return list;
        } catch (Exception e) {
            // 可以根据实际情况记录日志或者返回错误信息
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/getNotificationsForPickupByPage")
    @ResponseBody
    public Map<String, Object> getNotificationsForPickupByPage(
            @RequestParam("userId") int pickupUserId,
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        try {
            // System.out.println("getNotificationsForPickupByPage called with userId: " +
            // pickupUserId + ", pageNum: "
            // + pageNum + ", pageSize: " + pageSize);
            Map<String, Object> result = notificationService.getNotificationsForPickupByPage(pickupUserId, pageNum,
                    pageSize);
            System.out.println("Returned notifications for pickup user: " + result.get("list"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("error", "系统异常: " + e.getMessage());
            return errorResult;
        }
    }

    // 新增接口，获取所有快递点信息
    @GetMapping("/getAllExpressPoints")
    @ResponseBody
    public List<ExpressPoint> getAllExpressPoints() {
        List<ExpressPoint> expressPoints = partTimePickupUserService.getAllExpressPoints();
//        // 打印列表中的所有数据
//        for (ExpressPoint point : expressPoints) {
//            System.out.println(point.getPointId() + " " + point.getPointName() + " " + point.getLng() + " "
//                    + point.getLat());
//        }
        return expressPoints;
    }

    // 实现修改通知为已读状态
    @PostMapping("/readNotification")
    @ResponseBody
    public String readNotification(@RequestParam("notificationId") int notificationId) {
        try {
            boolean success = notificationService.readNotification(notificationId);
            return success ? "修改成功" : "修改失败";
        } catch (Exception e) {
            return "系统异常：" + e.getMessage();
        }
    }

}