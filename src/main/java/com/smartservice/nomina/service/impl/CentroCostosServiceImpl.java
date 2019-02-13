package com.smartservice.nomina.service.impl;

import com.smartservice.nomina.model.CentroCostos;
import com.smartservice.nomina.repository.CentroCostosRepository;
import com.smartservice.nomina.service.CentroCostosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CentroCostosServiceImpl implements CentroCostosService {

    @Autowired
    private CentroCostosRepository repository;

    @Override
    public List<CentroCostos> getAllCentrosCostos() {
        return this.repository.findAll();
    }

    @Override
    public List<CentroCostos> getCentroCostosByTipo(String tipo){
        return this.repository.findByTipo(tipo);
    }

    @Override
    public CentroCostos saveCentroCostos(CentroCostos centroCostos) {
        return this.repository.save(centroCostos);
    }

    @Override
    public void deleteCentroCostos(long idCentroCostos) {
        this.repository.delete(idCentroCostos);
    }
}
