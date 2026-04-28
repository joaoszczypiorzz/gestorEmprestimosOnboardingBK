package com.example.gestorEmprestimosOnBoarding.services;

import com.example.gestorEmprestimosOnBoarding.domain.Usuario;
import com.example.gestorEmprestimosOnBoarding.dto.UsuarioDto;
import com.example.gestorEmprestimosOnBoarding.repositories.UsuarioRepository;
import com.example.gestorEmprestimosOnBoarding.services.exceptions.ObjNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repo;


    @Transactional
    public Usuario insert(Usuario obj){
        obj.setId(null);
        return repo.save(obj);
    }

    public Usuario find(Integer id){
        Optional<Usuario> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundException(
                "Id informado inválido ou inexistente!"));
    }

    public List<Usuario> findAll(){
        return repo.findAll();
    }
}
