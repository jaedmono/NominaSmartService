package com.smartservice.nomina.processor.concept.impl;


import com.smartservice.nomina.processor.concept.BasicConcept;

public class InabilityCompany implements BasicConcept {

    private long ibcLastMonth;
    private int daysWorked;

    public InabilityCompany(long ibcLastMonth, int daysWorked ){
        this.ibcLastMonth = ibcLastMonth;
        this.daysWorked = daysWorked;
    }

    @Override
    public long process() {
        return ((ibcLastMonth/30)*2/3)*daysWorked;
    }
}
