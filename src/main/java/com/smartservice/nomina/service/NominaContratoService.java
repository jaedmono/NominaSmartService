package com.smartservice.nomina.service;


import com.smartservice.nomina.model.Contrato;
import com.smartservice.nomina.model.Nomina;
import com.smartservice.nomina.model.NominaContrato;
import com.smartservice.nomina.model.ResponseNominaContrato;

import java.util.Collection;
import java.util.List;

public interface NominaContratoService {

    List<NominaContrato> getAllNominaContrato();

    NominaContrato saveNominaContrato(NominaContrato nominaContrato);

    void deleteNominaContrato(long nominaContratoId);

    List<NominaContrato> createNominaContratos(Nomina nomina);

    List<ResponseNominaContrato> getNominaContratosByIdNomina(long idNomina);

    long findIbcLastMonth(long idContrato);

}
