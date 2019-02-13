package com.smartservice.nomina.service;


import com.smartservice.nomina.model.ArchivoNovedad;

import java.util.List;

public interface ArchivoNovedadesService {

    List<ArchivoNovedad> getAllArchivoNovedad();

    ArchivoNovedad saveArchivoNovedad(ArchivoNovedad archivoNovedad);

    void deleteArchivoNovedad(long archivoNovedadId);

    void saveArchivoNovedad(int empresaId, String fileName);

    ArchivoNovedad getArchivoNovedadById(long id);
}
