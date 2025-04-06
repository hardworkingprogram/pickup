package org.example.pojo;

import java.util.Date;

public class Package {
    private Integer packageId;
    private String expressCompany;
    private String trackingNumber;
    private Integer recipientId;
    private String status;
    private Date arrivalTime;
    private Date pickupTime;
    private Integer pickupUserId;

    public Package() {
    }

    public Package(Integer packageId, String expressCompany, String trackingNumber, Integer recipientId, String status, Date arrivalTime, Date pickupTime, Integer pickupUserId) {
        this.packageId = packageId;
        this.expressCompany = expressCompany;
        this.trackingNumber = trackingNumber;
        this.recipientId = recipientId;
        this.status = status;
        this.arrivalTime = arrivalTime;
        this.pickupTime = pickupTime;
        this.pickupUserId = pickupUserId;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Integer getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Integer recipientId) {
        this.recipientId = recipientId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Date pickupTime) {
        this.pickupTime = pickupTime;
    }

    public Integer getPickupUserId() {
        return pickupUserId;
    }

    public void setPickupUserId(Integer pickupUserId) {
        this.pickupUserId = pickupUserId;
    }
}
