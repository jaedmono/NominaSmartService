package com.smartservice.nomina.service.impl;


import com.smartservice.nomina.model.PeriodoNomina;
import com.smartservice.nomina.repository.PeriodosNominaRepository;
import com.smartservice.nomina.rest.PeriodosNominaRestController;
import com.smartservice.nomina.service.PeriodosNominaService;
import com.smartservice.nomina.util.DateUtils;
import com.smartservice.nomina.util.PeriodoPago;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PeriodosNominaServiceImpl implements PeriodosNominaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeriodosNominaRestController.class);

    private PeriodosNominaRepository periodosNominaRepository;

    @Autowired
    public PeriodosNominaServiceImpl(final PeriodosNominaRepository periodosNominaRepository){
        this.periodosNominaRepository = periodosNominaRepository;
    }

    @Override
    public List<PeriodoNomina> getPeriodosNominaByYearAndIdEmpresa(String tipoNomina, int year, long idEmpresa) {
        return periodosNominaRepository.findByTipoNominaAndYearAndIdEmpresa(tipoNomina, year,idEmpresa);
    }

    @Override
    public PeriodoNomina savePeriodoNomina(PeriodoNomina periodoNomina) {
        return periodosNominaRepository.save(periodoNomina);
    }

    @Override
    public List<PeriodoNomina> generatePeriodosNomina(String tipoNomina, int year, long idEmpresa) {
        List<PeriodoNomina> periods =
                periodosNominaRepository.findByTipoNominaAndYearAndIdEmpresa(tipoNomina,year,idEmpresa);
        if(periods == null || periods.isEmpty()){
            if(PeriodoPago.MENSUAL.toString().equals(tipoNomina)){
                periods = generateMonthPeriods(year, idEmpresa);
            }else{
                periods = generateHalfPeriods(year, idEmpresa);
            }
            periodosNominaRepository.save(periods);
        }
        return periods;
    }

    @Override
    public List<PeriodoNomina> findPeriods(int year, String type, long idEmpresa) {

        LocalDate dateIni = getInitDate(type);
        LocalDate dateFinal = getFinalDate(type);
        String periodTime = getPeriodTime(dateIni,dateFinal);
        LOGGER.info("periodTime: "+periodTime);
        return periodosNominaRepository.findByidEmpresaAndPeriodo(idEmpresa, periodTime);
    }

    @Override
    public List<PeriodoNomina> findPeriodsByIdEmpresa(long company) {
        return periodosNominaRepository.findByIdEmpresa(company);
    }

    private LocalDate getInitDate(String type) {
        LocalDate now = LocalDate.now();
        int day = now.getDayOfMonth();
        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();

        if(day < 16 || PeriodoPago.MENSUAL.toString().equals(type)){
            now = LocalDate.of(year,month,1);
        }else{
            now = LocalDate.of(year,month,16);
        }
        return now;
    }

    private LocalDate getFinalDate(String type) {
        int day = LocalDate.now().getDayOfMonth();
        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();
        LocalDate dateFinal ;
        if(PeriodoPago.QUINCENAL.toString().equals(type) && day < 16){
            dateFinal = LocalDate.of(year,month,15);
        }else{
            dateFinal = LocalDate.of(year,month,LocalDate.now().lengthOfMonth());
        }
        return dateFinal;
    }

    private List<PeriodoNomina> generateHalfPeriods(int year, long idEmpresa) {
        List<PeriodoNomina> periods = new ArrayList<>();
        PeriodoNomina periodoNomina;
        LocalDate dateIni;
        LocalDate dateFinal;
        for(int month=1;month<=12;month++){
            dateIni = LocalDate.of(year,month,1);
            dateFinal = LocalDate.of(year,month,15);
            periodoNomina = createPeriodoNomina(idEmpresa,dateIni,dateFinal,PeriodoPago.QUINCENAL,year);
            periods.add(periodoNomina);

            dateIni = LocalDate.of(year,month,16);
            dateFinal = LocalDate.of(year,month,dateIni.lengthOfMonth());
            periodoNomina = createPeriodoNomina(idEmpresa,dateIni,dateFinal,PeriodoPago.QUINCENAL,year);
            periods.add(periodoNomina);

        }
        return periods;
    }

    private List<PeriodoNomina> generateMonthPeriods(int year, long idEmpresa) {
        List<PeriodoNomina> periods = new ArrayList<>();
        PeriodoNomina periodoNomina;
        for(int month=1;month<=12;month++){
            LocalDate dateIni = LocalDate.of(year,month,1);
            LocalDate dateFinal = LocalDate.of(year,month,dateIni.lengthOfMonth());
            periodoNomina = createPeriodoNomina(idEmpresa, dateIni, dateFinal, PeriodoPago.MENSUAL, year);
            periods.add(periodoNomina);

        }
        return periods;
    }

    private PeriodoNomina createPeriodoNomina(long idEmpresa, LocalDate dateIni, LocalDate dateFinal, PeriodoPago periodoPago, int year){
        PeriodoNomina periodoNomina = new PeriodoNomina();
        periodoNomina.setFechaPago(DateUtils.asDate(dateFinal));
        periodoNomina.setIdEmpresa(idEmpresa);
        periodoNomina.setPeriodo(getPeriodTime(dateIni,dateFinal));
        periodoNomina.setTipoNomina(periodoPago.toString());
        periodoNomina.setYear(year);
        return periodoNomina;
    }

    private String getPeriodTime(LocalDate dateIni, LocalDate dateFinal) {
        return dateIni.format(DateTimeFormatter.ISO_LOCAL_DATE)+" Al "+dateFinal.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
