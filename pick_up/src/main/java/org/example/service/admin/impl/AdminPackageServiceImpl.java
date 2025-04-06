package org.example.service.admin.impl;

import org.example.mapper.partTimeUser.PackageMapper;
import org.example.pojo.Package;
import org.example.service.admin.AdminPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminPackageServiceImpl implements AdminPackageService {

    @Autowired
    private PackageMapper packageMapper;

    @Override
    public List<Package> getAllPackages() {
        return packageMapper.getAllPackages();
    }

    @Override
    public boolean addPackage(Package pkg) {
        int result = packageMapper.insertPackage(pkg);
        return result > 0;
    }

    @Override
    public boolean updatePackage(Package pkg) {
        int result = packageMapper.updatePackage(pkg);
        return result > 0;
    }

    @Override
    public boolean deletePackage(Integer packageId) {
        int result = packageMapper.deletePackage(packageId);
        return result > 0;
    }
}