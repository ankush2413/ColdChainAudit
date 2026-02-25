package com.supplychain.ColdChainAudit_API.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.supplychain.ColdChainAudit_API.dto.TelemetryRequest;
import com.supplychain.ColdChainAudit_API.models.TemperatureTelemetry;
import com.supplychain.ColdChainAudit_API.repository.TelemetryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TelemetryService {

    private final TelemetryRepository repository;

    public void saveTelemetry(TelemetryRequest request) {
        TemperatureTelemetry telemetry = TemperatureTelemetry.builder()
                .sensorId(request.getSensorId())
                .temperature(request.getTemperature())
                .recordedAt(LocalDateTime.now())
                .isArchived(false)
                .build();
        
        repository.save(telemetry);
    }

    public List<TemperatureTelemetry> getAllTelemetry() {
        return repository.findAll();
    }
    
}
