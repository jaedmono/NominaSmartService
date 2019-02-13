package com.smartservice.nomina.processor.concept;


import com.smartservice.nomina.model.Concepto;
import com.smartservice.nomina.model.NominaContrato;
import com.smartservice.nomina.processor.concept.impl.*;
import com.smartservice.nomina.repository.NominaContratoRepository;
import com.smartservice.nomina.repository.NominaNovedadRepository;
import com.smartservice.nomina.util.ConceptEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConceptFactory {

    @Autowired
    private NominaNovedadRepository nominaNovedadRepository;

    @Autowired
    private NominaContratoRepository nominaContratoRepository;


    public BasicConcept getExecutorConcept(Concepto concepto, int time, NominaContrato nominaContrato){
        BasicConcept basicConcept = null;
        long ibcLastMonth = 0;
        long totalSalary= 0;
        ConceptEnum conceptEnum = ConceptEnum.valueOf(concepto.getClase());
        switch (conceptEnum){
            case HEALTH:
                totalSalary =
                        nominaNovedadRepository.sumValueByIdContratoAndIdNominaAndApplyEPS(nominaContrato.getContrato().getIdContrato(),nominaContrato.getIdNomina(),true);
                basicConcept = new HealthyConcept(concepto, totalSalary);
                break;
            case RETIRE:
                totalSalary =
                    nominaNovedadRepository.sumValueByIdContratoAndIdNominaAndApplyRetire(nominaContrato.getContrato().getIdContrato(),nominaContrato.getIdNomina(),true);
                basicConcept = new RetirementConcept(concepto, totalSalary);
                break;
            case TRANSPORTATION_ALLOWENCE:
                basicConcept = new TransportationAllowance(  time , nominaContrato.getContrato().getSueldo());
                break;
            case SALARY:
                basicConcept = new SalaryConcept(nominaContrato.getContrato().getSueldo(), time);
                break;
            case DISCOUNT_TRANSPORTATION_ALLOWENCE:
                basicConcept = new DiscountTransportationAllowance( time);
                break;
            case DAYS_BASIC_CONCEPT:
                basicConcept = new DaysBasicConcept(nominaContrato.getContrato().getSueldo(), time);
                break;
            case EXTRA_HOUR:
                basicConcept = new ExtraHour(concepto, nominaContrato, time);
                break;
            case INABILITY_COMPANY:
                ibcLastMonth = getIbcLastMonth(nominaContrato);
                basicConcept = new InabilityCompany(ibcLastMonth,time);
                break;
            case INABILITY_EPS:
                basicConcept = new InabilityEPS(nominaContrato.getContrato().getSueldo(),time);
                break;
            case SOLIDARITY_FOUND:
                totalSalary =
                        nominaNovedadRepository.sumValueByContratoAndIdNominaAndNaturaleza(nominaContrato.getContrato().getIdContrato(),nominaContrato.getIdNomina(),"devengo");
                basicConcept = new SolidarityFound(totalSalary,time);
                break;
            case INABILITY_ACCIDENT_JOB_COMPANY:
                basicConcept = new JobAccidentAdmin(nominaContrato.getContrato().getSueldo(),time);
                break;
            case INABILITY_ACCIDENT_JOB_ADMIN:
                ibcLastMonth = getIbcLastMonth(nominaContrato);
                basicConcept = new JobAccidentCompany(ibcLastMonth,time);
                break;
            case LICENSE:
                ibcLastMonth = getIbcLastMonth(nominaContrato);
                basicConcept = new License(ibcLastMonth,time);
                break;
            case VACATIONS:
                long avgSalary = getIbcLastMonth(nominaContrato);;
                basicConcept = new Vacations(avgSalary,time);
                break;
        }
        return basicConcept;
    }

    private long getIbcLastMonth(NominaContrato nominaContrato) {
        long salary =  nominaContratoRepository.findByIdContratoLastAccrued(nominaContrato.getContrato().getIdContrato());
        salary = salary == 0?nominaContrato.getContrato().getSueldo(): salary;
        return salary;
    }

}
