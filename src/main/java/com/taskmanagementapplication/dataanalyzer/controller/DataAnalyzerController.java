package com.taskmanagementapplication.dataanalyzer.controller;

import com.taskmanagementapplication.dataanalyzer.service.DataAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DataAnalyzerController {

    private final DataAnalyzerService dataAnalyzerService;

    @Autowired
    public DataAnalyzerController(DataAnalyzerService dataAnalyzerService) {
        this.dataAnalyzerService = dataAnalyzerService;
    }

    @GetMapping("/analyze/{userName}")
    public ResponseEntity<Double> analyzeTaskData(@PathVariable String userName) {
        double completionPercentage = dataAnalyzerService.analyzeTaskData(userName);
        return new ResponseEntity<>(completionPercentage, HttpStatus.OK);
    }

}
