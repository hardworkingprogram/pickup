package org.example.pojo;

import java.util.Date;

public class Notification {
    private int notification_id;
    private int user_id;
    private int package_id;
    private String notification_type;
    private String content;
    private Date send_time;
    private boolean is_read;

    // 构造函数、getter和setter方法
    public Notification() {}

    public Notification(int user_id, int package_id, String notification_type, String content, Date send_time) {
        this.user_id = user_id;
        this.package_id = package_id;
        this.notification_type = notification_type;
        this.content = content;
        this.send_time = send_time;
    }

    // Getters and Setters
    public int getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(int notification_id) {
        this.notification_id = notification_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPackage_id() {
        return package_id;
    }

    public void setPackage_id(int package_id) {
        this.package_id = package_id;
    }

    public String getNotification_type() {
        return notification_type;
    }

    public void setNotification_type(String notification_type) {
        this.notification_type = notification_type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSend_time() {
        return send_time;
    }

    public void setSend_time(Date send_time) {
        this.send_time = send_time;
    }

    public boolean isIs_read() {
        return is_read;
    }

    public void setIs_read(boolean is_read) {
        this.is_read = is_read;
    }
}