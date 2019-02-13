package com.smartservice.nomina.batch.writer;



import com.smartservice.nomina.batch.ArchivoNovedadContent;
import com.smartservice.nomina.service.impl.ArchivoNovedadServiceImpl;
import com.smartservice.nomina.service.impl.NovedadValidadaServiceImpl;
import com.smartservice.nomina.util.EstadoArchivosNovedad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArchivoNovedadesItemWriter implements ItemWriter<ArchivoNovedadContent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArchivoNovedadesItemWriter.class);

    @Autowired
    private NovedadValidadaServiceImpl novedadValidadaService;

    @Autowired
    private ArchivoNovedadServiceImpl archivoNovedadesService;

    @Override
    public void write( List<? extends ArchivoNovedadContent> items) throws Exception {
        LOGGER.info(String.format("Se van a registrar %s items ",items.size()) );
        for (ArchivoNovedadContent novedadContent: items ) {
            novedadValidadaService.saveNovedadValidada(novedadContent.getNovedadValidadas());
            novedadContent.getArchivoNovedad().setEstado(EstadoArchivosNovedad.VALIDADO.toString());
            archivoNovedadesService.saveArchivoNovedad(novedadContent.getArchivoNovedad());
        }


    }
}
