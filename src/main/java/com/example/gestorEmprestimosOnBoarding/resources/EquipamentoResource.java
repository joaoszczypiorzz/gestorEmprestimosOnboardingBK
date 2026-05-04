package com.example.gestorEmprestimosOnBoarding.resources;

import com.example.gestorEmprestimosOnBoarding.domain.Equipamento;
import com.example.gestorEmprestimosOnBoarding.dto.EquipamentoDto;
import com.example.gestorEmprestimosOnBoarding.resources.doc.EquipamentoResourceDoc;
import com.example.gestorEmprestimosOnBoarding.services.EquipamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/equipamentos")
public class EquipamentoResource implements EquipamentoResourceDoc {

    @Autowired
    private EquipamentoService service;


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<EquipamentoDto> find(@PathVariable Integer id){
        Equipamento obj = service.find(id);
        EquipamentoDto objDto = new EquipamentoDto(obj);
        return ResponseEntity.ok().body(objDto);
    }

    @GetMapping
    public ResponseEntity<List<EquipamentoDto>> findAll(){
        List<Equipamento> list = service.findAll();

        List<EquipamentoDto> listDto = list.stream()
                .map(obj -> new EquipamentoDto(obj))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody EquipamentoDto objDto){
        Equipamento obj = service.fromDto(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody EquipamentoDto objDto, @PathVariable Integer id){
        Equipamento obj = service.fromDto(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

}
