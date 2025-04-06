package org.example.mapper.ordinaryUser;

import org.example.pojo.PickupApplication;

import java.util.List;

public interface PickupApplicationMapper {
    int insertPickupApplication(PickupApplication pickupApplication);
    List<PickupApplication> getPickupApplicationsByUserId(int userId);
}