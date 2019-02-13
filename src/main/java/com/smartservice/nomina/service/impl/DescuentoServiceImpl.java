package com.smartservice.nomina.service.impl;

import com.smartservice.nomina.model.*;
import com.smartservice.nomina.repository.DescuentoRepository;
import com.smartservice.nomina.service.DescuentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DescuentoServiceImpl implements DescuentoService {

    @Autowired
    private DescuentoRepository repository;

    @Autowired
    private ConceptoServiceImpl conceptoService;

    @Autowired
    private NominaNovedadServiceImpl nominaNovedadService;


    @Override
    public List<Descuento> getAllDescuentos() {
        return this.repository.findAll();
    }

    @Override
    public List<Descuento> getDescuentosByCompany(long idCompany, String relacionadoCon) {
        return this.repository.findByIdEmpresaAndRelacionadoCon(idCompany, relacionadoCon);
    }

    @Override
    public Descuento saveDescuento(Descuento descuento) {
        return this.repository.save(descuento);
    }

    @Override
    public void deleteDescuento(long descuentoId) {
        this.repository.delete(descuentoId);
    }

    @Override
    public void addDescuentos(NominaContrato nominaContrato, Date fechapago) {
        List<Descuento> descuentos =
            this.repository.findByIdContratoAndEstadoAndFechaFinalizacionLessThan(nominaContrato.getContrato().getIdContrato(),"A", fechapago);
        NominaNovedad nominaNovedad = new NominaNovedad();
        Concepto concepto ;
        for (Descuento descuento:descuentos) {
            concepto = conceptoService.getConceptoById(descuento.getConcepto().getIdConcepto());
            nominaNovedad.setConcepto(concepto);
            nominaNovedad.setIdNomina(nominaContrato.getIdNominaContrato());
            nominaNovedad.setCantidad(1);
            nominaNovedad.setValor(descuento.getTotalDescuento()/descuento.getNumeroCuotas());
            this.nominaNovedadService.saveNominaNovedad(nominaNovedad);
        }
    }
}
