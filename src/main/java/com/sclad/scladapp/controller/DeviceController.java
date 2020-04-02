package com.sclad.scladapp.controller;

import com.sclad.scladapp.entity.Device;
import com.sclad.scladapp.entity.DeviceType;
import com.sclad.scladapp.model.DeviceModel;
import com.sclad.scladapp.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/device")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public Device create(@RequestBody DeviceModel model) {
        return deviceService.create(model);
    }

    @RequestMapping(value="/getDeviceDetail/{id}", method = RequestMethod.GET)
    public Device getDeviceDetail(@PathVariable Long id) {
        return deviceService.getById(id);
    }

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @RequestMapping(value = "/listAllDevicesByType/{deviceType}", method = RequestMethod.GET)
    public List<Device> listAllDevicesByCategory(@PathVariable DeviceType deviceType) {
        return deviceService.listAllDevicesByType(deviceType);
    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.PUT)
    public Device updateDevice(@RequestBody DeviceModel updatedModel, @PathVariable Long id) {
        return deviceService.updateDevice(updatedModel,id);
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public void deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
    }
}