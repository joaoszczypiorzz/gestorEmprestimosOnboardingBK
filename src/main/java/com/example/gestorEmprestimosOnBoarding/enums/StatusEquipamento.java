package com.example.gestorEmprestimosOnBoarding.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEquipamento {

    DISPONIVEL(1,"DISPONÍVEL"),
    EM_USO(2,"EM_USO"),
    MANUTENCAO(3,"MANUTENÇÃO");


    private int codigo;
    private String descricao;

    public static StatusEquipamento toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for(StatusEquipamento x : StatusEquipamento.values()){
            if(cod.equals(x.getCodigo())){
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido " + cod);
    }

}
