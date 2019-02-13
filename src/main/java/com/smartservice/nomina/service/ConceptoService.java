package com.smartservice.nomina.service;

import com.smartservice.nomina.model.Concepto;

import java.util.List;

public interface ConceptoService{

    List<Concepto> getAllConceptos();

    Concepto saveConcepto(Concepto concepto);

    void deleteConcepto(long conceptoId);

    Concepto getConceptoById(long idConcepto);

    List<Concepto> getUntouchablesConcepts(String clasificacion);

    List<Concepto> findByRelacionadoCon(String modoLiquidacion);

}
