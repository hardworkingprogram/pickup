package org.example.pojo;

import java.util.Date;

public class Announcement {
    private int announcement_id;
    private String title;
    private String content;
    private Date publish_time;

    public int getAnnouncement_id() {
        return announcement_id;
    }

    public void setAnnouncement_id(int announcement_id) {
        this.announcement_id = announcement_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(Date publish_time) {
        this.publish_time = publish_time;
    }

    public Announcement() {
    }

    public Announcement(int announcement_id, String title, String content, Date publish_time) {
        this.announcement_id = announcement_id;
        this.title = title;
        this.content = content;
        this.publish_time = publish_time;
    }
}
