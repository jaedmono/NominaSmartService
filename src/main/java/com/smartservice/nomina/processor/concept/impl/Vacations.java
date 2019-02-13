package com.smartservice.nomina.processor.concept.impl;


import com.smartservice.nomina.processor.concept.BasicConcept;

public class Vacations implements BasicConcept {

    private long averageSalary;
    private int days;

    public Vacations(long averageSalary, int days){
        this.days = days;
        this.averageSalary = averageSalary;
    }

    @Override
    public long process() {
        return (averageSalary/30)*days;
    }
}
