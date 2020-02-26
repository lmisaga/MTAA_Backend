package com.sclad.scladapp.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Device {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    @NotNull
    private String productName;

    @Column
    @NotNull
    private String productCode;

    @Column
    @NotNull
    private Integer quantity;

    @Column
    private Integer quantityThreshold;

    @Column
    private Boolean isReordered;

    @Enumerated(EnumType.STRING)
    private DeviceType deviceType = DeviceType.NOTEBOOK;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        isReordered = reordered;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }
}
