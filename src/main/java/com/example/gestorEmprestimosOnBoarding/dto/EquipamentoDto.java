package com.example.gestorEmprestimosOnBoarding.dto;

import com.example.gestorEmprestimosOnBoarding.domain.Equipamento;
import com.example.gestorEmprestimosOnBoarding.enums.StatusEquipamento;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonPropertyOrder({"id", "patrimonio", "nome", "tipo", "categoria", "status"})
public class EquipamentoDto {
    


    private Integer id;

    @NotEmpty(message = "Preenchimento Obrigatório!")
    @Length(min = 4, max = 50, message = "O patrimonio deve ter entre 4 a 50 caracteces")
    private String patrimonio;

    @NotEmpty(message = "Preenchimento Obrigatório!")
    @Length(min = 4, max = 100, message = "O nome deve ter entre 4 e 100 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento Obrigatório!")
    @Length(min = 4, max = 50, message = "O Tipo deve ter entre 4 e 50 caracteres")
    private String tipo;

    @NotEmpty(message = "Preenchimento Obrigatório!")
    @Length(min = 4, max = 50)
    private String categoria;

    private StatusEquipamento status;

    public EquipamentoDto(Equipamento obj){
        id = obj.getId();
        patrimonio = obj.getPatrimonio();
        nome = obj.getNome();
        tipo = obj.getTipo();
        categoria = obj.getCategoria();
        status = obj.getStatus();
    }

}
