package org.example.mapper.partTimeUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.pojo.Package;

import java.util.List;

@Mapper
public interface PackageMapper {
    // 插入包裹信息
    int insertPackage(Package pkg);

    // 根据包裹 ID 查询包裹信息
    Package getPackageById(Integer packageId);

    // 更新包裹状态
    int updatePackageStatus(@Param("packageId") int packageId, @Param("status") String status);

    // 查询所有包裹信息
    List<Package> getAllPackages();
}