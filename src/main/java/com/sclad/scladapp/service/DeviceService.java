package com.sclad.scladapp.service;

import javax.validation.Valid;

import com.sclad.scladapp.entity.Device;
import com.sclad.scladapp.model.DeviceModel;

import java.util.List;

public interface DeviceService extends AbstractService<Device> {

    Device create(@Valid DeviceModel model);

    List<Device> getAllDevices();

    List<Device> listAllDevicesByType(String deviceType);

    Device updateDevice(DeviceModel updatedModel, Long id);

    void deleteDevice(Long id);

    Device getDeviceByProductName(String productName);

    Device getSingleDeviceByProductName(String productName);
}
