package org.example.mapper.ordinaryUser;

import org.apache.ibatis.annotations.Param;
import org.example.pojo.PickupApplication;

import java.util.List;

public interface PickupApplicationMapper {
    int insertPickupApplication(PickupApplication pickupApplication);
    List<PickupApplication> getPickupApplicationsByUserId(int userId);
    // 根据用户ID分页查询代取记录
    List<PickupApplication> getPickupRecordsByPage(
            @Param("userId") int userId,
            @Param("offset") int offset,
            @Param("pageSize") int pageSize
    );

    // 根据用户ID获取代取记录总数
    int getTotalCountByUserId(@Param("userId") int userId);
}