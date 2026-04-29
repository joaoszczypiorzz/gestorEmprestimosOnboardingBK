package com.example.gestorEmprestimosOnBoarding.dto;

import com.example.gestorEmprestimosOnBoarding.domain.Usuario;
import com.example.gestorEmprestimosOnBoarding.services.validation.UsuarioUpdator;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Getter
@Setter
@UsuarioUpdator
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsuarioUpdateDto implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer id;
    @Length(min = 4,max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
    private String nome;
    @Email(message = "Email Inválido")
    private String email;

    private String departamento;

    public UsuarioUpdateDto(Usuario obj){
        id = obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();
        departamento = obj.getDepartamento();
    }

}
