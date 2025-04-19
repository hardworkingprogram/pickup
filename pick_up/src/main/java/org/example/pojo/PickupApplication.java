package org.example.pojo;

import java.util.Date;

public class PickupApplication {
    private int application_id;
    private int user_id;
    private int package_id;
    private Date pickup_time;
    private String pickup_location;
    private String status;
    private double pickup_lng;
    private double pickup_lat;

    public double getPickup_lng() {
        return pickup_lng;
    }

    public void setPickup_lng(double pickup_lng) {
        this.pickup_lng = pickup_lng;
    }

    public double getPickup_lat() {
        return pickup_lat;
    }

    public void setPickup_lat(double pickup_lat) {
        this.pickup_lat = pickup_lat;
    }

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

    public PickupApplication() {
    }

    public PickupApplication(int application_id, int user_id, int package_id, Date pickup_time, String pickup_location, String status, double pickup_lng, double pickup_lat) {
        this.application_id = application_id;
        this.user_id = user_id;
        this.package_id = package_id;
        this.pickup_time = pickup_time;
        this.pickup_location = pickup_location;
        this.status = status;
        this.pickup_lng = pickup_lng;
        this.pickup_lat = pickup_lat;
    }
}
