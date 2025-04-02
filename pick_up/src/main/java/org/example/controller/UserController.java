package org.example.controller;


import org.example.pojo.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    @RequestMapping("/login")
    @ResponseBody
    public String login(String phone_number, String password, HttpServletRequest request) {
        User user = userService.login(phone_number, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("phone_number", phone_number);
            return "<script>sessionStorage.setItem('phone_number', '" + phone_number + "'); window.location.href='/pickup_SpringBoot/dashboard.html';</script>";
        }
        return "登录失败";
    }


    // 个人中心
    @RequestMapping("/personalCenter")
    public String personalCenter() {
        return "personalCenter.html";
    }
    @GetMapping("/getPersonalInfo")
    @ResponseBody
    public Map<String, Object> getPersonalInfo(@RequestParam String phone_number) {
        User user = userService.getUserByPhoneNumber(phone_number);
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
        System.out.println("接收到的用户信息：" + user);
        boolean success = userService.updateUserInfo(user);
        if (success) {
            return "个人信息修改成功";
        } else {
            return "个人信息修改失败";
        }
    }




    @RequestMapping("/announcement")
    public String announcement() {
        return "announcement.html";
    }

    @RequestMapping("/expressPickup")
    public String expressPickup() {
        return "expressPickup.html";
    }

    @RequestMapping("/notification")
    public String notification() {
        return "notification.html";
    }

    @RequestMapping("/feedback")
    public String feedback() {
        return "feedback.html";
    }
}
