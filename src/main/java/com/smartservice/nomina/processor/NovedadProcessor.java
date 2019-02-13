package com.smartservice.nomina.processor;

import com.smartservice.nomina.model.Concepto;
import com.smartservice.nomina.model.NominaContrato;
import com.smartservice.nomina.model.NominaNovedad;
import com.smartservice.nomina.model.NovedadValidada;
import com.smartservice.nomina.processor.concept.BasicConcept;
import com.smartservice.nomina.processor.concept.ConceptFactory;
import com.smartservice.nomina.repository.ContratoRepository;
import com.smartservice.nomina.repository.NominaContratoRepository;
import com.smartservice.nomina.repository.NominaNovedadRepository;
import com.smartservice.nomina.service.impl.NominaNovedadServiceImpl;
import com.smartservice.nomina.util.PeriodoPago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NovedadProcessor {

    private NominaNovedadRepository nominaNovedadRepository;

    private NominaContratoRepository nominaContratoRepository;

    private ConceptFactory conceptFactory;

    @Autowired
    public NovedadProcessor( final NominaNovedadRepository nominaNovedadRepository,
                             final NominaContratoRepository nominaContratoRepository,
                             final ConceptFactory conceptFactory){
        this.nominaNovedadRepository = nominaNovedadRepository;
        this.nominaContratoRepository = nominaContratoRepository;
        this.conceptFactory = conceptFactory;
    }

    public void loadNovedades(List<NovedadValidada> novedadesValidadas, long idEmpresa) {
        NominaNovedad nominaNovedad;
        List<NominaNovedad> nominaNovedades = new ArrayList<>();
        for (NovedadValidada novedadValidada: novedadesValidadas ) {
            nominaNovedad = new NominaNovedad();
            nominaNovedad.setConcepto(novedadValidada.getConcepto());
            nominaNovedad.setValor(novedadValidada.getValor());
            nominaNovedad.setCantidad(novedadValidada.getCantidad());
            nominaNovedad.setIdContrato(novedadValidada.getIdContrato());
            nominaNovedad.setIdEmpresa(idEmpresa);
            nominaNovedad.setIdNomina(getIdNominaActive(novedadValidada, idEmpresa));
            nominaNovedades.add(nominaNovedad);
        }
        this.nominaNovedadRepository.save(nominaNovedades);
    }

    private long getIdNominaActive(NovedadValidada novedadValidada, long idEmpresa) {
        return nominaContratoRepository.findActivePayrollIdByIdContrato(novedadValidada.getIdContrato());
    }


    public long processsConceptValue(Concepto concepto, int time, NominaContrato nominaContrato){
        BasicConcept basicConcept =  this.conceptFactory.getExecutorConcept(concepto, time, nominaContrato);
        if(basicConcept != null) {
            return basicConcept.process();
        }else{
            return 0;
        }

    }
}
