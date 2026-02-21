package com.supplychain.ColdChainAudit_API;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/telemetry") // Base path for all methods in this class
public class TelemetryController {

    @GetMapping("/ingest")
    public String ingestData() {
        // Logic to save to RDS will go here
        return "Data received successfully";
    }
}