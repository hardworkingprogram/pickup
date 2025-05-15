package org.example.service.partTimeUser;


import org.example.pojo.PartTimePickupUser;
import org.example.pojo.PickupApplication;
import org.example.pojo.User;

import java.util.List;

public interface PartTimePickupUserService {
    PartTimePickupUser getPartTimeUserById(int pickup_user_id);
    PartTimePickupUser login(String phone_number, String password);
    List<PickupApplication> getAllApplications();
    boolean acceptOrder(int applicationId, int pickupUserId);
    boolean updateUserInfo(PartTimePickupUser partTimePickupUser);
    PartTimePickupUser getByPhone(String phoneNumber);
    boolean register(PartTimePickupUser user);
}