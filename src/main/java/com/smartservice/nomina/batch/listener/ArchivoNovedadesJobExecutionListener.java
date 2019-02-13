package com.smartservice.nomina.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class ArchivoNovedadesJobExecutionListener implements JobExecutionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArchivoNovedadesJobExecutionListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        LOGGER.info(String.format("*** Job with id: %s started at: %s ***", jobExecution.getJobId(), jobExecution.getStartTime()));
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        LOGGER.info(String.format("*** Job with id: %s finished at: %s ***", jobExecution.getJobId(), jobExecution.getEndTime()));

    }
}
