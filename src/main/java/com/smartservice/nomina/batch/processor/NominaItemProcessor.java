package com.smartservice.nomina.batch.processor;


import com.smartservice.nomina.model.Nomina;
import com.smartservice.nomina.model.NominaContrato;
import com.smartservice.nomina.service.impl.NominaContratoServiceImpl;
import com.smartservice.nomina.service.impl.NominaNovedadServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NominaItemProcessor implements ItemProcessor<Nomina,Nomina> {

    private static final Logger LOGGER = LoggerFactory.getLogger(NominaItemProcessor.class);

    @Autowired
    private NominaContratoServiceImpl nominaContratoService;

    @Autowired
    private NominaNovedadServiceImpl nominaNovedadService;

    @Override
    public Nomina process(Nomina nomina) throws Exception {
        LOGGER.info("Se va a procesar la "+ nomina);
        List<NominaContrato> nominaContratos =  nominaContratoService.createNominaContratos(nomina);
        for (NominaContrato nominaContrato: nominaContratos) {
            nominaNovedadService.loadAvailableNovedades(nominaContrato);
            nominaNovedadService.loadNovedadesUntouchables(nomina, nominaContrato);
        }

        return nomina;
    }
}
