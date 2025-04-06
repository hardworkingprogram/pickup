package org.example.controller.admin;


import org.example.pojo.Announcement;
import org.example.service.admin.AdminAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adminAnnouncement")
public class AdminAnnouncementController {
    @Autowired
    private AdminAnnouncementService adminAnnouncementService;

    @GetMapping
    public List<Announcement> getAllAnnouncements() {
        return adminAnnouncementService.getAllAnnouncements();
    }

    @GetMapping("/{announcementId}")
    public Announcement getAnnouncementById(@PathVariable int announcementId) {
        return adminAnnouncementService.getAnnouncementById(announcementId);
    }

    @PostMapping
    public String addAnnouncement(@RequestBody Announcement announcement) {
        boolean success = adminAnnouncementService.addAnnouncement(announcement);
        if (success) {
            return "公告添加成功";
        }
        return "公告添加失败";
    }

    @PutMapping
    public String updateAnnouncement(@RequestBody Announcement announcement) {
        boolean success = adminAnnouncementService.updateAnnouncement(announcement);
        if (success) {
            return "公告修改成功";
        }
        return "公告修改失败";
    }

    @DeleteMapping("/{announcementId}")
    public String deleteAnnouncement(@PathVariable int announcementId) {
        boolean success = adminAnnouncementService.deleteAnnouncement(announcementId);
        if (success) {
            return "公告删除成功";
        }
        return "公告删除失败";
    }
}