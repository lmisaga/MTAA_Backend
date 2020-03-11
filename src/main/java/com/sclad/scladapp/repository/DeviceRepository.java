package com.sclad.scladapp.repository;

import com.sclad.scladapp.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

}
