package com.sclad.scladapp.controller;

import com.sclad.scladapp.entity.DefectReport;
import com.sclad.scladapp.entity.RestockOrder;
import com.sclad.scladapp.model.DefectReportModel;
import com.sclad.scladapp.model.RestockOrderModel;
import com.sclad.scladapp.repository.DefectReportRepository;
import com.sclad.scladapp.service.DefectReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/defectReport")
public class DefectReportController {

    private final DefectReportRepository defectReportRepository;
    private final DefectReportService defectReportService;

    @Autowired
    public DefectReportController(DefectReportRepository defectReportRepository, DefectReportService defectReportService) {
        this.defectReportRepository = defectReportRepository;
        this.defectReportService = defectReportService;
    }

    @RequestMapping(value = "/create/", method = RequestMethod.POST)
    public DefectReport create(@RequestBody @Valid RestockOrderModel model) {
        //TODO CHANGE
        return defectReportService.create(model);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public DefectReport getById(@PathVariable Long id) {
        return defectReportRepository.getOne(id);
    }

}
