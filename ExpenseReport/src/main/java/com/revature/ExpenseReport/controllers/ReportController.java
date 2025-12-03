package com.revature.ExpenseReport.controllers;

import com.revature.ExpenseReport.Services.ReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/reports")
public class ReportController {
    //fields
    private final ReportService service;

    //constructors
    public ReportController(ReportService service){
        this.service = service;
    }

    //methods
    @GetMapping
    public List<ReportDTO> getAllReports(){
        return service.getAllReports();
    }

    //create a report
    @PostMapping
    public ReportDTO create(@RequestBody ReportWOIDDTO dto){
        return service.create(dto);
    }

    //get report by id
    @GetMapping("/{id}")
    public ReportDTO getById(@PathVariable String id){
        return service.getById(id);
    }

    //update
    @PutMapping("/{id}")
    public ReportDTO update(@PathVariable String id, @RequestBody ReportDTO dto){
        return service.update(id, dto);
    }

    //delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        service.delete(id);
    }

}
