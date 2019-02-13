package com.smartservice.nomina.batch;


import com.smartservice.nomina.model.ArchivoNovedad;
import com.smartservice.nomina.model.NovedadValidada;

import java.util.List;


public class ArchivoNovedadContent {

    private List<NovedadValidada> novedadValidadas;

    private ArchivoNovedad archivoNovedad;

    public List<NovedadValidada> getNovedadValidadas() {
        return novedadValidadas;
    }

    public void setNovedadValidadas(List<NovedadValidada> novedadValidadas) {
        this.novedadValidadas = novedadValidadas;
    }

    public ArchivoNovedad getArchivoNovedad() {
        return archivoNovedad;
    }

    public void setArchivoNovedad(ArchivoNovedad archivoNovedad) {
        this.archivoNovedad = archivoNovedad;
    }
}
