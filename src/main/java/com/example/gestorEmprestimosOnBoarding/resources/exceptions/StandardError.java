package com.example.gestorEmprestimosOnBoarding.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StandardError {
    

    private Integer status;
    private String mensagem;
    private long timeStamp;

}
