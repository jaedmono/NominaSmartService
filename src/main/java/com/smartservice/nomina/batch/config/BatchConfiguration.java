package com.smartservice.nomina.batch.config;

import com.smartservice.nomina.batch.ArchivoNovedadContent;
import com.smartservice.nomina.batch.listener.ArchivoNovedadesJobExecutionListener;
import com.smartservice.nomina.batch.listener.NominaJobExecutionListener;
import com.smartservice.nomina.batch.processor.ArchivoNovedadesItemProcessor;
import com.smartservice.nomina.batch.processor.NominaItemProcessor;
import com.smartservice.nomina.batch.reader.ArchivoNovedadesItemReader;
import com.smartservice.nomina.batch.reader.NominaItemReader;
import com.smartservice.nomina.batch.writer.ArchivoNovedadesItemWriter;
import com.smartservice.nomina.batch.writer.NominaItemWriter;
import com.smartservice.nomina.model.ArchivoNovedad;
import com.smartservice.nomina.model.Nomina;
import com.smartservice.nomina.repository.ArchivoNovedadRepository;
import com.smartservice.nomina.repository.NominaRepository;
import com.smartservice.nomina.util.EstadoArchivosNovedad;
import com.smartservice.nomina.util.EstadoNomina;
import com.smartservice.nomina.util.NominaServiceConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@ComponentScan(basePackages = "com.smartservice.nomina")


@EnableTransactionManagement
@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration
public class BatchConfiguration extends SimpleBatchConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchConfiguration.class);

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ArchivoNovedadRepository archivoNovedadRepository;

    @Autowired
    private NominaRepository nominaRepository;

    @Autowired
    private ArchivoNovedadesItemProcessor archivoNovedadesItemProcessor;

    @Autowired
    private ArchivoNovedadesItemWriter archivoNovedadesItemWriter;

    @Autowired
    private NominaItemProcessor nominaItemProcessor;

    @Autowired
    private NominaItemWriter nominaItemWriter;

    private Step createArchivoNovedadesStep() {
        return stepBuilderFactory.get(NominaServiceConstants.STEP_NAME_ARCHIVO)
                .<ArchivoNovedad, ArchivoNovedadContent> chunk(5)
                .reader(getReaderArchivoNovedades())
                .processor(getProcessorArchivoNovedades())
                .writer(getWriterArchivoNovedades())
                .build();
    }

    public Job createArchivoNovedadesJob() throws Exception {
        return jobBuilderFactory.get(NominaServiceConstants.JOB_NAME_ARCHIVO)
                .incrementer(new RunIdIncrementer())
                .listener(getJobListenerArchivoNovedades())
                .start(createArchivoNovedadesStep())
                .build();
    }

    public ArchivoNovedadesItemReader getReaderArchivoNovedades() {
        LOGGER.debug("Inicio consulta del Reader");
        ArchivoNovedadesItemReader  repositoryItemReader= new ArchivoNovedadesItemReader();
        repositoryItemReader.setRepository(archivoNovedadRepository);

        repositoryItemReader.setMethodName(NominaServiceConstants.METHOD_NAME);
        repositoryItemReader.setArguments(getQueryArgumentsList());

        Map<String, Sort.Direction> sortMap = new HashMap<>();
        sortMap.put(NominaServiceConstants.SORTING_COLUMN ,Sort.Direction.ASC);
        repositoryItemReader.setSort(sortMap);
        return repositoryItemReader;
    }

    private List getQueryArgumentsList(){
        List<Object> queryArgumentsList = new ArrayList<>();
        queryArgumentsList.add(EstadoArchivosNovedad.CARGADO.toString());
        return queryArgumentsList;
    }


    private Step createNominaStep() {
        return stepBuilderFactory.get(NominaServiceConstants.STEP_NAME_NOMINA)
                .<Nomina, Nomina> chunk(5)
                .reader(getNominaReader())
                .processor(getNominaProcessor())
                .writer(getNominaWriter())
                .build();
    }

    public Job createNominaJob() throws Exception {
        return jobBuilderFactory.get(NominaServiceConstants.JOB_NAME_NOMINA)
                .incrementer(new RunIdIncrementer())
                .listener(getNominaJobListener())
                .start(createNominaStep())
                .build();
    }

    public NominaItemReader getNominaReader() {
        LOGGER.debug("Inicio consulta del Reader Job Nomina");
        NominaItemReader repositoryItemReader= new NominaItemReader();
        repositoryItemReader.setRepository(nominaRepository);

        repositoryItemReader.setMethodName(NominaServiceConstants.METHOD_NAME_NOMINA);
        repositoryItemReader.setArguments(getNominaQueryArgumentsList());

        return repositoryItemReader;
    }

    private List getNominaQueryArgumentsList(){
        List<Object> queryArgumentsList = new ArrayList<>();
        queryArgumentsList.add(EstadoNomina.PENDIENTE.toString());
        return queryArgumentsList;
    }

    public ArchivoNovedadesItemProcessor getProcessorArchivoNovedades() {
        return this.archivoNovedadesItemProcessor;
    }

    public ArchivoNovedadesItemWriter getWriterArchivoNovedades() {
        return this.archivoNovedadesItemWriter;
    }

    public JobExecutionListener getJobListenerArchivoNovedades() {
        return new ArchivoNovedadesJobExecutionListener();
    }

    public NominaItemProcessor getNominaProcessor() {
        return this.nominaItemProcessor;
    }

    public NominaItemWriter getNominaWriter() {
        return this.nominaItemWriter;
    }

        public JobExecutionListener getNominaJobListener() {
            return new NominaJobExecutionListener();
        }



}
