package org.example.service.partTimeUser;


import org.example.pojo.Package;

import java.util.List;

public interface PackageService {
    // 插入包裹信息
    int insertPackage(Package pkg);

    // 根据包裹 ID 查询包裹信息
    Package getPackageById(Integer packageId);

    // 更新包裹状态
    int updatePackageStatus(Integer packageId, String status);

    // 查询所有包裹信息
    List<Package> getAllPackages();
}