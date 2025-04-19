package org.example.mapper.partTimeUser;


import org.apache.ibatis.annotations.Param;
import org.example.pojo.PartTimePickupUser;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.PickupApplication;
import org.example.pojo.User;

import java.util.List;

@Mapper
public interface PartTimePickupUserMapper {
    PartTimePickupUser getPartTimeUserById(int pickup_user_id);
    PartTimePickupUser selectUserByPhoneAndPassword(@Param("phone_number") String phone_number, @Param("password") String password);
    List<PickupApplication> selectUnassignedTasks();

    // 查询订单详情
    PickupApplication getApplicationById(int applicationId);

    // 更新订单状态和兼职用户ID
    int updateApplicationStatus(int applicationId);

    int updatePackageTimeAndId(@Param("packageId") int packageId, @Param("pickupUserId") int pickupUserId);
}