package com.example.gestorEmprestimosOnBoarding.services;


import com.example.gestorEmprestimosOnBoarding.domain.Usuario;
import com.example.gestorEmprestimosOnBoarding.dto.UsuarioDto;
import com.example.gestorEmprestimosOnBoarding.repositories.UsuarioRepository;
import com.example.gestorEmprestimosOnBoarding.services.exceptions.DataIntegrityException;
import com.example.gestorEmprestimosOnBoarding.services.exceptions.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repo;

    public Usuario find(Integer id){
        Optional<Usuario> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundException(
                "Id informado inválido ou inexistente!"));
    }

    public void delete(Integer id){
        find(id);
        repo.deleteById(id);
    }

    public List<Usuario> findAll(){
        return repo.findAll();
    }

    public Usuario insert(Usuario obj){
        if(repo.existsByEmail(obj.getEmail())){
            throw new DataIntegrityException("Erro: E-mail já cadastrado no sistema!");
        }

        obj.setId(null);
        obj = repo.save(obj);
        return obj;
    }

    public Usuario update(Usuario obj){
        Usuario newObj = find(obj.getId());

        boolean conflict = repo.existsByEmailAndIdNot(obj.getEmail(),obj.getId());
        if(conflict){
            throw new DataIntegrityException("Erro: E-mail Informado já cadastrado no sistema!");
        }

        updateData(newObj,obj);
        return repo.save(newObj);
    }

    public void updateData(Usuario newObj, Usuario oldObj){
        newObj.setNome(oldObj.getNome());
        newObj.setEmail(oldObj.getEmail());
        newObj.setDepartamento(oldObj.getDepartamento());
    }

    public Usuario fromDto(UsuarioDto objDto){
        Usuario entidade = new Usuario();

        entidade.setNome(objDto.getNome());
        entidade.setEmail(objDto.getEmail());
        entidade.setDepartamento(objDto.getDepartamento());

        return entidade;
    }

}
