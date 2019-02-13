package com.smartservice.nomina.service.impl;

import com.smartservice.nomina.exception.NominaException;
import com.smartservice.nomina.model.Nomina;
import com.smartservice.nomina.model.PeriodoNomina;
import com.smartservice.nomina.repository.ContratoRepository;
import com.smartservice.nomina.repository.NominaRepository;
import com.smartservice.nomina.service.NominaService;
import com.smartservice.nomina.util.EstadoContrato;
import com.smartservice.nomina.util.EstadoNomina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NominaServiceImpl implements NominaService {

    @Autowired
    private NominaRepository repository;

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private NominaContratoServiceImpl nominaContratoService;

    @Override
    public List<Nomina> getAllNominas() {
        return this.repository.findAll();
    }

    @Override
    public Nomina saveNomina(Nomina nomina) {
        return this.repository.save(nomina);
    }

    @Override
    public Nomina insertNomina(Nomina nomina)  {
        try {
            this.validateNominaPeriod(nomina.getPeriodoNomina());
            this.validateContracts(nomina.getPeriodoNomina().getTipoNomina());
        } catch (NominaException e) {
            e.printStackTrace();
        }
        return this.persistNomina(nomina);

    }

    private void validateNominaPeriod(PeriodoNomina periodoPago) throws NominaException {
        long nominasProceso = repository.countByPeriodoNominaAndEstado(periodoPago, EstadoNomina.EN_PROCESO.toString());
        if(nominasProceso >= 0){
            throw new NominaException("Ya existe una nomina en proceso para el periodo de tiempo enviado.");
        }
    }

    private void validateContracts(String periodoPago) throws NominaException {
        long contratosActivos = contratoRepository.countByPeriodoPagoAndEstado(periodoPago, EstadoContrato.ACTIVO.toString());
        if(contratosActivos <= 0){
            throw new NominaException("No hay contratos activos para lanzar la nomina.");
        }
    }

    private Nomina persistNomina(Nomina nomina) {
        nomina.setEstado(EstadoNomina.PENDIENTE.toString());
        return this.repository.save(nomina);
    }

    @Override
    public void deleteNomina(long nominaid) {
        this.repository.delete(nominaid);
    }

    @Override
    public Nomina findByIdNomina(long nominaId){
        return this.repository.findOne(nominaId);
    }

    @Override
    public List<Nomina> findByIdEmpresa(long idEmpresa) {
        return this.repository.findByIdEmpresaOrderByPeriodoNomina(idEmpresa);
    }
}
