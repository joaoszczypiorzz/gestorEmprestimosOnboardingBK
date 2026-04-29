package com.example.gestorEmprestimosOnBoarding.services;

import com.example.gestorEmprestimosOnBoarding.domain.Equipamento;
import com.example.gestorEmprestimosOnBoarding.dto.EquipamentoDto;
import com.example.gestorEmprestimosOnBoarding.repositories.EquipamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository repo;

    @Transactional
    public Equipamento insert(Equipamento obj){
        obj.setId(null);
        obj = repo.save(obj);
        return obj;
    }

    public Equipamento fromDto(EquipamentoDto objDto){
        return new Equipamento(null,objDto.getPatrimonio(),objDto.getNome(),objDto.getTipo(),objDto.getCategoria(),objDto.getStatus());
    }
}
