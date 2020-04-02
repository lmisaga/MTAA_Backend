package com.sclad.scladapp.service;

import com.sclad.scladapp.entity.DefectReport;
import com.sclad.scladapp.model.DefectReportModel;

import java.util.List;

public interface DefectReportService extends AbstractService<DefectReport> {

    DefectReport create(DefectReportModel model);

    List<DefectReport> getAll();

    void resolveFaultReport(Long id);

}
