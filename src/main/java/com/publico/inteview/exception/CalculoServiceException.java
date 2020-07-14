package com.publico.inteview.exception;

public class CalculoServiceException extends Exception {

    public CalculoServiceException(String message){
        super(message);
    }

    public CalculoServiceException(String message, Throwable throwable){
        super(message,throwable);
    }
}
