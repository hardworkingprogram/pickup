package org.example.mapper.admin;

import org.example.pojo.PickupApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminPickupApplicationMapper {

    List<PickupApplication> selectUnassignedTasksOrderByCreateTime();

    PickupApplication getApplicationById(int applicationId);

    int updateApplicationStatusAndCourierId(@Param("applicationId") int applicationId, @Param("courierId") int courierId);
}