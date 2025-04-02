package org.example.controller;

import org.example.pojo.Announcement;
import org.example.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("/announcements")
    @ResponseBody
    public List<Announcement> getAllAnnouncements() {
        return announcementService.getAllAnnouncements();
    }
}