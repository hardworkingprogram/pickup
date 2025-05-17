package org.example.service.partTimeUser;

import org.example.pojo.PartTimePickupUser;
import org.example.pojo.PickupApplication;
import org.example.pojo.User;
import org.example.pojo.ExpressPoint;

import java.util.List;

public interface PartTimePickupUserService {
    PartTimePickupUser getPartTimeUserById(int pickup_user_id);

    PartTimePickupUser login(String phone_number, String password);

    List<PickupApplication> getAllApplications();

    boolean acceptOrder(int applicationId, int pickupUserId);

    boolean updateUserInfo(PartTimePickupUser partTimePickupUser);

    PartTimePickupUser getByPhone(String phoneNumber);

    boolean register(PartTimePickupUser user);

    // 新增方法，获取带位置信息并已排序的申请列表
    List<PickupApplication> getAllApplicationsWithLocationAndSort(double userLng, double userLat);

    // 新增方法，获取所有快递点信息
    List<ExpressPoint> getAllExpressPoints();
}