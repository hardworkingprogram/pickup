package org.example.service.ordinaryUser;

import org.example.pojo.Announcement;
import java.util.List;
import java.util.Map;

public interface AnnouncementService {
    List<Announcement> getAllAnnouncements();

    Map<String, Object> getAnnouncementsByPage(int pageNum, int pageSize);
}