package com.example.gestorEmprestimosOnBoarding.services.exceptions;

public class ObjNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ObjNotFoundException(String message) {
        super(message);
    }

    public ObjNotFoundException(String msg, Throwable cause){
        super(msg,cause);
    }
}
