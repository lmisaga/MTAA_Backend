package com.sclad.scladapp.controller;

import com.sclad.scladapp.entity.Device;
import com.sclad.scladapp.entity.DeviceType;
import com.sclad.scladapp.repository.DeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/device")
public class DeviceController {

    private final DeviceRepository deviceRepository;

    private static final Logger logger =  LoggerFactory.getLogger(DeviceController.class);

    @Autowired
    public DeviceController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    // TODO change this!!!!!!!!!!!!!!!!!!!!1!!!!
    @RequestMapping(value = "/testMethod")
    public Device testMethod() {
        long deviceCount = deviceRepository.count();
        Device newDevice = new Device();
        newDevice.setProductCode("consoleProduct" + deviceCount);
        newDevice.setProductName("consoleName" + deviceCount);
        newDevice.setQuantity(11);
        newDevice.setQuantityThreshold(2);
        newDevice.setDeviceType(DeviceType.CONSOLE);
        logger.info("Saving device " + newDevice.getProductCode() + " ...");
        deviceRepository.save(newDevice);
        return newDevice;
    }


}
