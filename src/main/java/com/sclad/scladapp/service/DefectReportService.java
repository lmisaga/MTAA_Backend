package com.sclad.scladapp.service;

import com.sclad.scladapp.entity.DefectReport;
import com.sclad.scladapp.model.RestockOrderModel;

public interface DefectReportService extends AbstractService<DefectReport> {

    DefectReport create(RestockOrderModel model);

}
