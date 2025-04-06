package org.example.controller.ordinaryUser;

import org.example.pojo.Announcement;
import org.example.service.ordinaryUser.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/ordinaryUserAnnouncement")
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("/announcements")
    @ResponseBody
    public List<Announcement> getAllAnnouncements() {
        return announcementService.getAllAnnouncements();
    }
}