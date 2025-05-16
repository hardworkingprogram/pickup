package org.example.service.ordinaryUser.impl;

import org.example.mapper.ordinaryUser.AnnouncementMapper;
import org.example.pojo.Announcement;
import org.example.service.ordinaryUser.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public List<Announcement> getAllAnnouncements() {
        return announcementMapper.getAllAnnouncements();
    }

    @Override
    public Map<String, Object> getAnnouncementsByPage(int pageNum, int pageSize) {
        // 计算偏移量
        int offset = (pageNum - 1) * pageSize;

        // 获取总记录数
        int total = announcementMapper.getTotalCount();

        // 获取分页数据
        List<Announcement> announcements = announcementMapper.getAnnouncementsByPage(offset, pageSize);

        // 封装返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("list", announcements);
        result.put("pageNum", pageNum);
        result.put("pageSize", pageSize);

        return result;
    }
}