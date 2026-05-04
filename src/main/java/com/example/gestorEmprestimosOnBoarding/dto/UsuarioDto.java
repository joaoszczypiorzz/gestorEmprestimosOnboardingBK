package com.example.gestorEmprestimosOnBoarding.dto;

import com.example.gestorEmprestimosOnBoarding.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"id", "nome", "email", "departamento"})
public class UsuarioDto {
    

    private Integer id;

    @NotEmpty(message = "Preenchimento Obrigatório!")
    @Length(min = 4,max = 100, message = "O tamanho deve ser entre 5 e 80 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento Obrigatório!")
    @Email(message = "Email Inválido!")
    private String email;

    @NotEmpty(message = "Preenchimento Obrigatório!")
    @Length(min = 2, max = 100, message = "O tamanho Deve ser entre 5 e 100 Caracteres")
    private String departamento;

    public UsuarioDto(Usuario obj){
        id = obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();
        departamento = obj.getDepartamento();
    }
}
