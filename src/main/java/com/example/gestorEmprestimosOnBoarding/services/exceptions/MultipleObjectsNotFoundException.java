package com.example.gestorEmprestimosOnBoarding.services.exceptions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MultipleObjectsNotFoundException extends RuntimeException {
    private static final long versionUID = 1L;

    private List<String> errors = new ArrayList<>();

    public MultipleObjectsNotFoundException(List<String> StringErros){
        super("Múltiplos erros de Validação encontrados!");
        this.errors = StringErros;
    }

    public MultipleObjectsNotFoundException(String message) {
        super(message);
    }

}
