package com.smartservice.nomina.service.impl;

import com.smartservice.nomina.model.*;
import com.smartservice.nomina.processor.NovedadProcessor;
import com.smartservice.nomina.repository.NominaContratoRepository;
import com.smartservice.nomina.repository.NominaNovedadRepository;
import com.smartservice.nomina.service.NominaNovedadService;
import com.smartservice.nomina.util.PeriodoPago;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NominaNovedadServiceImpl implements NominaNovedadService {


    private static final Logger LOGGER = LoggerFactory.getLogger(NominaNovedadServiceImpl.class);

    private static final String UNTOUCHABLES = "intocable";

    @Autowired
    private NominaNovedadRepository repository;

    @Autowired
    private ConceptoServiceImpl conceptoService;

    @Autowired
    private NovedadProcessor novedadProcessor;

    @Autowired
    private NominaContratoRepository nominaContratoRepository;

    @Override
    public List<NominaNovedad> getAllNominaNovedad() {
        return this.repository.findAll();
    }

    @Override
    public NominaNovedad saveNominaNovedad(NominaNovedad nominaNovedad) {
        setValueChange(nominaNovedad);
        return this.repository.save(nominaNovedad);
    }

    private void setValueChange(NominaNovedad nominaNovedad) {
        LOGGER.info("noimina");
        if(nominaNovedad.getValor() <= 0 && nominaNovedad.getIdNomina() > 0) {
            NominaContrato nominaContrato =
                    nominaContratoRepository.findByIdNominaAndIdContrato(nominaNovedad.getIdNomina(), nominaNovedad.getIdContrato());
            long value = novedadProcessor.processsConceptValue(nominaNovedad.getConcepto(), (int) nominaNovedad.getCantidad(), nominaContrato);
            if(value == 0) {
                nominaNovedad.setCantidad(0);
                nominaNovedad.setValor(nominaNovedad.getCantidad());
            }else {
                nominaNovedad.setValor(value);
            }
        }
    }

    @Override
    public void deleteNominaNovedad(long nominaNovedadId) {
        this.repository.delete(nominaNovedadId);
    }

    @Override
    public void loadNovedadesUntouchables(Nomina nomina, NominaContrato nominaContrato) {
        LOGGER.info("Se van a cargar las novedades para el contrato: "+ nominaContrato.getContrato().getIdContrato());
        List<Concepto> conceptos = conceptoService.getUntouchablesConcepts(UNTOUCHABLES);
        LOGGER.info("Total conceptos intocables: "+ conceptos.size());
        NominaNovedad nominaNovedad;
        for (Concepto concepto: conceptos) {
            nominaNovedad = getNominaNovedad(nomina.getIdEmpresa(), nominaContrato, concepto, nomina.getPeriodoNomina().getTipoNomina() );
            if(nominaNovedad != null) {
                this.repository.save(nominaNovedad);
            }
        }


    }

    @Override
    public void loadAvailableNovedades(NominaContrato nominaContrato) {
        List<NominaNovedad> nominaNovedades = repository.findByIdContratoAndIdNominaIsNull(nominaContrato.getContrato().getIdContrato());
        for (NominaNovedad nominaNovedad:nominaNovedades) {
            nominaContrato.setIdNomina(nominaContrato.getIdNomina());
            repository.save(nominaNovedad);
        }
    }

    @Override
    public List<NominaNovedad> getChangesPayrollByContractAndPayroll(long idContrato, long idNomina) {
       // List<NominaNovedad> nominaNovedades =
        return repository.findByIdContratoAndIdNomina(idContrato,idNomina);
        /*List<ResponseChangesPayroll> responseChangesPayrolls = new ArrayList<>();
        ResponseChangesPayroll responseChangesPayroll;
        for (NominaNovedad nominaNovedad : nominaNovedades ) {
            responseChangesPayroll = new ResponseChangesPayroll();
            responseChangesPayroll.setDescription(nominaNovedad.getConcepto().getDescripcion());
            responseChangesPayroll.setFactor(String.valueOf(nominaNovedad.getConcepto().getFactor()));
            responseChangesPayroll.setAmount(nominaNovedad.getCantidad());
            responseChangesPayroll.setValue(nominaNovedad.getValor());
            responseChangesPayroll.setTypeConceptEnum(ConceptEnum.valueOf(nominaNovedad.getConcepto().getClase()));
            responseChangesPayrolls.add(responseChangesPayroll);
        }
        return responseChangesPayrolls;*/
    }

    /*private ConceptEnum getTypeConcept(String clase) {
        for (ConceptEnum conceptEnum : ConceptEnum.values() ) {
            if(conceptEnum.toString().equals(clase)){
                return conceptEnum;
            }
        }
        return null;
    }*/

    @Override
    public long getTotalAccrued(Contrato contrato, long idNomina) {
        Long response = repository.sumValueByContratoAndIdNominaAndNaturaleza(contrato.getIdContrato(),idNomina,"devengo");
        return response == null?0:response;
    }

    @Override
    public long getTotalDeducted(Contrato contrato, long idNomina) {
        Long response =  repository.sumValueByContratoAndIdNominaAndNaturaleza(contrato.getIdContrato(),idNomina,"deduccion");
        return response == null?0:response;
    }

    @Override
    public long getTotalAccruedApplyEPS(long idContrato, long idNomina) {
        return repository.sumValueByIdContratoAndIdNominaAndApplyEPS(idContrato,idNomina,true);
    }

    @Override
    public long getTotalAccruedApplyRetire(long idContrato, long idNomina) {
        return repository.sumValueByIdContratoAndIdNominaAndApplyRetire(idContrato,idNomina, true);
    }

    @Override
    public void saveNominaNovedades(List<NominaNovedad> nominaNovedades) {
        repository.save(nominaNovedades);
    }

    @Override
    public List<Concepto> getConceptsPayroll(long idNomina) {
        return repository.getConceptsPayroll(idNomina);
    }

    @Override
    public List<NominaNovedad> getChangesPayrollByIdContract(long idContrato, long idNomina) {
        return repository.findByIdContratoAndIdNomina(idContrato, idNomina);
    }

    private NominaNovedad getNominaNovedad(long idEmpresa, NominaContrato nominaContrato, Concepto concepto, String periodoPago){
        int days = getDutyPeriod(periodoPago);
        long value = novedadProcessor.processsConceptValue(concepto, days, nominaContrato);
        NominaNovedad nominaNovedad = null;
        if(value > 0) {
            nominaNovedad = new NominaNovedad();
            nominaNovedad.setIdEmpresa(idEmpresa);
            nominaNovedad.setConcepto(concepto);
            nominaNovedad.setCantidad(days);
            nominaNovedad.setValor(value);
            nominaNovedad.setIdNomina(nominaContrato.getIdNomina());
            nominaNovedad.setIdContrato(nominaContrato.getContrato().getIdContrato());
        }
        return nominaNovedad;
    }

    public int getDutyPeriod(String periodoPago){
        if(PeriodoPago.MENSUAL.toString().equals(periodoPago)){
            return 30;
        }else {
            return 15;
        }
    }


}
