package com.smartservice.nomina;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartServiceNominaApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmartServiceNominaApplication.class);

    public static void main(String[] args) {
        LOGGER.info("*** Starting the SmartServiceNominaApplication ***");
        SpringApplication.run(SmartServiceNominaApplication.class, args);
        LOGGER.info("*** SmartServiceNominaApplication started ***");
    }
}
