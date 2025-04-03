package org.example.controller;

import org.example.pojo.PickupApplication;
import org.example.service.PickupApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/expressPickup")
public class PickupApplicationController {
    @Autowired
    private PickupApplicationService pickupApplicationService;

    @PostMapping("/submitApplication")
    public String submitApplication(@RequestBody PickupApplication pickupApplication) {
        //System.out.println("接收到的代取申请的状态：" + pickupApplication.getStatus());
        pickupApplication.setStatus("待处理");
        int result = pickupApplicationService.submitPickupApplication(pickupApplication);
        if (result > 0) {
            return "代取申请提交成功";
        } else {
            return "代取申请提交失败";
        }
    }

    @GetMapping("/getPickupRecords")
    public List<PickupApplication> getPickupRecords(@RequestParam int userId) {
        return pickupApplicationService.getPickupApplicationsByUserId(userId);
    }
}