package org.example.mapper.ordinaryUser;

import org.apache.ibatis.annotations.Param;
import org.example.pojo.Announcement;

import java.util.List;

public interface AnnouncementMapper {
    List<Announcement> getAllAnnouncements();

    List<Announcement> getAnnouncementsByPage(@Param("offset")int offset, @Param("pageSize")int pageSize);

    int getTotalCount();
}