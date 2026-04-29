package com.example.gestorEmprestimosOnBoarding.services.validation;

import com.example.gestorEmprestimosOnBoarding.domain.Usuario;
import com.example.gestorEmprestimosOnBoarding.dto.UsuarioDtoInsert;
import com.example.gestorEmprestimosOnBoarding.dto.UsuarioUpdateDto;
import com.example.gestorEmprestimosOnBoarding.repositories.UsuarioRepository;
import com.example.gestorEmprestimosOnBoarding.resources.exceptions.FieldMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsuarioUpdatorValidator implements ConstraintValidator<UsuarioUpdator, UsuarioUpdateDto> {

    @Autowired
    private UsuarioRepository repo;
    @Autowired
    private HttpServletRequest request;

    @Override
    public void initialize(UsuarioUpdator ann) {
    }

    @Override
    public boolean isValid(UsuarioUpdateDto usuarioDto, ConstraintValidatorContext context) {
        //Map para conseguir pegar o Id da requisição:
        Map<String, String> map = (Map<String,String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> listErrors = new ArrayList<>();

        Usuario aux = repo.findByEmail(usuarioDto.getEmail());
        if(aux != null && !aux.getId().equals(uriId)){
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
