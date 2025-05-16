package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Package {
    private Integer package_id;
    private String express_company;
    private String tracking_number;
    private Integer recipient_id;
    private String status;

    public Integer getPoint_id() {
        return point_id;
    }

    public void setPoint_id(Integer point_id) {
        this.point_id = point_id;
    }

    private Integer point_id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date arrival_time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pickup_time;
    private Integer pickup_user_id;

    public Package() {
    }

    public Package(Integer package_id, String express_company, String tracking_number, Integer recipient_id, String status, Date arrival_time, Date pickup_time, Integer pickup_user_id, Integer point_id) {
        this.package_id = package_id;
        this.express_company = express_company;
        this.tracking_number = tracking_number;
        this.recipient_id = recipient_id;
        this.status = status;
        this.arrival_time = arrival_time;
        this.pickup_time = pickup_time;
        this.pickup_user_id = pickup_user_id;
        this.point_id = point_id;
    }

    public Integer getPackage_id() {
        return package_id;
    }

    public void setPackage_id(Integer package_id) {
        this.package_id = package_id;
    }

    public String getExpress_company() {
        return express_company;
    }

    public void setExpress_company(String express_company) {
        this.express_company = express_company;
    }

    public String getTracking_number() {
        return tracking_number;
    }

    public void setTracking_number(String tracking_number) {
        this.tracking_number = tracking_number;
    }

    public Integer getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(Integer recipient_id) {
        this.recipient_id = recipient_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(Date arrival_time) {
        this.arrival_time = arrival_time;
    }

    public Date getPickup_time() {
        return pickup_time;
    }

    public void setPickup_time(Date pickup_time) {
        this.pickup_time = pickup_time;
    }

    public Integer getPickup_user_id() {
        return pickup_user_id;
    }

    public void setPickup_user_id(Integer pickup_user_id) {
        this.pickup_user_id = pickup_user_id;
    }
}
