package com.UserApplication.demo.repository;

import com.UserApplication.demo.entity.DeviceDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceDetRepository extends JpaRepository<DeviceDetailsEntity,Long >{

    @Query(value = " SELECT COUNT(*) FROM device_details WHERE is_active=true AND token= ?1 ", nativeQuery = true)
    Integer countByToken(String token);

}
