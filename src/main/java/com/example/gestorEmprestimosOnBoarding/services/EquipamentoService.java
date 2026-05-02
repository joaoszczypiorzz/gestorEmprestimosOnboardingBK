package com.example.gestorEmprestimosOnBoarding.services;

import com.example.gestorEmprestimosOnBoarding.domain.Equipamento;
import com.example.gestorEmprestimosOnBoarding.dto.EquipamentoDto;
import com.example.gestorEmprestimosOnBoarding.repositories.EquipamentoRepository;
import com.example.gestorEmprestimosOnBoarding.services.exceptions.DataIntegrityException;
import com.example.gestorEmprestimosOnBoarding.services.exceptions.ObjNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository repo;


    public void delete(Integer id){
        find(id);
        repo.deleteById(id);
    }

    public Equipamento find(Integer id){
        Optional<Equipamento> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundException(
                "Id informado inválido ou inexistente!"));
    }

    public List<Equipamento> findAll(){
        return repo.findAll();
    }

    @Transactional
    public Equipamento insert(Equipamento obj){
        if(repo.existsByPatrimonio(obj.getPatrimonio())){
            throw new DataIntegrityException("Patrimonio já cadastrado!");
        }

        obj.setId(null);
        obj = repo.save(obj);
        return obj;
    }

    public Equipamento update(Equipamento obj){
        Equipamento newObj = find(obj.getId());

        boolean conflict = repo.existsByPatrimonioAndIdNot(obj.getPatrimonio(),obj.getId());
        if(conflict){
            throw new DataIntegrityException("Erro: patrimônio já pertence a outro equipamento cadastrado!");
        }

        updateData(newObj,obj);
        return repo.save(newObj);
    }

    public void updateData(Equipamento newObj, Equipamento objOld){
        newObj.setPatrimonio(objOld.getPatrimonio());
        newObj.setNome(objOld.getNome());
        newObj.setTipo(objOld.getTipo());
        newObj.setCategoria(objOld.getCategoria());
        newObj.setStatus(objOld.getStatus());
    }

    public Equipamento fromDto(EquipamentoDto objDto){
        Equipamento entidade = new Equipamento();

        entidade.setPatrimonio(objDto.getPatrimonio());
        entidade.setNome(objDto.getNome());
        entidade.setTipo(objDto.getTipo());
        entidade.setCategoria(objDto.getCategoria());
        entidade.setStatus(objDto.getStatus());

        return entidade;
    }
}
