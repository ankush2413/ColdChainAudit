package com.supplychain.ColdChainAudit_API.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.supplychain.ColdChainAudit_API.models.TemperatureTelemetry;
import com.supplychain.ColdChainAudit_API.repository.TelemetryRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional // // Ensures either BOTH S3 and DB update happen, or NEITHER happens
@Slf4j
public class ArchiveScheduler {
    
    private final TelemetryRepository repository;
    private final S3Service s3Service;

// This runs every 1 minute for testing (cron: sec min hour day month weekday)
    // In production, you'd use "0 0 0 * * *" for midnight
    @Scheduled(cron = "0 */2 * * * *")
    public void archiveDataToS3()
    {
        log.info("Starting scheduled archival process...");

        List<TemperatureTelemetry> dataToArchive = repository.findByIsArchivedFalse();

        if (dataToArchive.isEmpty()) {
            log.info("No new data to archive.");
            return;
        }

        // Convert List to CSV format
        String csvContent = "ID,SensorID,Temperature,Timestamp\n" +
            dataToArchive.stream()
                .map(t -> String.format("%d,%s,%f,%s", 
                    t.getId(), t.getSensorId(), t.getTemperature(), t.getRecordedAt()))
                .collect(Collectors.joining("\n"));

        log.info("Generated CSV Content:\n{}", csvContent);

        String fileName = "telemetry_archive_" + System.currentTimeMillis() + ".csv";

        s3Service.uploadCsv(fileName, csvContent);
        
        // Mark as archived so we don't pick them up again next minute
        dataToArchive.forEach(t -> t.setIsArchived(true));
        repository.saveAll(dataToArchive);

        log.info("Archived {} records successfully.", dataToArchive.size());
    }
}
