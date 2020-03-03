package com.sclad.scladapp.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class DefectReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String deviceSerialNumber;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    @Column
    private LocalDate dateOfDiscovery;

    @OneToOne
    private UploadedFile attachment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceSerialNumber() {
        return deviceSerialNumber;
    }

    public void setDeviceSerialNumber(String deviceSerialNumber) {
        this.deviceSerialNumber = deviceSerialNumber;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public LocalDate getDateOfDiscovery() {
        return dateOfDiscovery;
    }

    public void setDateOfDiscovery(LocalDate dateOfDiscovery) {
        this.dateOfDiscovery = dateOfDiscovery;
    }

    public UploadedFile getAttachment() {
        return attachment;
    }

    public void setAttachment(UploadedFile attachment) {
        this.attachment = attachment;
    }
}
