package com.smartservice.nomina.service;


import com.smartservice.nomina.model.*;

import java.util.List;

public interface NominaNovedadService {

    List<NominaNovedad> getAllNominaNovedad();

    NominaNovedad saveNominaNovedad(NominaNovedad nominaNovedad);

    void deleteNominaNovedad(long nominaNovedadId);

    void loadNovedadesUntouchables(Nomina nomina, NominaContrato nominaContrato);

    void loadAvailableNovedades(NominaContrato nominaContrato);

    List<NominaNovedad> getChangesPayrollByContractAndPayroll(long idContrato, long idNomina);

    long getTotalAccrued(Contrato contrato, long idNomina);

    long getTotalDeducted(Contrato contrato, long idNomina);

    long getTotalAccruedApplyEPS(long idContrato, long idNomina);

    long getTotalAccruedApplyRetire(long idContrato, long idNomina);

    void saveNominaNovedades(List<NominaNovedad> nominaNovedades);

    List<Concepto> getConceptsPayroll(long idNomina);

    List<NominaNovedad> getChangesPayrollByIdContract(long idContrato, long idNomina);
}
