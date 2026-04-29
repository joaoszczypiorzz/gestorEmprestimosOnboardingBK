package com.example.gestorEmprestimosOnBoarding.resources;

import com.example.gestorEmprestimosOnBoarding.domain.Equipamento;
import com.example.gestorEmprestimosOnBoarding.dto.EquipamentoDto;
import com.example.gestorEmprestimosOnBoarding.services.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/equipamentos")
public class EquipamentoResource {

    @Autowired
    private EquipamentoService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody EquipamentoDto objDto){
        Equipamento obj = service.fromDto(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


}
