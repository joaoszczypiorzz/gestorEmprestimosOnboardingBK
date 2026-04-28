package com.example.gestorEmprestimosOnBoarding.resources;

import com.example.gestorEmprestimosOnBoarding.domain.Usuario;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<Usuario> listar(){
        Usuario usuario1 = new Usuario(null,"João","joao@gmail.com","TI");
        Usuario usuario2 = new Usuario(null, "Rafael","rafael@gmail.com","Financeiro");

        List<Usuario> lista = new ArrayList<>();
        lista.add(usuario1);
        lista.add(usuario2);

        return lista;
    }

}
