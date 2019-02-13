package com.smartservice.nomina.service.impl;

import com.smartservice.nomina.model.EntidadParafiscal;
import com.smartservice.nomina.repository.EntidadParafiscalRepository;
import com.smartservice.nomina.service.EntidadParafiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntidadParafiscalServiceImpl implements EntidadParafiscalService {

    @Autowired
    private EntidadParafiscalRepository repository;

    @Override
    public List<EntidadParafiscal> getAllEntidadesParafiscal() {
        return this.repository.findAll();
    }

    @Override
    public List<EntidadParafiscal> getEntidadParafiscal(String tipoEntidad) {
        return this.repository.findByTipoEntidad(tipoEntidad);
    }

    @Override
    public EntidadParafiscal saveEntidadParafiscal(EntidadParafiscal entidadParafiscal) {
        return this.repository.save(entidadParafiscal);
    }

    @Override
    public void deleteEntidadParafiscal(long entidadParafiscalId) {
        this.repository.delete(entidadParafiscalId);
    }
}
