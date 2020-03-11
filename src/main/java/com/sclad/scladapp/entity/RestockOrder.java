package com.sclad.scladapp.entity;


import javax.persistence.*;

@Entity
@Table
public class RestockOrder extends AbstractEntity {

    @Column(nullable = false)
    private Integer quantityToReorder;

    @Column
    private Boolean sendNotification;

    @Column
    private String productName;

    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;


    //TODO: lets talk about this
//    @OneToOne
//    private Device device;

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
