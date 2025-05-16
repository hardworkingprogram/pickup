package org.example.service.ordinaryUser;

import org.example.pojo.PickupApplication;

import java.util.List;
import java.util.Map;

public interface PickupApplicationService {
    int submitPickupApplication(PickupApplication pickupApplication);
    List<PickupApplication> getPickupApplicationsByUserId(int userId);
    Map<String, Object> getPickupRecordsByPage(int userId,int pageNum, int pageSize);
}