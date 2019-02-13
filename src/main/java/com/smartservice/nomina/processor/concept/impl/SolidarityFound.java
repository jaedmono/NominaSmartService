package com.smartservice.nomina.processor.concept.impl;


import com.smartservice.nomina.model.NominaContrato;
import com.smartservice.nomina.processor.concept.BasicConcept;

public class SolidarityFound implements BasicConcept {

    private long totalSalaryConcepts;
    private double factor;

    public SolidarityFound(long totalSalaryConcepts, double factor){
        this.factor = factor;
        this.totalSalaryConcepts = totalSalaryConcepts;
    }

    @Override
    public long process() {
        return (long)(totalSalaryConcepts*factor);
    }
}
