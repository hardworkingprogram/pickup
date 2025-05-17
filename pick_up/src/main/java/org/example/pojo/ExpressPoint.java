package org.example.pojo;

public class ExpressPoint {
    private int pointId;
    private String pointName;
    private double lng;
    private double lat;

    // Default constructor
    public ExpressPoint() {
    }

    // Constructor with all fields
    public ExpressPoint(int pointId, String pointName, double lng, double lat) {
        this.pointId = pointId;
        this.pointName = pointName;
        this.lng = lng;
        this.lat = lat;
    }

    // Getters
    public int getPointId() {
        return pointId;
    }

    public String getPointName() {
        return pointName;
    }

    public double getLng() {
        return lng;
    }

    public double getLat() {
        return lat;
    }

    // Setters
    public void setPointId(int pointId) {
        this.pointId = pointId;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "ExpressPoint{" +
               "pointId=" + pointId +
               ", pointName='" + pointName + '\'' +
               ", lng=" + lng +
               ", lat=" + lat +
               '}';
    }
} 