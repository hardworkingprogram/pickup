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

    @Override
    public boolean acceptOrder(int applicationId, int pickupUserId) {
        // 1. 校验订单是否存在且状态为"待处理"
        PickupApplication application = partTimePickupUserMapper.getApplicationById(applicationId);
        if (application == null || !"待处理".equals(application.getStatus())) {
            return false;
        }

        // 2. 更新订单状态为"已处理"


        //todo:根据package_id查找packages表，然后更新其中的pickup_time和pickup_user_id
        int packageId = application.getPackage_id();
        int updatePackageRows = partTimePickupUserMapper.updatePackageTimeAndId(packageId, pickupUserId);
        int updateApplicationRows = partTimePickupUserMapper.updateApplicationStatus(applicationId);
        return updatePackageRows > 0 && updateApplicationRows > 0;
    }
}