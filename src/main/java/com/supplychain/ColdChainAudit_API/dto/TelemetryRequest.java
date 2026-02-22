package com.supplychain.ColdChainAudit_API.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class TelemetryRequest {
    
    @NotBlank(message = "Sensor ID is required")
    private String sensorId;

    @NotNull(message = "Temperature is required")
    private Double temperature;
    
}

/*
DTO -> Short for Data Transfer Objects. These are simple classes used to capture data from the API request.
You don't want to expose your internal database Entity directly to the outside world.
*/