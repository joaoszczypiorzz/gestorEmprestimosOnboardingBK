package com.example.gestorEmprestimosOnBoarding.services.exceptions;

public class ObjNotFoundException extends RuntimeException {
    

    public ObjNotFoundException(String message) {
        super(message);
    }

    public ObjNotFoundException(String msg, Throwable cause){
        super(msg,cause);
    }
}
