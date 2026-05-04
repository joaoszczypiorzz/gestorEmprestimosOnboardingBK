package com.example.gestorEmprestimosOnBoarding.services.exceptions;

public class IllegalEmprestimoState extends RuntimeException {
    

    public IllegalEmprestimoState(String message) {
        super(message);
    }

    public IllegalEmprestimoState(String msg, Throwable cause){
        super(msg,cause);
    }
}
