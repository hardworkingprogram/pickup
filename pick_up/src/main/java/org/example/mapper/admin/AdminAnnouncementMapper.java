package org.example.mapper.admin;


import org.example.pojo.Announcement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminAnnouncementMapper {
    List<Announcement> getAllAnnouncements();
    Announcement getAnnouncementById(int announcementId);
    int insertAnnouncement(Announcement announcement);
    int updateAnnouncement(Announcement announcement);
    int deleteAnnouncement(int announcementId);
}