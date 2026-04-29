package com.example.gestorEmprestimosOnBoarding.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEmprestimo {

    ATIVO(1, "ATIVO"),
    INATIVO(2, "INATIVO");

    private int codigo;
    private String descricao;

    public static StatusEmprestimo toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for(StatusEmprestimo x : StatusEmprestimo.values()){
            if(cod.equals(x.getCodigo())){
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido " + cod);
    }

}
