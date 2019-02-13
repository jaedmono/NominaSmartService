package com.smartservice.nomina.repository;


import com.smartservice.nomina.model.PeriodoNomina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeriodosNominaRepository extends JpaRepository<PeriodoNomina, Long> {
    List<PeriodoNomina> findByYearAndIdEmpresa(int year, long idEmpresa);

    List<PeriodoNomina> findByTipoNominaAndYearAndIdEmpresa(String tipoNomina, int year, long idEmpresa);

    List<PeriodoNomina> findByidEmpresaAndPeriodo(long idEmpresa, String periodTime);

    List<PeriodoNomina> findByIdEmpresa(long company);
}
