package com.supplychain.ColdChainAudit_API.service;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

@Service
@Slf4j
public class S3Service {
    // private final S3Template s3Template;
    @Autowired
    private S3Client s3Client;

    @Autowired
    private S3Presigner s3Presigner;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucketName;

    public void uploadCsv(String fileName, String csvContent) {
         log.info("Uploading {} to S3 bucket: {}", fileName, bucketName);
         try {
        //     // Converts the String to an InputStream which S3 requires
                //     s3Template.upload(bucketName, fileName, inputStream);
        //     log.info("Successfully uploaded {} to S3", fileName);
        // } catch (Exception e) {
        //     log.error("Failed to upload to S3: {}", e.getMessage());
        //     throw new RuntimeException("S3 Upload Failed", e);
              ByteArrayInputStream inputStream = new ByteArrayInputStream(csvContent.getBytes(StandardCharsets.UTF_8));
              PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                                                    .bucket(bucketName)
                                                    .key(fileName)
                                                    .contentType("text/csv")
                                                    .build();
                s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, csvContent.getBytes(StandardCharsets.UTF_8).length));
         } catch(Exception e){
            log.error("Failed to upload to S3: {}", e.getMessage());
            throw new RuntimeException("S3 Upload Failed", e);
         }
       
    }

    public String getPresignedURL(String fileName)
    {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
            .bucket(bucketName)
            .key(fileName)
            .build();

            GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
            .signatureDuration(Duration.ofMinutes(10)) // URL valid for 10 minutes
            .getObjectRequest(getObjectRequest)
            .build();

        return s3Presigner.presignGetObject(presignRequest).url().toString();
    } 
}
