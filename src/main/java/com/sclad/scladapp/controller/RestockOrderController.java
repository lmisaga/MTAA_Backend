package com.sclad.scladapp.controller;


import com.sclad.scladapp.entity.RestockOrder;
import com.sclad.scladapp.model.RestockOrderModel;
import com.sclad.scladapp.service.RestockOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/restockOrder")
public class RestockOrderController {

    private final RestockOrderService restockOrderService;

    @Autowired
    public RestockOrderController(RestockOrderService restockOrderService) {
        this.restockOrderService = restockOrderService;
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public RestockOrder create(@RequestBody @Valid RestockOrderModel model) {
        return restockOrderService.create(model);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public RestockOrder getById(@PathVariable Long id) {
        return restockOrderService.getById(id);
    }
}
