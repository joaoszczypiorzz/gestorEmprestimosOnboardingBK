package com.example.gestorEmprestimosOnBoarding.resources;

import com.example.gestorEmprestimosOnBoarding.domain.Usuario;
import com.example.gestorEmprestimosOnBoarding.dto.UsuarioDto;
import com.example.gestorEmprestimosOnBoarding.resources.doc.UsuarioResourceDoc;
import com.example.gestorEmprestimosOnBoarding.services.UsuarioService;
import com.example.gestorEmprestimosOnBoarding.services.exceptions.DataIntegrityException;
import com.example.gestorEmprestimosOnBoarding.services.exceptions.ObjNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource implements UsuarioResourceDoc {

    @Autowired
    private UsuarioService service;


    @GetMapping(value = "{id}")
    public ResponseEntity<UsuarioDto> find(@PathVariable Integer id) throws ObjNotFoundException{
        Usuario obj = service.find(id);
        UsuarioDto objDto = new UsuarioDto(obj);
        return ResponseEntity.ok().body(objDto);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws ObjNotFoundException {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> findAll(){
        List<Usuario> list = service.findAll();

        List<UsuarioDto> listDto = list.stream()
                .map(obj -> new UsuarioDto(obj))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody UsuarioDto objDto) throws DataIntegrityException {
        Usuario obj = service.fromDto(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody UsuarioDto objDto, @PathVariable Integer id) throws DataIntegrityException, ObjNotFoundException {
        Usuario obj = service.fromDto(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }


}
