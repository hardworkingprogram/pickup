package org.example.service.partTimeUser;


import org.example.pojo.PartTimePickupUser;
import org.example.pojo.User;

public interface PartTimePickupUserService {
    PartTimePickupUser getPartTimeUserById(int pickup_user_id);
    PartTimePickupUser login(String phone_number, String password);
}