package com.smartservice.nomina.repository;

import com.smartservice.nomina.model.Concepto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConceptoRepository extends JpaRepository<Concepto, Long> {
    Concepto findByIdConcepto(long idConcepto);

    List<Concepto> findByClasificacion(String clasificacion);

    List<Concepto> findByRelacionadoCon(String relacionadoCon);
}
