package com.smartservice.nomina.exception;

public class NominaException extends Exception{

    public NominaException(String message, Exception e){
        super(message,e);
    }

    public NominaException(String message){
        super(message);
    }
}
