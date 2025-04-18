package org.example.service.ordinaryUser.impl;

import org.example.mapper.ordinaryUser.PickupApplicationMapper;
import org.example.pojo.PickupApplication;
import org.example.service.ordinaryUser.PickupApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PickupApplicationServiceImpl implements PickupApplicationService {
    @Autowired
    private PickupApplicationMapper pickupApplicationMapper;

    @Override
    public int submitPickupApplication(PickupApplication pickupApplication) {
        return pickupApplicationMapper.insertPickupApplication(pickupApplication);
    }

    @Override
    public List<PickupApplication> getPickupApplicationsByUserId(int userId) {
        return pickupApplicationMapper.getPickupApplicationsByUserId(userId);
    }
}