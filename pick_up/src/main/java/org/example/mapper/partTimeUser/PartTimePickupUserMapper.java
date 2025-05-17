package org.example.mapper.partTimeUser;

import org.apache.ibatis.annotations.Param;
import org.example.pojo.PartTimePickupUser;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.PickupApplication;
import org.example.pojo.User;
import org.example.pojo.ExpressPoint;

import java.util.List;

@Mapper
public interface PartTimePickupUserMapper {
    PartTimePickupUser getPartTimeUserById(int pickup_user_id);

    PartTimePickupUser selectUserByPhoneAndPassword(@Param("phone_number") String phone_number,
            @Param("password") String password);

    List<PickupApplication> selectUnassignedTasks();

    // 查询订单详情
    PickupApplication getApplicationById(int applicationId);

    // 更新订单状态和兼职用户ID
    int updateApplicationStatus(int applicationId);

    int updatePackageTimeAndId(@Param("packageId") int packageId, @Param("pickupUserId") int pickupUserId);

    int updateUserInfo(PartTimePickupUser partTimePickupUser);

    PartTimePickupUser selectByPhone(String phoneNumber);

    int insertUser(PartTimePickupUser user);

    // 获取所有快递点信息
    List<ExpressPoint> getAllExpressPoints();
}