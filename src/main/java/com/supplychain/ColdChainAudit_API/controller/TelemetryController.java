package com.supplychain.ColdChainAudit_API.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supplychain.ColdChainAudit_API.dto.TelemetryRequest;
import com.supplychain.ColdChainAudit_API.service.TelemetryService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/telemetry") // Base path for all methods in this class
@RequiredArgsConstructor
public class TelemetryController {

    private final TelemetryService telemetryService;

    @GetMapping("/ingest")
    public String AppUP() {
        // Logic to save to RDS will go here
        return "Hello Ingest Some Data";
    }

    @PostMapping("/ingest")
    public ResponseEntity<String> ingestData(@RequestBody TelemetryRequest request) {
        telemetryService.saveTelemetry(request);
        
        return ResponseEntity.ok("Data Ingested Successfully");
    }
    
}