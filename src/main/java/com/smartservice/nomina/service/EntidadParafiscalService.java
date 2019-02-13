package com.smartservice.nomina.service;

import com.smartservice.nomina.model.EntidadParafiscal;

import java.util.List;

public interface EntidadParafiscalService {

    List<EntidadParafiscal> getAllEntidadesParafiscal();

    List<EntidadParafiscal> getEntidadParafiscal(String tipoEntidad);

    EntidadParafiscal saveEntidadParafiscal(EntidadParafiscal entidadParafiscal);

    void deleteEntidadParafiscal(long entidadParafiscalId);
}
