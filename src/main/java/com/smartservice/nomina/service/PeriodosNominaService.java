package com.smartservice.nomina.service;


import com.smartservice.nomina.model.PeriodoNomina;

import java.util.List;

public interface PeriodosNominaService {

    List<PeriodoNomina> getPeriodosNominaByYearAndIdEmpresa(String tipoNomina, int year, long idEmpresa);

    PeriodoNomina savePeriodoNomina(PeriodoNomina periodoNomina);

    List<PeriodoNomina> generatePeriodosNomina(String tipoNomina, int year, long idEmpresa );

    List<PeriodoNomina> findPeriods(int year, String type, long company);

    List<PeriodoNomina> findPeriodsByIdEmpresa(long company);
}
