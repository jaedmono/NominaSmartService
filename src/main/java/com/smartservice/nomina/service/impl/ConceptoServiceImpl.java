package com.smartservice.nomina.service.impl;


import com.smartservice.nomina.model.Concepto;
import com.smartservice.nomina.repository.ConceptoRepository;
import com.smartservice.nomina.service.ConceptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConceptoServiceImpl implements ConceptoService{

    @Autowired
    private ConceptoRepository repository;


    @Override
    public List<Concepto> getAllConceptos() {
        return this.repository.findAll();
    }

    @Override
    public Concepto saveConcepto(Concepto concepto) {
        return this.repository.save(concepto);
    }

    @Override
    public void deleteConcepto(long conceptoId) {
        this.repository.delete(conceptoId);
    }

    @Override
    public Concepto getConceptoById(long idConcepto){
        return repository.findByIdConcepto(idConcepto);
    }

    @Override
    public List<Concepto> getUntouchablesConcepts(String clasificacion) {
        return repository.findByClasificacion(clasificacion);
    }

    @Override
    public List<Concepto> findByRelacionadoCon(String relacionadoCon) {
        return repository.findByRelacionadoCon(relacionadoCon);
    }
}
