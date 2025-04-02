package org.example.controller;


import org.example.pojo.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
}
