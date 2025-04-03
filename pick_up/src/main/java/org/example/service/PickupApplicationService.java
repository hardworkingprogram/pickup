package org.example.service;

import org.example.pojo.PickupApplication;

import java.util.List;

public interface PickupApplicationService {
    int submitPickupApplication(PickupApplication pickupApplication);
    List<PickupApplication> getPickupApplicationsByUserId(int userId);
}