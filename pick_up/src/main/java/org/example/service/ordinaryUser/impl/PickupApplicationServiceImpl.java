package org.example.service.ordinaryUser.impl;

import org.example.mapper.ordinaryUser.PickupApplicationMapper;
import org.example.pojo.PickupApplication;
import org.example.service.ordinaryUser.PickupApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PickupApplicationServiceImpl implements PickupApplicationService {
    @Autowired
    private PickupApplicationMapper pickupApplicationMapper;

    @Override
    public int submitPickupApplication(PickupApplication pickupApplication) {
        return pickupApplicationMapper.insertPickupApplication(pickupApplication);
    }

    @Override
    public List<PickupApplication> getPickupApplicationsByUserId(int userId) {
        return pickupApplicationMapper.getPickupApplicationsByUserId(userId);
    }

    @Override
    public Map<String, Object> getPickupRecordsByPage(int userId, int pageNum, int pageSize) {
        // 计算偏移量
        int offset = (pageNum - 1) * pageSize;

        // 获取总记录数
        int total = pickupApplicationMapper.getTotalCountByUserId(userId);

        // 获取分页数据
        List<PickupApplication> pickupRecords = pickupApplicationMapper.getPickupRecordsByPage(userId, offset, pageSize);

        // 封装返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("list", pickupRecords);
        result.put("pageNum", pageNum);
        result.put("pageSize", pageSize);

        return result;
    }
}