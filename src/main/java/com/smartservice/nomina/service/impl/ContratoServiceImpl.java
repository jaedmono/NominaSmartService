package com.smartservice.nomina.service.impl;


import com.smartservice.nomina.model.Contrato;
import com.smartservice.nomina.model.Empleado;
import com.smartservice.nomina.repository.ContratoRepository;
import com.smartservice.nomina.service.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratoServiceImpl implements ContratoService{

    @Autowired
    private ContratoRepository repository;

    @Override
    public List<Contrato> getAllContratos() {
        return this.repository.findAll();
    }

    @Override
    public Contrato saveContrato(Contrato contrato) {
        return this.repository.save(contrato);
    }

    @Override
    public void deleteContrato(long contratoId) {
        this.repository.delete(contratoId);
    }

    public List<Contrato> getContratosByIdEmpresaAndEstatusAndPeriodoPago(long idEmpresa, String estado, String periodoPago){
        return this.repository.findByIdEmpresaAndEstadoAndPeriodoPago(idEmpresa, estado, periodoPago);
    }

    public List<Contrato> getContratosByIdEmpresaAndEstatus(long idEmpresa, String estado){
        return this.repository.findByIdEmpresaAndEstado(idEmpresa, estado);
    }

    @Override
    public Contrato getContratoByTrabajadorAndEstado(Empleado empleado, String estado) {
        return this.repository.findByEmpleadoAndEstado(empleado, estado);
    }


}
