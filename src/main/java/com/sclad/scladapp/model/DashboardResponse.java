package com.sclad.scladapp.model;

public class DashboardResponse {

    private Long deviceCount;

    private Long restockOrderCount;

    private Long defectReportCount;

    public Long getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(Long deviceCount) {
        this.deviceCount = deviceCount;
    }

    public Long getRestockOrderCount() {
        return restockOrderCount;
    }

    public void setRestockOrderCount(Long restockOrderCount) {
        this.restockOrderCount = restockOrderCount;
    }

    public Long getDefectReportCount() {
        return defectReportCount;
    }

    public void setDefectReportCount(Long defectReportCount) {
        this.defectReportCount = defectReportCount;
    }
}
