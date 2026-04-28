package com.example.gestorEmprestimosOnBoarding.dto;

import com.example.gestorEmprestimosOnBoarding.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonPropertyOrder({"nome", "email", "departamento"})
public class UsuarioDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Preenchimento Obrigatório!")
    @Length(min = 4,max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento Obrigatório!")
    private String email;

    @NotEmpty(message = "Preenchimento Obrigatório!")
    private String departamento;

    public UsuarioDto(Usuario obj){
        nome = obj.getNome();
        email = obj.getEmail();
        departamento = obj.getDepartamento();
    }
}
