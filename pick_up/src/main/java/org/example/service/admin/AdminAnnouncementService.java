package org.example.service.admin;



import org.example.pojo.Announcement;

import java.util.List;

public interface AdminAnnouncementService {
    List<Announcement> getAllAnnouncements();
    Announcement getAnnouncementById(int announcementId);
    boolean addAnnouncement(Announcement announcement);
    boolean updateAnnouncement(Announcement announcement);
    boolean deleteAnnouncement(int announcementId);
}