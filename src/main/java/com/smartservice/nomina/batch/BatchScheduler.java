package com.smartservice.nomina.batch;


import com.smartservice.nomina.batch.config.BatchConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class BatchScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private BatchConfiguration batchConfiguration;

    private Long jobIdentifier ;

    private final String JOB_ID_ARCHIVO = "jobId";

    private final String JOB_ID_NOMINA = "jobId";



    //@Scheduled(fixedRateString = "${job.archivo.polling-frequency}")
    public void runArchivoNovedadesJob() throws Exception{
        jobIdentifier = Calendar.getInstance().getTimeInMillis();
        Job job = batchConfiguration.createArchivoNovedadesJob();
        JobParameters jobParameters = new JobParametersBuilder().addString(JOB_ID_ARCHIVO, jobIdentifier.toString())
                .toJobParameters();
        jobLauncher.run(job, jobParameters);
    }

    //@Scheduled(fixedRateString = "${job.nomina.polling-frequency}")
    public void runNominasJob() throws Exception{
        jobIdentifier = Calendar.getInstance().getTimeInMillis();
        Job job = batchConfiguration.createNominaJob();
        JobParameters jobParameters = new JobParametersBuilder().addString(JOB_ID_NOMINA, jobIdentifier.toString())
                .toJobParameters();
        jobLauncher.run(job, jobParameters);
    }


}
