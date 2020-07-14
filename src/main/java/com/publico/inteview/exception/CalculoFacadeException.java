package com.publico.inteview.exception;

public class CalculoFacadeException extends Exception {

    public CalculoFacadeException(String message){
        super(message);
    }

    public CalculoFacadeException(String message, Throwable throwable){
        super(message,throwable);
    }
}
