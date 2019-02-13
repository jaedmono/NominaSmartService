package com.smartservice.nomina.processor.concept.impl;


import com.smartservice.nomina.processor.concept.BasicConcept;
import com.smartservice.nomina.util.UserPreference;
import com.smartservice.nomina.util.UserPreferences;
import org.springframework.beans.factory.annotation.Autowired;

public class TransportationAllowance implements BasicConcept {

    private long transportAllowence;
    private int daysWorked;
    private int days = 30;
    private long salary;

    @Autowired
    private UserPreferences userPreferences;

    public TransportationAllowance(int daysWorked, long salary){
        this.daysWorked = daysWorked;
        this.salary = salary;
        this.transportAllowence = getTransportAllowence();
    }


    @Override
    public long process() {
        long minimumSalary = getMinimumSalary();
        if(minimumSalary*2 <= salary){
            return (transportAllowence /days)*daysWorked;
        }
        return 0;
    }

    private long getMinimumSalary() {
        return Long.valueOf(userPreferences.getUserPreference(UserPreference.MINIMUM_SALARY));
    }

    private long getTransportAllowence(){
        return Long.valueOf(userPreferences.getUserPreference(UserPreference.ALLOWENCE_TRANSPORTATION));
    }
}
