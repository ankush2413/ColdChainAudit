package com.supplychain.ColdChainAudit_API.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supplychain.ColdChainAudit_API.dto.TelemetryRequest;
import com.supplychain.ColdChainAudit_API.models.TemperatureTelemetry;
import com.supplychain.ColdChainAudit_API.service.S3Service;
import com.supplychain.ColdChainAudit_API.service.TelemetryService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/telemetry") // Base path for all methods in this class
@RequiredArgsConstructor
public class TelemetryController {

    private final TelemetryService telemetryService;
    private final S3Service s3Service;

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

    @GetMapping("/all")
    public ResponseEntity<List<TemperatureTelemetry>> getAllData() {
        List<TemperatureTelemetry> data = telemetryService.getAllTelemetry();
        return ResponseEntity.ok(data);
    }
    
    @GetMapping("/archive/url/{fileName}")
    public ResponseEntity<String> getArchiveUrl(@PathVariable String fileName) {
    String url = s3Service.getPresignedURL(fileName);
    return ResponseEntity.ok(url);
    }
}