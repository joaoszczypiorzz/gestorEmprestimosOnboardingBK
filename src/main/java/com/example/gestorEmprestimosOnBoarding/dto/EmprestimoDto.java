package com.example.gestorEmprestimosOnBoarding.dto;

import com.example.gestorEmprestimosOnBoarding.domain.Emprestimo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class EmprestimoDto {
    

    @NotNull(message = "Campo Obrigatório")
    private Integer userId;

    @NotNull(message = "Campo Obrigatório")
    private Integer equipamentoId;

    public EmprestimoDto(Emprestimo obj){
        userId = obj.getUser().getId();
        equipamentoId = obj.getEquipamento().getId();
    }


}
