package com.example.gestorEmprestimosOnBoarding.services.exceptions;

public class IllegalEmprestimoState extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public IllegalEmprestimoState(String message) {
        super(message);
    }

    public IllegalEmprestimoState(String msg, Throwable cause){
        super(msg,cause);
    }
}
