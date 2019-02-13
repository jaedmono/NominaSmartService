package com.smartservice.nomina.processor.concept.impl;


import com.smartservice.nomina.processor.concept.BasicConcept;

public class License implements BasicConcept {

    private long ibcLastMonth;
    private int daysWorked;

    public License(long ibcLastMonth, int daysWorked){
        this.ibcLastMonth = ibcLastMonth;
        this.daysWorked = daysWorked;
    }

    @Override
    public long process() {
        return (ibcLastMonth/30)*daysWorked;
    }
}
