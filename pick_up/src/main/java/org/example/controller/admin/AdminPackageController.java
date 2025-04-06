package org.example.controller.admin;

import org.example.pojo.Package;
import org.example.service.admin.AdminPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/packages")
public class AdminPackageController {

    @Autowired
    private AdminPackageService adminPackageService;

    // 获取所有快递信息
    @GetMapping
    public List<Package> getAllPackages() {
        return adminPackageService.getAllPackages();
    }

    // 新增快递信息
    @PostMapping
    public String addPackage(@RequestBody Package pkg) {
        boolean success = adminPackageService.addPackage(pkg);
        if (success) {
            return "快递信息添加成功";
        }
        return "快递信息添加失败";
    }

    // 修改快递信息
    @PutMapping("/{packageId}")
    public String updatePackage(@PathVariable Integer packageId, @RequestBody Package pkg) {
        pkg.setPackage_id(packageId);
        boolean success = adminPackageService.updatePackage(pkg);
        if (success) {
            return "快递信息修改成功";
        }
        return "快递信息修改失败";
    }

    // 删除快递信息
    @DeleteMapping("/{packageId}")
    public String deletePackage(@PathVariable Integer packageId) {
        boolean success = adminPackageService.deletePackage(packageId);
        if (success) {
            return "快递信息删除成功";
        }
        return "快递信息删除失败";
    }
}