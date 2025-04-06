package org.example.service.admin;

import org.example.pojo.Package;

import java.util.List;

public interface AdminPackageService {
    List<Package> getAllPackages();
    boolean addPackage(Package pkg);
    boolean updatePackage(Package pkg);
    boolean deletePackage(Integer packageId);
}