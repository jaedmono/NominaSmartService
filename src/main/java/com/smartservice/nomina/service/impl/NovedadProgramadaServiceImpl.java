package com.smartservice.nomina.service.impl;

import com.smartservice.nomina.model.*;
import com.smartservice.nomina.repository.NovedadProgramadaReposotory;
import com.smartservice.nomina.service.NovedadProgramadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class NovedadProgramadaServiceImpl implements NovedadProgramadaService {

    @Autowired
    private NovedadProgramadaReposotory novedadProgramadaReposotory;

    @Autowired
    private NominaNovedadServiceImpl nominaNovedadService;

    @Autowired
    private ConceptoServiceImpl conceptoService;


    @Override
    public List<NovedadProgramada> getAllNovedadesProgramadas() {
        return this.novedadProgramadaReposotory.findAll();
    }

    @Override
    public NovedadProgramada saveNovedadProgramada(NovedadProgramada novedadProgramada) {
        return this.novedadProgramadaReposotory.save(novedadProgramada);
    }

    @Override
    public void deleteNovedadProgramada(long novedadProgramadaId) {
        this.novedadProgramadaReposotory.delete(novedadProgramadaId);
    }

    @Override
    public void addNovedadesProgramadas(NominaContrato nominaContrato, Date fechaPago) {
        List<NovedadProgramada> novedadesProgramadas =
                this.novedadProgramadaReposotory.findByIdContratoAndEstadoNovedadAndFechaFinalizacionLessThan(nominaContrato.getContrato().getIdContrato(),"A", fechaPago);
        NominaNovedad nominaNovedad = new NominaNovedad();
        Concepto concepto;
        for (NovedadProgramada novedadProgramada:novedadesProgramadas) {
            concepto = conceptoService.getConceptoById(novedadProgramada.getConcepto().getIdConcepto());
            nominaNovedad.setConcepto(concepto);
            nominaNovedad.setIdNomina(nominaContrato.getIdNominaContrato());
            nominaNovedad.setCantidad(novedadProgramada.getCantidad());
            nominaNovedad.setValor(novedadProgramada.getValor());
            this.nominaNovedadService.saveNominaNovedad(nominaNovedad);
        }

    }

    public Collection<NovedadProgramada> getNovedadProgramadaByIdEmpresa(long idEmpresa) {
        return this.novedadProgramadaReposotory.findByIdEmpresa(idEmpresa);
    }
}
