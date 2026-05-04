package com.example.gestorEmprestimosOnBoarding.resources;

import com.example.gestorEmprestimosOnBoarding.domain.Emprestimo;
import com.example.gestorEmprestimosOnBoarding.dto.EmprestimoDto;
import com.example.gestorEmprestimosOnBoarding.resources.doc.EmprestimoResourceDoc;
import com.example.gestorEmprestimosOnBoarding.services.EmprestimoService;
import com.example.gestorEmprestimosOnBoarding.services.exceptions.DataIntegrityException;
import com.example.gestorEmprestimosOnBoarding.services.exceptions.MultipleObjectsNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping(value = "/emprestimos")
public class EmprestimoResource implements EmprestimoResourceDoc {

    @Autowired
    private EmprestimoService service;

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody EmprestimoDto objDto) throws IllegalStateException, MultipleObjectsNotFoundException {
        Emprestimo obj = service.fromDto(objDto);
        obj = service.insertEmprestimo(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> findAll(){
        List<Emprestimo> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Void> devolucao(@PathVariable Integer id) throws DataIntegrityException {
        service.devolucao(id);
        return ResponseEntity.noContent().build();
    }

}
