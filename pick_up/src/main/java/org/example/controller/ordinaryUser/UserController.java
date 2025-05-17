package org.example.controller.ordinaryUser;

import org.example.pojo.Admin;
import org.example.pojo.PartTimePickupUser;
import org.example.pojo.User;
import org.example.service.admin.AdminService;
import org.example.service.ordinaryUser.UserService;
import org.example.service.partTimeUser.PartTimePickupUserService;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ordinaryUser")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PartTimePickupUserService partTimePickupUserService;
    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    @ResponseBody
    public Result<Map<String, Object>> login(
            @RequestParam String phone_number,
            @RequestParam String password,
            @RequestParam String role,
            HttpServletRequest request) {
        User user = null;
        Admin admin = null;
        PartTimePickupUser partTimePickupUser = null;

        // 获取session
        HttpSession session = request.getSession();
        // 封装响应数据
        Map<String, Object> data = new HashMap<>();

        if ("ordinary_user".equals(role)) {
            user = userService.login(phone_number, password);
            session.setAttribute("user_id", user.getUser_id());
            data.put("user_id", user.getUser_id());
        } else if ("part_time_user".equals(role)) {
            partTimePickupUser = partTimePickupUserService.login(phone_number, password);
            session.setAttribute("user_id", partTimePickupUser.getPickup_user_id());
            data.put("user_id", partTimePickupUser.getPickup_user_id());
        } else if ("admin".equals(role)) {
            admin = adminService.login(phone_number, password);
            session.setAttribute("user_id", admin.getAdmin_id());
            data.put("user_id", admin.getAdmin_id());
        }

        if (user != null || partTimePickupUser != null || admin != null) {
            session.setAttribute("phone_number", phone_number);
            session.setAttribute("role", role);

            data.put("role", role);
            data.put("phone_number", phone_number);

            return Result.success(data, "登录成功");
        }
        return Result.fail("登录失败，账号或密码错误");
    }

    @PostMapping("/register")
    public String register(@RequestBody Map<String, Object> userData) {
        try {
            String phoneNumber = (String) userData.get("phone_number");
            String password = (String) userData.get("password");
            String name = (String) userData.get("name");
            String nickname = (String) userData.get("nickname");
            String contactInfo = (String) userData.get("contact_info");
            String userType = (String) userData.get("user_type");

            // 校验必填项
            if (phoneNumber == null || password == null || name == null || nickname == null || contactInfo == null
                    || userType == null) {
                return "请填写所有必填字段";
            }
            if (!phoneNumber.matches("^1[3-9]\\d{9}$")) {
                return "手机号格式不正确";
            }
            if (password.length() < 6) {
                return "密码长度至少6位";
            }

            if ("ordinary".equals(userType)) {
                String address = (String) userData.get("address");
                if (address == null) {
                    return "请填写地址";
                }
                // 检查手机号是否已存在
                if (userService.getByPhone(phoneNumber) != null) {
                    return "该手机号已被注册";
                }
                User user = new User();
                user.setPhone_number(phoneNumber);
                user.setPassword(password);
                user.setName(name);
                user.setNickname(nickname);
                user.setContact_info(contactInfo);
                user.setAddress(address);
                if (userService.register(user)) {
                    return "注册成功";
                } else {
                    return "注册失败，请稍后重试";
                }
            } else if ("partTime".equals(userType)) {
                // 评分默认为0f
                float score = 0f;
                // 检查手机号是否已存在
                if (partTimePickupUserService.getByPhone(phoneNumber) != null) {
                    return "该手机号已被注册";
                }
                PartTimePickupUser user = new PartTimePickupUser();
                user.setPhone_number(phoneNumber);
                user.setPassword(password);
                user.setName(name);
                user.setNickname(nickname);
                user.setContact_info(contactInfo);
                user.setScore(score);
                if (partTimePickupUserService.register(user)) {
                    return "注册成功";
                } else {
                    return "注册失败，请稍后重试";
                }
            } else {
                return "无效的用户类型";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "注册失败，请稍后重试";
        }
    }

    // 个人中心
    @RequestMapping("/personalCenter")
    public String personalCenter() {
        return "/ordinaryUser/personalCenter.html";
    }

    @GetMapping("/getPersonalInfo")
    @ResponseBody
    public Map<String, Object> getPersonalInfo(@RequestParam int user_id) {
        User user = userService.getUserById(user_id);
        Map<String, Object> result = new HashMap<>();
        if (user != null) {
            result.put("name", user.getName());
            result.put("nickname", user.getNickname());
            result.put("contact_info", user.getContact_info());
            result.put("address", user.getAddress());
        }
        return result;
    }

    @PostMapping("/updatePersonalInfo")
    @ResponseBody
    public String updatePersonalInfo(@RequestBody User user) {
        System.out.println("接收到的用户信息：" + user.getUser_id() + " " + user.getPhone_number() + " " + user.getPassword()
                + " " + user.getName() + " " + user.getNickname() + " " + user.getContact_info() + " "
                + user.getAddress());
        boolean success = userService.updateUserInfo(user);
        if (success) {
            return "个人信息修改成功";
        } else {
            return "个人信息修改失败";
        }
    }

    @RequestMapping("/announcement")
    public String announcement() {
        return "/ordinaryUser/announcement.html";
    }

    @RequestMapping("/submitApplication")
    public String submitApplication() {
        return "/ordinaryUser/submitApplication.html";
    }

    @RequestMapping("/viewPickupRecords")
    public String viewPickupRecords() {
        return "/ordinaryUser/viewPickupRecords.html";
    }

    @RequestMapping("/trackPackageStatus")
    public String trackPackageStatus() {
        return "/ordinaryUser/trackPackageStatus.html";
    }

    @RequestMapping("/notification")
    public String notification() {
        return "/ordinaryUser/notification.html";
    }

    @RequestMapping("/feedback")
    public String feedback() {
        return "/ordinaryUser/feedback.html";
    }
}
