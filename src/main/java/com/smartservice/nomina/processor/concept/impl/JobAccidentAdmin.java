package com.smartservice.nomina.processor.concept.impl;


import com.smartservice.nomina.processor.concept.BasicConcept;

public class JobAccidentAdmin implements BasicConcept {

    private double ibcLastMonth;
    private int days;

    public JobAccidentAdmin(long ibcLastMonth, int days){
        this.days = days;
        this.ibcLastMonth = (double)ibcLastMonth;
    }

    @Override
    public long process() {
        return (long)((ibcLastMonth/30D)*days);
    }
}
