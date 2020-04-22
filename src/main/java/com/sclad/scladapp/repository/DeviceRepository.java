package com.sclad.scladapp.repository;

import com.sclad.scladapp.entity.Device;
import com.sclad.scladapp.entity.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    List<Device> findByDeviceType(DeviceType deviceType);

    Optional<Device> findByProductNameLike(String productName);
}

