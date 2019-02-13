package com.smartservice.nomina.service;


import com.smartservice.nomina.model.NovedadValidada;

import java.util.List;

public interface NovedadValidadaService {

    List<NovedadValidada> getAllNovedadesValidadas();

    List<NovedadValidada> getAllNovedadesValidadasById(long id, String type);

    NovedadValidada saveNovedadValidada(NovedadValidada novedadValidada);

    List<NovedadValidada> saveNovedadValidada(List<NovedadValidada> novedadesValidadas);

    void deleteNovedadValidada(long idNovedadValidada);

    NovedadValidada saveNovedadValidada(long id, String type, long idEmpresa);
}
