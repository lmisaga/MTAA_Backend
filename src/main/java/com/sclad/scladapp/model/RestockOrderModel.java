package com.sclad.scladapp.model;

import com.sclad.scladapp.entity.DeviceType;

public class RestockOrderModel extends AbstractModel {
    //TODO this will be used in RestockOrderController as a front-end model
    //TODO  (form value from frontend will map onto this model in the controller, basically)

    private Integer quantityToReorder;

    private Boolean sendNotification;

    //private Device device;

    private String productName;

    private DeviceType deviceType;

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }
}
