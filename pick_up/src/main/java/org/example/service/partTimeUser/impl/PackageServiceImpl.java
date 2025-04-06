package org.example.service.partTimeUser.impl;


import org.example.pojo.Package;
import org.example.mapper.partTimeUser.PackageMapper;
import org.example.service.partTimeUser.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageServiceImpl implements PackageService {
    @Autowired
    private PackageMapper packageMapper;

    @Override
    public int insertPackage(Package pkg) {
        return packageMapper.insertPackage(pkg);
    }

    @Override
    public Package getPackageById(Integer packageId) {
        return packageMapper.getPackageById(packageId);
    }

    @Override
    public int updatePackageStatus(Integer packageId, String status) {
        return packageMapper.updatePackageStatus(packageId, status);
    }

    @Override
    public List<Package> getAllPackages() {
        return packageMapper.getAllPackages();
    }
}