package com.example.gestorEmprestimosOnBoarding.services.validation;

import com.example.gestorEmprestimosOnBoarding.domain.Usuario;
import com.example.gestorEmprestimosOnBoarding.dto.UsuarioDtoInsert;
import com.example.gestorEmprestimosOnBoarding.repositories.UsuarioRepository;
import com.example.gestorEmprestimosOnBoarding.resources.exceptions.FieldMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, UsuarioDtoInsert> {

    @Autowired
    private UsuarioRepository repo;
    @Autowired
    private HttpServletRequest request;

    @Override
    public void initialize(UsuarioInsert ann) {
    }

    @Override
    public boolean isValid(UsuarioDtoInsert usuarioDto, ConstraintValidatorContext context) {

        List<FieldMessage> listErrors = new ArrayList<>();

        Usuario aux = repo.findByEmail(usuarioDto.getEmail());
        if(aux != null){
            listErrors.add(new FieldMessage("email","Email já está em uso"));
        }

        for(FieldMessage e: listErrors){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return listErrors.isEmpty();
    }
}
