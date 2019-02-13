package com.smartservice.nomina.service;


import com.smartservice.nomina.model.NominaContrato;
import com.smartservice.nomina.model.NovedadProgramada;
import com.smartservice.nomina.model.NovedadValidada;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface NovedadProgramadaService {

    List<NovedadProgramada> getAllNovedadesProgramadas();

    NovedadProgramada saveNovedadProgramada(NovedadProgramada novedadProgramada);

    void deleteNovedadProgramada(long novedadProgramadaId);

    void addNovedadesProgramadas(NominaContrato nomina, Date fechaPago);

    Collection<NovedadProgramada> getNovedadProgramadaByIdEmpresa(long idEmpresa);
}
