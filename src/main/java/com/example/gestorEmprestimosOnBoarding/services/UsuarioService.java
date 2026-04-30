package com.example.gestorEmprestimosOnBoarding.services;

import com.example.gestorEmprestimosOnBoarding.domain.Usuario;
import com.example.gestorEmprestimosOnBoarding.dto.UsuarioDtoInsert;
import com.example.gestorEmprestimosOnBoarding.dto.UsuarioUpdateDto;
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
        obj = repo.save(obj);
        return obj;
    }

    public Usuario find(Integer id){
        Optional<Usuario> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundException(
                "Id informado inválido ou inexistente!"));
    }

    public List<Usuario> findAll(){
        return repo.findAll();
    }

    public void delete(Integer id){
        find(id);
        repo.deleteById(id);
    }

    public Usuario update(Usuario obj){
        Usuario newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    /**
     * Função auxiliar para dar um update nos dados do Usuário
     * @param newObj novo objeto Cliente a ser Sobrescrito com os novos dados
     * @param obj Cliente com os novos dados passados pela requisição
     */
    public void updateData(Usuario newObj, Usuario obj){
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
        newObj.setDepartamento(obj.getDepartamento());
    }

    public Usuario fromDto(UsuarioUpdateDto objDto){
        return new Usuario(objDto.getId(),objDto.getNome(), objDto.getEmail(), objDto.getDepartamento());
    }

    public Usuario fromDto(UsuarioDtoInsert objDto){
        Usuario usuario = new Usuario(null,objDto.getNome(),objDto.getEmail(),objDto.getDepartamento());
        return usuario;
    }

}
