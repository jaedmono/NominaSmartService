package com.smartservice.nomina.service;


import com.smartservice.nomina.exception.NominaException;
import com.smartservice.nomina.model.Nomina;

import java.util.List;

public interface NominaService {

    List<Nomina> getAllNominas();

    Nomina saveNomina(Nomina nomina);

    Nomina insertNomina(Nomina nomina) throws NominaException;

    void deleteNomina(long nominaid);

    Nomina findByIdNomina(long nominaid);

    List<Nomina> findByIdEmpresa(long nominaid);
}
