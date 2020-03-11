package com.sclad.scladapp.model;

import com.sclad.scladapp.entity.DeviceType;

public class DeviceModel extends AbstractModel {
    private String productName;
    private String productCode;
    private Integer quantity;
    private Integer quantityTreshold;
    private Boolean isReordered;
    private DeviceType deviceType;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantityTreshold() {
        return quantityTreshold;
    }

    public void setQuantityTreshold(Integer quantityTreshold) {
        this.quantityTreshold = quantityTreshold;
    }

    public Boolean getReordered() {
        return isReordered;
    }

    public void setReordered(Boolean reordered) {
        this.isReordered = reordered;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }
}
