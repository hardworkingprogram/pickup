package org.example.pojo;

import java.util.Date;

public class PickupApplication {
    private int application_id;
    private int user_id;
    private int package_id;
    private Date pickup_time;
    private String pickup_location;
    private String status;
    private Double pickup_lng;
    private Double pickup_lat;
    // 添加新的字段
    private Double express_lng;
    private Double express_lat;
    private Double totalDistance;

    public int getApplication_id() {
        return application_id;
    }

    public void setApplication_id(int application_id) {
        this.application_id = application_id;
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

    public Date getPickup_time() {
        return pickup_time;
    }

    public void setPickup_time(Date pickup_time) {
        this.pickup_time = pickup_time;
    }

    public String getPickup_location() {
        return pickup_location;
    }

    public void setPickup_location(String pickup_location) {
        this.pickup_location = pickup_location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPickup_lng() {
        return pickup_lng;
    }

    public void setPickup_lng(Double pickup_lng) {
        this.pickup_lng = pickup_lng;
    }

    public Double getPickup_lat() {
        return pickup_lat;
    }

    public void setPickup_lat(Double pickup_lat) {
        this.pickup_lat = pickup_lat;
    }

    public Double getExpress_lng() {
        return express_lng;
    }

    public void setExpress_lng(Double express_lng) {
        this.express_lng = express_lng;
    }

    public Double getExpress_lat() {
        return express_lat;
    }

    public void setExpress_lat(Double express_lat) {
        this.express_lat = express_lat;
    }

    public Double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public PickupApplication() {
    }

    public PickupApplication(int application_id, int user_id, int package_id, Date pickup_time, String pickup_location, String status, Double pickup_lng, Double pickup_lat, Double express_lng, Double express_lat, Double totalDistance) {
        this.application_id = application_id;
        this.user_id = user_id;
        this.package_id = package_id;
        this.pickup_time = pickup_time;
        this.pickup_location = pickup_location;
        this.status = status;
        this.pickup_lng = pickup_lng;
        this.pickup_lat = pickup_lat;
        this.express_lng = express_lng;
        this.express_lat = express_lat;
        this.totalDistance = totalDistance;
    }
}
