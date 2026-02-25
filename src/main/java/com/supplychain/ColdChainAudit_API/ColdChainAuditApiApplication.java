package com.supplychain.ColdChainAudit_API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ColdChainAuditApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ColdChainAuditApiApplication.class, args);
	}

}
