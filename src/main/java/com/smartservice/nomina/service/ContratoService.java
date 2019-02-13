package com.smartservice.nomina.service;

import com.smartservice.nomina.model.Contrato;
import com.smartservice.nomina.model.Empleado;

import java.util.List;

public interface ContratoService {

    List<Contrato> getAllContratos();

    Contrato saveContrato(Contrato contrato);

    void deleteContrato(long contratoId);

    Contrato getContratoByTrabajadorAndEstado(Empleado empleado, String estado);
}
