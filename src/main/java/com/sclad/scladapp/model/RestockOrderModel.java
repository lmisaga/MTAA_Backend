package com.sclad.scladapp.model;

import com.sclad.scladapp.entity.Device;

public class RestockOrderModel extends AbstractModel {
    //TODO this will be used in RestockOrderController as a front-end model
    //TODO  (form value from frontend will map onto this model in the controller, basically)

    private Integer quantityToReorder;

    private Boolean sendNotification;

    private Device device;

    public Integer getQuantityToReorder() {
        return quantityToReorder;
    }

    public void setQuantityToReorder(Integer quantityToReorder) {
        this.quantityToReorder = quantityToReorder;
    }

    public Boolean getSendNotification() {
        return sendNotification;
    }

    public void setSendNotification(Boolean sendNotification) {
        this.sendNotification = sendNotification;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
