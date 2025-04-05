package org.example.controller.ordinaryUser;


import org.example.pojo.User;
import org.example.service.ordinaryUser.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUsers")
    @ResponseBody
    public String findAllUsers() {
        List list =  userService.findAllUsers();
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            User u = (User)list.get(i);
            s+=u.getUser_id()+" "+u.getPhone_number()+" "+u.getPassword()+" "+u.getName()+" "+u.getNickname()+" "+u.getContact_info()+" "+u.getAddress()+"<br>";
        }
        return s;
    }

//    todo:这里需要实现注册功能
    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestParam String phone_number, @RequestParam String password, @RequestParam String role, HttpServletRequest request) {
        User user = null;
        if ("ordinary_user".equals(role)) {
            user = userService.login(phone_number, password);
        } else if ("part_time_user".equals(role)) {
            // 这里需要实现兼职用户的登录逻辑，假设存在 PartTimeUserService
            // user = partTimeUserService.login(phone_number, password);
        } else if ("admin".equals(role)) {
            // 这里需要实现管理员的登录逻辑，假设存在 AdminService
            // user = adminService.login(phone_number, password);
        }
        //todo:登录功能session需要保存用户的id以便后续操作
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("phone_number", phone_number);
            session.setAttribute("role", role); // 保存用户角色
            session.setAttribute("user_id", user.getUser_id());
            return "<script>sessionStorage.setItem('phone_number', '" + phone_number + "'); sessionStorage.setItem('role', '" + role + "'); sessionStorage.setItem('userId', '" + user.getUser_id() + "'); window.location.href='/pickup_SpringBoot/ordinaryUser/dashboard.html';</script>";
        }
        return "登录失败";
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
        System.out.println("接收到的用户信息：" + user.getUser_id()+" "+user.getPhone_number()+" "+user.getPassword()+" "+user.getName()+" "+user.getNickname()+" "+user.getContact_info()+" "+user.getAddress());
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
