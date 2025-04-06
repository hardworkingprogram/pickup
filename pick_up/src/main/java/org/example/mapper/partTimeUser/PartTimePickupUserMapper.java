package org.example.mapper.partTimeUser;


import org.apache.ibatis.annotations.Param;
import org.example.pojo.PartTimePickupUser;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.User;

@Mapper
public interface PartTimePickupUserMapper {
    PartTimePickupUser getPartTimeUserById(int pickup_user_id);
    PartTimePickupUser selectUserByPhoneAndPassword(@Param("phone_number") String phone_number, @Param("password") String password);

}