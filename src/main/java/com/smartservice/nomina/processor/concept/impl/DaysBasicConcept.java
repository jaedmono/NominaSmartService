package com.smartservice.nomina.processor.concept.impl;

import com.smartservice.nomina.processor.concept.BasicConcept;

public class DaysBasicConcept implements BasicConcept {

    private long salary;
    private int daysWorked;

    public DaysBasicConcept(long salary, int daysWorked){
        this.salary = salary;
        this.daysWorked = daysWorked;
    }

    @Override
    public long process() {
        return (salary/30)*daysWorked;
    }
}
