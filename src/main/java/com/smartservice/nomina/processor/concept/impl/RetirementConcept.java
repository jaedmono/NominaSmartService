package com.smartservice.nomina.processor.concept.impl;


import com.smartservice.nomina.model.Concepto;
import com.smartservice.nomina.model.NominaContrato;
import com.smartservice.nomina.model.NominaNovedad;
import com.smartservice.nomina.processor.concept.BasicConcept;
import com.smartservice.nomina.repository.NominaNovedadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

public class RetirementConcept implements BasicConcept {

    private long totalSalary;

    private Concepto concepto;

    public RetirementConcept(Concepto concepto, long totalSalary){
        this.concepto = concepto;
        this.totalSalary = totalSalary;
    }

    @Override
    public long process() {
        return (long) (totalSalary * concepto.getFactor());
    }


}
