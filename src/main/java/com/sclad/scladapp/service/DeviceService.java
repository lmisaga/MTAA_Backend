package com.sclad.scladapp.service;

import com.sclad.scladapp.entity.Device;
import com.sclad.scladapp.model.DeviceModel;

import java.util.List;

public interface DeviceService extends AbstractService<Device> {

    Device create(DeviceModel model);

    List<Device> getAllDevices();

    List<Device> listAllDevicesByType(String deviceType);

    Device updateDevice(DeviceModel updatedModel, Long id);

    void deleteDevice(Long id);
}
