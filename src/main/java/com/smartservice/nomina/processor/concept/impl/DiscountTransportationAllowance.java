package com.smartservice.nomina.processor.concept.impl;


import com.smartservice.nomina.processor.concept.BasicConcept;
import com.smartservice.nomina.util.UserPreference;
import com.smartservice.nomina.util.UserPreferences;
import org.springframework.beans.factory.annotation.Autowired;

public class DiscountTransportationAllowance implements BasicConcept{

    private long transportAllowence;
    private int daysWorked;
    private int days = 30;

    @Autowired
    private UserPreferences userPreferences;

    public DiscountTransportationAllowance( int daysWorked){
        this.daysWorked = daysWorked;
        this.transportAllowence = getTransportAllowence();
    }

    @Override
    public long process() {
        return (transportAllowence/30)*daysWorked;
    }

    private long getTransportAllowence(){
        return Long.valueOf(userPreferences.getUserPreference(UserPreference.ALLOWENCE_TRANSPORTATION));
    }
}
