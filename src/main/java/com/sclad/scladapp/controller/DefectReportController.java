package com.sclad.scladapp.controller;

import com.sclad.scladapp.entity.DefectReport;
import com.sclad.scladapp.model.DefectReportModel;
import com.sclad.scladapp.service.DefectReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/defectReport")
public class DefectReportController {

    private final DefectReportService defectReportService;

    @Autowired
    public DefectReportController(DefectReportService defectReportService) {
        this.defectReportService = defectReportService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Long create(@RequestBody @Valid DefectReportModel model) {
        return defectReportService.create(model).getId();
    }

    @RequestMapping(value = "/getDefectReportById/{id}", method = RequestMethod.GET)
    public DefectReport getById(@PathVariable("id") Long id) {
        return defectReportService.getById(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<DefectReport> list() {
        return defectReportService.getAll();
    }

    @RequestMapping(value = "/resolve/{id}", method = RequestMethod.DELETE)
    public void resolve(@PathVariable Long id) {
        defectReportService.resolveFaultReport(id);
    }
}
