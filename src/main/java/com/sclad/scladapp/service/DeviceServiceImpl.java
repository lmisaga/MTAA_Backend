package com.sclad.scladapp.service;

import com.sclad.scladapp.entity.Device;
import com.sclad.scladapp.entity.DeviceType;
import com.sclad.scladapp.exceptions.DeviceNotFoundException;
import com.sclad.scladapp.model.DeviceModel;
import com.sclad.scladapp.repository.DeviceRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Device create(DeviceModel model) {
        Device device = new Device();
        device.setProductName(model.getProductName());
        device.setProductCode(model.getProductCode());
        device.setQuantity(model.getQuantity());
        device.setQuantityThreshold(model.getQuantityTreshold());
        device.setReordered(model.getReordered());
        device.setDeviceType(model.getDeviceType());
        deviceRepository.save(device);
        return device;
    }

    @Override
    public Device getById(Long id) {
        return deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException(id));
    }

    @Override
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    @Override
    public List<Device> listAllDevicesByType(DeviceType deviceType) {
        //TODO
        return null;
    }

    @Override
    public Device updateDevice(DeviceModel updatedModel, Long id) {
        Device device;
        device = deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException(id));

        device.setProductName(updatedModel.getProductName());
        device.setProductCode(updatedModel.getProductCode());
        device.setDeviceType(updatedModel.getDeviceType());
        device.setQuantity(updatedModel.getQuantity());
        device.setQuantityThreshold(updatedModel.getQuantityTreshold());
        device.setReordered(updatedModel.getReordered());
        deviceRepository.save(device);
        return device;
    }

    @Override
    public void deleteDevice(Long id) {
        try {
            deviceRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new DeviceNotFoundException(id);
        }

    }
}
