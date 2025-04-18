package org.example.controller.partTimeUser;


import org.example.pojo.PartTimePickupUser;
import org.example.pojo.User;
import org.example.service.partTimeUser.PartTimePickupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/partTimeUser")
public class PartTimePickupUserController {

    @Autowired
    private PartTimePickupUserService partTimePickupUserService;

    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestParam String phone_number, @RequestParam String password, @RequestParam String role, HttpServletRequest request) {
        PartTimePickupUser partTimePickupUser = null;
        if ("part_time_user".equals(role)) {
            partTimePickupUser = partTimePickupUserService.login(phone_number, password);
        }
        //todo:登录功能session需要保存用户的id以便后续操作
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
}