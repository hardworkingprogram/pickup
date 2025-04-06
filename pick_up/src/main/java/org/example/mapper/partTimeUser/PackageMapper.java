package org.example.mapper.partTimeUser;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.pojo.Package;

import java.util.List;

@Mapper
public interface PackageMapper {
    int insertPackage(Package pkg);
    int updatePackage(Package pkg);
    int deletePackage(Integer packageId);
    List<Package> getAllPackages();
    Package getPackageById(Integer packageId);
    int updatePackageStatus(@Param("packageId") Integer packageId, @Param("status") String status);
}