package com.supplychain.ColdChainAudit_API.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supplychain.ColdChainAudit_API.models.TemperatureTelemetry;

@Repository
public interface TelemetryRepository extends JpaRepository<TemperatureTelemetry, Long> {

    List<TemperatureTelemetry> findByRecordedAtBeforeAndIsArchivedFalse(LocalDateTime threshold);
}

/*

@Repository ->  This tells Spring Framework:
“This interface is a data access layer component.”
Spring will automatically detect it and create a working implementation at runtime.

public interface TelemetryRepository extends JpaRepository<TemperatureTelemetry, Long>
- This means Enitity: TemperatureTelemetry, and Primary Key type is long


Query Method:
List<TemperatureTelemetry> findByRecordedAtBeforeAndIsArchivedFalse(LocalDateTime threshold);

- Spring reads the method name and builds SQL automatically.

findBy → SELECT query
RecordedAtBefore → WHERE recorded_at < ?
    And
IsArchivedFalse → AND is_archived = false
 Query -> SELECT * FROM temperature_telemetry WHERE recorded_at < :threshold AND is_archived = false;
*/