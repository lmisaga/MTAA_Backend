package com.sclad.scladapp.service;

import com.sclad.scladapp.entity.RestockOrder;
import com.sclad.scladapp.exceptions.DeviceNotFoundException;
import com.sclad.scladapp.model.RestockOrderModel;
import com.sclad.scladapp.repository.RestockOrderRepository;
import org.springframework.stereotype.Service;

@Service
public class RestockOrderServiceImpl implements RestockOrderService {

    private final RestockOrderRepository restockOrderRepository;

    public RestockOrderServiceImpl(RestockOrderRepository restockOrderRepository) {
        this.restockOrderRepository = restockOrderRepository;
    }

    @Override
    public RestockOrder create(RestockOrderModel model) {
        RestockOrder restockOrder = new RestockOrder();
        restockOrder.setProductName(model.getProductName());
        restockOrder.setDeviceType(model.getDeviceType());
        restockOrder.setQuantityToReorder(model.getQuantityToReorder());
        restockOrder.setSendNotification(model.getSendNotification());
        restockOrderRepository.save(restockOrder);
        return restockOrder;
    }

    @Override
    public RestockOrder getById(Long id) {
        return restockOrderRepository.findById(id)
                .orElseThrow(()-> new DeviceNotFoundException(id));
    }
}
