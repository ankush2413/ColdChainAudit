package com.supplychain.ColdChainAudit_API.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "temperature_telemetry")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemperatureTelemetry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sensor_id", nullable = false)
    private String sensorId;

    @Column(nullable = false)
    private Double temperature;

    @Column(name = "recorded_at", nullable = false)
    private LocalDateTime recordedAt;

    @Column(name = "is_archived")
    private Boolean isArchived = false;
}

/*

Entity - > This class should be mapped to a database table. Without this, Hibernate ignores it.”
TableName -> Specifies the exact table name in DB.
ID ->  Marks this field as the primary key.
@GeneratedValue(strategy = GenerationType.IDENTITY) -> Database auto-generates the ID, Usually auto-increment

@NoArgsConstructor -> Creates empty constructor, required by JPA
@AllArgsConstructor -> Creates Constructor with all fields
@Builder ->  Allow this :
TemperatureTelemetry telemetry = TemperatureTelemetry.builder()
        .sensorId("SENSOR_01")
        .temperature(4.3)
        .recordedAt(LocalDateTime.now())
        .build();
    
*/