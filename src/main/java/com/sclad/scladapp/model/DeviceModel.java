package com.sclad.scladapp.model;

import com.sclad.scladapp.entity.DeviceType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DeviceModel extends AbstractModel {

    @NotNull
    private String productName;

    @NotNull
    @NotBlank(message = "Product code needs to be filled!")
    private String productCode;

    private Integer quantity = 10;

    private Integer quantityThreshold = 1;

    private Boolean isReordered;

    private DeviceType deviceType = DeviceType.NOTEBOOK;

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

    public Integer getQuantityThreshold() {
        return quantityThreshold;
    }

    public void setQuantityThreshold(Integer quantityThreshold) {
        this.quantityThreshold = quantityThreshold;
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
