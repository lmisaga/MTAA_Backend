package com.sclad.scladapp.entity;


import javax.persistence.*;

@Entity
@Table
public class RestockOrder extends AbstractEntity {

    @Column(nullable = false)
    private Integer quantityToReorder;

    @Column
    private Boolean sendNotification;

    @OneToOne
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
