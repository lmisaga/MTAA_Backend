package com.sclad.scladapp.controller;

import com.sclad.scladapp.entity.Device;
import com.sclad.scladapp.entity.DeviceType;
import com.sclad.scladapp.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceController {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    // TODO change this!!!!!!!!!!!!!!!!!!!!1!!!!
    @RequestMapping(value = "/testMethod")
    public Device testMethod() {
        Device newDevice = new Device();
        newDevice.setProductCode("consoleProduct");
        newDevice.setProductName("consoleName");
        newDevice.setQuantity(32);
        newDevice.setQuantityThreshold(1);
        newDevice.setDeviceType(DeviceType.CONSOLE);
        deviceRepository.save(newDevice);
        return newDevice;
    }

}
