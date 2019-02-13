package com.smartservice.nomina.batch.writer;


import com.smartservice.nomina.model.Nomina;
import com.smartservice.nomina.service.impl.NominaServiceImpl;
import com.smartservice.nomina.util.EstadoNomina;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NominaItemWriter implements ItemWriter<Nomina> {

    private static final Logger LOGGER = LoggerFactory.getLogger(NominaItemWriter.class);

    @Autowired
    private NominaServiceImpl nominaService;

    @Override
    public void write(List<? extends Nomina> nominas) throws Exception {
        LOGGER.info(String.format("Se van a actualizar %s nominas ",nominas.size()) );
        for (Nomina nomina: nominas ) {
            nomina.setEstado(EstadoNomina.PROCESADA.toString());
            this.nominaService.saveNomina(nomina);
        }
    }
}
