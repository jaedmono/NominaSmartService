package com.smartservice.nomina.repository;


import com.smartservice.nomina.model.NovedadProgramada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface NovedadProgramadaReposotory extends JpaRepository<NovedadProgramada, Long>{

    List<NovedadProgramada> findByIdContratoAndEstadoNovedadAndFechaFinalizacionLessThan(long idContrato, String estado, Date fechaFinalizacion);

    Collection<NovedadProgramada> findByIdEmpresa(long idEmpresa);
}
