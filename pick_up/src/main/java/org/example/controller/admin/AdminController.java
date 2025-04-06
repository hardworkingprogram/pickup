package org.example.controller.admin;

import org.example.pojo.Admin;
import org.example.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    // 管理员登录
    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestParam String phone_number, @RequestParam String password, @RequestParam String role, HttpServletRequest request) {
        Admin admin = adminService.login(phone_number, password);
        if (admin != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user_id", admin.getAdmin_id());
            session.setAttribute("role", role); // 保存用户角色
            session.setAttribute("username", phone_number);
            return "<script>sessionStorage.setItem('phone_number', '" + phone_number + "'); " +
                    "sessionStorage.setItem('role', '" + role + "'); " +
                    "sessionStorage.setItem('userId', '" + admin.getAdmin_id() + "');" +
                    " window.location.href='/pickup_SpringBoot/admin/dashboard.html';</script>";

        }
        return "登录失败";
    }
    // 新增：查看所有普通用户信息
    @GetMapping("/ordinaryUsers")
    public List<Map<String, Object>> getAllOrdinaryUsers() {
        return adminService.getAllOrdinaryUsers();
    }

    // 新增：查看所有兼职代取用户信息
    @GetMapping("/partTimeUsers")
    public List<Map<String, Object>> getAllPartTimeUsers() {
        return adminService.getAllPartTimeUsers();
    }

    // 新增：修改用户信息
    @PostMapping("/updateUserInfo")
    public String updateUserInfo(@RequestParam String tableName, @RequestParam int userId, @RequestParam String field, @RequestParam String value) {
        boolean success = adminService.updateUserInfo(tableName, userId, field, value);
        if (success) {
            return "用户信息修改成功";
        }
        return "用户信息修改失败";
    }

    // 新增：冻结用户账号
    @PostMapping("/freezeUser")
    public String freezeUser(@RequestParam String tableName, @RequestParam int userId) {
        boolean success = adminService.freezeUser(tableName, userId);
        if (success) {
            return "用户账号冻结成功";
        }
        return "用户账号冻结失败";
    }

    // 新增：解冻用户账号
    @PostMapping("/unfreezeUser")
    public String unfreezeUser(@RequestParam String tableName, @RequestParam int userId) {
        boolean success = adminService.unfreezeUser(tableName, userId);
        if (success) {
            return "用户账号解冻成功";
        }
        return "用户账号解冻失败";
    }
}