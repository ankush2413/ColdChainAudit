# ColdChainAudit-API

**ColdChain** is an enterprise-grade backend microservice designed for the pharmaceutical and logistics industries. It provides high-frequency ingestion of temperature telemetry data from IoT sensors, ensuring real-time monitoring while implementing an automated, cost-effective archival strategy using AWS S3.

## 🚀 Key Features
- **Real-time Telemetry Ingestion**: RESTful API endpoints built with Spring Boot to capture temperature data from warehouse sensors.
- **Cloud-Native Architecture**: Fully integrated with **AWS RDS (PostgreSQL)** for transactional data and **Amazon S3** for long-term storage.
- **Automated Data Archiving**: A scheduled Spring cron job that aggregates daily telemetry, converts it to CSV, and migrates it to S3.
- **Secure File Retrieval**: Implements **S3 Pre-signed URLs**, allowing secure, temporary access to audit logs directly from the cloud.
- **Performance Optimized**: Built using the **AWS SDK for Java (v2)** for non-blocking I/O operations.

## 🏗️ System Architecture & Flow

1.  **Ingestion Phase**: Sensors send POST requests with JSON payloads to the Spring Boot API. Data is persisted in an AWS RDS PostgreSQL instance.
2.  **Archival Phase**: Every 24 hours (at midnight), a `@Scheduled` task queries the database for the previous day’s records.
3.  **Cloud Storage**: The task generates a CSV report and uploads it to an S3 bucket using the AWS SDK.
4.  **Audit Phase**: Users request audit logs via a secure endpoint. The system generates a temporary Pre-signed URL valid for 5 minutes, ensuring the bucket remains private.

## 🛠️ Tech Stack
- **Language**: Java 17+
- **Framework**: Spring Boot 3.x
- **Build Tool**: Maven
- **Cloud Services**: AWS RDS (PostgreSQL), Amazon S3, AWS IAM
- **Database**: PostgreSQL
- **Key Libraries**: Spring Data JPA, AWS SDK for Java (v2), Lombok

## 📋 Prerequisites
- JDK 17 or higher
- Maven 3.x
- An AWS Account with an S3 Bucket and RDS Instance
- AWS Access Key & Secret Key (configured as environment variables)

<!-- ## 🔧 Installation & Setup

1. **Clone the repository**
   ```bash
   git clone [https://github.com/your-username/thermovault-api.git](https://github.com/your-username/thermovault-api.git)
   cd thermovault-api -->

## 📍 API Endpoints

Method,Endpoint,Description
POST,/api/v1/telemetry/ingest,Ingest sensor data into RDS
GET,/api/v1/audit/logs/{date},Generate S3 Pre-signed URL for a specific date
GET,/api/v1/telemetry/latest,Fetch most recent data for dashboard