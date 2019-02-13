package com.smartservice.nomina.service;


import com.smartservice.nomina.model.CentroCostos;

import java.util.List;

public interface CentroCostosService {

    List<CentroCostos> getAllCentrosCostos();

    List<CentroCostos> getCentroCostosByTipo(String tipo);

    CentroCostos saveCentroCostos(CentroCostos centroCostos);

    void deleteCentroCostos(long idCentroCostos);
}
