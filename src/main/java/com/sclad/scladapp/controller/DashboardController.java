package com.sclad.scladapp.controller;


import com.sclad.scladapp.model.DashboardResponse;
import com.sclad.scladapp.repository.DefectReportRepository;
import com.sclad.scladapp.repository.DeviceRepository;
import com.sclad.scladapp.repository.RestockOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/dashboard")
public class DashboardController {

    private final DeviceRepository deviceRepository;
    private final DefectReportRepository defectReportRepository;
    private final RestockOrderRepository restockOrderRepository;

    @Autowired
    public DashboardController(DeviceRepository deviceRepository, DefectReportRepository defectReportRepository, RestockOrderRepository restockOrderRepository) {
        this.deviceRepository = deviceRepository;
        this.defectReportRepository = defectReportRepository;
        this.restockOrderRepository = restockOrderRepository;
    }

    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public DashboardResponse getDashboardStats() {
        DashboardResponse dashboardResponse = new DashboardResponse();
        dashboardResponse.setDeviceCount(deviceRepository.count());
        dashboardResponse.setDefectReportCount(defectReportRepository.count());
        dashboardResponse.setRestockOrderCount(restockOrderRepository.count());
        return dashboardResponse;
    }
}
