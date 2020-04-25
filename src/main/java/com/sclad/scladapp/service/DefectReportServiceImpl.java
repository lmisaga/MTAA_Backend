package com.sclad.scladapp.service;

import com.sclad.scladapp.entity.DefectReport;
import com.sclad.scladapp.entity.Device;
import com.sclad.scladapp.exceptions.DefectReportNotFoundException;
import com.sclad.scladapp.exceptions.DeviceNotFoundException;
import com.sclad.scladapp.model.DefectReportModel;
import com.sclad.scladapp.repository.DefectReportRepository;
import com.sclad.scladapp.repository.DeviceRepository;
import com.sclad.scladapp.repository.UploadedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefectReportServiceImpl implements DefectReportService {

    private final DefectReportRepository defectReportRepository;
    private final DeviceService deviceService;
    private final DeviceRepository deviceRepository;
    private final UploadedFileRepository uploadedFileRepository;

    @Autowired
    public DefectReportServiceImpl(DefectReportRepository defectReportRepository, DeviceService deviceService, DeviceRepository deviceRepository, UploadedFileRepository uploadedFileRepository) {
        this.defectReportRepository = defectReportRepository;
        this.deviceService = deviceService;
        this.deviceRepository = deviceRepository;
        this.uploadedFileRepository = uploadedFileRepository;
    }

    @Override
    public DefectReport create(DefectReportModel model) {
        DefectReport defectReport = new DefectReport();
        if (model.getDevice() != null && deviceService.getById(model.getDevice().getId()) != null) {
            defectReport.setDevice(model.getDevice());
        } else if (model.getProductName() != null) {
            Device device = deviceRepository.findByProductNameLike(model.getProductName()).orElse(null);
            if (device != null) {
                defectReport.setDevice(device);
            } else {
                throw new DeviceNotFoundException(-1L);
            }
        }
        defectReport.setDateOfDiscovery(model.getDateOfDiscovery());
        defectReport.setDeviceSerialNumber(model.getDeviceSerialNumber());
        defectReport.setFaultDescription(model.getFaultDescription());
        if (model.getAttachmentId() != null) {
            defectReport.setAttachment(uploadedFileRepository.getOne(model.getAttachmentId()));
        }
        defectReportRepository.save(defectReport);
        return defectReport;
    }

    @Override
    public List<DefectReport> getAll() {
        return defectReportRepository.findAll();
    }

    @Override
    //Delete fault report - resolve
    public void resolveFaultReport(Long id) {
        DefectReport defectReport = getById(id);
        if (defectReport != null) {
            //check if there is any file attached to fault report
            defectReportRepository.delete(defectReport);
            if (defectReport.getAttachment() != null && defectReport.getAttachment().getId() != null) {
                uploadedFileRepository.delete(defectReport.getAttachment());
            }
        }
    }

    @Override
    public DefectReport getById(Long id) {
        DefectReport defectReport = this.defectReportRepository.findById(id).orElse(null);
        if (defectReport != null) {
            return defectReport;
        } else {
            throw new DefectReportNotFoundException();
        }
    }
}
