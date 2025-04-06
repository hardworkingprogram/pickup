package org.example.service.ordinaryUser.impl;

import org.example.mapper.ordinaryUser.AnnouncementMapper;
import org.example.pojo.Announcement;
import org.example.service.ordinaryUser.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public List<Announcement> getAllAnnouncements() {
        return announcementMapper.getAllAnnouncements();
    }
}