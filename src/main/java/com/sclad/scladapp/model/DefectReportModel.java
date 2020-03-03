package com.sclad.scladapp.model;

import com.sclad.scladapp.entity.Device;
import com.sclad.scladapp.entity.UploadedFile;

import java.time.LocalDate;

public class DefectReportModel extends AbstractModel {

    private String deviceSerialNumber;

    private Device device;

    private LocalDate dateOfDiscovery;

    private UploadedFile attachment;

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
