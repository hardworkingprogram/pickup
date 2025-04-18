package org.example.service.partTimeUser.impl;


import org.example.mapper.partTimeUser.PartTimePickupUserMapper;
import org.example.pojo.PartTimePickupUser;
import org.example.pojo.PickupApplication;
import org.example.service.partTimeUser.PartTimePickupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartTimePickupUserServiceImpl implements PartTimePickupUserService {

    @Autowired
    private PartTimePickupUserMapper partTimePickupUserMapper;

    @Override
    public PartTimePickupUser getPartTimeUserById(int pickup_user_id) {
        return partTimePickupUserMapper.getPartTimeUserById(pickup_user_id);
    }

    @Override
    public PartTimePickupUser login(String phone_number, String password) {
        return partTimePickupUserMapper.selectUserByPhoneAndPassword(phone_number, password);
    }

    @Override
    public List<PickupApplication> getAllApplications() {
        return partTimePickupUserMapper.selectUnassignedTasks();
    }
}