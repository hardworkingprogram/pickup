package org.example.service.admin.impl;


import org.example.mapper.admin.AdminAnnouncementMapper;
import org.example.pojo.Announcement;
import org.example.service.admin.AdminAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminAdminAnnouncementServiceImpl implements AdminAnnouncementService {
    @Autowired
    private AdminAnnouncementMapper adminAnnouncementMapper;

    @Override
    public List<Announcement> getAllAnnouncements() {
        return adminAnnouncementMapper.getAllAnnouncements();
    }

    @Override
    public Announcement getAnnouncementById(int announcementId) {
        return adminAnnouncementMapper.getAnnouncementById(announcementId);
    }

    @Override
    public boolean addAnnouncement(Announcement announcement) {
        int rows = adminAnnouncementMapper.insertAnnouncement(announcement);
        return rows > 0;
    }

    @Override
    public boolean updateAnnouncement(Announcement announcement) {
        int rows = adminAnnouncementMapper.updateAnnouncement(announcement);
        return rows > 0;
    }

    @Override
    public boolean deleteAnnouncement(int announcementId) {
        int rows = adminAnnouncementMapper.deleteAnnouncement(announcementId);
        return rows > 0;
    }
}