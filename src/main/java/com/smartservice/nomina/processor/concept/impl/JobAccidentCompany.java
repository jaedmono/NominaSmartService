package com.smartservice.nomina.processor.concept.impl;




import com.smartservice.nomina.processor.concept.BasicConcept;

public class JobAccidentCompany implements BasicConcept {

    private long salary;
    private int days;

    public JobAccidentCompany(long salary, int days){
        this.salary = salary;
        this.days = days;
    }

    @Override
    public long process() {
        return (salary/30)*1;
    }
}
