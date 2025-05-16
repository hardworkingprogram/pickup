package org.example.controller.ordinaryUser;

import org.example.pojo.Announcement;
import org.example.service.ordinaryUser.AnnouncementService;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ordinaryUserAnnouncement")
@CrossOrigin
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("/announcements")
    public Result<List<Announcement>> getAllAnnouncements() {
        return Result.success(announcementService.getAllAnnouncements());
    }

    @GetMapping("/page")
    public Result<Map<String, Object>> getAnnouncementsByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "5") int pageSize) {
        return Result.success(announcementService.getAnnouncementsByPage(pageNum, pageSize));
    }
}