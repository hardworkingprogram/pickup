package org.example.controller.partTimeUser;


import org.example.pojo.Package;
import org.example.service.partTimeUser.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/packages")
public class PackageController {
    @Autowired
    private PackageService packageService;

    // 插入包裹信息
    @PostMapping("/insertPackage")
    public String insertPackage(@RequestBody Package pkg) {
        // 设置默认状态和到达时间
        pkg.setStatus("到达快递点");
        pkg.setArrivalTime(new Date());

        int result = packageService.insertPackage(pkg);
        if (result > 0) {
            return "包裹信息插入成功";
        } else {
            return "包裹信息插入失败";
        }
    }

    // 根据包裹 ID 查询包裹信息
    @GetMapping("/{packageId}")
    public Package getPackageById(@PathVariable Integer packageId) {
        return packageService.getPackageById(packageId);
    }

    // 更新包裹状态
    @PutMapping("/{packageId}/status")
    public String updatePackageStatus(@PathVariable Integer packageId, @RequestParam String status) {
        int result = packageService.updatePackageStatus(packageId, status);
        if (result > 0) {
            return "包裹状态更新成功";
        } else {
            return "包裹状态更新失败";
        }
    }

    // 查询所有包裹信息
    @GetMapping
    public List<Package> getAllPackages() {
        return packageService.getAllPackages();
    }
}