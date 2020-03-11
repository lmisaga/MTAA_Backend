package com.sclad.scladapp.service;

import com.sclad.scladapp.entity.DefectReport;
import com.sclad.scladapp.model.RestockOrderModel;
import com.sclad.scladapp.repository.DefectReportRepository;
import com.sclad.scladapp.repository.DeviceRepository;
import com.sclad.scladapp.repository.UploadedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DefectReportServiceImpl implements DefectReportService {

    private final DefectReportRepository defectReportRepository;
    private final DeviceRepository deviceRepository;
    private final UploadedFileRepository uploadedFileRepository;

    @Autowired
    public DefectReportServiceImpl(DefectReportRepository defectReportRepository, DeviceRepository deviceRepository, UploadedFileRepository uploadedFileRepository) {
        this.defectReportRepository = defectReportRepository;
        this.deviceRepository = deviceRepository;
        this.uploadedFileRepository = uploadedFileRepository;
    }

    @Override
    public DefectReport create(RestockOrderModel model) {
        //TODO CHANGE
        DefectReport defectReport = new DefectReport();
        defectReport.setDeviceSerialNumber("serioveCiselkoXDXD");
        defectReport.setDevice(deviceRepository.findAll().get(0));
        defectReport.setDateOfDiscovery(LocalDate.now());
        defectReport.setAttachment(null);
        defectReportRepository.save(defectReport);
        return defectReport;
    }

    @Override
    public DefectReport getById(Long id) {
        return null;
    }
}
