package com.sclad.scladapp.service;

import com.sclad.scladapp.entity.RestockOrder;
import com.sclad.scladapp.model.RestockOrderModel;

import java.util.Optional;

public interface RestockOrderService extends AbstractService<RestockOrder> {
    RestockOrder create(RestockOrderModel model);
    RestockOrder getById(Long id);
}
